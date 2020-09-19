package personal.dongxia.android.multimeter.phone.vo

import androidx.annotation.StringDef

/**
 * vo
 * @date 2020/9/19
 * @author wudongxia
 */
data class Resource<T>(@Type val status: String, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Type.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Type.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Type.LOADING, data, null)
        }
    }

    @StringDef(Type.ERROR, Type.LOADING, Type.SUCCESS)
    @kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
    annotation class Type {
        companion object {
            const val SUCCESS = "SUCCESS"
            const val ERROR = "ERROR"
            const val LOADING = "LOADING"
        }
    }
}