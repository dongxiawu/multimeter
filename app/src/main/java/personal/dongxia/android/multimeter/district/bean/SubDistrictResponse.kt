package personal.dongxia.android.multimeter.district.bean

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 * @date 2020/6/27
 * @author wudongxia
 */
@Keep
class SubDistrictResponse {
    lateinit var status: String
    lateinit var info: String
    @SerializedName("infocode") lateinit var infoCode: String
    lateinit var districts: List<District>
}