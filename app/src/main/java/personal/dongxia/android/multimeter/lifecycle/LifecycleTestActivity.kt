package personal.dongxia.android.multimeter.lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import personal.dongxia.android.multimeter.R

class LifecycleTestActivity : AppCompatActivity() {
    private lateinit var lifecycleTestComponent: LifecycleTestComponent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle_test)
        lifecycleTestComponent = LifecycleTestComponent(this)
    }
}
