package personal.dongxia.android.multimeter.district.bean

import androidx.annotation.Keep
import androidx.annotation.StringDef
import com.google.gson.annotations.SerializedName
import personal.dongxia.android.multimeter.district.model.LonLatPoint
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * @date 2020/6/27
 * @author wudongxia
 */
@Keep
data class District(@SerializedName("citycode") val cityCode: Any,
                    @SerializedName("adcode") val addressCode: String,
                    @SerializedName("center") val center: String,
                    @SerializedName("name") val name: String,
                    @Level @SerializedName("level") val level: String,
                    @SerializedName("districts") val districts: List<District>) {


    fun getCenterLonLat(): LonLatPoint {
        val result = center.split(",")
        return LonLatPoint(result[0].toFloat(), result[1].toFloat())
    }

    fun hasNextLevel() = level != Level.STREET

    @StringDef(Level.COUNTRY, Level.PROVINCE, Level.CITY, Level.DISTRICT, Level.STREET)
    @Retention(RetentionPolicy.SOURCE)
    annotation class Level {
        companion object {
            /**
             * 国家
             */
            const val COUNTRY = "country"

            /**
             * 省份
             * （直辖市会在province和city显示）
             */
            const val PROVINCE = "province"

            /**
             * 城市
             * （直辖市会在province和city显示）
             */
            const val CITY = "city"

            /**
             * 区县
             */
            const val DISTRICT = "district"

            /**
             * 街道
             */
            const val STREET = "street"
        }
    }
}