package personal.dongxia.android.multimeter.district.bean

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 * @date 2020/6/27
 * @author wudongxia
 */
@Keep
class DistrictResponse {
    lateinit var status: String
    lateinit var info: String
    @SerializedName("infocode") lateinit var infoCode: String
    lateinit var count: String
    lateinit var suggestion: Suggestion
    lateinit var districts: List<District>

    companion object {
        class Suggestion {
            lateinit var keywords: List<String>
            lateinit var cities: List<String>
        }
    }
}