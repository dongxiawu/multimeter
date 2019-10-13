package personal.dongxia.multimeter.foundation.base;

import java.util.List;


public class PageResult<T> {
    private List<T> modelList;
    private int pageNum;
    private int pageSize;
    private int totalSize;
    private int totalPage;

    public int getPageNum() {
        return pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public List<T> getModelList() {
        return modelList;
    }

    public void setModelList(List<T> modelList) {
        this.modelList = modelList;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }
}
