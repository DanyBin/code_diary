package concurrency.test;

/**
 * @ClassName BaseLine
 * @Author bin
 * @Date 2020/8/25 下午4:58
 * @Decr TODO
 * @Link TODO
 **/
public class BaseLine extends Accumulator {
    {id = "BaseLine";}
    public void accumulate() {
        value += preLoaded[index++];
        if(index > SIZE) index = 0;
    }

    public long read() {
        return value;
    }

}
