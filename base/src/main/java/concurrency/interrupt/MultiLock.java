package concurrency.interrupt;

/**
 * @ClassName MultiLock
 * @Author bin
 * @Date 2020/8/14 下午3:37
 * @Decr TODO
 *      验证 sync 是可重入的对象锁
 * @Link TODO
 **/
public class MultiLock {

    public synchronized void  f1( int count) {
        if(--count > 0) {
            System.out.println("f1() calling f2() with count :" + count);
            f2(count);
        }
    }

    public synchronized void f2(int count) {
        if(--count > 0) {
            System.out.println("f2() calling f1() with count " + count);
            f1(count);
        }
    }

    public static void main(String[] args) {
        final MultiLock multiLock = new MultiLock();
        new Thread() {
            public void run(){
                multiLock.f1(10);
            }
        }.start();
    }
}
