package personal.dongxia.android.multimeter.ip;

import personal.dongxia.android.business.Ip.model.Ip;
import personal.dongxia.android.framework.mvp.IPresenter;
import personal.dongxia.android.framework.mvp.IView;

public interface IpContract {
    interface View extends IView<Presenter> {
        void showQueryResult(Ip ip);
    }

    interface Presenter extends IPresenter {
        Ip queryIpInfo(String address);
    }
}
