package concurrency;

/**
 * @ClassName ThreadLocalTest
 * @Author bin
 * @Date 2020/8/13 下午12:07
 * @Decr TODO
 *      线程本地存储-测试
 * @Link TODO
 **/
public class ThreadLocalTest implements Runnable{
    private int id;
    public ThreadLocalTest(int id) {this.id = id;}


    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            ThreadLocalVarialbleHolder.increment();
            System.out.println(this);
            Thread.yield();
        }
    }

    @Override
    public String toString() {
        return "#" + id + ":" + ThreadLocalVarialbleHolder.get();
    }
}
