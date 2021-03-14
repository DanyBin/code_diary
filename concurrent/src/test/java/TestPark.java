import java.util.concurrent.locks.LockSupport;

/**
 * @ClassName TestPark
 * @Author bin
 * @Date 2020/9/3 上午10:14
 * @Decr TODO
 * @Link TODO
 **/
public class TestPark {
    public void testPark(){
        LockSupport.park(this);
    }

    public static void main(String[] args) {

        String t = "hmart_waimai_dw_ad.xxx";
        String[] split = t.split("\\.");
        for(String s: split) {
            System.out.println(s);
        }
        System.out.println(t.split("."));

    }
}
