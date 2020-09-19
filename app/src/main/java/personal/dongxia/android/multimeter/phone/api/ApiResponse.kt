package personal.dongxia.android.multimeter.phone.api

/**
 * 密封类
 * @date 2020/9/19
 * @author wudongxia
 */
sealed class ApiResponse<T> {
    companion object {
        fun <T> create(errorCode: String, errorMsg: String): ApiErrorResponse<T> {
            return ApiErrorResponse(errorCode, errorMsg)
        }
        fun <T> create(data: T): ApiSuccessResponse<T> {
            return ApiSuccessResponse(data)
        }
        fun <T> create(): ApiEmptyResponse<T> {
            return ApiEmptyResponse()
        }
    }
}
class ApiEmptyResponse<T> : ApiResponse<T>()

class ApiErrorResponse<T>(val errorCode: String, val errorMsg: String) : ApiResponse<T>()

class ApiSuccessResponse<T>(val data: T): ApiResponse<T>()