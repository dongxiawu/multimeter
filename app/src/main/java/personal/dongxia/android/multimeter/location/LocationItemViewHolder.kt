package personal.dongxia.android.multimeter.location

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import personal.dongxia.android.multimeter.R

/**
 * @date 2020/6/27
 * @author wudongxia
 */
class LocationItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val tvName : TextView = itemView.findViewById(R.id.name)
}