package concurrency;

/**
 * @ClassName DualSynch
 * @Author bin
 * @Date 2020/8/13 上午11:55
 * @Decr TODO
 *      在其它对象上同步.
 *
 * @Link TODO
 **/
public class DualSynch {
    private Object syncObject  = new Object();

    //使用this
    public synchronized void f(){
        for(int i=0;i < 5; i++){
            System.out.println("f()");
            Thread.yield();
        }
    }

    public void g(){
        //使用其它对象
        synchronized (syncObject) {
            for(int i=0;i < 5;i ++){
                System.out.println("g()");
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) {
        final  DualSynch ds = new DualSynch();
        new Thread(){
            public void run(){
                ds.f();
            }
        }.start();
        ds.g();
    }
}
