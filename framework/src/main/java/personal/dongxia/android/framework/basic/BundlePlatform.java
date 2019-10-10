package personal.dongxia.android.framework.basic;

import android.app.Application;

import androidx.annotation.NonNull;

public class BundlePlatform {
    private static Application application;

    public synchronized void init(@NonNull Application application) {
        if (application != null) {
            // todo 已经初始化过了 抛出异常？
        }
        this.application = application;
    }
}
