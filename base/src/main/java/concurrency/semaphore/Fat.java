package concurrency.semaphore;

/**
 * @ClassName Fat
 * @Author bin
 * @Date 2020/8/21 下午7:24
 * @Decr TODO
 * @Link TODO
 **/
public class Fat {

    private volatile double d;
    private static int counter = 0;
    private final int id = counter ++;

    public Fat(){
        for(int i=0 ;i < 10000; i++){
            d += (Math.PI + Math.E)/i;
        }
    }

    public void operation(){
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Fat id:" + id;
    }
}
