package personal.dongxia.android.framework.service;

public class BaseServiceException extends RuntimeException {

    public int errorCode;
    public String errorMsg;

    public BaseServiceException() {
    }

    public BaseServiceException(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
