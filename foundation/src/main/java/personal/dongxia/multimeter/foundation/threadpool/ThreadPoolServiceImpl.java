package personal.dongxia.multimeter.foundation.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolServiceImpl implements ThreadPoolService {

    private ExecutorService executorService;

    public ThreadPoolServiceImpl() {
        // todo 获取cup核数
        executorService = Executors.newFixedThreadPool(5);
    }

    @Override
    public ExecutorService getExecutorService() {
        return executorService;
    }
}
