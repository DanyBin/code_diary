package generics.decorator;

import java.util.Date;

/**
 * @ClassName TimeStamped
 * @Author bin
 * @Date 2020/7/13 下午7:55
 * @Decr TODO
 * @Link TODO
 **/
public class TimeStamped extends Decorator {
    private final long timeStamp;

    public TimeStamped(Basic basic) {
        super(basic);
        timeStamp = System.currentTimeMillis();
    }
    public long getStamp(){return timeStamp;}
}
