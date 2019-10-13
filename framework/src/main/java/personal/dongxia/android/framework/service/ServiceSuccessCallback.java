package personal.dongxia.android.framework.service;

/**
 * 通用服务成功回调
 * @param <T> 成功 model
 */
public interface ServiceSuccessCallback<T> {
    void onSuccess(T result);
}
