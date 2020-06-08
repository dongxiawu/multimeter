package personal.dongxia.android.multimeter.kotlin.type

/**
 * @date 2020/4/19
 * @author wudongxia
 * 申明处的类型编译
 */
interface Source<out T> {
    fun next(): T
}

fun test(strs: Source<String>) {
    val objects : Source<Any> = strs
}

interface Compareable<in T> {
    fun compareTo(other: T);
}

fun test2(x: Compareable<Number>) {
    val y: Double = 1.0
    x.compareTo(y)
}

fun copy(from: Array<out Any>, to: Array<Any>) {

}

fun fill(dest: Array<in String>, value: String) {

}