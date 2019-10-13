package personal.dongxia.android.framework.service;

/**
 * 通用服务失败回调
 * @param <E> 失败 model
 */
public interface ServiceFailureCallback<E> {
    void onError(E error);
}
