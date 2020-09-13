package personal.dongxia.android.multimeter.rxjava

import android.content.Context
import android.content.Intent
import androidx.annotation.IntDef

/**
 * Intent 处理器
 * @author wudongxia
 * @date 2020/9/3
 */
interface IntentProcessor {

    /**
     * 获取目标的tag
     * @return
     */
    fun getTargetHookPoint(): String

    /**
     * 获取processor tag 用于处理优先级
     */
    fun getTag(): String

    /**
     * 是否处理该 intent 如果需要处理
     * @param context 上下文
     * @param intent 待处理的intent
     * @return true 处理该 intent，false 不处理
     */
    fun handleIntent(context: Context, intent: Intent): Boolean

    /**
     * 异步处理该intent
     * @param context 上下文
     * @param intent 待处理的intent
     * @param callback 回调，返回 true 代表拦截，false 代表不拦截 intent 为传递到下一个 processor的 intent
     */
    fun process(context: Context, intent: Intent, callback: Callback)

    /**
     * 处理回调
     */
    interface Callback {
        /**
         * 回调
         * @param interrupt 是否拦截 true 是，不继续处理，false 否，继续处理
         */
        fun onCallback(interrupt: Boolean)
    }
}