package personal.dongxia.android.framework.service;

public interface ServiceCallback {

    void onServiceRegister(Class<?> clazz, Object service);

    void onServiceUnregister(Class<?> clazz, Object service);
}