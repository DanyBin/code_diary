package test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName TestThreadName
 * @Author bin
 * @Date 2020/9/27 下午4:00
 * @Decr TODO
 * @Link TODO
 **/
public class TestThreadName {

    static ThreadPoolExecutor executorOne = new ThreadPoolExecutor(5,5,1, TimeUnit.MINUTES,new LinkedBlockingQueue<>(),new NamedThreadFactory("TTT"));

    static ThreadPoolExecutor executorTwo = new  ThreadPoolExecutor(5,5,1, TimeUnit.MINUTES,new LinkedBlockingQueue<>(),new NamedThreadFactory("SSS"));

    public static void main(String[] args) {

        //订单模块
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("保存订单的线程");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                throw new NullPointerException();
            }
        },"threadOne");


        //发货模型
        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("保存收获地址的线程");
            }
        },"threadTwo");

        threadOne.start();
        threadTwo.start();



        executorOne.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("接受用户链接线程");
                throw new NullPointerException();
            }
        });

        executorTwo.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("具体处理业务的请求线程");
            }
        });

        executorOne.shutdown();
        executorTwo.shutdown();
    }
}
