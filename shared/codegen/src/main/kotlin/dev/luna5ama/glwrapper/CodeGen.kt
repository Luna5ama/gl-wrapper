package dev.luna5ama.glwrapper

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.MemberName.Companion.member
import com.sun.source.tree.*
import com.sun.source.util.JavacTask
import com.sun.source.util.SimpleTreeVisitor
import dev.luna5ama.ktgen.KtgenProcessor
import java.nio.file.Path
import javax.lang.model.element.Modifier
import javax.tools.JavaFileObject
import javax.tools.ToolProvider
import kotlin.io.path.extension


class CodeGen : KtgenProcessor {
    private val glCoreRegex = "GL(\\d{2})C".toRegex()
    private val kmogusArrClassName = ClassName("dev.luna5ama.kmogus", "Arr")
    private val kmogusPtrClassName = ClassName("dev.luna5ama.kmogus", "Ptr")
    private val glWrapperClassName = ClassName("dev.luna5ama.glwrapper.api", "GLWrapper")
    private val glBaseClassName = ClassName("dev.luna5ama.glwrapper.api", "GLBase")

    private val nativeTypePtrMethodRegex = "@NativeType\\(\".+? \\*\"\\)[\\W\n]+public static ".toRegex()
    private val unsafeAnnotationSpec = AnnotationSpec.builder(ClassName("dev.luna5ama.glwrapper.api", "Unsafe")).build()
    private val ptrReturnAnnotationSpec =
        AnnotationSpec.builder(ClassName("dev.luna5ama.glwrapper.api", "PtrReturn")).build()
    private val nullableReturnAnnotationSpec =
        AnnotationSpec.builder(ClassName("dev.luna5ama.glwrapper.api", "NullableReturn")).build()
    private val coreOverloadAnnotationSpec =
        AnnotationSpec.builder(ClassName("dev.luna5ama.glwrapper.api", "CoreOverload")).build()
    private val core = """GL_ARB_gl_spirv
GL_ARB_indirect_parameters
GL_ARB_pipeline_statistics_query
GL_ARB_polygon_offset_clamp
GL_ARB_shader_atomic_counter_ops
GL_ARB_shader_draw_parameters
GL_ARB_shader_group_vote
GL_ARB_spirv_extensions
GL_ARB_texture_filter_anisotropic
GL_ARB_transform_feedback_overflow_query
GL_KHR_no_error
GL_ARB_ES3_1_compatibility
GL_ARB_clip_control
GL_ARB_conditional_render_inverted
GL_ARB_cull_distance
GL_ARB_derivative_control
GL_ARB_direct_state_access
GL_ARB_get_texture_sub_image
GL_ARB_shader_texture_image_samples
GL_ARB_texture_barrier
GL_KHR_context_flush_control
GL_KHR_robustness
GL_EXT_shader_integer_mix
GL_MAX_VERTEX_ATTRIB_STRIDE
GL_ARB_buffer_storage
GL_ARB_clear_texture
GL_ARB_enhanced_layouts
GL_ARB_multi_bind
GL_ARB_query_buffer_object
GL_ARB_texture_mirror_clamp_to_edge
GL_ARB_texture_stencil8
GL_ARB_vertex_type_10f_11f_11f_rev
GL_ARB_arrays_of_arrays
GL_ARB_ES3_compatibility
GL_ARB_clear_buffer_object
GL_ARB_compute_shader
GL_ARB_copy_image
GL_KHR_debug
GL_ARB_explicit_uniform_location
GL_ARB_fragment_layer_viewport
GL_ARB_framebuffer_no_attachments
GL_ARB_internalformat_query2
GL_ARB_invalidate_subdata
GL_ARB_multi_draw_indirect
GL_ARB_program_interface_query
GL_ARB_robust_buffer_access_behavior
GL_ARB_shader_image_size
GL_ARB_shader_storage_buffer_object
GL_ARB_stencil_texturing
GL_ARB_texture_buffer_range
GL_ARB_texture_query_levels
GL_ARB_texture_storage_multisample
GL_ARB_texture_view
GL_ARB_vertex_attrib_binding
GL_ARB_texture_compression_bptc
GL_ARB_compressed_texture_pixel_storage
GL_ARB_shader_atomic_counters
GL_ARB_texture_storage
GL_ARB_transform_feedback_instanced
GL_ARB_base_instance
GL_ARB_shader_image_load_store
GL_ARB_conservative_depth
GL_ARB_shading_language_420pack
GL_ARB_shading_language_packing
GL_ARB_internalformat_query
GL_ARB_map_buffer_alignment
GL_ARB_ES2_compatibility
GL_ARB_get_program_binary
GL_ARB_separate_shader_objects
GL_ARB_shader_precision
GL_ARB_vertex_attrib_64bit
GL_ARB_viewport_array
GL_ARB_draw_buffers_blend
GL_ARB_draw_indirect
GL_ARB_gpu_shader5
GL_ARB_gpu_shader_fp64
GL_ARB_sample_shading
GL_ARB_shader_subroutine
GL_ARB_tessellation_shader
GL_ARB_texture_buffer_object_rgb32
GL_ARB_texture_cube_map_array
GL_ARB_texture_gather
GL_ARB_texture_query_lod
GL_ARB_transform_feedback2
GL_ARB_transform_feedback3
GL_ARB_blend_func_extended
GL_ARB_explicit_attrib_location
GL_ARB_occlusion_query2
GL_ARB_sampler_objects
GL_ARB_shader_bit_encoding
GL_ARB_texture_rgb10_a2ui
GL_ARB_texture_swizzle
GL_ARB_timer_query
GL_ARB_instanced_arrays
GL_ARB_vertex_type_2_10_10_10_rev
GL_ARB_vertex_array_bgra
GL_ARB_draw_elements_base_vertex
GL_ARB_fragment_coord_conventions
GL_ARB_provoking_vertex
GL_ARB_seamless_cube_map
GL_ARB_texture_multisample
GL_ARB_depth_clamp
GL_ARB_sync
GLX_ARB_create_context_profile
GL_ARB_draw_instanced
GL_ARB_copy_buffer
GL_NV_primitive_restart
GL_ARB_texture_buffer_object
GL_ARB_texture_rectangle
GL_ARB_uniform_buffer_object
GL_EXT_texture_snorm
GL_NV_conditional_render
GL_ARB_map_buffer_range
GL_ARB_color_buffer_float
GL_ARB_texture_float
GL_EXT_packed_float
GL_EXT_texture_shared_exponent
GL_ARB_depth_buffer_float
GL_ARB_framebuffer_object
GL_ARB_half_float_pixel
GL_ARB_half_float_vertex
GL_EXT_texture_integer
GL_EXT_texture_array
GL_EXT_draw_buffers2
GL_EXT_texture_compression_rgtc
GL_ARB_texture_rg
GL_EXT_transform_feedback
GL_ARB_vertex_array_object
GL_EXT_framebuffer_sRGB
GLX_ARB_create_context
GL_ARB_vertex_program
GL_ARB_vertex_shader""".lineSequence().map { it.removePrefix("GL_").replace("_", "").lowercase() }.toSet()

