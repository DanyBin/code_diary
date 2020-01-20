package base;

/**
 * @ClassName VolatileDemo
 * @Author bin
 * @Date 2019/11/26 下午5:18
 * @Decr TODO
 *      volatile 关键值的测试
 * @Link TODO
 **/
public class VolatileDemo {
    int x=0;
    volatile  boolean v = false;

    public void writer(){
        x = 42;
        v = true;
    }

    public void reader(){
        if(v == true){
            System.out.println(x); // 42
        }
    }

    public static void main(String[] args) {
        VolatileDemo volatileDemo = new VolatileDemo();
        Thread th1 = new Thread(() -> {
            volatileDemo.writer();
        });

        Thread th2 = new Thread(() -> {
            volatileDemo.reader();
        });

        th1.start();
        th2.start();
    }
}
