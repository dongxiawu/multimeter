package personal.dongxia.android.multimeter.viewmodel

/**
 * @date 2020/6/25
 * @author wudongxia
 */
class User(name: String, age: Int) {
    var name: String = ""
    var age: Int = 0
    init {
        this.name = name
        this.age = age
    }

    override fun toString(): String = "User{ age = $age name = $name }"
}