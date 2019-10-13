package personal.dongxia.android.multimeter;

import android.app.Application;

import personal.dongxia.android.business.Ip.IpService;
import personal.dongxia.android.business.Ip.IpServiceImpl;
import personal.dongxia.android.framework.bundle.BundlePlatform;

public class MultimeterApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        BundlePlatform.init(this);
        BundlePlatform.getServiceManager().registerGlobalService(IpService.class, new IpServiceImpl());
    }
}
