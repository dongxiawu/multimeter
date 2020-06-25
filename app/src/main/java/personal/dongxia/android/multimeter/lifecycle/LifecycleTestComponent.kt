package personal.dongxia.android.multimeter.lifecycle

import android.content.Context
import android.util.Log
import androidx.lifecycle.*

private const val TAG = "LifecycleTestComponent"

/**
 * @date 2020/6/25
 * @author wudongxia
 */
class LifecycleTestComponent(context: Context): LifecycleObserver {
    init {
        if (context is LifecycleOwner) {
            context.lifecycle.addObserver(this)
            context.lifecycle.addObserver(object : LifecycleEventObserver {
                override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                    Log.d(TAG, "onStateChanged" + event.name)
                }
            })
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        Log.d(TAG, "onCreate")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        Log.d(TAG, "onStart")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        Log.d(TAG, "onResume")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        Log.d(TAG, "onPause")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        Log.d(TAG, "onStop")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        Log.d(TAG, "onDestroy")
    }
}