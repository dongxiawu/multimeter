package com.dongxia.android.permission

import android.content.Context

/**
 * 权限请求拦截器
 * @date 2020/6/27
 * @author wudongxia
 */
interface Interceptor {
    fun beforePermissionRequest(context: Context, permissions: Array<String>, callback: InterceptorCallback)
    fun afterPermissionRequest(context: Context, permissions: Array<String>, callback: InterceptorCallback)
}