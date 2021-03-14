package concurrency.share;

/**
 * @ClassName EvenGenerator
 * @Author bin
 * @Date 2020/8/11 下午3:55
 * @Decr TODO
 * @Link TODO
 **/
public class EvenGenerator extends IntGenerator {
    private int currentEventValue = 0 ;
    public int next() {
        ++ currentEventValue;
        ++ currentEventValue;
        return currentEventValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new EvenGenerator());
    }
}
