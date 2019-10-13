package personal.dongxia.multimeter.foundation.base;

/**
 * 通用服务成功回调
 * @param <T> 成功 model
 */
public interface IServiceSuccessCallback<T> {
    void onSuccess(T result);
}
