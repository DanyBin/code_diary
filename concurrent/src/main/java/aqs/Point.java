package aqs;

import java.util.concurrent.locks.StampedLock;

/**
 * @ClassName Point
 * @Author bin
 * @Date 2020/9/16 下午7:20
 * @Decr TODO
 *      使用StampedLock，不可重入
 * @Link TODO
 **/
public class Point {
    private double x,y;
    private final StampedLock sl = new StampedLock();

    //排它锁- 写锁（wirterLock）
    void move(double  deltaX,double deltaY) {
        long stamp = sl.writeLock();
        try {
            x += deltaX;
            y += deltaY;
        }finally {
            sl.unlockWrite(stamp);
        }
    }

    //  乐观读锁(tryOptimisticRead)
    double distanceFromOrigin(){

        //  1.尝试获取乐观读锁
        long stamp = sl.tryOptimisticRead();

        //  2.将全部变量复制到方法体栈内
        double currentX = x, currentY = y;

        //  3.检查读锁的有效性，防止其它抢占
        if(!sl.validate(stamp)) {

            //4. 如果被强站则获取一个共享读锁（悲观获取）
            stamp = sl.readLock();

            try {
                //5. 将全部变量复制到方法体栈内
                currentX = x;
                currentY = y;
            }finally {
                sl.unlockRead(stamp);
            }
        }

        return Math.sqrt(currentX * currentX + currentY * currentY);
    }

    //使用悲观锁获取读锁，并尝试转换为写锁
    void moveIfAtOrigin(double newX,double newY) {

        //1。获取悲观读锁
        long stamp = sl.readLock();
        try {

            //2 如果当前点在原点则移动
            while (x == 0.0 && y == 0.0 ) {
                //3. 尝试将获取的读锁升级为写锁
                long ws = sl.tryConvertToWriteLock(stamp);

                //4。升级成功，则更新stamp，并设置坐标值，然后推出循环
                if(ws != 0L) {
                    stamp = ws;
                    x = newX;
                    y = newY;
                } else {
                    //5. 读锁升级写锁失败，则释放读锁。显示获取独占写锁，循环重试
                    sl.unlockRead(stamp);
                    stamp = sl.writeLock();
                }
            }
        }finally {
            //6。 释放锁
            sl.unlock(stamp);
        }
    }
}
