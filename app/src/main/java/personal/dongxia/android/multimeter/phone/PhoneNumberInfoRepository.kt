package personal.dongxia.android.multimeter.phone

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * @date 2020/9/19
 * @author wudongxia
 */
class PhoneNumberInfoRepository {
    companion object {
//      http://apis.juhe.cn/mobile/get?phone=13429667914&key=您申请的ApiKey"
        const val KEY = "e9c6579e25371e46198c06ce0b095f45"
        const val SCHEME = "https"
        const val HOST = "apis.juhe.cn"
        const val PATH = "mobile/get"
        var index = 0
    }

    fun loadPhoneNumberInfo(phoneNumber: String): LiveData<PhoneNumberInfo> {
        val uri = Uri.Builder().scheme(SCHEME).authority(HOST).path(PATH)
                .appendQueryParameter("key", KEY).appendQueryParameter("phone", phoneNumber).build()

        val response = OkHttpClient().newCall(Request.Builder().url(uri.toString()).build()).execute()
        val result = MutableLiveData<PhoneNumberInfo>()
        val info = PhoneNumberInfo("浙江", "杭州", "0571", "310000${index++}", "中国移动")
        result.value = info
//        val client = OkHttpClient()
//        val request = Request.Builder().url(uri.toString()).build()
//        var response: Response? = null
//        var result: QueryResult? = null
//        try {
//            response = client.newCall(request).execute()
//            val stringResult = response.body!!.string()
//            result = com.alibaba.fastjson.JSONObject.parseObject(stringResult, QueryResult::class.java)
//            //result.setAddress(address);
//        } catch (e: IOException) {
//            // todo
//        } finally {
//            response?.close()
//        }
        return result
    }

    fun getPhoneNumberInfo(phoneNumber: String): PhoneNumberInfo = PhoneNumberInfo("浙江", "杭州", "0571", "310000${index++}", "中国移动")

}