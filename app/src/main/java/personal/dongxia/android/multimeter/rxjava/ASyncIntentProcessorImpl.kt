package personal.dongxia.android.multimeter.rxjava

import android.content.Context
import android.content.Intent
import androidx.annotation.IntDef

/**
 * Intent 处理器
 * @author wudongxia
 * @date 2020/9/3
 */
class ASyncIntentProcessorImpl: IntentProcessorImpl() {

    /**
     * 获取目标的tag
     * @return
     */
    override fun getTargetHookPoint(): String = "111"

    /**
     * 获取processor tag 用于处理优先级
     */
    override fun getTag(): String = "222"

    /**
     * 异步处理该intent
     * @param context 上下文
     * @param intent 待处理的intent
     * @param callback 回调，返回 true 代表拦截，false 代表不拦截 intent 为传递到下一个 processor的 intent
     */
    override fun process(context: Context, intent: Intent, callback: IntentProcessor.Callback) {
        Thread(Runnable {
            Thread.sleep(5000)
            callback.onCallback(false)
        }).start()
    }
}