package concurrency.interrupt;

/**
 * @ClassName NeedsCleanup
 * @Author bin
 * @Date 2020/8/14 下午4:36
 * @Decr TODO
 * @Link TODO
 **/
public class NeedsCleanup {
    private final int id;

    public NeedsCleanup(int ident) {
        id = ident;
        System.out.println("NeedsCleanup :" + ident);
    }

    public void cleanup() {
        System.out.println("Cleaning up : " + id);
    }
}
