package personal.dongxia.android.multimeter.kotlin.`object`

/**
 * @date 2020/4/20
 * @author wudongxia
 */
fun foo() {
    val adHoc = object {
        var x: Int = 0
        var y: Int = 0
    }
    print(adHoc.x + adHoc.y)
}

//只有在局部并且私有的声明范围内, 匿名对象才可以被用作类型. 如果你将匿名对象用作公开函数的返回类型,
// 或者用作公开属性的 类型, 那么这个函数或属性的真实类型会被声明为这个匿名对象的超类, 如果匿名对象没有超类, 则是 Any .
// 在匿名对象中添加的成员将 无法访问
class C {
    // 私有函数, 因此它的返回类型为匿名对象类型
    private fun foo() = object {
        val x: String = "x"
    }

    // 公开函数, 因此它的返回类型为 Any
    fun publicFoo() = object {
        val x: String = "x"
    }

    fun bar() {
        val x1 = foo().x // 正确
//        publicFoo()
//        val x2 = publicFoo().x // 错误: 无法找到 'x'
    }
}

class MyClass1 {
    companion object { }
}

// 虽然同伴对象的成员看起来很像其他语言中的类的静态成员(static member), 但在运行时期,
// 这些成员仍然是真实对象的实例的成 员, 它们与静态成员是不同的, 举例来说, 它还可以实现接口
interface Factory<T> { fun create(): T
}
class MyClass {
    companion object : Factory<MyClass> {
        override fun create(): MyClass = MyClass()
    }
}

class MyClass2 {
    companion object {
        @JvmStatic fun aaaaa() {

        }
    }
}