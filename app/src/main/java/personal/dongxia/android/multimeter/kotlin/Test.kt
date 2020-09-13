package personal.dongxia.android.multimeter.kotlin

import android.util.Log

/**
 * kotlin 测试类
 * @date 2020/3/28
 * @author wudongxia
 */
// 定义类，默认是 public 的
class Test {

    constructor() {

    }

    /**
     * 定义函数，有不可空 可空的区别
     * todo 函数默认public的吗
     * 可以用做网络请求接口
     * 默认值
     */
    fun sum(a: Int, b: Int = 0) {
        Log.i()
    }

    /**
     * 返回类型自动推断
     */
    fun sum2(a: Int, b: Int) = a + b

    /**
     * 没有返回类型的函数，Unit可以省略
     */
    fun test(a: Int, b: Int): Unit {
        // 不需要分号结尾
        // val 表示不可变，类似java 的final
        // todo var const val final 都什么情况用
        var a: Int = 1
        // 类型自动推断，但是是不是有坑？
        var b = 2
        // 参数定义时必须指定类型或者赋值
        val c: Int
        // 字符串模板
        var s1 = "a is $a"
        a = 2
        // 用大括号可以使用表达式，并且证明字符串模板是在赋值时替换的
        val s2 = "${s1.replace("is", "was")}, but now is $a"

        // 定义数组的一种方式
        val items = listOf("apple", "banana", "kiwifruit")
        val sets = setOf("apple", "banana", "kiwifruit")
        for (item in items) {
            println(item)
        }
        // 获取 索引
        for (index in items.indices) {
            println("item at $index is ${items[index]}")
        }

        // 判断是否在范围内
        // 闭区间 包括最后一个
        if (a in 1..b) {

        }

        if (a !in 1..b) {

        }

        for (a in 1..10 step 2) {

        }

        // 开区间，不包括最后一个
        for (a in 1 until 10) {

        }

        for (a in 10 downTo 1 step 3) {

        }

        //lambda 表达式
        items.filter { it.startsWith("a") }.sortedBy { it }.map { it.toLowerCase() }.forEach{ println(it) }

        val customer: Customer = Customer("11", "22")

        val customer2 = customer.copy()
    }

    // 条件表达式 是否可以替换三目运算符
    fun max(a: Int, b: Int) = if (a > b) a else b

    // 还能这样做 解析整数
    fun parseInt(a: String): Int? {
        return a.toIntOrNull()
    }

    fun printProduct(arg1: String, arg2: String) {
        val x = parseInt(arg1)
        val y = parseInt(arg2)
        // 直接使用 `x * y` 会导致错误, 因为它们可能为 null.
        if (x != null && y != null) {
            // 在进行过 null 值检查之后, x 和 y 的类型会被自动转换为非 null 变量
            // 这个 println 很像js
            println(x * y)
        } else {
            println("either '$arg1' or '$arg2' is not a number")
        }
        when (arg1) {
            "1" -> print(arg1)
            "2" -> print("2")
        }
    }

    // Any 代替了 Object
    fun getStringLength(obj: Any): Int? {
        // is 代替了 java instanceOf
        if (obj is String) {
            // 在这个分支中, `obj` 的类型会被自动转换为 `String`
            return obj.length
        }
        // 在类型检查所影响的分支之外, `obj` 的类型仍然是 `Any`
        return null
    }

    fun getStringLength2(obj: Any): Int? {
        // is 代替了 java instanceOf
        if (obj !is String) {
            // 在类型检查所影响的分支之外, `obj` 的类型仍然是 `Any`
            return null
        }
        // 在这个分支中, `obj` 的类型会被自动转换为 `String`
        return obj.length
    }

    fun getStringLength3(obj: Any): Int? {
        // 在 `&&` 运算符的右侧, `obj` 的类型会被自动转换为 `String`
        if (obj is String && obj.length > 0) {
            return obj.length
        }
        return null
    }


    fun printLength(obj: Any) {
        // 这个是否可以代替三目表达式
        // 当null时 执行某个语句
        println("'$obj' string length is ${getStringLength(obj) ?: "... err, not a string"} ")


        loop@ for (i in 1..100) {
            for (j in 1..100) {
                if (j > 10) break@loop
            }
        }

        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return // 非局部的返回(non-local return), 直接返回到 foo() 函数的调用者
            print(it) }

        listOf(1, 2, 3, 4, 5).forEach lit@{
            if (it == 3) return@lit // 局部的返回(local return), 返回到 Lambda 表达式的调用者, 也就是, 返回到 forEach 循环
            print(it) }

        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return@forEach // 局部的返回(local return), 返回到 Lambda 表达式的调用者, 也就是, 返回到 forEach 循环
            print(it) }

        listOf(1, 2, 3, 4, 5).forEach(fun(value: Int) {
            if (value == 3) return // 局部的返回(local return), 返回到匿名函数的调用者, 也就是, 返回到 forEach 循环
            print(value) })

        // run 是干嘛用的
        run loop@{
            listOf(1, 2, 3, 4, 5).forEach {
                if (it == 3) return@loop // 非局部的返回(non-local return), 从传递给 run 函数的 Lambda 表达式中返回 print(it)
            } }
        //
        when (obj) {
            is Int -> obj + 1
            is String -> obj + "1"
            else -> obj
        }

        when (obj) {
            0, 1 -> print("x == 0 or x == 1")
            else -> print("otherwise")
        }

        // 只读map
        val map2 = mapOf("a" to 1, "b" to 2)

        // map 初始化
        val map: MutableMap<String, String> = HashMap()
        map.put("1", "1")
        map.put("1", "1")
        map.put("1", "1")

        for ((k, v) in map) {

        }

        val list: MutableList<Pair<String, String>> = ArrayList()
        list.add(Pair("1", "2"))
        list.add(Pair("1", "2"))
        list.add(Pair("1", "2"))
        list.add(Pair("1", "2"))
        for ((k, v) in list) {

        }
    }

    // when 可以替代 switch
    fun describe(obj: Any): String =
            when (obj) {
                1 -> "One"
                "Hello" -> "Greeting"
                is Long -> "Long"
                !is String -> "Not a string"
                else -> "Unknown"
            }

}