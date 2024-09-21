package dev.luna5ama.glwrapper

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.sun.source.tree.*
import com.sun.source.util.JavacTask
import com.sun.source.util.SimpleTreeVisitor
import dev.luna5ama.ktgen.KtgenProcessor
import java.nio.file.Path
import javax.lang.model.element.Modifier
import javax.tools.JavaFileObject
import javax.tools.ToolProvider
import kotlin.io.path.extension

class BaseCodeGen : KtgenProcessor {
    private val glCoreRegex = "GL(\\d{2})C".toRegex()
    private val kmogusArrClassName = ClassName("dev.luna5ama.kmogus", "Arr")
    private val kmogusPtrClassName = ClassName("dev.luna5ama.kmogus", "Ptr")
    private val glBaseClassName = ClassName("dev.luna5ama.glwrapper.base", "GLBase")
    private val glWrapperClassName = ClassName("dev.luna5ama.glwrapper.base", "GLWrapper")
    private val ensureCapacityMemeberName = MemberName("dev.luna5ama.kmogus", "ensureCapacity")

    private val nativeTypePtrMethodRegex = "@NativeType\\(\".+? \\*\"\\)[\\W\n]+public static ".toRegex()
    private val unsafeAnnotationSpec =
        AnnotationSpec.builder(ClassName("dev.luna5ama.glwrapper.base", "Unsafe")).build()
    private val ptrReturnAnnotationSpec =
        AnnotationSpec.builder(ClassName("dev.luna5ama.glwrapper.base", "PtrReturn")).build()
    private val nullableReturnAnnotationSpec =
        AnnotationSpec.builder(ClassName("dev.luna5ama.glwrapper.base", "NullableReturn")).build()
    private val coreOverloadAnnotationSpec =
        AnnotationSpec.builder(ClassName("dev.luna5ama.glwrapper.base", "CoreOverload")).build()
    private val keepExtTypes = listOf(
        "khr", "arb", "ext", "nv", "amd"
    )
    private val core = """
ARB_gl_spirv
ARB_spirv_extensions
ARB_shader_draw_parameters
ARB_indirect_parameters
ARB_pipeline_statistics_query
ARB_transform_feedback_overflow_query
ARB_texture_filter_anisotropic
ARB_polygon_offset_clamp
KHR_no_error
ARB_shader_atomic_counter_ops
ARB_shader_group_vote
ARB_clip_control
ARB_cull_distance
ARB_ES3_1_compatibility
ARB_conditional_render_inverted
ARB_derivative_control
ARB_direct_state_access
ARB_get_texture_sub_image
KHR_robustness
ARB_shader_texture_image_samples
ARB_texture_barrier
ARB_buffer_storage
ARB_clear_texture
ARB_enhanced_layouts
ARB_multi_bind
ARB_query_buffer_object
ARB_texture_mirror_clamp_to_edge
ARB_texture_stencil8
ARB_vertex_type_10f_11f_11f_rev
KHR_debug
ARB_arrays_of_arrays
ARB_clear_buffer_object
ARB_compute_shader
ARB_copy_image
ARB_ES3_compatibility
ARB_explicit_uniform_location
ARB_fragment_layer_viewport
ARB_framebuffer_no_attachments
ARB_internalformat_query2
ARB_invalidate_subdata
ARB_multi_draw_indirect
ARB_program_interface_query
ARB_shader_image_size
ARB_shader_storage_buffer_object
ARB_stencil_texturing
ARB_texture_buffer_range
ARB_texture_query_levels
ARB_texture_storage_multisample
ARB_texture_view
ARB_vertex_attrib_binding
ARB_robust_buffer_access_behavior
ARB_robustness_isolation
WGL_ARB_robustness_isolation
GLX_ARB_robustness_isolation
ARB_shader_atomic_counters
ARB_shader_image_load_store
ARB_texture_storage
ARB_transform_feedback_instanced
ARB_shading_language_420pack
ARB_base_instance
ARB_internalformat_query
ARB_compressed_texture_pixel_storage
ARB_shading_language_packing
ARB_map_buffer_alignment
ARB_conservative_depth
ARB_texture_compression_BPTC
ARB_get_program_binary
ARB_separate_shader_objects
ARB_ES2_compatibility
ARB_shader_precision
ARB_vertex_attrib_64_bit
ARB_viewport_array
ARB_texture_query_lod
ARB_gpu_shader5
ARB_gpu_shader_fp64
ARB_shader_subroutine
ARB_texture_gather
ARB_draw_indirect
ARB_sample_shading
ARB_tessellation_shader
ARB_texture_buffer_object_rgb32
ARB_texture_cube_map_array
ARB_transform_feedback2
ARB_transform_feedback3
ARB_draw_buffers_blend
ARB_shader_bit_encoding
ARB_blend_func_extended
ARB_explicit_attrib_location
ARB_occlusion_query2
ARB_sampler_objects
ARB_texture_rgb10_a2ui
ARB_texture_swizzle
ARB_timer_query
ARB_instanced_arrays
ARB_vertex_type_2_10_10_10_rev
ARB_vertex_array_bgra
ARB_draw_elements_base_vertex
ARB_fragment_coord_conventions
ARB_provoking_vertex
ARB_seamless_cube_map
ARB_texture_multisample
ARB_depth_clamp
ARB_sync
ARB_geometry_shader4
ARB_uniform_buffer_object
ARB_draw_instanced
ARB_copy_buffer
NV_primitive_restart
ARB_texture_buffer_object
ARB_texture_rectangle
ARB_framebuffer_object
ARB_vertex_array_object
NV_conditional_render
ARB_color_buffer_float
ARB_texture_float
EXT_packed_float
EXT_texture_shared_exponent
NV_half_float
EXT_half_float_pixel
EXT_texture_integer
EXT_texture_array
EXT_draw_buffers2
EXT_texture_compression_rgtc
ARB_transform_feedback
EXT_framebuffer_sRGB
ARB_pixel_buffer_object
EXT_texture_sRGB
ARB_shader_objects
ARB_vertex_shader
ARB_fragment_shader
ARB_shading_language_100
ARB_draw_buffers
ARB_texture_non_power_of_two
ARB_point_sprite
ATI_separate_stencil
EXT_stencil_two_side
ARB_vertex_buffer_object
ARB_occlusion_query
EXT_shadow_funcs
SGIS_generate_mipmap
NV_blend_square
ARB_depth_texture
ARB_shadow
EXT_fog_coord
EXT_multi_draw_arrays
ARB_point_parameters
EXT_secondary_color
EXT_blend_func_separate
EXT_stencil_wrap
ARB_texture_env_crossbar
EXT_texture_lod_bias
ARB_texture_mirrored_repeat
ARB_window_pos
ARB_texture_compression
ARB_texture_cube_map
ARB_multisample
ARB_multitexture
ARB_texture_env_add
ARB_texture_env_combine
ARB_texture_env_dot3
ARB_texture_border_clamp
ARB_transpose_matrix
EXT_texture3D
EXT_bgra
EXT_packed_pixels
EXT_rescale_normal
EXT_separate_specular_color
SGIS_texture_edge_clamp
SGIS_texture_lod
EXT_draw_range_elements
SGI_color_table
EXT_color_subtable
EXT_convolution
HP_convolution_border_modes
SUN_convolution_border_modes
SGI_color_matrix
EXT_histogram
EXT_blend_color
EXT_blend_minmax
EXT_blend_subtract
EXT_vertex_array
EXT_polygon_offset
EXT_blend_logic_op
EXT_texture
EXT_texture
EXT_texture
EXT_copy_texture
EXT_subtexture
EXT_texture_object
""".parseGLExtensionList()

