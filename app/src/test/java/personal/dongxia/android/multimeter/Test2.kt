package personal.dongxia.android.multimeter

import androidx.annotation.Keep
import org.junit.Test

/**
 * @date 2020/3/28
 * @author wudongxia
 */

lateinit var subject: String

fun test() {
    // todo
//    if (subject.isInitialized) {
//    }
}

class Test2 {
    constructor(name: String, email: String) {
        println(name2)
    }
    companion object {
        const val name2 = "sss"
    }
    var stringRepresentation: String
        get() = this.toString()
        set(value) {
            // 解析字符串内容, 并将解析得到的值赋给对应的其他属性
        }
    var counter = 0 // 注意: 这里的初始化代码直接赋值给后端域变量
    set(value) {
        if (value >= 0) field = value
    }

    private var _table: Map<String, Int>? = null
    public val table: Map<String, Int>
        get() {
            if (_table == null) {
                _table = HashMap() // 类型参数可以自动推断得到, 不必指定
            }
            return _table ?: throw AssertionError("Set to null by another thread")
        }
}


class Test3()

class Test4 constructor(val name: String)

class Test5(val name: String)

class Test6 constructor(name: String) {

    val firstProperty = "First property: $name".also(::println)
    // 初始化代码段 可以有多个 按照顺序执行
    // 初始化代码段中的代码实际上会成为主构造器的一部分
    init {
        println("First initializer block that prints $name")
    }
    val secondProperty = "Second property: ${name.length}".also(::println)
    init {
        println("Second initializer block that prints ${name.length}")
    }

    // 次级构造器
    constructor(name: String, email: String): this(name) {

    }
    // 初始化代码段
    init {

    }
}

class Customer public @Keep constructor(name: String) {

}

// open 关键字作用
// 只有open的类才能被继承和重写
open class Base(p: Int) {
    open fun v() {  }
    fun nv() {  }
}
open class Derived(p: Int) : Base(p) {
    override fun v() { }
}

class Derived2(p: Int) : Derived(p) {
    final override fun v() { }
}

interface Foo { val count: Int
}
class Bar1(override val count: Int) : Foo
class Bar2 : Foo {
    override var count: Int = 0
}

//sampleStart
open class Base2(val name: String) {
    init { println("Initializing Base") }
    open val size: Int =
            name.length.also { println("Initializing size in Base: $it") }
}
class Derived3(
        name: String,
        val lastName: String
) : Base2(name.capitalize().also { println("Argument for Base: $it") }) {
    init { println("Initializing Derived") }
    override val size: Int =
            (super.size + lastName.length).also { println("Initializing size in Derived: $it") }
}
//sampleEnd
@Test
fun main() {
    println("Constructing Derived(\"hello\", \"world\")")
    val d = Derived3("hello", "world")
}

open class Foo2 {
    open fun f() { println("Foo.f()") } open val x: Int get() = 1
}

class Bar3 : Foo2() {
    override fun f() { /* ... */ } override val x: Int get() = 0
    // 内部类
    inner class Baz { fun g() {
        super@Bar3.f() // 调用 Foo 类中的 f() 函数实现
        println(super@Bar3.x) // 使用 Foo 类中的 x 属性取值方法实现 }
    } }
}

open class A {
    open fun f() { print("A") } fun a() { print("a") }
}
interface B {
    fun f() { print("B") } // 接口的成员默认是 'open' 的 fun b() { print("b") }
}
class C() : A(), B {
// 编译器要求 f() 方法必须覆盖:
    override fun f() {
        super<A>.f() // 调用 A.f()
        super<B>.f() // 调用 B.f()
    }
}