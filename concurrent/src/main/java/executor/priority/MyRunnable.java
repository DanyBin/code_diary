package executor.priority;

/**
 * @ClassName MyRunnable
 * @Author bin
 * @Date 2021/4/6 下午4:25
 * @Decr TODO
 * @Link TODO
 **/
public class MyRunnable implements Runnable {

    private final Runnable runnable;

    public MyRunnable(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void run() {
        this.runnable.run();
    }

    public static MyRunnable get(Runnable runnable) {
        return runnable == null?null:(runnable instanceof MyRunnable?(MyRunnable)runnable:new MyRunnable(runnable));
    }

}
