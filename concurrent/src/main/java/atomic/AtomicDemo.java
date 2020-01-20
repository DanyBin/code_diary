package atomic;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @ClassName AtomicDemo
 * @Author bin
 * @Date 2019/12/17 下午4:25
 * @Decr TODO
 *      原子类-累加器
 * @Link TODO
 **/
public class AtomicDemo {

    AtomicLong count = new AtomicLong(0);

    public void add(){
        for(int i=0;i < 1000;i++){
            count.getAndIncrement();
        }
    }

    public static void main(String[] args) {
        AtomicDemo atomicDemo = new AtomicDemo();
        for(int i=0;i< 10;i++){
            new Thread(()->{
                atomicDemo.add();
            }).start();
        }
        System.out.println(atomicDemo.count);
    }
}
