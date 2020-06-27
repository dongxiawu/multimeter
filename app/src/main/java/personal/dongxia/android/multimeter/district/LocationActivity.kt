package personal.dongxia.android.multimeter.district

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import personal.dongxia.android.multimeter.R
import personal.dongxia.android.multimeter.district.bean.District

private const val TAG = "LocationActivity"

class LocationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)
        val fragment = LocationFragment.newInstance("100000")
        fragment.onItemClickListener = onItemClickListener
        supportFragmentManager.beginTransaction().add(R.id.content, fragment).show(fragment).commit()
    }
    private val onItemClickListener = object : LocationFragment.OnItemClickListener {
        override fun onClick(district: District) {
            if (district.hasNextLevel()) {
                val fragment = LocationFragment.newInstance(district.addressCode)
                fragment.onItemClickListener = this
                supportFragmentManager.beginTransaction().add(R.id.content, fragment).show(fragment).commit()
            }
        }
    }
}
