package personal.dongxia.android.multimeter.kotlin.interfaze

//如果你不指定任何可见度修饰符,默认会使用 public,其含义是,你声明的东西在任何位置都可以访问
//—如果你不指定任何可见度修饰符,默认会使用 public,其含义是,你声明的东西在任何位置都可以访问;
//— 如果你将声明的东西标记为 private , 那么它将只在同一个源代码文件内可以访问;
//— 如果标记为 internal , 那么它将在同一个模块(module)内的任何位置都可以访问;
//— 对于顶级(top-level)声明, protected 修饰符是无效的.
/**
 * @date 2020/4/12
 * @author wudongxia
 */
interface Interface {
    val prop: Int
    val t: String
        get() {return  ""}
    fun test()
    fun test1() {
        print("111")
    }
}

class Child : Interface {
    override val prop: Int
        get() = 1
    override fun test() {
        bar = 2
    }
}

private fun foo() {  } // 只在 example.kt 文件内可访问
public var bar: Int = 5 // 这个属性在任何地方都可以访问
    private set // 但它的设值方法只在 example.kt 文件内可以访问
internal var baz = 6 // 在同一个模块(module)内可以访问