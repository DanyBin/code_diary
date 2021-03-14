package concurrency.visibliity;

/**
 * @ClassName SerialNumberGenerator
 * @Author bin
 * @Date 2020/8/13 上午10:59
 * @Decr TODO
 *      序号生成器.
 * @Link TODO
 **/

public class SerialNumberGenerator {
    private static volatile int serialNumber = 0;
    public static synchronized int nextSerialNumber() {
        return serialNumber ++;
    }
}
