package concurrency;

/**
 * @ClassName LiftOff
 * @Author bin
 * @Date 2020/8/11 上午10:49
 * @Decr TODO
 * @Link TODO
 **/
public class LiftOff implements Runnable{
    protected int countDown = 10;
    private static int taskCount = 0;
    private final int id = taskCount ++ ;
    public LiftOff(){}
    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    public String status(){
        return "#"+id + "(" + (countDown > 0 ? countDown : "LiftOff!") + "),";
    }
    public void run() {
        while (countDown-- >0){
            System.out.println(status());
            Thread.yield();
        }
    }

    public static void main(String[] args) {
//        LiftOff off = new LiftOff();
//        off.run();


//        Thread t = new Thread(new LiftOff());
//        t.start();
//        System.out.println("waiting for lift off");

        for(int i=0;i < 5; i ++){
            new Thread(new LiftOff()).start();
        }
        System.out.println("waiting for lift off");
    }
}
