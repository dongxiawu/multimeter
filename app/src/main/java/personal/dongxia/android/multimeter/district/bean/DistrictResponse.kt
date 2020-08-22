package personal.dongxia.android.multimeter.district.bean

import androidx.annotation.Keep
import androidx.annotation.StringDef
import com.google.gson.annotations.SerializedName
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * @date 2020/6/27
 * @author wudongxia
 */
@Keep
class DistrictResponse {
    @Status lateinit var status: String
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

    @StringDef(Status.SUCCESS, Status.FAIL)
    @Retention(RetentionPolicy.SOURCE)
    annotation class Status {
        companion object {
            const val SUCCESS = "0"
            const val FAIL = "1"
        }
    }
}