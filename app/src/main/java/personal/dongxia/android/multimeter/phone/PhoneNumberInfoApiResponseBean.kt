package personal.dongxia.android.multimeter.phone

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * @author wudongxia
 * @date 2020/9/19
 */
class PhoneNumberInfoApiResponseBean : Serializable {
    @SerializedName("resultcode")
    var resultCode: String? = null
    var reason: String? = null
    var result: PhoneNumberInfoBean? = null
    @SerializedName("error_code")
    var errorCode: Int? = null
    class PhoneNumberInfoBean {
        var province: String? = null
        var city: String? = null
        @SerializedName("areacode")
        var areaCode: String? = null
        var zip: String? = null
        var company: String? = null
        var card: String? = null
    }
}