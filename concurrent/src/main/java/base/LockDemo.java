package base;

/**
 * @ClassName LockDemo
 * @Author bin
 * @Date 2019/11/26 下午7:12
 * @Decr TODO
 *      互斥锁-定义-同一个时刻只有一个线程执行。保证对共享变量的修改是互斥
 *
 * @Link TODO
 **/
public class LockDemo {

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
        LockDemo lockDemo = new LockDemo();

        Thread th1 = new Thread(() -> {
            lockDemo.add();
        });

        Thread th2 = new Thread(() -> {
            lockDemo.add();
        });

        th1.start();
        th2.start();

        System.out.println(lockDemo.count);
        System.out.println(lockDemo.getCount());

    }
}
