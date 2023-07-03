package dev.luna5ama.glwrapper

import com.google.devtools.ksp.getAllSuperTypes
import com.google.devtools.ksp.getClassDeclarationByName
import com.google.devtools.ksp.getDeclaredFunctions
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.ClassKind
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ksp.toClassName
import com.squareup.kotlinpoet.ksp.writeTo

class CoreCodeGenProcessor(private val environment: SymbolProcessorEnvironment) : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        val glBase = resolver.getClassDeclarationByName("dev.luna5ama.glwrapper.api.GLBase")!!.asType(emptyList())

        val classList = resolver.getAllFiles()
            .flatMap { it.declarations }
            .filterIsInstance<KSClassDeclaration>()
            .filter { it.classKind == ClassKind.INTERFACE }
            .filter { it.getAllSuperTypes().contains(glBase) }
            .forEach { genAccessors(it) }

        return emptyList()
    }

    private fun genAccessors(clazz: KSClassDeclaration) {
        val clazzName = clazz.simpleName.asString()
        val glVer = clazzName.removePrefix("I")
        val glVerLower = glVer.lowercase()

        try {
            FileSpec.builder("dev.luna5ama.glwrapper.api", glVer)
                .addAnnotation(AnnotationSpec.builder(JvmName::class).addMember(""""$glVer"""").build())
                .addAnnotation(AnnotationSpec.builder(Suppress::class).addMember(""""NOTHING_TO_INLINE"""").build())
                .addImport("dev.luna5ama.glwrapper.api.GLWrapper.Companion", "instance")
                .apply {
                    clazz.getDeclaredFunctions()
                        .filter { it.simpleName.asString().startsWith("gl") }
                        .forEach { funcDecl ->
                            val funcName = funcDecl.simpleName.asString()
                            val rType = funcDecl.returnType!!.resolve()
                            addFunction(
                                FunSpec.builder(funcName)
                                    .returns(rType.toClassName().copy(nullable = rType.isMarkedNullable))
                                    .addModifiers(KModifier.INLINE)
                                    .addParameters(funcDecl.parameters.map {
                                        ParameterSpec.builder(it.name!!.asString(), it.type.resolve().toClassName())
                                            .apply {
                                                if (it.isVararg) addModifiers(KModifier.VARARG)
                                            }
                                            .build()
                                    })
                                    .addStatement("return instance.$glVerLower.$funcName(${parameterAsArg(funcDecl)})")
                                    .build()
                            )
                        }

                    clazz.declarations
                        .filterIsInstance<KSClassDeclaration>()
                        .filter { it.isCompanionObject }
                        .flatMap { it.getAllProperties() }
                        .forEach {
                            val fieldName = it.simpleName.asString()
                            addProperty(
                                PropertySpec.builder(fieldName, it.type.resolve().toClassName())
                                    .initializer("$clazzName.$fieldName")
                                    .build()
                            )
                        }
                }
                .build()
                .writeTo(environment.codeGenerator, Dependencies(false))
        } catch (e: FileAlreadyExistsException) {
            // ignore
        }
    }

    private fun parameterAsArg(funcDecl: KSFunctionDeclaration): String {
        return funcDecl.parameters.joinToString {
            val paramName = it.name!!.asString()
            "${paramName}=$paramName"
        }
    }
}