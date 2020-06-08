
open class ClassTest {
}
//如果子类有主构造器, 那么可以(而且必须)在主构造器中使用主构造器的参数来初始化基类.
class ClassTest2 : ClassTest()

//如果类没有主构造器, 那么所有的次级构造器都必须使用 super 关键字来初始化基类, 或者委托到另一个构造器, 由被委托的构造器来初 始化基类. 注意, 这种情况下, 不同的次级构造器可以调用基类中不同的构造器:
class ClassTest3 : ClassTest {
    constructor()
}

//与 Java 不同, Kotlin 要求使用明确的修饰符来标识允许被子类覆盖的 成员(我们称之为 open), 而且也要求使用明确的修饰符来标识对超类成员的覆盖
//属性的覆盖方式与方法覆盖类似; 超类中声明的属性在后代类中再次声明时, 必须使用 override 关键字来标记, 而且覆盖后的属性数据类 型必须与超类中的属性数据类型兼容. 可以使用带初始化器的属性来覆盖超类属性, 也可以使用带取值方法(getter)的属性来覆盖.
//但不可以反过来使用一个 val 属性覆盖一个 var 属性. 允许这种覆盖的原因是, val 属性本质上只是定义了一个 get 方法, 使用 var 属性来覆盖它, 只是向后代类中添加了一个 set 方法.
open class ClassTest4 {
    open fun v() {

    }
    fun nv() {

    }
    open val x: Int = 2
        get() {
            return  field
        }
    val y: Int = 2
}

open class ClassTest5() : ClassTest4() {
    override fun v() {

    }
    override val x: Int = 1
}

//当一个子类成员标记了 override 修饰符来覆盖父类成员时, 覆盖后的子类成员本身也将是 open 的, 也就是说, 子类成员可以被自己的子 类再次覆盖. 如果你希望禁止这种再次覆盖, 可以使用 final 关键字:
open class ClassTest6() : ClassTest5() {
    final override fun v() {

    }
}

open class Base(val name: String) {
    init { println("Initializing Base") }
    open val size: Int =
            name.length.also { println("Initializing size in Base: $it") }
}
class Derived(
        name: String,
        val lastName: String
) : Base(name.capitalize().also { println("Argument for Base: $it") }) {
    init { println("Initializing Derived") }
    override val size: Int =
            (super.size + lastName.length).also { println("Initializing size in Derived: $it") }
}

fun main() {
    println("Constructing Derived(\"hello\", \"world\")")
    val d = Derived("hello", "world")
}