package concurrency;

/**
 * @ClassName UIUnResponsive
 * @Author bin
 * @Date 2020/8/11 下午3:05
 * @Decr TODO
 * @Link TODO
 **/
public class UIUnResponsive {
    private volatile double d = 1;
    public UIUnResponsive() throws Exception{
        while (d > 0) {
            d = d + (Math.PI + Math.E) /d;
        }
        System.in.read();
    }
}
