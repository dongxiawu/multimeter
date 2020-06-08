package personal.dongxia.android.multimeter.kotlin.data

/**
 * @date 2020/4/19
 * @author wudongxia
 * — equals() / hashCode() 函数对;
 * — toString() 函数, 输出格式为 "User(name=John, age=42)" ;
 * — componentN() 函数群, 这些函数与类的属性对应, 函数名中的数字 1 到 N, 与属性的声明顺序一致;
 * — copy() 函数 (详情见下文)
 */
data class Data1(val name: String, val age: Int? = null) {
}

val d = Data1("111", 1)
val d2 = d.copy()

val d3 = Pair(d, d2)

sealed class Expr
data class Const(val number: Double) : Expr()
data class Sum(val e1: Expr, val e2: Expr) : Expr()
object NotANumber : Expr()