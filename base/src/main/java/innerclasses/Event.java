package innerclasses;

/**
 * @ClassName Event
 * @Author bin
 * @Date 2020/7/2 下午3:53
 * @Decr TODO
 * @Link TODO
 **/
public abstract class Event {
    private long eventTime;
    protected  final long delayTime;

    public Event(long delayTime){
        this.delayTime = delayTime;
        start();
    }

    public void start(){
        eventTime = System.nanoTime() +delayTime;
    }
    public Boolean  ready(){
        return System.nanoTime() >= eventTime;
    }

    public abstract void action();
}
