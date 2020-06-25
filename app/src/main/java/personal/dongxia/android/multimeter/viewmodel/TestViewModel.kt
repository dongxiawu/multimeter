package personal.dongxia.android.multimeter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @date 2020/6/25
 * @author wudongxia
 */
class TestViewModel: ViewModel() {
    var text = ""
    var data: MutableLiveData<User> = MutableLiveData()
    var dataList: MutableLiveData<MutableList<User>> = MutableLiveData()
    init {
        data.postValue(User("1", 1))
        dataList.value = ArrayList(1)
    }
    override fun onCleared() {

    }

    fun doSomething() {
        var user = data.value
        user?.let {
            it.name = it.name + "1"
            it.age = it.age + 1
            data.value = it
        }
    }
}