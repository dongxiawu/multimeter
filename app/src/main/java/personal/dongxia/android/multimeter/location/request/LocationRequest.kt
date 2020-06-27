package personal.dongxia.android.multimeter.location.request

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import personal.dongxia.android.multimeter.location.model.Location
import personal.dongxia.android.multimeter.location.model.LocationResponse

/**
 * @date 2020/6/27
 * @author wudongxia
 */
private const val APP_KEY = "054053e5108beb7abf26c9f303c9b518"
private const val HOST = "https://apis.juhe.cn/xzqh/query"
private const val ROOT_ID = "0"
private val DEFAULT_EMPTY_LOCATION_RESPONSE =
        Gson().toJson(JSONObject()
                .put("reason", "empty")
                .put("errorCode", -1)
                .put("result", emptyList<Location>()))

fun query(parentId: String = ROOT_ID): LocationResponse {
    val client = OkHttpClient()
    val request = Request.Builder()
            .url("$HOST?fid=$parentId&key=$APP_KEY")
            .build()
    val response = client.newCall(request).execute()
    return Gson().fromJson(response.body?.string() ?: DEFAULT_EMPTY_LOCATION_RESPONSE, LocationResponse::class.java)
}