    private val excluded = """GL_EXT_memory_object
GL_EXT_semaphore""".lineSequence().map { it.removePrefix("GL_").replace("_", "").lowercase() }.toSet()

    private val suppressAnnotation = AnnotationSpec.builder(ClassName("kotlin", "Suppress"))
        .addMember("%S", "NOTHING_TO_INLINE")
        .addMember("%S", "unused")
        .addMember("%S", "RedundantVisibilityModifier")
        .build()

    override fun process(inputs: List<Path>, outputDir: Path) {
        val javaFiles = inputs.asSequence()
            .filter { it.extension == "java" }
            .map { it.toFile() }
            .toList()

        val compiler = ToolProvider.getSystemJavaCompiler()
        val classNames = mutableListOf<String>()
        val globalTypeSpecList = mutableListOf<TypeSpec>()
        val globalPropertyNameSet = mutableSetOf<String>()

        compiler.getStandardFileManager(null, null, Charsets.UTF_8).use { fileManager ->
            val compilationUnits: Iterable<JavaFileObject?> = fileManager.getJavaFileObjectsFromFiles(javaFiles)
            val javacTask =
                compiler.getTask(null, fileManager, null, listOf("-proc:none"), null, compilationUnits) as JavacTask
            val compilationUnitTrees = javacTask.parse()
            compilationUnitTrees.asSequence()
                .filter { it.packageName?.toString() == "org.lwjgl.opengl" }
                .flatMap { it.typeDecls }
                .map { classTree ->
                    val glClassVisitor = GLClassVisitor()
                    classTree.accept(glClassVisitor, null)
                    glClassVisitor
                }.toList().filter {
                    it.className != null
                }.sortedByDescending {
                    it.className!!.startsWith("GL") && it.className!!.contains(glCoreRegex)
                }.toList().forEach { visitor ->
                    hardcodedOverloads(visitor)

                    val className = visitor.className!!
                    val typeSpec = visitor.typeBuilder!!.build()

                    classNames.add(className)
                    globalTypeSpecList.add(typeSpec)

                    val fileBuilder = FileSpec.builder("dev.luna5ama.glwrapper.api", className)
                    fileBuilder.addAnnotation(
                        suppressAnnotation
                    )
                    fileBuilder.addImport("dev.luna5ama.kmogus", "ensureCapacity")
                    fileBuilder.addType(typeSpec)
                    visitor.properties.forEach {
                        if (globalPropertyNameSet.add(it.name)) {
                            fileBuilder.addProperty(it)
                        }
                    }
                    visitor.functions.forEach { fileBuilder.addFunction(it) }

                    fileBuilder.indent("    ")
                    val fileSpec = fileBuilder.build()
                    fileSpec.writeTo(outputDir)
                }
        }

        FileSpec.builder(glWrapperClassName)
            .addType(
                TypeSpec.classBuilder("GLWrapper")
                    .superclass(ClassName("dev.luna5ama.glwrapper.api", "GLBase", "Impl"))
                    .primaryConstructor(
                        FunSpec.constructorBuilder()
                            .addParameters(
                                classNames.map {
                                    ParameterSpec.builder(it, ClassName("dev.luna5ama.glwrapper.api", it))
                                        .build()
                                }
                            )
                            .build()
                    )
                    .addFunction(
                        FunSpec.constructorBuilder()
                            .addParameter(
                                ParameterSpec.builder("delegate", glWrapperClassName)
                                    .build()
                            )
                            .addParameters(
                                classNames.map {
                                    ParameterSpec.builder(it, ClassName("dev.luna5ama.glwrapper.api", it))
                                        .defaultValue("%N.%N", "delegate", it)
                                        .build()
                                }
                            )
                            .callThisConstructor(
                                classNames.map {
                                    CodeBlock.of("%N", it)
                                }
                            )
                            .build()
                    )
                    .addProperty(
                        PropertySpec.builder("vendor", ClassName("dev.luna5ama.glwrapper.api", "GpuVendor"))
                            .getter(
                                FunSpec.getterBuilder()
                                    .addStatement(
                                        "return %T.get(this)",
                                        ClassName("dev.luna5ama.glwrapper.api", "GpuVendor")
                                    )
                                    .build()
                            )
                            .build()
                    )
                    .addProperties(
                        classNames.map {
                            PropertySpec.builder(it, ClassName("dev.luna5ama.glwrapper.api", it))
                                .initializer(it)
                                .build()
                        }
                    )
                    .addType(
                        TypeSpec.companionObjectBuilder()
                            .superclass(ClassName("dev.luna5ama.glwrapper.api", "GLWrapperControl"))
                            .build()
                    )
                    .build()
            )
            .indent("    ")
            .build()
            .writeTo(outputDir)
    }

