package base;

/**
 * @ClassName Visibility
 * @Author bin
 * @Date 2019/11/26 下午4:21
 * @Decr TODO
 *      1. 可见性.
 *      测试由于多核CPU的缓存 与 内存的数据，不一致的，会到数据「可见性」的问题
 *
 *      2. 原子性问题
 * @Link TODO
 **/
public class Visibility {
    private long count = 0;
    public static void main(String[] args) throws  InterruptedException {
        System.out.println(calc());

    }



    public void add(){
        int idx = 0;
        while (idx++ < 10000){
            count += 1;
        }
    }

    public static long calc() throws InterruptedException{
        final Visibility Visibility = new Visibility();

        //创建两个线程，执行add() 操作
        Thread thread1 = new Thread(() -> {
            Visibility.add();
        });


        Thread thread2 = new Thread(() -> {
            Visibility.add();
        });


        //启动线程
        thread1.start();
        thread2.start();

        //等待线程结束
        thread1.join();
        thread2.join();

        return Visibility.count;
    }
}



