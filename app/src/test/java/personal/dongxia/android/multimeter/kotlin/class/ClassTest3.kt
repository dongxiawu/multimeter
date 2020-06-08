package personal.dongxia.android.multimeter.kotlin.`class`

import personal.dongxia.android.multimeter.C

// 大多数情况下, 建议使用包级函数(package-level function)替代静态方法.
fun gtN(): String {
    return ""
}

//— 必须是顶级属性, 或者是一个 object 声明 的成员, 或者是一个 companion object 的成员. — 值被初始化为 String 类型, 或基本类型(primitive type)
//— 不存在自定义的取值方法
const val SUBSYSTEM_DEPRECATED: String = "This subsystem is deprecated"
class ClassTest12 {
    //    field 标识符只允许在属性的访问器函数内使用.
//    如果属性 get/set 方法中的任何一个使用了默认实现, 或者在 get/set 方法的自定义实现中通过 属性自动生成后端域变量.
    var prop: String
        get() {
            return field
        }
        set(value) {
            // 后端域变量
            field = value
        }

    var ppp : String = ""

    // 可以推断出类型，不需要指明
    val result = true

    //
    val simple: Int?

    lateinit var sss : String

    private var _table: Map<String, Int>? = null
    public val table: Map<String, Int>
        get() {
            if (_table == null) {
                _table = HashMap() // 类型参数可以自动推断得到, 不必指定
            }
            return _table ?: throw AssertionError("Set to null by another thread")
        }

    companion object {
        const val SUBSYSTEM_DEPRECATED: String = "This subsystem is deprecated"
    }

    val SUBSYSTEM_DEPRECATED: String = "This subsystem is deprecated"

    constructor() {
        // 变量可以在构造函数中初始化
        simple = 1
        prop = ""
    }
}

object ClassTest55 {
    const val SUBSYSTEM_DEPRECATED: String = "This subsystem is deprecated"
}

fun test() {
    val a = ClassTest12()
    if (a::sss.isLateinit) {

    }
}