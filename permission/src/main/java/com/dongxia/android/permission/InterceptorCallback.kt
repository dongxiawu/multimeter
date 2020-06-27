package com.dongxia.android.permission

/**
 * @date 2020/6/27
 * @author wudongxia
 */
interface InterceptorCallback {
    fun setResult(intercepted: Boolean, permissions: Array<String>)
}