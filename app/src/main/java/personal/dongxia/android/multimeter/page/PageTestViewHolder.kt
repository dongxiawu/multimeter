package personal.dongxia.android.multimeter.page

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import personal.dongxia.android.multimeter.R

/**
 * @date 2020/6/26
 * @author wudongxia
 */
class PageTestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvName = itemView.findViewById<TextView>(R.id.tv_name)
    val tvAge = itemView.findViewById<TextView>(R.id.tv_age)
}