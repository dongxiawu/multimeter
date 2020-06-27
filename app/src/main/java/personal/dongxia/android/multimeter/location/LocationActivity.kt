package personal.dongxia.android.multimeter.location

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.RecyclerView
import personal.dongxia.android.multimeter.R
import personal.dongxia.android.multimeter.location.model.Location
import personal.dongxia.android.multimeter.location.request.query
import personal.dongxia.android.multimeter.uikit.widget.recyclerview.DividerItemDecoration

private const val TAG = "LocationActivity"

class LocationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)
        val fragment = LocationFragment.newInstance("0")
        fragment.onItemClickListener = onItemClickListener
        supportFragmentManager.beginTransaction().add(R.id.content, fragment).show(fragment).commit()
    }
    private val onItemClickListener = object : LocationFragment.OnItemClickListener {
        override fun onClick(location: Location) {
            if (location.hasNextLevel()) {
                val fragment = LocationFragment.newInstance(location.id)
                fragment.onItemClickListener = this
                supportFragmentManager.beginTransaction().add(R.id.content, fragment).show(fragment).commit()
            }
        }
    }
}
