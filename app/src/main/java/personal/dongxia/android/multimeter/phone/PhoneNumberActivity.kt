package personal.dongxia.android.multimeter.phone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import personal.dongxia.android.multimeter.R

class PhoneNumberActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_number)
        val fragment = PhoneNumberFragment()
        supportFragmentManager.beginTransaction()
                .add(R.id.root, fragment)
                .show(fragment).commit()
    }
}