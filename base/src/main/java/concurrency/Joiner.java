package concurrency;

/**
 * @ClassName Joiner
 * @Author bin
 * @Date 2020/8/11 下午2:41
 * @Decr TODO
 * @Link TODO
 **/
public class Joiner extends Thread {
    private Sleeper sleeper;
    public Joiner(String name,Sleeper sleeper){
        super(name);
        this.sleeper = sleeper;
        start();
    }

    public void run(){
        try {
            sleeper.join();
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
        }
        System.out.println(getName() + " join completed");
    }
}
