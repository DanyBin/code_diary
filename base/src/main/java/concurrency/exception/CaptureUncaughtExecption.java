package concurrency.exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName CaptureUncaughtExecption
 * @Author bin
 * @Date 2020/8/11 下午3:36
 * @Decr TODO
 * @Link TODO
 **/
public class CaptureUncaughtExecption {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool(new HandlerThreadFactory());
        exec.execute(new ExceptionThread());
    }
}
