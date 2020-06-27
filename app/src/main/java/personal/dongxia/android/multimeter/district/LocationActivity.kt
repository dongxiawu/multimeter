package personal.dongxia.android.multimeter.district

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import personal.dongxia.android.multimeter.R
import personal.dongxia.android.multimeter.district.bean.District

private const val TAG = "LocationActivity"

class LocationActivity : AppCompatActivity() {
    private val fragmentList = ArrayList<Fragment>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)
        val fragment = LocationFragment.newInstance("100000")
        fragment.onItemClickListener = onItemClickListener
        fragmentList.add(fragment)
        supportFragmentManager.beginTransaction().add(R.id.content, fragment).show(fragment).commit()
    }
    private val onItemClickListener = object : LocationFragment.OnItemClickListener {
        override fun onClick(district: District) {
            if (district.hasNextLevel()) {
                val currentFragment = fragmentList.last()
                val newFragment = LocationFragment.newInstance(district.addressCode)
                fragmentList.add(newFragment)
                newFragment.onItemClickListener = this
                supportFragmentManager.beginTransaction().add(R.id.content, newFragment).hide(currentFragment).show(newFragment).commit()
            }
        }
    }

    override fun onBackPressed() {
//        super.onBackPressed()
        if (fragmentList.isNotEmpty()) {
            val currentFragment = fragmentList.last()
            fragmentList.remove(currentFragment)
            val preFragment = if (fragmentList.isNotEmpty()) { fragmentList.last() } else { null }
            if (preFragment != null) {
                supportFragmentManager.beginTransaction().remove(currentFragment).show(preFragment).commit()
            } else {
                super.onBackPressed()
            }
        } else {
            super.onBackPressed()
        }
    }
}
