package personal.dongxia.android.multimeter.location.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 * @date 2020/6/27
 * @author wudongxia
 */
@Keep
data class Location(val id: String,
                    @SerializedName("fid") val parentId: String,
                    @SerializedName("level_id") val levelId: String,
                    val name: String) {
    fun hasNextLevel() = levelId.toInt() < 4
}