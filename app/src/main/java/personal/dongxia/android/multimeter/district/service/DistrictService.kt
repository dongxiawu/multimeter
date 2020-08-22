package personal.dongxia.android.multimeter.district.service

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import personal.dongxia.android.multimeter.base.Response
import personal.dongxia.android.multimeter.district.bean.District
import personal.dongxia.android.multimeter.district.bean.DistrictResponse

/**
 * @date 2020/6/27
 * @author wudongxia
 */
private const val APP_KEY = "bed8a40d112d76cc5b29d7355c58c713"
private const val HOST = "https://restapi.amap.com/v3/config/district"
// 根节点地址代码，中国
private const val ROOT_ADDRESS_CODE = "100000"

fun querySubDistrictList(parentAddressCode: String = ROOT_ADDRESS_CODE): Response<List<District>> {
    val networkResponse = OkHttpClient().newCall(Request.Builder()
            .url("$HOST?keywords=$parentAddressCode&key=$APP_KEY&subdistrict=1")
            .build())
            .execute()
    val response = Response<List<District>>()
    if (!networkResponse.isSuccessful) {
        response.success = false
        response.errorCode = networkResponse.code.toString()
        response.errorMsg = networkResponse.message
        response.data = emptyList()
        return response
    }
    val originalResult = Gson().fromJson(networkResponse.body?.string(), DistrictResponse::class.java)
    response.success = DistrictResponse.Status.SUCCESS == originalResult.status
    response.errorMsg = originalResult.info
    response.errorCode = originalResult.infoCode
    response.data = originalResult.districts[0].subDistricts
    return response
}