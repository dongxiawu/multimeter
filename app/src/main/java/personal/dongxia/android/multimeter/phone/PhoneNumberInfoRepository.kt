package personal.dongxia.android.multimeter.phone

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import personal.dongxia.android.multimeter.phone.api.ApiResponse
import personal.dongxia.android.multimeter.phone.vo.Resource

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

    fun load(phoneNumber: String): LiveData<Resource<PhoneNumberInfo>> {
        val result = MutableLiveData<Resource<PhoneNumberInfo>>()
        result.postValue(Resource.loading(null))
        val uri = Uri.Builder().scheme(SCHEME).authority(HOST).path(PATH)
                .appendQueryParameter("key", KEY).appendQueryParameter("phone", phoneNumber).build()
        Thread(Runnable {
            val response = OkHttpClient().newCall(Request.Builder().url(uri.toString()).build()).execute()
            if (response.isSuccessful) {
                if (response.body != null) {
                    val bean = Gson().fromJson(response.body?.string(), PhoneNumberInfoApiResponseBean::class.java)
                    if (bean.resultCode == "200" && bean.result != null) {
                        result.postValue(Resource.success(PhoneNumberInfo.from(bean)))
                    } else {
                        result.postValue(Resource.error(response.message, null))
                    }
                } else {
                    // todo 结果为空
                    result.postValue(Resource.error(response.message, null))
                }
            } else {
                result.postValue(Resource.error(response.message, null))
            }
        }).start()
        return result
    }

    fun getPhoneNumberInfo(phoneNumber: String): PhoneNumberInfo = PhoneNumberInfo("浙江", "杭州", "0571", "310000${index++}", "中国移动")

}