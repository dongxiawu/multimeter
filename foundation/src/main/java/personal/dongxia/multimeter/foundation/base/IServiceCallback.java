package personal.dongxia.multimeter.foundation.base;

/**
 * 通用服务回调
 * @param <T> 成功 model
 * @param <E> 失败 model
 */
public interface IServiceCallback<T, E> extends IServiceSuccessCallback<T>, IServiceFailureCallback<E> {
}
