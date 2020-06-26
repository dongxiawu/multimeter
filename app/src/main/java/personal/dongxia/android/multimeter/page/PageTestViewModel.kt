package personal.dongxia.android.multimeter.page

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

/**
 * @date 2020/6/26
 * @author wudongxia
 */
class PageTestViewModel: ViewModel() {
    var data: LiveData<PagedList<PageItem>> = MutableLiveData()
//    private var concertDataSource: DataSource<Int, PageItem>? = null

    init {
        val concertFactory = PageItemFactory()
//        concertDataSource = concertFactory.create()
        data = LivePagedListBuilder(concertFactory, 20).build()
    }
}