    private inner class GLClassVisitor : SimpleTreeVisitor<Unit, Unit?>() {
        var className: String? = null
        var typeBuilder: TypeSpec.Builder? = null

        val properties = mutableListOf<PropertySpec>()
        val functions = mutableListOf<FunSpec>()

        override fun visitClass(node: ClassTree, p: Unit?) {
            val simpleName = node.simpleName.toString()
            if (!(simpleName.startsWith("GL") && simpleName.contains(glCoreRegex)
                    || simpleName.startsWith("ARB")
                    || simpleName.startsWith("EXT")
                    )
            ) return

            val lookUpName = simpleName.replace("_", "").lowercase()
            if (core.contains(lookUpName)) return
            if (excluded.contains(lookUpName)) return

            className = simpleName
            if (simpleName.startsWith("GL")) {
                val matchResult = glCoreRegex.find(simpleName) ?: return
                className = "GL${matchResult.groupValues[1]}"
            }

            typeBuilder = TypeSpec.interfaceBuilder(className!!)
                .addSuperinterface(glBaseClassName)

            val ptrParameters = mutableMapOf<String, MutableSet<String>>()
            val ptrReturn = mutableSetOf<String>()


            node.members.forEach { member ->
                member.accept(object : SimpleTreeVisitor<Unit, Unit?>() {
                    override fun visitVariable(node: VariableTree, p: Unit?) {
                        if (!node.name.startsWith("GL_")) return

                        val vStr = node.initializer.toString()
                        addConst(parseType(node.type), node.name.toString(), vStr)
                    }
                }, null)
            }

            node.members.forEach { member ->
                member.accept(object : SimpleTreeVisitor<Unit, Unit?>() {
                    override fun visitMethod(node: MethodTree, p: Unit?) {
                        if (!node.name.startsWith("gl")) return

                        val funcName = node.name.toString()
                        node.parameters.asSequence()
                            .filter { it.toString().contains('*') }
                            .forEach {
                                ptrParameters.getOrPut(funcName, ::mutableSetOf)
                                    .add(it.name.toString())
                            }

                        if (node.returnType.toString().contains("Buffer")) {
                            ptrReturn.add(funcName)
                        }

                        if (node.toString().contains(nativeTypePtrMethodRegex)) {
                            ptrReturn.add(funcName)
                        }
                    }
                }, null)
            }

            node.members.forEach { member ->
                member.accept(object : SimpleTreeVisitor<Unit, Unit?>() {
                    override fun visitMethod(node: MethodTree, p: Unit?) {
                        var containNativeModifier = false
                        node.modifiers.accept(object : SimpleTreeVisitor<Unit, Unit?>() {
                            override fun visitModifiers(node: ModifiersTree, p: Unit?) {
                                containNativeModifier = containNativeModifier || node.flags.contains(Modifier.NATIVE)
                            }
                        }, null)

                        if (!node.name.startsWith("ngl") && !containNativeModifier) return

                        val funcName = node.name.removePrefix("n").toString()
                        val returnType = parseType(node.returnType)
                        val parameters = node.parameters.map {
                            parseType(it.type) to it.name.toString()
                        }

                        val isPtrReturn = ptrReturn.contains(funcName)
                        val hasPtr = ptrParameters.containsKey(funcName) || isPtrReturn

                        fun FunSpec.Builder.addUnsafeAnnotation(): FunSpec.Builder {
                            if (hasPtr) {
                                addAnnotation(
                                    unsafeAnnotationSpec
                                )
                            }

                            return this
                        }

                        if (hasPtr) {
                            val safeParameters = parameters.map { pair ->
                                val parameterNames = ptrParameters[funcName]
                                if (parameterNames?.contains(pair.second) == true) {
                                    kmogusPtrClassName to pair.second
                                } else {
                                    pair
                                }
                            }

                            val ptrIndices = safeParameters.indices
                                .filter { safeParameters[it].first == kmogusPtrClassName }
                                .toIntArray()

                            val safeReturnType = if (isPtrReturn) kmogusPtrClassName else returnType

                            addFuncWithTopLevel(funcName, safeParameters, safeReturnType) {
                                if (isPtrReturn) {
                                    addAnnotation(ptrReturnAnnotationSpec)
                                }
                                if (ptrIndices.isNotEmpty()) {
                                    addAnnotation(
                                        AnnotationSpec.builder(ClassName("dev.luna5ama.glwrapper.api", "PtrParameter"))
                                            .addMember("%L", ptrIndices.joinToString(prefix = "[", postfix = "]"))
                                            .build()
                                    )
                                }
                                addModifiers(KModifier.ABSTRACT)
                            }

                            if (!isPtrReturn) {
                                addFuncWithTopLevel(funcName, parameters, returnType) {
                                    addModifiers(KModifier.ABSTRACT)
                                    addUnsafeAnnotation()
                                }
                            }
                        } else {
                            addFuncWithTopLevel(funcName, parameters, returnType) {
                                addModifiers(KModifier.ABSTRACT)
                            }
                        }
                    }
                }, null)
            }
        }

        fun addConst(type: TypeName, name: String, valueStr: String) {
            val value = if (valueStr == "-1L") {
                "-1"
            } else {
                valueStr.toLongOrNull()?.toInt()?.let {
                    if (it < 0) {
                        "-0x%X".format(-it)
                    } else {
                        "0x%X".format(it)
                    }
                } ?: valueStr.substringAfter('.')
            }

            properties.add(
                PropertySpec.builder(name, type)
                    .addModifiers(KModifier.CONST)
                    .initializer(value)
                    .build()
            )
        }

        private fun FunSpec.Builder.addParameters(params: List<Pair<TypeName, String>>): FunSpec.Builder {
            params.forEach { (type, name) ->
                addParameter(name, type)
            }
            return this
        }

        fun overloadCall(funcName: String, params: List<Pair<TypeName, String>>): CodeBlock {
            return CodeBlock.builder()
                .add(
                    "return %T.%N.%N.%N",
                    glWrapperClassName,
                    "instance",
                    className,
                    funcName
                )
                .apply {
                    add("(")
                    params.forEachIndexed { index, (_, name) ->
                        add("%N", name)
                        if (index != params.size - 1) {
                            add(", ")
                        }
                    }
                    add(")")
                }.build()
        }

        fun addFuncWithTopLevel(
            funcName: String,
            params: List<Pair<TypeName, String>>,
            returnType: TypeName,
            block: FunSpec.Builder.() -> Unit = {},
        ) {
            typeBuilder!!.addFunction(
                FunSpec.builder(funcName)
                    .apply {
                        if (returnType.isNullable) {
                            addAnnotation(nullableReturnAnnotationSpec)
                        }
                    }
                    .addParameters(params)
                    .returns(returnType)
                    .apply(block)
                    .build()
            )

            functions.add(
                FunSpec.builder(funcName)
                    .apply {
                        if (returnType.isNullable) {
                            addAnnotation(nullableReturnAnnotationSpec)
                        }
                    }
                    .addParameters(params)
                    .returns(returnType)
                    .addModifiers(KModifier.INLINE)
                    .addCode(overloadCall(funcName, params))
                    .build()
            )
        }
    }