    private fun String.parseGLExtensionList(): Set<String> {
        return lineSequence()
            .filter { it.isNotBlank() }
            .map { it.removePrefix("GL_").replace("_", "").lowercase() }
            .toSet()
    }

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
        val classNames = mutableListOf<ClassName>()

        compiler.getStandardFileManager(null, null, Charsets.UTF_8).use { fileManager ->
            val compilationUnits: Iterable<JavaFileObject?> = fileManager.getJavaFileObjectsFromFiles(javaFiles)
            val javacTask =
                compiler.getTask(null, fileManager, null, listOf("-proc:none"), null, compilationUnits) as JavacTask
            val compilationUnitTrees = javacTask.parse()
            var visitors = compilationUnitTrees.asSequence()
                .filter { it.packageName?.toString() == "org.lwjgl.opengl" }
                .flatMap { it.typeDecls }
                .mapNotNull { classTree ->
                    val glClassVisitor = GLClassVisitor()
                    classTree.accept(glClassVisitor, null)
                    glClassVisitor.classData
                }.toList()

            val compareIndex = keepExtTypes.withIndex().map { (index, value) -> value to index }

            visitors = visitors.asSequence()
                .filter { data ->
                    if (data.isGLCore) return@filter true

                    val lookUpName = data.className.replace("_", "").lowercase()
                    if (keepExtTypes.none { lookUpName.startsWith(it) }) return@filter false
                    if (lookUpName.startsWith("nvx")) return@filter false
                    if (core.contains(lookUpName)) return@filter false

                    true
                }
                .sortedWith(compareBy { data ->
                    compareIndex.find {
                        data.className.startsWith(it.first, true)
                    }?.second ?: Int.MIN_VALUE
                })
                .toList()


            val globalTypeSpecList = mutableListOf<TypeSpec>()
            val globalPropertySet = mutableSetOf<Pair<String, String>>()
            val globalFunctionSet = mutableSetOf<Triple<String, String, List<String>>>()

            visitors.forEach { visitor ->
                hardcodedOverloads(visitor)

                val className = ClassName("dev.luna5ama.glwrapper.base", visitor.className)
                val typeSpec = visitor.typeBuilder.build()

                classNames.add(className)
                globalTypeSpecList.add(typeSpec)

                val fileBuilder = FileSpec.builder(className)
                fileBuilder.addAnnotation(suppressAnnotation)
                fileBuilder.addType(typeSpec)
                visitor.topProperties.forEach {
                    val key = Pair(it.name, it.type.toString())
                    if (globalPropertySet.add(key)) {
                        fileBuilder.addProperty(it)
                    }
                }
                visitor.topFunctions.forEach { funSpec ->
                    val key =
                        Triple(funSpec.name, funSpec.returnType.toString(), funSpec.parameters.map { it.toString() })
                    if (globalFunctionSet.add(key)) {
                        fileBuilder.addFunction(funSpec)
                    }
                }

                fileBuilder.indent("    ")
                val fileSpec = fileBuilder.build()
                fileSpec.writeTo(outputDir)
            }
        }

