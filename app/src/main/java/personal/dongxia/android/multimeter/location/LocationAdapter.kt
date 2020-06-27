package personal.dongxia.android.multimeter.location

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import personal.dongxia.android.multimeter.R
import personal.dongxia.android.multimeter.location.model.Location

/**
 * @date 2020/6/27
 * @author wudongxia
 */
class LocationAdapter: RecyclerView.Adapter<LocationItemViewHolder>() {
    val locationList = ArrayList<Location>()

    override fun onBindViewHolder(holder: LocationItemViewHolder, position: Int) {
        val location = locationList[position]
        holder.tvName.text = location.name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_location, parent, false)
        return LocationItemViewHolder(itemView)
    }

    override fun getItemCount(): Int = locationList.size
}