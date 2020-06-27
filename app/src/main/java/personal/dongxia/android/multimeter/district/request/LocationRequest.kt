package personal.dongxia.android.multimeter.district.request

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import personal.dongxia.android.multimeter.district.bean.District
import personal.dongxia.android.multimeter.district.bean.DistrictResponse

/**
 * @date 2020/6/27
 * @author wudongxia
 */
private const val APP_KEY = "bed8a40d112d76cc5b29d7355c58c713"
private const val HOST = "https://restapi.amap.com/v3/config/district"
private const val ROOT_ID = "0"
private const val ROOT_ADDRESS_CODE = "100000"
private val DEFAULT_EMPTY_LOCATION_RESPONSE =
        Gson().toJson(JSONObject()
                .put("reason", "empty")
                .put("errorCode", 1)
                .put("result", emptyList<District>()))

fun query(addressCode: String = ROOT_ADDRESS_CODE): DistrictResponse {
    val client = OkHttpClient()
    val request = Request.Builder()
            .url("$HOST?keywords=$addressCode&key=$APP_KEY&subdistrict=1")
            .build()
    val response = client.newCall(request).execute()
    val result = Gson().fromJson(response.body?.string(), DistrictResponse::class.java)
//    if (addressCode != ROOT_ADDRESS_CODE) {
//        var districts = result.districts[0]
//        while (districts.addressCode != addressCode) {
//            districts = districts.districts[0]
//        }
//    }
    return result
}