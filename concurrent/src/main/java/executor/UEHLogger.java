package executor;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @ClassName UEHLogger
 * @Author bin
 * @Date 2020/6/1 上午11:50
 * @Decr TODO
 *      处理未捕获的异常
 * @Link TODO
 **/
public class UEHLogger implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Logger logger = Logger.getAnonymousLogger();
        logger.log(Level.SEVERE,"Thread terminated with exception : "+ t.getName(),e);
    }
}
