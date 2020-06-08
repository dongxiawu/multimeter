package personal.dongxia.android.multimeter.kotlin.`class`

/**
 * @date 2020/4/9
 * @author wudongxia
 */
class ClassTest1 {
}

//Kotlin 中所有的类都有一个共同的超类 Any , 如果类声明时没有指定超类, 则默认为 Any :
//注意: Any 不是 java.lang.Object; 尤其要注意, 除 equals(), hashCode() 和 toString() 之外, 它没有任何成员.
// 类可以这么定义
class ClassTest2

class ClassTest3 constructor(name: String) {

}

// 如果主构造器没有任何注解和可见度修饰符，constructor可以省略
class ClassTest4(name: String) {

}

//主构造器中不能包含任何代码. 初始化代码可以放在 初始化代码段 (initializer block) 中, 初始化代码段使用 init 关键字作为前缀.
//在类的实例初始化过程中, 初始化代码段按照它们在类主体中出现的顺序执行, 初始化代码段之间还可以插入属性的初始化代码:
class ClassTest5(name: String) {
    val firstProperty = "First property: $name".also(::println)

    init {
        println("First initializer block that prints $name")
    }

    val secondProperty = "Second property: ${name.length}".also(::println)

    init {
        println("Second initializer block that prints ${name.length}")
    }

    //    注意, 主构造器的参数可以在初始化代码段中使用. 也可以在类主体定义的属性初始化代码中使用:
    val customerKey = name.toUpperCase()
}

//实际上, Kotlin 有一种简洁语法, 可以通过主构造器来定义属性并初始化属性值:
class ClassTest6(val firstName: String, val lastName: String, var age: Int) {
    companion object {
        const val a = 1
    }

}
//如果构造器有注解, 或者有可见度修饰符, 这时 constructor 关键字是必须的, 注解和修饰符要放在它之前:
//class ClassTest7 public @Inject constructor(name: String) {
//}

//类还可以声明 次级构造器 (secondary constructor), 使用 constructor 关键字作为前缀:
//如果类有主构造器, 那么每个次级构造器都必须委托给主构造器, 要么直接委托, 要么通过其他次级构造器间接委托. 委托到同一个类的另 一个构造器时, 使用 this 关键字实现:
//注意, 初始化代码段中的代码实际上会成为主构造器的一部分. 对主构造器的委托调用, 会作为次级构造器的第一条语句来执行, 因此所有 初始化代码段中代码, 都会在次级构造器的函数体之前执行. 即使类没有定义主构造器, 也会隐含地委托调用主构造器, 因此初始化代码段 仍然会被执行:
class ClassTest8(val name: String) {
    constructor(name: String, age: Int) : this(name) {

    }
}

//如果一个非抽象类没有声明任何主构造器和次级构造器, 它将带有一个自动生成的, 无参数的主构造器. 这个构造器的可见度为 public. 如 果不希望你的类带有 public 的构造器, 你需要声明一个空的构造器, 并明确设置其可见度:
class ClassTest9 private constructor() {
    var a: Any? = null
}

//在 JVM 中, 如果主构造器的所有参数都指定了默认值, 编译器将会产生一个额外的无参数构造器, 这个无参数构造器会使用默 认参数值来调用既有的构造器. 有些库(比如 Jackson 或 JPA) 会使用无参数构造器来创建对象实例, 这个特性将使得 Kotlin 比较容易 与这种库协同工作.
open class ClassTest10(val customerName: String = "") {
    open fun f() {
    }

    open val x = 0;
}

// 在内部类(inner class)的代码中, 可以使用 super 关键字加上外部类名称限定符: super@Outer 来访问外部类(outer class)的超类:
class ClassTest11 : ClassTest10() {
    override fun f() {
        /* ... */
    }

    override val x: Int get() = 0

    inner class Baz {
        fun g() {
            super@ClassTest11.f() // 调用 Foo 类中的 f() 函数实现
            println(super@ClassTest11.x) // 使用 Foo 类中的 x 属性取值方法实现 }
        }
    }
}


//在 Kotlin 中, 类继承中的方法实现问题, 遵守以下规则: 如果一个类从它的直接超类中继承了同一个成员的多个实现, 那么这个子类必须覆 盖这个成员, 并提供一个自己的实现(可以使用继承得到的多个实现中的某一个). 为了表示使用的方法是从哪个超类继承得到的, 我们使用 super 关键字, 将超类名称放在尖括号类, 比如, super<Base> :
open class A {
    open fun f() {
        print("A")
    }

    fun a() {
        print("a")
    }
}

interface B {
    fun f() {
        print("B")
    } // 接口的成员默认是 'open' 的 fun b() { print("b") }
}

class C() : A(), B {
    // 编译器要求 f() 方法必须覆盖:
    override fun f() {
        super<A>.f() // 调用 A.f()
        super<B>.f() // 调用 B.f()
    }
}

open class Base {
    open fun f() {

    }
}
abstract class Derived : Base() {
    override abstract fun f()
}