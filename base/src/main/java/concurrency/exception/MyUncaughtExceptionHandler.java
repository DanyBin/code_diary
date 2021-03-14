package concurrency.exception;

/**
 * @ClassName MyUncaughtExceptionHandler
 * @Author bin
 * @Date 2020/8/11 下午3:34
 * @Decr TODO
 *          异常处理器
 * @Link TODO
 **/
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("caught " +e );
    }
}
