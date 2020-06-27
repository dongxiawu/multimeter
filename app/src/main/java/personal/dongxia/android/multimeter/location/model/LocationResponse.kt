package personal.dongxia.android.multimeter.location.model

import androidx.annotation.Keep

/**
 * @date 2020/6/27
 * @author wudongxia
 */
@Keep
class LocationResponse {
    lateinit var reason: String
    lateinit var result: List<Location>
    var errorCode: Int = 0
}