    private val TypeName.byteSize
        get() = when (this) {
            BYTE, BOOLEAN -> 1L
            SHORT, CHAR -> 2L
            INT, FLOAT -> 4L
            DOUBLE, LONG -> 8L
            else -> 1
        }

    private val TypeName.ptrGetName
        get() = when (this) {
            BYTE -> "getByte"
            SHORT -> "getShort"
            INT -> "getInt"
            FLOAT -> "getFloat"
            DOUBLE -> "getDouble"
            LONG -> "getLong"
            else -> throw IllegalStateException("Unknown type: $this")
        }

    private val TypeName.ptrSetIncName
        get() = when (this) {
            BYTE -> "setByteInc"
            SHORT -> "setShortInc"
            INT -> "setIntInc"
            FLOAT -> "setFloatInc"
            DOUBLE -> "setDoubleInc"
            LONG -> "setLongInc"
            else -> throw IllegalStateException("Unknown type: $this")
        }


    private fun hardcodedOverloads(visitor: GLClassVisitor) {
        fun getScalar(funcName: String, orginalName: String, type: TypeName, params: List<Pair<TypeName, String>>) {

            visitor.addFuncWithTopLevel(funcName, params, type) {
                addStatement(
                    "%N.%L(%L, %L)",
                    "tempArr",
                    "ensureCapacity",
                    type.byteSize,
                    false
                )
                addCode(
                    CodeBlock.builder()
                        .add("%L(", orginalName)
                        .apply {
                            params.forEachIndexed { index, (_, name) ->
                                add("%N", name)
                                if (index != params.size - 1) {
                                    add(", ")
                                }
                            }
                        }
                        .add(", %N.%N)\n", "tempArr", "ptr")
                        .build()
                )
                addStatement(
                    "return %N.%N.%N()",
                    "tempArr",
                    "ptr",
                    type.ptrGetName
                )
            }
        }

        fun setScalar(
            funcName: String,
            orginalName: String,
            type: TypeName,
            params: List<Pair<TypeName, String>>,
            vparams: List<String>
        ) {
            visitor.addFuncWithTopLevel(funcName, params + vparams.map { type to it }, UNIT) {
                addStatement(
                    "%N.%L(%L, %L)",
                    "tempArr",
                    "ensureCapacity",
                    type.byteSize * vparams.size,
                    false
                )
                addCode(
                    CodeBlock.builder()
                        .apply {
                            vparams.forEachIndexed { index, name ->
                                if (index == 0) {
                                    add("%N.%N.%N(%N)", "tempArr", "ptr", type.ptrSetIncName, name)
                                } else {
                                    add(".%N(%N)", type.ptrSetIncName, name)
                                }
                            }
                        }
                        .add("\n")
                        .build()
                )
                addCode(
                    CodeBlock.builder()
                        .add("%L(", orginalName)
                        .apply {
                            params.forEachIndexed { index, (_, name) ->
                                add("%N", name)
                                if (index != params.size - 1) {
                                    add(", ")
                                }
                            }
                        }
                        .add(", %N.%N)\n", "tempArr", "ptr")
                        .build()
                )
            }
        }

        when (visitor.className) {
            "GL11" -> {
                visitor.addConst(INT, "GL_TEXTURE", "0x1702")

                visitor.addFuncWithTopLevel("glGetBoolean", listOf(INT to "pname"), BOOLEAN) {
                    addStatement("%N.%N(%L, %L)", "tempArr", "ensureCapacity", 4L, false)
                    addStatement("%N(%N, %N.%N)", "glGetBooleanv", "pname", "tempArr", "ptr")
                    addStatement("return %N.%N.%N() != %L", "tempArr", "ptr", "getInt", 0)
                }

                getScalar("glGetInteger", "glGetIntegerv", INT, listOf(INT to "pname"))
                getScalar("glGetFloat", "glGetFloatv", FLOAT, listOf(INT to "pname"))
                getScalar("glGetDouble", "glGetDoublev", DOUBLE, listOf(INT to "pname"))

                visitor.addFuncWithTopLevel("glGetPointer", listOf(INT to "pname"), kmogusPtrClassName) {
                    addAnnotation(ptrReturnAnnotationSpec)
                    addStatement("%N.%N(%L, %L)", "tempArr", "ensureCapacity", 8L, false)
                    addStatement("%N(%N, %N.%N)", "glGetPointerv", "pname", "tempArr", "ptr")
                    addStatement("return %T(%N.%N.%N())", kmogusPtrClassName, "tempArr", "ptr", "getLong")
                }

                visitor.typeBuilder!!.funSpecs.removeIf { it.name == "glGetString" }
                visitor.functions.removeIf { it.name == "glGetString" }

                visitor.addFuncWithTopLevel("glGetString", listOf(INT to "name"), STRING.copy(true)) {
                    addModifiers(KModifier.ABSTRACT)
                }

                visitor.addFuncWithTopLevel("glGenTextures", emptyList(), INT) {
                    addModifiers(KModifier.ABSTRACT)
                }

                visitor.addFuncWithTopLevel("glDeleteTextures", listOf(INT to "texture"), UNIT) {
                    addModifiers(KModifier.ABSTRACT)
                }
            }
            "GL15" -> {
                visitor.addFuncWithTopLevel("glDeleteQueries", listOf(INT to "query"), UNIT) {
                    addModifiers(KModifier.ABSTRACT)
                }
                visitor.addFuncWithTopLevel("glDeleteBuffers", listOf(INT to "buffer"), UNIT) {
                    addModifiers(KModifier.ABSTRACT)
                }
                visitor.addFuncWithTopLevel("glGetQueryObjecti", listOf(INT to "id", INT to "pname"), INT) {
                    addStatement("%N.%N(%L, %L)", "tempArr", "ensureCapacity", 4L, false)
                    addStatement("%N(%N, %N, %N.%N)", "glGetQueryObjectiv", "id", "pname", "tempArr", "ptr")
                    addStatement("return %N.%N.%N()", "tempArr", "ptr", "getInt")
                }
                visitor.addFuncWithTopLevel("glGetQueryObjectui", listOf(INT to "id", INT to "pname"), INT) {
                    addStatement("%N.%N(%L, %L)", "tempArr", "ensureCapacity", 4L, false)
                    addStatement("%N(%N, %N, %N.%N)", "glGetQueryObjectuiv", "id", "pname", "tempArr", "ptr")
                    addStatement("return %N.%N.%N()", "tempArr", "ptr", "getInt")
                }
            }
            "GL20" -> {
                getScalar("glGetShaderi", "glGetShaderiv", INT, listOf(INT to "shader", INT to "pname"))
                getScalar("glGetProgrami", "glGetProgramiv", INT, listOf(INT to "program", INT to "pname"))

                visitor.addFuncWithTopLevel("glGetShaderInfoLog", listOf(INT to "shader", INT to "maxLength"), STRING) {
                    addModifiers(KModifier.ABSTRACT)
                }

                visitor.addFuncWithTopLevel(
                    "glGetProgramInfoLog",
                    listOf(INT to "program", INT to "maxLength"),
                    STRING
                ) {
                    addModifiers(KModifier.ABSTRACT)
                }

                visitor.addFuncWithTopLevel(
                    "glGetUniformLocation",
                    listOf(INT to "program", CHAR_SEQUENCE to "name"),
                    INT
                ) {
                    addModifiers(KModifier.ABSTRACT)
                }

                visitor.addFuncWithTopLevel(
                    "glShaderSource",
                    listOf(INT to "shader", CHAR_SEQUENCE to "string"),
                    UNIT
                ) {
                    addModifiers(KModifier.ABSTRACT)
                }
            }
            "GL30" -> {
                visitor.addFuncWithTopLevel("glDeleteRenderbuffers", listOf(INT to "renderbuffer"), UNIT) {
                    addModifiers(KModifier.ABSTRACT)
                }
                visitor.addFuncWithTopLevel("glDeleteVertexArrays", listOf(INT to "array"), UNIT) {
                    addModifiers(KModifier.ABSTRACT)
                }
                visitor.addFuncWithTopLevel("glDeleteFramebuffers", listOf(INT to "framebuffer"), UNIT) {
                    addModifiers(KModifier.ABSTRACT)
                }
            }
            "GL32" -> {
                visitor.addFuncWithTopLevel("glGetSynci", listOf(LONG to "sync", INT to "pname"), INT) {
                    addStatement("%N.%N(%L, %L)", "tempArr", "ensureCapacity", 8L, false)
                    addStatement("val ptr = %N.%N", "tempArr", "ptr")
                    addStatement("%N.%N(%L)", "ptr", "setInt", 1)
                    addStatement("%N(%N, %N, 1, %N, %N + %L)", "glGetSynciv", "sync", "pname", "ptr", "ptr", 4L)
                    addStatement("return (%N + %L).%N()", "ptr", 4L, "getInt")
                }
            }
            "GL33" -> {
                setScalar(
                    "glSamplerParameterfv",
                    "glSamplerParameterfv",
                    FLOAT,
                    listOf(INT to "sampler", INT to "pname"),
                    listOf("v1", "v2", "v3", "v4")
                )
                setScalar(
                    "glSamplerParameteriv",
                    "glSamplerParameteriv",
                    INT,
                    listOf(INT to "sampler", INT to "pname"),
                    listOf("v1", "v2", "v3", "v4")
                )
                visitor.addFuncWithTopLevel("glGetQueryObjecti64", listOf(INT to "id", INT to "pname"), LONG) {
                    addStatement("%N.%N(%L, %L)", "tempArr", "ensureCapacity", 8L, false)
                    addStatement("%N(%N, %N, %N.%N)", "glGetQueryObjecti64v", "id", "pname", "tempArr", "ptr")
                    addStatement("return %N.%N.%N()", "tempArr", "ptr", "getLong")
                }
                visitor.addFuncWithTopLevel("glGetQueryObjectui64", listOf(INT to "id", INT to "pname"), LONG) {
                    addStatement("%N.%N(%L, %L)", "tempArr", "ensureCapacity", 8L, false)
                    addStatement("%N(%N, %N, %N.%N)", "glGetQueryObjecti64v", "id", "pname", "tempArr", "ptr")
                    addStatement("return %N.%N.%N()", "tempArr", "ptr", "getLong")
                }
                visitor.addFuncWithTopLevel("glDeleteSamplers", listOf(INT to "sampler"), UNIT) {
                    addModifiers(KModifier.ABSTRACT)
                }
            }
            "GL40" -> {
                visitor.addFuncWithTopLevel(
                    "glGetSubroutineIndex",
                    listOf(INT to "program", INT to "shadertype", CHAR_SEQUENCE to "name"),
                    INT
                ) {
                    addModifiers(KModifier.ABSTRACT)
                }

                visitor.addFuncWithTopLevel("glDeleteTransformFeedbacks", listOf(INT to "transformFeedback"), UNIT) {
                    addModifiers(KModifier.ABSTRACT)
                }
            }
            "GL41" -> {
                visitor.addFuncWithTopLevel("glDeleteProgramPipelines", listOf(INT to "pipeline"), UNIT) {
                    addModifiers(KModifier.ABSTRACT)
                }
            }
            "GL42" -> {
                visitor.addFuncWithTopLevel("glGetInternalformati", listOf(INT to "target", INT to "internalformat", INT to "pname"), INT) {
                    addStatement("%N.%N(%L, %L)", "tempArr", "ensureCapacity", 4L, false)
                    addStatement("%N(%N, %N, %N, %L, %N.%N)", "glGetInternalformativ", "target", "internalformat", "pname", 1, "tempArr", "ptr")
                    addStatement("return %N.%N.%N()", "tempArr", "ptr", "getInt")
                }
                visitor.addFuncWithTopLevel("glGetInternalformati64", listOf(INT to "target", INT to "internalformat", INT to "pname"), LONG) {
                    addStatement("%N.%N(%L, %L)", "tempArr", "ensureCapacity", 8L, false)
                    addStatement("%N(%N, %N, %N, %L, %N.%N)", "glGetInternalformati64v", "target", "internalformat", "pname", 1, "tempArr", "ptr")
                    addStatement("return %N.%N.%N()", "tempArr", "ptr", "getLong")
                }
            }
            "GL43" -> {
                visitor.addFuncWithTopLevel(
                    "glObjectLabel",
                    listOf(INT to "identifier", INT to "name", CHAR_SEQUENCE to "label"),
                    UNIT
                ) {
                    addModifiers(KModifier.ABSTRACT)
                }

                visitor.addFuncWithTopLevel(
                    "glGetProgramResourceIndex",
                    listOf(INT to "program", INT to "programInterface", CHAR_SEQUENCE to "name"),
                    INT
                ) {
                    addModifiers(KModifier.ABSTRACT)
                }

                visitor.addFuncWithTopLevel(
                    "glPushDebugGroup",
                    listOf(INT to "source", INT to "id", CHAR_SEQUENCE to "message"),
                    UNIT
                ) {
                    addModifiers(KModifier.ABSTRACT)
                }
            }
            "GL45" -> {
                visitor.addFuncWithTopLevel("glCreateBuffers", listOf(), INT) {
                    addModifiers(KModifier.ABSTRACT)
                }

                visitor.addFuncWithTopLevel("glCreateRenderbuffers", listOf(), INT) {
                    addModifiers(KModifier.ABSTRACT)
                }

                visitor.addFuncWithTopLevel("glCreateSamplers", listOf(), INT) {
                    addModifiers(KModifier.ABSTRACT)
                }

                visitor.addFuncWithTopLevel("glCreateFramebuffers", listOf(), INT) {
                    addModifiers(KModifier.ABSTRACT)
                }

                visitor.addFuncWithTopLevel("glCreateVertexArrays", listOf(), INT) {
                    addModifiers(KModifier.ABSTRACT)
                }

                visitor.addFuncWithTopLevel("glCreateTextures", listOf(INT to "target"), INT) {
                    addModifiers(KModifier.ABSTRACT)
                }

                visitor.addFuncWithTopLevel("glCreateProgramPipelines", listOf(), INT) {
                    addModifiers(KModifier.ABSTRACT)
                }

                visitor.addFuncWithTopLevel("glCreateQueries", listOf(INT to "target"), INT) {
                    addModifiers(KModifier.ABSTRACT)
                }

                visitor.addFuncWithTopLevel("glCreateTransformFeedbacks", listOf(), INT) {
                    addModifiers(KModifier.ABSTRACT)
                }

                setScalar(
                    "glTextureParameterfv",
                    "glTextureParameterfv",
                    FLOAT,
                    listOf(INT to "texture", INT to "pname"),
                    listOf("v1", "v2", "v3", "v4")
                )

                setScalar(
                    "glTextureParameteriv",
                    "glTextureParameteriv",
                    INT,
                    listOf(INT to "texture", INT to "pname"),
                    listOf("v1", "v2", "v3", "v4")
                )

                setScalar(
                    "glClearNamedFramebufferi",
                    "glClearNamedFramebufferiv",
                    INT,
                    listOf(INT to "framebuffer", INT to "buffer", INT to "drawbuffer"),
                    listOf("stencil")
                )
                setScalar(
                    "glClearNamedFramebufferf",
                    "glClearNamedFramebufferfv",
                    FLOAT,
                    listOf(INT to "framebuffer", INT to "buffer", INT to "drawbuffer"),
                    listOf("red", "green", "blue", "alpha")
                )
                setScalar(
                    "glClearNamedFramebufferf",
                    "glClearNamedFramebufferfv",
                    FLOAT,
                    listOf(INT to "framebuffer", INT to "buffer", INT to "drawbuffer"),
                    listOf("depth")
                )

                visitor.functions.removeIf {it.name == "glMapNamedBufferRange"}
                visitor.typeBuilder!!.funSpecs.removeIf {it.name == "glMapNamedBufferRange"}

                visitor.addFuncWithTopLevel(
                    "nglMapNamedBufferRange",
                    listOf(INT to "buffer", LONG to "offset", LONG to "length", INT to "access"),
                    kmogusPtrClassName
                ) {
                    addAnnotation(ptrReturnAnnotationSpec)
                    addModifiers(KModifier.ABSTRACT)
                }

                visitor.addFuncWithTopLevel(
                    "glMapNamedBufferRange",
                    listOf(INT to "buffer", LONG to "offset", LONG to "length", INT to "access"),
                    kmogusArrClassName
                ) {
                    addAnnotation(coreOverloadAnnotationSpec)
                    addStatement(
                        "return %T.%N(%N(%N, %N, %N, %N).%N, %N)",
                        kmogusArrClassName,
                        "wrap",
                        "nglMapNamedBufferRange",
                        "buffer",
                        "offset",
                        "length",
                        "access",
                        "address",
                        "length"
                    )
                }
            }
        }
    }

    private fun parseType(type: Tree): TypeName {
        return when (type.toString()) {
            "void" -> UNIT
            "int" -> INT
            "long" -> LONG
            "float" -> FLOAT
            "double" -> DOUBLE
            "boolean" -> BOOLEAN
            "short" -> SHORT
            "byte" -> BYTE
            else -> throw IllegalStateException("Unknown return type: $type")
        }
    }
}

