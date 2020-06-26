package personal.dongxia.android.multimeter.page

/**
 * @date 2020/6/25
 * @author wudongxia
 */
class PageItem(id: Int, name: String, age: Int) {
    var id : Int = 1
    var name: String = ""
    var age: Int = 0
    init {
        this.id = id
        this.name = name
        this.age = age
    }

    override fun toString(): String = "User{ age = $age name = $name }"
}