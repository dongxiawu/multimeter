package personal.dongxia.multimeter.foundation.base;

/**
 * 通用服务返回
 * @param <T>
 * @param <E>
 */
public class ServiceResponse<T, E> {
    private boolean success;
    private T model;
    private E error;

    public void setError(E error) {
        this.error = error;
    }

    public E getError() {
        return error;
    }

    public void setModel(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
}
