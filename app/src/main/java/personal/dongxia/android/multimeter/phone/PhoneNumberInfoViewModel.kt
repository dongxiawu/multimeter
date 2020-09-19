package personal.dongxia.android.multimeter.phone

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * 依赖注入
 * @date 2020/9/19
 * @author wudongxia
 */
class PhoneNumberInfoViewModel : ViewModel() {
    private var phoneNumber: String? = null
    private val repository: PhoneNumberInfoRepository = PhoneNumberInfoRepository()

    val phoneNumberInfo: MutableLiveData<PhoneNumberInfo> by lazy {
        MutableLiveData<PhoneNumberInfo>()
    }

    private fun loadPhoneNumberInfo(phoneNumber: String?) {
        phoneNumberInfo.value = repository.getPhoneNumberInfo("")
    }

    fun setPhoneNumber(phoneNumber: String?) {
        loadPhoneNumberInfo(this.phoneNumber)
//        if (this.phoneNumber != phoneNumber) {
//            this.phoneNumber = phoneNumber
//            loadPhoneNumberInfo(this.phoneNumber)
//        }
    }
}