package concurrency.exception;

import java.util.concurrent.ThreadFactory;

/**
 * @ClassName HandlerThreadFactory
 * @Author bin
 * @Date 2020/8/11 下午3:35
 * @Decr TODO
 * @Link TODO
 **/
public class HandlerThreadFactory implements ThreadFactory {
    public Thread newThread(Runnable r) {
        System.out.println(this + " create new Thread");
        Thread t = new Thread(r);
        System.out.println("create " + t);
        //设置异常处理器
        t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());

        System.out.println("eh = " + t.getUncaughtExceptionHandler());
        return t;
    }
}
