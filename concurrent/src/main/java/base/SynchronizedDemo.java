package base;

import org.openjdk.jol.info.ClassLayout;

/**
 * @ClassName SynchronizedDemo
 * @Author bin
 * @Date 2019/11/26 下午7:12
 * @Decr TODO
 *      互斥锁-定义-同一个时刻只有一个线程执行。保证对共享变量的修改是互斥
 *
 * @Link TODO
 **/
public class SynchronizedDemo {

    int count =0;

    //修饰-动态方法
    synchronized void foo(){
        //临界区
    }

    //修饰-静态方法
    synchronized static void bar(){
        //临界区
    }


    //修饰代码块
   Object object =  new Object();

    void  baz(){
        synchronized (object){
            //临界区
        }
    }


    //互斥锁-锁当前对象
    synchronized  void add(){
        int idx =0;
        while (idx++ < 1000) {
            count++;
        }
    }

    //互斥锁-锁当前对象
    synchronized int getCount(){
        return count;
    }

    public static void main(String[] args) {
        SynchronizedDemo lockDemo = new SynchronizedDemo();

        //无锁
        System.out.println(ClassLayout.parseInstance(lockDemo).toPrintable());

        //加锁 - 轻量级锁
        synchronized (lockDemo) {
            System.out.println(ClassLayout.parseInstance(lockDemo).toPrintable());
        }

        lockDemo.weightSyc(lockDemo);


//        Thread th1 = new Thread(() -> {
//
//            lockDemo.add();
//
//        });
//
//        Thread th2 = new Thread(() -> {
//            lockDemo.add();
//        });
//
//        th1.start();
//        th2.start();
//
//        System.out.println(lockDemo.count);
//        System.out.println(lockDemo.getCount());

    }

    public void weightSyc(SynchronizedDemo lockDemo ){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                //加锁
                synchronized (lockDemo) {
                    try {
                        //让线程晚点儿死亡，造成锁的竞争
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                //加锁
                synchronized (lockDemo) {
                    System.out.println("thread2 locking");
                    System.out.println(ClassLayout.parseInstance(lockDemo).toPrintable());
                    try {
                        //让线程晚点儿死亡，造成锁的竞争
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t1.start();
        t2.start();
    }
}
