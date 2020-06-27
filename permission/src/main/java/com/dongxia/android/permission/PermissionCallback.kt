package com.dongxia.android.permission

import android.content.Context

/**
 * @date 2020/6/27
 * @author wudongxia
 */
interface PermissionCallback {
    fun onPermissionCallback(context: Context, permissions: Array<String>)
}