package personal.dongxia.android.multimeter.async;

/**
 * @author wudongxia
 * @date 2020/4/15
 */
public interface Callback<T> {
    void onCallback(T result);
}
