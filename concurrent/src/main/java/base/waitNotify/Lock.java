package base.waitNotify;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName Lock
 * @Author bin
 * @Date 2019/11/27 下午7:45
 * @Decr TODO
 *      实现等待-通知机制。
 *      通过银行转帐的例子，来理解
 *
 *
 * @Link TODO
 **/
public class Lock {


    public static void main(String[] args) throws InterruptedException {
        Account src = new Account(100);
        Account target = new Account(100);

        CountDownLatch countDownLatch = new CountDownLatch(10);

        for(int i=0;i < 10 ;i ++){
            new Thread(()->{
                src.transactionToTarget(1,target);
                //计数
                countDownLatch.countDown();
            }).start();
        }

        countDownLatch.await();

        System.out.println("src : "+ src.getBanalce());

        System.out.println("target:"+target.getBanalce());


    }


    //账户类
    static class Account{

        //余额
        private Integer banalce ;

        public Account(int banalce){
            this.banalce = banalce;
        }

        /**
         * 转帐方法
         * @param money 金额
         * @param target   转账对象
         */
        public void transactionToTarget(Integer money,Account target){

            //申请资源
            Allocator.getInstance().apply(this,target);

            this.banalce -= money;
            target.setBanalce(target.getBanalce()+money);

            //释放资源
            Allocator.getInstance().release(this,target);

        }

        public Integer getBanalce() {
            return banalce;
        }

        public void setBanalce(Integer banalce) {
            this.banalce = banalce;
        }
    }



    //单例锁
    static class Allocator{
        private List<Account> locks = new ArrayList<>(); //资源管理
        private Allocator(){};

        //静态类，创建单例
        static class AllocatorSingle{
            public static Allocator install = new Allocator();
        }


        //获取对象
        public static Allocator getInstance(){
            return AllocatorSingle.install;
        }

        //一次申请所有资源
        synchronized void apply(Account from,Account to){

            //仅有一个资源时
            while (locks.contains(from) || locks.contains(to)){

                //等待- 释放持有的互斥-资源
                try {
                    wait();
                }catch (InterruptedException e){
                }
            }
            //添加资源
            locks.add(from);
            locks.add(to);
        }

        //归还资源
        synchronized void release(Account from,Account to){
            locks.remove(from);
            locks.remove(to);

            //通知-等待的线程
            notifyAll();
        }
    }

}