        buildGLWrapperBase(classNames).writeTo(outputDir)
    }

    private fun buildGLWrapperBase(classNames: List<ClassName>): FileSpec {
        val wrapperPropertiesClassName = ClassName("dev.luna5ama.glwrapper.base", "WrapperProperties")
        val glBaseConstructorFunctionInterface = ClassName("dev.luna5ama.glwrapper.base", "GLBase", "Constructor")
        val glProperties = classNames.map {
            PropertySpec.builder(it.simpleName, it).build()
        }

        return FileSpec.builder(glWrapperClassName)
            .addType(
                TypeSpec.classBuilder(glWrapperClassName)
                    .addProperty("properties", wrapperPropertiesClassName)
                    .addProperties(glProperties)
                    .addFunction(
                        FunSpec.constructorBuilder()
                            .addParameters(
                                classNames.map {
                                    ParameterSpec.builder(
                                        it.simpleName,
                                        glBaseConstructorFunctionInterface.parameterizedBy(it)
                                    )
                                        .build()
                                }
                            )
                            .addStatement("this.%N = %T(this)", "properties", wrapperPropertiesClassName)
                            .addCode(
                                CodeBlock.builder()
                                    .apply {
                                        classNames.forEach { name ->
                                            addStatement("this.%N = %N(this)", name.simpleName, name.simpleName)
                                        }
                                    }
                                    .build()
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
                                    ParameterSpec.builder(it.simpleName, it)
                                        .defaultValue("%N.%N", "delegate", it.simpleName)
                                        .build()
                                }
                            )
                            .addStatement("this.%N = %N.%N", "properties", "delegate", "properties")
                            .addCode(
                                CodeBlock.builder()
                                    .apply {
                                        classNames.forEach { name ->
                                            addStatement("this.%N = %N", name.simpleName, name.simpleName)
                                        }
                                    }
                                    .build()
                            )
                            .build()
                    )
                    .addType(
                        TypeSpec.companionObjectBuilder()
                            .superclass(ClassName("dev.luna5ama.glwrapper.base", "WrapperManager"))
                            .build()
                    )
                    .build()
            )
            .indent("    ")
            .build()
    }

    private val byteRegex = """(?:\(byte\))?\s?(\d+)""".toRegex()
    private val byteHexRegex = """(?:\(byte\))?\s?0x([0-9a-fA-F]+)""".toRegex()

    private val hexNumRegex = """-?0x([0-9a-fA-F]+)L?""".toRegex()
    private val numRegex = """-?(\d+)L?""".toRegex()
    private val refRegex = """\S+?\.(\S+?)""".toRegex()

    private inner class GLClassData(
        val className: String,
        val typeBuilder: TypeSpec.Builder,
    ) {
        val isGLCore = className.startsWith("GL")

        val topProperties = mutableListOf<PropertySpec>()
        val topFunctions = mutableListOf<FunSpec>()

        fun addConst(type: TypeName, name: String, valueStr: String) {
            fun CodeBlock.Builder.hexStr(value: String): CodeBlock.Builder {
                val longValue = value.toLong()

                return if (longValue < 0) {
                    add("-0x%X".format(-longValue))
                } else {
                    add("0x%X".format(longValue))
                }
            }

            val value = when (type) {
                BYTE -> {
                    byteHexRegex.matchEntire(valueStr)?.let {
                        CodeBlock.Builder()
                            .hexStr(it.groupValues[1])
                            .add(".%N()", "toByte")
                            .build()
                    } ?: byteRegex.matchEntire(valueStr)?.let {
                        CodeBlock.Builder()
                            .hexStr(it.groupValues[1])
                            .add(".%N()", "toByte")
                            .build()
                    } ?: refRegex.matchEntire(valueStr)?.let {
                        CodeBlock.of("%L", it.groupValues[1])
                    } ?: error("Cannot parse byte: $valueStr for $name")
                }
                INT -> {
                    hexNumRegex.matchEntire(valueStr)?.let {
                        CodeBlock.Builder()
                            .hexStr(it.groupValues[1])
                            .add(".%N()", "toInt")
                            .build()
                    } ?: numRegex.matchEntire(valueStr)?.let {
                        CodeBlock.Builder()
                            .add("%L", it.groupValues[1].toInt())
                            .build()
                    } ?: refRegex.matchEntire(valueStr)?.let {
                        CodeBlock.of("%L", it.groupValues[1])
                    } ?: error("Cannot parse int: $valueStr for $name")
                }
                LONG -> {
                    hexNumRegex.matchEntire(valueStr)?.let {
                        CodeBlock.Builder()
                            .hexStr(it.groupValues[1])
                            .add("L")
                            .build()
                    } ?: numRegex.matchEntire(valueStr)?.let {
                        CodeBlock.Builder()
                            .hexStr(it.groupValues[1])
                            .add("L")
                            .build()
                    } ?: refRegex.matchEntire(valueStr)?.let {
                        CodeBlock.of("%L", it.groupValues[1])
                    } ?: error("Cannot parse long: $valueStr for $name")
                }
                else -> error("Unknown type: $type for $name with value $valueStr")
            }

            topProperties.add(
                PropertySpec.builder(name, type)
                    .addModifiers(KModifier.CONST)
                    .initializer(value)
                    .build()
            )
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
            returnType: TypeName,
            params: List<Pair<TypeName, String>>,
            block: FunSpec.Builder.() -> Unit,
        ) {
            fun FunSpec.Builder.addParameters(params: List<Pair<TypeName, String>>): FunSpec.Builder {
                params.forEach { (type, name) ->
                    addParameter(name, type)
                }
                return this
            }

            typeBuilder.addFunction(
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

            topFunctions.add(
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

        fun addFuncWithTopLevel(
            funcName: String,
            returnType: TypeName,
            params: List<Pair<TypeName, String>>
        ) {
            addFuncWithTopLevel(funcName, returnType, params) {
                addModifiers(KModifier.ABSTRACT)
            }
        }

        fun addOverloadFunc(
            funcName: String,
            returnType: TypeName,
            params: List<Pair<TypeName, String>>,
            block: FunSpec.Builder.() -> Unit,
        ) {
            addFuncWithTopLevel(funcName, returnType, params) {
                addAnnotation(coreOverloadAnnotationSpec)
                block()
            }
        }
    }

    private inner class GLClassVisitor : SimpleTreeVisitor<Unit, Unit?>() {
        var classData: GLClassData? = null

        override fun visitClass(node: ClassTree, p: Unit?) {
            val simpleName = node.simpleName.toString()

            try {
                var className = simpleName
                if (simpleName.startsWith("GL")) {
                    val matchResult = glCoreRegex.find(simpleName) ?: return
                    className = "GL${matchResult.groupValues[1]}"
                }

                val typeBuilder = TypeSpec.interfaceBuilder(className)
                    .addSuperinterface(glBaseClassName)

                val ptrParameters = mutableMapOf<String, MutableSet<String>>()
                val ptrReturn = mutableSetOf<String>()

                val classData = GLClassData(className, typeBuilder)

                node.members.forEach { member ->
                    member.accept(object : SimpleTreeVisitor<Unit, Unit?>() {
                        override fun visitVariable(node: VariableTree, p: Unit?) {
                            if (!node.name.startsWith("GL_")) return

                            val vStr = node.initializer.toString()
                            classData.addConst(parseType(node.type), node.name.toString(), vStr)
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
                                    containNativeModifier =
                                        containNativeModifier || node.flags.contains(Modifier.NATIVE)
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

                                classData.addFuncWithTopLevel(funcName, safeReturnType, safeParameters) {
                                    if (isPtrReturn) {
                                        addAnnotation(ptrReturnAnnotationSpec)
                                    }
                                    if (ptrIndices.isNotEmpty()) {
                                        addAnnotation(
                                            AnnotationSpec.builder(
                                                ClassName(
                                                    "dev.luna5ama.glwrapper.base",
                                                    "PtrParameter"
                                                )
                                            )
                                                .addMember("%L", ptrIndices.joinToString(prefix = "[", postfix = "]"))
                                                .build()
                                        )
                                    }
                                    addModifiers(KModifier.ABSTRACT)
                                }

                                if (!isPtrReturn) {
                                    classData.addFuncWithTopLevel(funcName, returnType, parameters) {
                                        addModifiers(KModifier.ABSTRACT)
                                        addUnsafeAnnotation()
                                    }
                                }
                            } else {
                                classData.addFuncWithTopLevel(funcName, returnType, parameters)
                            }
                        }
                    }, null)
                }

                this.classData = classData
            } catch (e: Exception) {
                throw RuntimeException("Error processing class $simpleName", e)
            }
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


    private fun hardcodedOverloads(classData: GLClassData) {
        fun getScalar(funcName: String, orginalName: String, type: TypeName, params: List<Pair<TypeName, String>>) {
            classData.addOverloadFunc(funcName, type, params) {
                addStatement(
                    "%N.%M(%L, %L)",
                    "tempArr",
                    ensureCapacityMemeberName,
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
            classData.addOverloadFunc(funcName, UNIT, params + vparams.map { type to it }) {
                addStatement(
                    "%N.%M(%L, %L)",
                    "tempArr",
                    ensureCapacityMemeberName,
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

        when (classData.className) {
            "GL11" -> {
                classData.addConst(INT, "GL_TEXTURE", "0x1702")

                classData.addOverloadFunc("glGetBoolean", BOOLEAN, listOf(INT to "pname")) {
                    addStatement("%N.%M(%L, %L)", "tempArr", ensureCapacityMemeberName, 4L, false)
                    addStatement("%N(%N, %N.%N)", "glGetBooleanv", "pname", "tempArr", "ptr")
                    addStatement("return %N.%N.%N() != %L", "tempArr", "ptr", "getInt", 0)
                }

                getScalar("glGetInteger", "glGetIntegerv", INT, listOf(INT to "pname"))
                getScalar("glGetFloat", "glGetFloatv", FLOAT, listOf(INT to "pname"))
                getScalar("glGetDouble", "glGetDoublev", DOUBLE, listOf(INT to "pname"))

                classData.addOverloadFunc("glGetPointer", kmogusPtrClassName, listOf(INT to "pname")) {
                    addAnnotation(ptrReturnAnnotationSpec)
                    addStatement("%N.%M(%L, %L)", "tempArr", ensureCapacityMemeberName, 8L, false)
                    addStatement("%N(%N, %N.%N)", "glGetPointerv", "pname", "tempArr", "ptr")
                    addStatement("return %T(%N.%N.%N())", kmogusPtrClassName, "tempArr", "ptr", "getLong")
                }

                classData.typeBuilder.funSpecs.removeIf { it.name == "glGetString" }
                classData.topFunctions.removeIf { it.name == "glGetString" }

                classData.addFuncWithTopLevel("glGetString", STRING.copy(true), listOf(INT to "name"))

                classData.addFuncWithTopLevel("glGenTextures", INT, emptyList())

                classData.addFuncWithTopLevel("glDeleteTextures", UNIT, listOf(INT to "texture"))
            }
            "GL15" -> {
                classData.addFuncWithTopLevel("glDeleteQueries", UNIT, listOf(INT to "query"))
                classData.addFuncWithTopLevel("glDeleteBuffers", UNIT, listOf(INT to "buffer"))
                getScalar("glGetQueryObjecti", "glGetQueryObjectiv", INT, listOf(INT to "id", INT to "pname"))
                getScalar("glGetQueryObjectui", "glGetQueryObjectuiv", INT, listOf(INT to "id", INT to "pname"))
            }
            "GL20" -> {
                getScalar("glGetShaderi", "glGetShaderiv", INT, listOf(INT to "shader", INT to "pname"))
                getScalar("glGetProgrami", "glGetProgramiv", INT, listOf(INT to "program", INT to "pname"))

                classData.addFuncWithTopLevel("glGetShaderInfoLog", STRING, listOf(INT to "shader", INT to "maxLength"))

                classData.addFuncWithTopLevel(
                    "glGetProgramInfoLog",
                    STRING,
                    listOf(INT to "program", INT to "maxLength")
                )

                classData.addFuncWithTopLevel(
                    "glGetUniformLocation",
                    INT,
                    listOf(INT to "program", CHAR_SEQUENCE to "name")
                )

                classData.addFuncWithTopLevel(
                    "glShaderSource",
                    UNIT,
                    listOf(INT to "shader", CHAR_SEQUENCE to "string")
                )
            }
            "GL30" -> {
                classData.addFuncWithTopLevel("glDeleteRenderbuffers", UNIT, listOf(INT to "renderbuffer"))
                classData.addFuncWithTopLevel("glDeleteVertexArrays", UNIT, listOf(INT to "array"))
                classData.addFuncWithTopLevel("glDeleteFramebuffers", UNIT, listOf(INT to "framebuffer"))
            }
            "GL32" -> {
                classData.addOverloadFunc("glGetSynci", INT, listOf(LONG to "sync", INT to "pname")) {
                    addStatement("%N.%M(%L, %L)", "tempArr", ensureCapacityMemeberName, 8L, false)
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
                getScalar("glGetQueryObjecti64", "glGetQueryObjecti64v", LONG, listOf(INT to "id", INT to "pname"))
                getScalar("glGetQueryObjectui64", "glGetQueryObjectui64v", LONG, listOf(INT to "id", INT to "pname"))
                classData.addFuncWithTopLevel("glDeleteSamplers", UNIT, listOf(INT to "sampler"))
            }
            "GL40" -> {
                classData.addFuncWithTopLevel(
                    "glGetSubroutineIndex",
                    INT,
                    listOf(INT to "program", INT to "shadertype", CHAR_SEQUENCE to "name")
                )

                classData.addFuncWithTopLevel("glDeleteTransformFeedbacks", UNIT, listOf(INT to "transformFeedback"))
            }
            "GL41" -> {
                classData.addFuncWithTopLevel("glDeleteProgramPipelines", UNIT, listOf(INT to "pipeline"))
            }
            "GL42" -> {
                classData.addOverloadFunc(
                    "glGetInternalformati",
                    INT,
                    listOf(INT to "target", INT to "internalformat", INT to "pname")
                ) {
                    addStatement("%N.%M(%L, %L)", "tempArr", ensureCapacityMemeberName, 4L, false)
                    addStatement(
                        "%N(%N, %N, %N, %L, %N.%N)",
                        "glGetInternalformativ",
                        "target",
                        "internalformat",
                        "pname",
                        1,
                        "tempArr",
                        "ptr"
                    )
                    addStatement("return %N.%N.%N()", "tempArr", "ptr", "getInt")
                }
                classData.addOverloadFunc(
                    "glGetInternalformati64",
                    LONG,
                    listOf(INT to "target", INT to "internalformat", INT to "pname")
                ) {
                    addStatement("%N.%M(%L, %L)", "tempArr", ensureCapacityMemeberName, 8L, false)
                    addStatement(
                        "%N(%N, %N, %N, %L, %N.%N)",
                        "glGetInternalformati64v",
                        "target",
                        "internalformat",
                        "pname",
                        1,
                        "tempArr",
                        "ptr"
                    )
                    addStatement("return %N.%N.%N()", "tempArr", "ptr", "getLong")
                }
            }
            "GL43" -> {
                classData.addFuncWithTopLevel(
                    "glObjectLabel",
                    UNIT,
                    listOf(INT to "identifier", INT to "name", CHAR_SEQUENCE to "label")
                )

                classData.addFuncWithTopLevel(
                    "glGetProgramResourceIndex",
                    INT,
                    listOf(INT to "program", INT to "programInterface", CHAR_SEQUENCE to "name")
                )

                classData.addFuncWithTopLevel(
                    "glPushDebugGroup",
                    UNIT,
                    listOf(INT to "source", INT to "id", CHAR_SEQUENCE to "message")
                )
            }
            "GL45" -> {
                classData.addFuncWithTopLevel("glCreateBuffers", INT, listOf())

                classData.addFuncWithTopLevel("glCreateRenderbuffers", INT, listOf())

                classData.addFuncWithTopLevel("glCreateSamplers", INT, listOf())

                classData.addFuncWithTopLevel("glCreateFramebuffers", INT, listOf())

                classData.addFuncWithTopLevel("glCreateVertexArrays", INT, listOf())

                classData.addFuncWithTopLevel("glCreateTextures", INT, listOf(INT to "target"))

                classData.addFuncWithTopLevel("glCreateProgramPipelines", INT, listOf())

                classData.addFuncWithTopLevel("glCreateQueries", INT, listOf(INT to "target"))

                classData.addFuncWithTopLevel("glCreateTransformFeedbacks", INT, listOf())

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

                classData.topFunctions.removeIf { it.name == "glMapNamedBufferRange" }
                classData.typeBuilder.funSpecs.removeIf { it.name == "glMapNamedBufferRange" }

                classData.addFuncWithTopLevel(
                    "nglMapNamedBufferRange",
                    kmogusPtrClassName,
                    listOf(INT to "buffer", LONG to "offset", LONG to "length", INT to "access")
                ) {
                    addAnnotation(ptrReturnAnnotationSpec)
                    addModifiers(KModifier.ABSTRACT)
                }

                classData.addOverloadFunc(
                    "glMapNamedBufferRange",
                    kmogusArrClassName,
                    listOf(INT to "buffer", LONG to "offset", LONG to "length", INT to "access")
                ) {
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

