package lockAPI;

import java.util.concurrent.locks.StampedLock;

/**
 * @ClassName StampLockDemo
 * @Author bin
 * @Date 2019/12/11 下午5:19
 * @Decr TODO
 *      使用StampLock 实现读多写少的场景
 *
 * @Link TODO
 **/
public class StampLockDemo {


    private Double x=0.0,y=0.0;

    //创建锁
    private final StampedLock  sl = new StampedLock();


    //更新原点  写锁操作- 线程安全
    void move(double dx,double dy){
        //获取写锁
        long stamp = sl.writeLock();
        try {
            x += dx;
            y += dy;
        }finally {
            //释放锁
            sl.unlock(stamp);
        }

    }


    //计算到原点的距离
    double distanceFromOrigin(){

        //乐观读
        long stamp = sl.tryOptimisticRead();

        //读取局部变量，数据可能被修改
        double currentX = x, currentY = y;


        //判断执行读操作时，是否存在写操作。如果存在返回 false
        if(!sl.validate(stamp)){

            //升级为悲观读锁
           stamp = sl.readLock();

            try {
                currentX=x;
                currentY=y;

            }finally {
                //释放悲观读锁
                sl.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }


    public static void main(String[] args) {
        StampLockDemo stampLockDemo = new StampLockDemo();

        for(int i=0;i < 100;i++){
            new Thread(()->{
                stampLockDemo.move(1,2);
                System.out.println( stampLockDemo.distanceFromOrigin());
            }).start();
        }

    }

}


