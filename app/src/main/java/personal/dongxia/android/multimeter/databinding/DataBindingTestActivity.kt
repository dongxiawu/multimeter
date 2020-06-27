
package personal.dongxia.android.multimeter.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.databinding.DataBindingUtil
import personal.dongxia.android.multimeter.R

class DataBindingTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityDataBindingTestBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding_test)
        binding.model = DataBindingModel("test", 1)
        findViewById<Button>(R.id.test).setOnClickListener {
//            binding.model.age
            val model = binding.model
            model!!.age.set(model.age.get() + 1)
//            binding.model.name.set(binding.model.name.get() + "1")
//            binding.model.name.set(binding.model.name.get() + "1")
//            binding.model.age.set(binding.model.age.get() + 1)
        }
    }
}
