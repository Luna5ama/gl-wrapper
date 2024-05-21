package dev.luna5ama.glwrapper

import com.squareup.kotlinpoet.*
import dev.luna5ama.glwrapper.api.*
import dev.luna5ama.ktgen.KtgenProcessor
import org.objectweb.asm.ClassReader
import org.objectweb.asm.tree.ClassNode
import java.lang.reflect.Modifier
import java.nio.file.Path
import kotlin.streams.asSequence

class CodeGen : KtgenProcessor {
    private val glCoreRegex = "GL(\\d{2})".toRegex()

    @OptIn(DelicateKotlinPoetApi::class)
    override fun process(inputs: List<Path>, outputDir: Path) {
        val glBaseClass = GLBase::class.java

        val glBaseImplClassName = ClassName("dev.luna5ama.glwrapper.api", "GLBase", "Impl")
        val kmogusPtrClassName = ClassName("dev.luna5ama.kmogus", "Ptr")

        val ptrReturnClass = PtrReturn::class
        val ptrParameterClass = PtrParameter::class
        val nullableReturnClass = NullableReturn::class
        val coreOverloadClass = CoreOverload::class

        val ptrReturnAnnotationSpec = AnnotationSpec.builder(ptrReturnClass).build()
        val nullableReturnAnnotationSpec = AnnotationSpec.builder(nullableReturnClass).build()


        val types = ClassUtils.findClasses("dev.luna5ama.glwrapper.api").parallelStream().asSequence()
            .filter { it.isInterface }
            .filter { it.interfaces.contains(glBaseClass) }
            .onEach { interfaceClass ->
                val simpleName = interfaceClass.simpleName
                val implName = "${simpleName}LWJGL3"
                val typeSpecBuilder = TypeSpec.classBuilder(implName)
                    .superclass(glBaseImplClassName)
                    .addSuperinterface(interfaceClass)

                val targetClassName = if (simpleName.startsWith("GL") && simpleName.contains(glCoreRegex)) {
                    "${simpleName}C"
                } else {
                    simpleName
                }

                val targetPoetClassName = ClassName("org.lwjgl.opengl", targetClassName)

                val targetClassNode = ClassNode()
                ClassReader("org.lwjgl.opengl.$targetClassName").accept(targetClassNode, 0)

                val filterMethods = targetClassNode.methods.asSequence()
                    .filter { it.name.startsWith("ngl") || it.name.startsWith("gl") }
                    .toList()

                interfaceClass.methods.asSequence()
                    .filter { it.name.startsWith("gl") || it.name.startsWith("ngl") }
                    .filter { method -> method.annotations.none { it.annotationClass == coreOverloadClass } }
                    .forEach { method ->
                        val methodName = method.name.substringBefore('-')
                        val returnType = method.returnType
                        val searchDesc = getMethodDescriptor(returnType, method.parameterTypes)

                        val name2 = "n$methodName"

                        val targetMethod = filterMethods.find {
                            (it.name == methodName || it.name == name2) && it.desc == searchDesc
                        }

                        val ptrParamIdx = method.annotations.find {
                            it.annotationClass == ptrParameterClass
                        }?.let {
                            (it as PtrParameter).ptrParamIdx
                        } ?: intArrayOf()

                        val realParameters = method.parameters.mapIndexed { index, parameter ->
                            if (index in ptrParamIdx) {
                                ParameterSpec.builder(parameter.name, kmogusPtrClassName).build()
                            } else {
                                ParameterSpec.builder(parameter.name, parameter.type.typeName(false)).build()
                            }
                        }

                        val ptrReturn = method.annotations.any { it.annotationClass == ptrReturnClass }
                        val nullableReturn = method.annotations.any { it.annotationClass == nullableReturnClass }

                        if (targetMethod == null) {
                            check(method.modifiers and Modifier.ABSTRACT != 0) {
                                "Method $methodName not found in $targetClassName"
                            }
                        } else {
                            typeSpecBuilder.addFunction(
                                FunSpec.builder(methodName)
                                    .addModifiers(KModifier.OVERRIDE)
                                    .apply {
                                        if (ptrReturn) {
                                            addAnnotation(ptrReturnAnnotationSpec)
                                        }

                                        if (ptrParamIdx.isNotEmpty()) {
                                            addAnnotation(
                                                AnnotationSpec.builder(
                                                    ClassName(
                                                        "dev.luna5ama.glwrapper.api",
                                                        "PtrParameter"
                                                    )
                                                )
                                                    .addMember(
                                                        "%L",
                                                        ptrParamIdx.joinToString(prefix = "[", postfix = "]")
                                                    )
                                                    .build()
                                            )
                                        }

                                        if (nullableReturn) {
                                            addAnnotation(nullableReturnAnnotationSpec)
                                        }
                                    }
                                    .addParameters(realParameters)
                                    .addCode(
                                        CodeBlock.builder()
                                            .apply {
                                                if (ptrReturn) {
                                                    add(
                                                        "return %T(%T.%L(",
                                                        kmogusPtrClassName,
                                                        targetPoetClassName,
                                                        targetMethod.name
                                                    )
                                                } else {
                                                    add("return %T.%L(", targetPoetClassName, targetMethod.name)
                                                }
                                                realParameters.forEachIndexed { index, parameter ->
                                                    if (index > 0) {
                                                        add(", ")
                                                    }
                                                    if (parameter.type == kmogusPtrClassName) {
                                                        add("%N.%N", parameter.name, "address")
                                                    } else {
                                                        add("%N", parameter.name)
                                                    }
                                                }
                                                if (ptrReturn) {
                                                    add("))\n")
                                                } else {
                                                    add(")\n")
                                                }
                                            }
                                            .build()
                                    )
                                    .returns(
                                        if (ptrReturn) kmogusPtrClassName else returnType.typeName(nullableReturn)
                                    )
                                    .build()
                            )
                        }


                    }

                FileSpec.builder("dev.luna5ama.glwrapper.api", implName)
                    .addType(typeSpecBuilder.build())
                    .build()
                    .writeTo(outputDir)
            }
            .map { it.simpleName to "${it.simpleName}LWJGL3" }
            .toList()

        FileSpec.builder("dev.luna5ama.glwrapper.api", "GLWrapperProviderLWJGL3")
            .addType(
                TypeSpec.classBuilder("GLWrapperProviderLWJGL3")
                    .addSuperinterface(ClassName("dev.luna5ama.glwrapper.api", "GLWrapperProvider"))
                    .addProperty(
                        PropertySpec.builder("priority", Int::class)
                            .addModifiers(KModifier.OVERRIDE)
                            .initializer("0")
                            .build()
                    )
                    .generateGLWrapper(types)
                    .build()
            )
            .build()
            .writeTo(outputDir)
    }

