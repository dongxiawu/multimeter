
package personal.dongxia.android.multimeter.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import personal.dongxia.android.multimeter.R

class ViewModelTestActivity : AppCompatActivity() {
    private lateinit var viewModel: TestViewModel
    private lateinit var tvTest: TextView
    private lateinit var btnText: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model_test)
        tvTest = findViewById(R.id.test)
        btnText = findViewById(R.id.btn_test)
        btnText.setOnClickListener {
            viewModel.doSomething()
        }
        viewModel = ViewModelProvider(this.viewModelStore, ViewModelProvider.NewInstanceFactory()).get(TestViewModel::class.java)
        viewModel.data.observe(this, Observer<User> {
            tvTest.text = it.toString()
        })
    }
}
