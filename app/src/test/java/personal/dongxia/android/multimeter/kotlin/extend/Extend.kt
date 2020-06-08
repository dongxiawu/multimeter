package personal.dongxia.android.multimeter.kotlin.extend

/**
 * @date 2020/4/12
 * @author wudongxia
 */
fun MutableList<Int>.swap(index1: Int, index2: Int) {
    val temp = this[index1]
    this[index1] = this[index2]
    this[index2] = temp
}

fun <T> MutableList<T>.swap2(index1: Int, index2: Int) {
    val tmp = this[index1] // 'this' 指代 list 实例
    this[index1] = this[index2]
    this[index2] = tmp
}

fun test() {
    val i = mutableListOf(1, 2, 3)
    i.swap(1, 2)
}

