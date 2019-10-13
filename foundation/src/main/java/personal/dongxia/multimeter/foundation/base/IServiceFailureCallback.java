package personal.dongxia.multimeter.foundation.base;

/**
 * 通用服务失败回调
 * @param <E> 失败 model
 */
public interface IServiceFailureCallback<E> {
    void onError(E error);
}
