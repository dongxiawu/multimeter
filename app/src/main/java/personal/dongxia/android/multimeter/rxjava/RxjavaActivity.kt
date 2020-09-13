package personal.dongxia.android.multimeter.rxjava

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_location.*
import kotlinx.android.synthetic.main.activity_rxjava.*
import org.reactivestreams.Subscriber
import personal.dongxia.android.multimeter.R
import java.util.concurrent.Callable

private const val TAG = "RxjavaActivity"

class RxjavaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rxjava)

        findViewById<Button>(R.id.create_test).setOnClickListener {
            createTest()
        }
        findViewById<Button>(R.id.defer_test).setOnClickListener {
            deferTest()
        }
        findViewById<Button>(R.id.concat_test).setOnClickListener {
            concatMapTest2()
            createTest()
        }
    }

    private fun createTest() {
        // 创建一个可以被观察的对象
        Observable.create(object : ObservableOnSubscribe<Int> {

            override fun subscribe(emitter: ObservableEmitter<Int>) {
                try {
                    if (emitter.isDisposed) {

                    }
                    for (i in 1..4) {
                        emitter.onNext(i)
                    }
                    emitter.onComplete()
                } catch (e: Exception) {
                    emitter.onError(e)
                }
            }
        }).subscribe(object : Observer<Int> {
            override fun onComplete() {
                Log.d(TAG, "onComplete")
            }

            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "onSubscribe")
            }

            override fun onNext(t: Int) {
                Log.d(TAG, "onNext: $t")
            }

            override fun onError(e: Throwable) {
                Log.d(TAG, "onError")
            }
        })
    }

    private fun deferTest() {
        // 创建一个可以被观察的对象
        Observable.defer(object : Callable<ObservableSource<Int>> {
            override fun call(): ObservableSource<Int> {
                return object : ObservableSource<Int> {
                    override fun subscribe(observer: Observer<in Int>) {
                        try {
                            for (i in 1..4) {
                                observer.onNext(i)
                            }
                            observer.onComplete()
                        } catch (e: Exception) {
                            observer.onError(e)
                        }
                    }
                }
            }
        }).subscribe(object : Observer<Int> {
            override fun onComplete() {
                Log.d(TAG, "onComplete")
            }

            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "onSubscribe")
            }

            override fun onNext(t: Int) {
                Log.d(TAG, "onNext: $t")
            }

            override fun onError(e: Throwable) {
                Log.d(TAG, "onError")
            }
        })
    }

    private fun emptyTest() {
        Observable.empty<Int>().subscribe(object : Observer<Int> {
            override fun onComplete() {
                Log.d(TAG, "onComplete")
            }

            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "onSubscribe")
            }

            override fun onNext(t: Int) {
                Log.d(TAG, "onNext: $t")
            }

            override fun onError(e: Throwable) {
                Log.d(TAG, "onError")
            }
        })
    }


    private fun createFun(processor: IntentProcessor): Function<Pair<Boolean, Intent>, ObservableSource<Pair<Boolean, Intent>>> {
        return object : Function<Pair<Boolean, Intent>, ObservableSource<Pair<Boolean, Intent>>> {
            override fun apply(t: Pair<Boolean, Intent>): ObservableSource<Pair<Boolean, Intent>> {
                return Observable.create(object : ObservableOnSubscribe<Pair<Boolean, Intent>> {
                    override fun subscribe(emitter: ObservableEmitter<Pair<Boolean, Intent>>) {
                        processor.process(applicationContext, t.second, object : IntentProcessor.Callback{
                            override fun onCallback(interrupt: Boolean) {
                                emitter.onNext(Pair(interrupt, t.second))
                                emitter.onComplete()
                            }
                        })
                    }
                })
            }
        }
    }

    private fun concatMapTest2() {
        val data = Pair(false, intent)
        Observable.just(data)
                .concatMap(createFun(SyncIntentProcessorImpl()))
                .concatMap(createFun(ASyncIntentProcessorImpl()))
                .concatMap(createFun(SyncIntentProcessorImpl()))
                .concatMap(createFun(ASyncIntentProcessorImpl()))
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(object :Observer<Pair<Boolean, Intent>>{
                    override fun onComplete() {
                        Log.d(TAG, "onComplete")
                    }

                    override fun onSubscribe(d: Disposable) {
                        Log.d(TAG, "onSubscribe")
                    }


                    override fun onError(e: Throwable) {
                        Log.d(TAG, "onError")
                    }

                    override fun onNext(t: Pair<Boolean, Intent>) {
                        Log.d(TAG, "onNext $t")
                    }
                })
    }
}
