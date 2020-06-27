package com.dongxia.android.permission;

import android.content.Context;
import androidx.fragment.app.FragmentActivity;

/**
 * @author wudongxia
 * @date 2020/6/26
 */
public class Permission {
    private String[] requestPermissions;
    private Context context;
    private PermissionCallback onPermissionCallback;
    private Interceptor interceptor;



    private Permission(Builder builder) {
        this.context = builder.context;
        this.interceptor = builder.interceptor;
        this.onPermissionCallback = builder.onPermissionCallback;
        this.requestPermissions = builder.requestPermissions;
    }

    public void execute() {
        if (context instanceof FragmentActivity) {
            //((FragmentActivity)context).getSupportFragmentManager().beginTransaction().add()
        }
    }

    public static class Builder {
        private Context context;
        private String[] requestPermissions;
        private PermissionCallback onPermissionCallback;
        private Interceptor interceptor;

        public Builder setInterceptor(Interceptor interceptor) {
            this.interceptor = interceptor;
            return this;
        }

        public Builder setOnPermissionCallback(PermissionCallback onPermissionCallback) {
            this.onPermissionCallback = onPermissionCallback;
            return this;
        }

        public Builder setContext(Context context) {
            this.context = context;
            return this;
        }

        public Builder setRequestPermissions(String[] requestPermissions) {
            this.requestPermissions = requestPermissions;
            return this;
        }

        public Permission build() {
            return new Permission(this);
        }
    }
}
