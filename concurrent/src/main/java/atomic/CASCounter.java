package atomic;

/**
 * @ClassName CASCounter
 * @Author bin
 * @Date 2020/6/12 上午10:44
 * @Decr TODO
 *      基于CAS实现线程安全的计数器
 * @Link TODO
 **/
public class CASCounter {
    private SimulatedCAS value;
    public int getValue(){
        return value.get();
    }
    public int incremnt(){
        int v;
        do {
            v =value.get();
        }
        while (v != value.compareAndSwap(v,v+1));
        return v+1;
    }
}
