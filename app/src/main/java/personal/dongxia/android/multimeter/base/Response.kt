package personal.dongxia.android.multimeter.base

import kotlin.properties.Delegates

/**
 * @date 2020/6/29
 * @author wudongxia
 */
class Response<T : Any> {
    var success by Delegates.notNull<Boolean>()
    lateinit var errorCode: String
    lateinit var errorMsg: String
    lateinit var data: T
}