package concurrency;

/**
 * @ClassName Joining
 * @Author bin
 * @Date 2020/8/11 下午2:43
 * @Decr TODO
 * @Link TODO
 **/
public class Joining {
    public static void main(String[] args) {
        Sleeper
                s = new Sleeper("s",1500),
                r = new Sleeper("r",1500);
        Joiner
                j1 = new Joiner("j1",s),
                j2 = new Joiner("j2",r);
        r.interrupt();
    }
}
