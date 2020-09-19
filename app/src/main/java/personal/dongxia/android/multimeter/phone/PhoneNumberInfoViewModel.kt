package personal.dongxia.android.multimeter.phone

import androidx.lifecycle.*
import personal.dongxia.android.multimeter.phone.vo.Resource

/**
 * 依赖注入
 * 如果某一个 lifeData 对象依赖其他值，则可以使用 MediatorLiveData 可以观察 其他 lifeData 的 LiveData
 * @date 2020/9/19
 * @author wudongxia
 */
class PhoneNumberInfoViewModel : ViewModel() {
    // todo 这里如何注入比较优雅
    private val repository: PhoneNumberInfoRepository = PhoneNumberInfoRepository()

    private var phoneNumber = MutableLiveData<String?>()

    val phoneNumberInfo: LiveData<Resource<PhoneNumberInfo>> by lazy {
        phoneNumber.switchMap {phoneNumber ->
            if (phoneNumber == null) {
                AbsentLiveData.create()
            } else {
                repository.load(phoneNumber)
            }
        }
    }

    fun setPhoneNumber(phoneNumber: String?) {
        if (this.phoneNumber.value != phoneNumber) {
            this.phoneNumber.value = phoneNumber
        }
    }
}