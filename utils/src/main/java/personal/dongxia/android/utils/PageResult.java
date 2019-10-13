package personal.dongxia.android.utils;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageResult<T> {
    private List<T> modelList;
    private int pageNum;
    private int pageSize;
    private int totalSize;
    private int totalPage;
}