    private fun TypeSpec.Builder.generateGLWrapper(types: List<Pair<String, String>>): TypeSpec.Builder {
        val shaderSrcPathResolverClassName = ClassName("dev.luna5ama.glwrapper", "ShaderSource", "PathResolver")
        val glWrapperClassName = ClassName("dev.luna5ama.glwrapper.api", "GLWrapper")
        this.addFunction(
            FunSpec.builder("create")
                .addModifiers(KModifier.OVERRIDE)
                .addParameter(
                    ParameterSpec.builder("shaderSrcPathResolver", shaderSrcPathResolverClassName)
                        .build()
                )
                .returns(glWrapperClassName)
                .addCode(
                    CodeBlock.builder()
                        .add("return %T(%N, ", glWrapperClassName, "shaderSrcPathResolver")
                        .apply {
                            for ((interfaceName, implName) in types) {
                                add("%N = %T(), ", interfaceName, ClassName("dev.luna5ama.glwrapper.api", implName))
                            }
                        }
                        .add(")\n")
                        .build()
                )
                .build()
        )
        return this
    }

    private fun Class<*>.typeName(nullable: Boolean): TypeName {
        when (this) {
            java.lang.Void.TYPE -> return UNIT.copy(nullable = nullable)
            java.lang.String::class.java -> return STRING.copy(nullable = nullable)
            java.lang.CharSequence::class.java -> return CHAR_SEQUENCE.copy(nullable = nullable)
            else -> return asTypeName().copy(nullable = nullable)
        }
    }

    private fun getClassDescriptor(clazz: Class<*>): String {
        return when {
            clazz == Byte::class.java -> "B"
            clazz == Char::class.java -> "C"
            clazz == Double::class.java -> "D"
            clazz == Float::class.java -> "F"
            clazz == Int::class.java -> "I"
            clazz == Long::class.java -> "J"
            clazz == Short::class.java -> "S"
            clazz == Boolean::class.java -> "Z"
            clazz == Void.TYPE -> "V"
            clazz == Unit.javaClass -> "V"
            clazz.isArray -> "[" + getClassDescriptor(clazz.componentType)
            else -> "L" + clazz.name.replace('.', '/') + ";"
        }
    }

    private fun getMethodDescriptor(returnType: Class<*>, parameterTypes: Array<Class<*>>): String {
        val returnTypeDescriptor = getClassDescriptor(returnType)
        val parameterTypesDescriptor = parameterTypes.joinToString(separator = "") { getClassDescriptor(it) }
        return "($parameterTypesDescriptor)$returnTypeDescriptor"
    }
}

