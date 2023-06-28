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
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ksp.toClassName
import com.squareup.kotlinpoet.ksp.writeTo

class CoreCodeGenProcessor(private val environment: SymbolProcessorEnvironment) : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        val glBase = resolver.getClassDeclarationByName("dev.luna5ama.glwrapper.api.GLBase")!!.asType(emptyList())

        resolver.getNewFiles()
            .flatMap { it.declarations }
            .filterIsInstance<KSClassDeclaration>()
            .filter { it.classKind == ClassKind.INTERFACE }
            .filter { it.getAllSuperTypes().contains(glBase) }
            .forEach { genAccessors(it) }

        return emptyList()
    }

    private fun genAccessors(clazz: KSClassDeclaration) {
        val glVer = clazz.simpleName.asString().removePrefix("I")
        val glVerLower = glVer.lowercase()
        FileSpec.builder("dev.luna5ama.glwrapper.api", glVer)
            .addAnnotation(AnnotationSpec.builder(Suppress::class).addMember(""""NOTHING_TO_INLINE"""").build())
            .addImport("dev.luna5ama.glwrapper.api.GLWrapper.Companion", "instance")
            .apply {
                clazz.getDeclaredFunctions()
                    .filter { it.simpleName.asString().startsWith("gl") }
                    .forEach { funcDecl ->
                        addFunction(
                            FunSpec.builder(funcDecl.simpleName.asString())
                                .addModifiers(KModifier.INLINE)
                                .returns(funcDecl.returnType!!.resolve().toClassName())
                                .addParameters(funcDecl.parameters.map {
                                    ParameterSpec.builder(it.name!!.asString(), it.type.resolve().toClassName())
                                        .apply {
                                            if (it.isVararg) addModifiers(KModifier.VARARG)
                                        }
                                        .build()
                                })
                                .addStatement("return instance.$glVerLower.${funcDecl.simpleName.asString()}(${funcDecl.parameters.joinToString { it.name!!.asString() }})")
                                .build()
                        )
                    }
            }
            .build()
            .writeTo(environment.codeGenerator, Dependencies(false))
    }
}