package concurrency.cooperation;

import concurrency.LiftOff;

import java.util.concurrent.BlockingQueue;

/**
 * @ClassName LiftOffRunner
 * @Author bin
 * @Date 2020/8/19 下午2:58
 * @Decr TODO
 * @Link TODO
 **/
public class LiftOffRunner implements Runnable {
    private BlockingQueue<LiftOff> rockets;


    public LiftOffRunner(BlockingQueue<LiftOff> queue){
        this.rockets = queue;
    }

    public void add(LiftOff lo) {
        try {
            rockets.put(lo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                LiftOff rocket = rockets.take();
                rocket.run();
            }
        } catch (InterruptedException e) {
            System.out.println("Waking from take() ");
        }
        System.out.println("ending ");
    }
}
