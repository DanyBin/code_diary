package generics.decorator;

/**
 * @ClassName SerialNumbered
 * @Author bin
 * @Date 2020/7/13 下午7:58
 * @Decr TODO
 * @Link TODO
 **/
public class SerialNumbered extends Decorator {
    private static long counter = 1;
    private final long serialNumber = counter ++ ;
    public SerialNumbered(Basic basic) {
        super(basic);
    }
    public long getSerialNumber(){return serialNumber;}
}
