package personal.dongxia.android.multimeter.ip;

import personal.dongxia.android.business.Ip.IpServiceImpl;
import personal.dongxia.android.business.Ip.model.Ip;

public class IpPresenter implements IpContract.Presenter {
    @Override
    public void start() {

    }

    @Override
    public Ip queryIpInfo(String address) {
        return new IpServiceImpl().queryIp(address);
    }
}
