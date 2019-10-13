package personal.dongxia.android.framework.mvp;

import personal.dongxia.android.framework.mvp.IPresenter;

public interface IView<T extends IPresenter> {
    void setPresenter(T presenter);
}
