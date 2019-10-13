package personal.dongxia.android.framework.service;

/**
 * 通用服务回调
 * @param <T> 成功 model
 * @param <E> 失败 model
 */
public interface ServiceCallback<T, E> extends ServiceSuccessCallback<T>, ServiceFailureCallback<E> {
}
