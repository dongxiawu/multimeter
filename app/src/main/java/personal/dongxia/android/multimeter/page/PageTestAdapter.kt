package personal.dongxia.android.multimeter.page

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.AsyncPagedListDiffer
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import personal.dongxia.android.multimeter.R
import personal.dongxia.android.multimeter.viewmodel.User

/**
 * @date 2020/6/26
 * @author wudongxia
 */
class PageTestAdapter: PagedListAdapter<PageItem, PageTestViewHolder> {

    constructor() : super(DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageTestViewHolder {
        return PageTestViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))
    }

    override fun onBindViewHolder(holder: PageTestViewHolder, position: Int) {
        val user = getItem(position)
        user?.let {
            holder.tvName.text = it.name
            holder.tvAge.text = it.age.toString()
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PageItem>() {
            override fun areContentsTheSame(oldItem: PageItem, newItem: PageItem): Boolean {
               return oldItem.age == newItem.age && oldItem.name == newItem.name
            }

            override fun areItemsTheSame(oldItem: PageItem, newItem: PageItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}