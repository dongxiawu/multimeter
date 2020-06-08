package personal.dongxia.android.multimeter.async;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

/**
 * 异步转同步测试
 * @author wudongxia
 * @date 2020/4/15
 */
public class Async {

    @Test
    public void call() {
        System.out.println("发起调用");
        AsyncCall asyncCall = new AsyncCall();
        asyncCall.call(new Callback<Long>() {
            @Override
            public void onCallback(Long result) {
                System.out.println("调用结束" +  result);
            }
        });
        System.out.println("调用返回");
    }

    @Test
    public void waitNotify() {
        final Object lock = new Object();
        System.out.println("发起调用");
        AsyncCall asyncCall = new AsyncCall();
        asyncCall.call(new Callback<Long>() {
            @Override
            public void onCallback(Long result) {
                System.out.println("调用结束" +  result);
                synchronized (lock) {
                    lock.notifyAll();
                }
            }
        });
        // 需要同步
        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("调用返回");
    }

    @Test
    public void conditionLock() {
        final Lock lock = new ReentrantLock();
        final Condition con = lock.newCondition();
        System.out.println("发起调用");
        AsyncCall asyncCall = new AsyncCall();
        asyncCall.call(new Callback<Long>() {
            @Override
            public void onCallback(Long result) {
                System.out.println("调用结束" +  result);
                lock.lock();
                try {
                    con.signal();
                } finally {
                    lock.unlock();
                }
            }
        });
        lock.lock();
        try {
            con.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        System.out.println("调用返回");
    }

    @Test
    public void countDownLatch() {
        System.out.println("发起调用");
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        AsyncCall asyncCall = new AsyncCall();
        asyncCall.call(new Callback<Long>() {
            @Override
            public void onCallback(Long result) {
                System.out.println("调用结束" +  result);
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("调用返回");
    }
}
