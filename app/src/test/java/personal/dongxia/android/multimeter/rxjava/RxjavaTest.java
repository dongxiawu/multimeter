package personal.dongxia.android.multimeter.rxjava;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.util.Log;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;
import org.junit.Test;

import io.reactivex.Observable;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * @author wudongxia
 * @date 2020/4/7
 */
public class RxjavaTest {
    private static final String TAG = "RxjavaTest";


    private void log(String tag, String msg) {
        System.out.println(tag + " " + msg);
    }

    @Test
    public void createTest() {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                log(TAG, "onSubscribe");
            }

            @Override
            public void onNext(String s) {
                log(TAG, "onNext:" + s);
            }

            @Override
            public void onError(Throwable e) {
                log(TAG, "onError");
            }

            @Override
            public void onComplete() {
                log(TAG, "onComplete");
            }
        };
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        };
        ObservableOnSubscribe<String> observableOnSubscribe = new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("Hello");
                emitter.onNext("World");
            }
        };
        Observable<String> observable = Observable.create(observableOnSubscribe);
        observable.subscribe(observer);
    }


    @Test
    public void flatMapTest() {
        final List<Observable<List<String>>> observableList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            final int k = i;
            observableList.add(Observable.create(emitter -> {
                final List<String> subList = new ArrayList<>();
                for (int j = 0; j < 10; j++) {
                    Thread.sleep(10);
                    subList.add(j + "");
                }
                log(TAG, "onSubNext");
                log(TAG, subList.toString());
                emitter.onNext(subList);
                emitter.onComplete();
                //if (k == 50) {
                //    emitter.onError(new Throwable());
                //} else {
                //    emitter.onNext(subList);
                //    emitter.onComplete();
                //}
            }));
        }
        //Observable.concat(observableList).subscribe();
        Observable.zipIterable(observableList, new Function<Object[], Object>() {
            @Override
            public Object apply(Object[] objects) throws Exception {
                log(TAG, "apply");
                return objects;
            }
        }, true, 10).subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {
                log(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Object o) {
                log(TAG, "onNext");
            }

            @Override
            public void onComplete() {
                log(TAG, "onComplete");
            }

            @Override
            public void onError(Throwable e) {
                log(TAG, "onError");
            }
        });
    }


    @Test
    public void test() {
        final List<String> stringList = new ArrayList<String>();
        final List<Observable<List<String>>> observableList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            observableList.add(create());
        }
    }

    public Observable<List<String>> create() {
        return Observable.create(emitter -> {
            final List<String> stringList = new ArrayList<String>();
            for (int i = 0; i < 10; i++) {
                stringList.add(i + "");
            }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < stringList.size(); i++) {
                        try {
                            Thread.sleep(10);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    emitter.onNext(stringList);
                }
            }).run();
        });
    }
}
