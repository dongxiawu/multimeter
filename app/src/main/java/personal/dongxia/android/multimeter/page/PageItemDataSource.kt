package personal.dongxia.android.multimeter.page

import androidx.paging.PositionalDataSource
import java.util.*

/**
 * @date 2020/6/26
 * @author wudongxia
 */
class PageItemDataSource : PositionalDataSource<PageItem>() {
    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<PageItem>) {
        callback.onResult(fetchItems(params.startPosition, params.loadSize))
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<PageItem>) {
        callback.onResult(fetchItems(0, 20), 0, 2000)
    }

    private fun fetchItems(startPosition: Int, pageSize: Int): List<PageItem> {
        val list = ArrayList<PageItem>()
        for (i in startPosition until startPosition + pageSize) {
            val concert = PageItem(i, "name$i", i)
            list.add(concert)
        }
        return list
    }
}