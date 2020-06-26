package personal.dongxia.android.multimeter.page;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class PageItemFactory extends DataSource.Factory<Integer, PageItem> {

    private MutableLiveData<PageItemDataSource> mSourceLiveData =
            new MutableLiveData<>();

    @Override
    public DataSource<Integer, PageItem> create() {
        PageItemDataSource concertDataSource = new PageItemDataSource();
        mSourceLiveData.postValue(concertDataSource);
        return concertDataSource;
    }
}
