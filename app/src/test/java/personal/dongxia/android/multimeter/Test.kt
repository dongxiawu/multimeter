package personal.dongxia.android.multimeter

import org.junit.Test
import java.nio.file.Files
import java.nio.file.Paths

// as 作用
import java.nio.file.Paths as bBar

/**
 * @date 2020/3/28
 * @author wudongxia
 * 定义一个类
 */
class Test {
    // lateinit 必须 为 var
    lateinit var b: String

    val c: String by lazy { "1" }

    // 初始化代码
    init {

    }

    @Test
    fun kotlinTest() {
        // 延迟初始化 必须为 val
        val a: String by lazy {
            println("111111")
            "1"
        }
        println(a)
        println(a)
    }

    @Test
    fun extendFunTest() {
        Resource.name
        /**
         * 重写函数
         */
        fun String.spaceToCamelCase() {
            print("11111")
        }
        "Convert this to camelcase".spaceToCamelCase()

        val value: String? = "111"

        // 不为null 时执行某段语句
        value?.let {
            println(it)
        }

        val mapped = value?.let { print(it) } ?: "defaultValueIfValueIsNull"


        val a: Int = 2;
        // try/catch 也可以作为表达式
        val result: Int? = try {
            1
        } catch (e: ArithmeticException) {
            throw IllegalStateException(e)
        }

        // if 作为表达式
        val result2 = if (a == 1) {
            "one"
        } else if (a == 2) {
            "two"
        } else {
            "three"
        }
    }

    // 返回值为 Unit 类型的多个方法, 可以通过 Builder 风格的方式来串联调用
    fun arrayOfMinusOnes(size: Int): IntArray {
        return IntArray(size).apply { fill(-1) }
    }

    // 使用单个表达式来定义一个函数
    fun theAnswer() = 42


    class Turtle {
        fun penDown() {}
        fun penUp() {}
        fun turn(degrees: Double) {}
        fun forward(pixels: Double) {}
    }
    // 在同一个对象实例上调用多个方法(with 语句)
    fun test2() {
        val myTurtle = Turtle()
        with(myTurtle) {
            // 描绘一个边长 100 像素的正方形
            penDown()
            for (i in 1..4) {
                forward(100.0)
                turn(90.0)
            }
            penUp()
        }

        val stream = Files.newInputStream(Paths.get("/some/file.txt"))
        stream.buffered().reader().use { reader ->
            println(reader.readText()) }

        // 交换2个变量的值
        var a = 1
        var b = 2
        a = b.also { b = a }
    }

    @Test
    fun test3() {
        val a = 0x0f
        val b = 0xff
        //– 带符号左移 (等于 Java 的 << )
        println(a shl 1)
        // – 带符号右移 (等于 Java 的 >> )
        println(a shr 1)
        // – 无符号右移 (等于 Java 的 >>> )
        println(a ushr 1)
        // – 按位与(and)
        println(a and b)
        // – 按位或(or)
        println(a or b)
        // – 按位异或(xor)
        println(a xor b)
        //– 按位取反
        println(a.inv())

        val c = true
        val d = false

        println(c && d)
        println(c || d)
        println(!d)
    }

    fun test4() {
        val asc = Array(5){ i -> (i * i).toString() }

        // 原生字符串
        val text = """
            for (c in "foo")
            print(c)
            """
        val text2 = """
|Tell me and I forget. |Teach me and I remember. |Involve me and I learn. |(Benjamin Franklin) """.trimMargin()
    }
}