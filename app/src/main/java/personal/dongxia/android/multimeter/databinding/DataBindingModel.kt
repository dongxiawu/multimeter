package personal.dongxia.android.multimeter.databinding

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt

/**
 * @date 2020/6/26
 * @author wudongxia
 */
class DataBindingModel(name: String, age: Int) {
    val name: ObservableField<String> = ObservableField<String>()
    val age: ObservableInt = ObservableInt()
    init {
        this.name.set(name)
        this.age.set(age)
    }

    override fun toString(): String = "User{ age = $age name = $name }"
}