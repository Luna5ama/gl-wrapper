package dev.luna5ama.glwrapper

import dev.luna5ama.kmogus.Arr

abstract class GLWrapper : GLBase {
    final override val tempArr = Arr.malloc(128L)

    abstract val gl11: IGL11
    abstract val gl12: IGL12
    abstract val gl13: IGL13
    abstract val gl14: IGL14
    abstract val gl15: IGL15

    abstract val gl20: IGL20
    abstract val gl21: IGL21

    abstract val gl30: IGL30
    abstract val gl31: IGL31
    abstract val gl32: IGL32
    abstract val gl33: IGL33

    abstract val gl40: IGL40
    abstract val gl41: IGL41
    abstract val gl42: IGL42
    abstract val gl43: IGL43
    abstract val gl44: IGL44
    abstract val gl45: IGL45
    abstract val gl46: IGL46
}