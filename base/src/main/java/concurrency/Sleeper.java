package concurrency;

/**
 * @ClassName Sleeper
 * @Author bin
 * @Date 2020/8/11 下午2:39
 * @Decr TODO
 * @Link TODO
 **/
public class Sleeper extends Thread {
    private int duration;

    public Sleeper(String name,int sleepTime) {
        super(name);
        this.duration = sleepTime;
        start();
    }

    public void run(){
        try {
            sleep(duration);
        } catch (InterruptedException e) {
            System.out.println(getName() + " was interrupt. " + " isInterrupted() : " + isInterrupted() );
            return;
        }
        System.out.println(getName() + " has awakened");
    }
}
