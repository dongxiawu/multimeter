package personal.dongxia.android.multimeter.async;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author wudongxia
 * @date 2020/4/15
 */
public class AsyncCall {

    private Random random = new Random(System.currentTimeMillis());

    private ExecutorService tp = Executors.newSingleThreadExecutor();

    public void call(BaseDemo demo) {

        new Thread(() -> {
            long res = random.nextInt(10);

            try {
                Thread.sleep(res*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            demo.callback(res);
        }).start();


    }

    //demo1,2,4,5调用方法
    public void call(Callback<Long> callback) {

        new Thread(() -> {
            long res = random.nextInt(10);

            try {
                Thread.sleep(res*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            callback.onCallback(res);
        }).start();


    }

    //demo3调用方法
    public Future<Long> futureCall() {

        return tp.submit(()-> {
            long res = random.nextInt(10);

            try {
                Thread.sleep(res*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return res;
        });

    }

    public void shutdown(){

        tp.shutdown();

    }

}
