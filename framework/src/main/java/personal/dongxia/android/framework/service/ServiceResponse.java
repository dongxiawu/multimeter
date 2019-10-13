package personal.dongxia.android.framework.service;

import lombok.Getter;
import lombok.Setter;

/**
 * 通用服务返回
 * @param <T>
 * @param <E>
 */
@Setter
@Getter
public class ServiceResponse<T, E> {
    private boolean success;
    private T model;
    private E error;
}
