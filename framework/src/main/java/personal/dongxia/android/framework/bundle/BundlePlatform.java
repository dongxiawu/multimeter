package personal.dongxia.android.framework.bundle;

import android.app.Application;

import androidx.annotation.NonNull;

import personal.dongxia.android.framework.service.ServiceManager;

public class BundlePlatform {
    private static Application application;
    private static ServiceManager serviceManager;

    public synchronized static void init(@NonNull Application application) {
        if (application != null) {
            // todo 已经初始化过了 抛出异常？
        }
        application = application;
        serviceManager = new ServiceManager();
    }

    public static ServiceManager getServiceManager() {
        return serviceManager;
    }
}
