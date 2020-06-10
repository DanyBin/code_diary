package thredSynchronize;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * @ClassName SemaphoreDemo
 * @Author bin
 * @Date 2020/4/28 上午10:43
 * @Decr TODO
 *          通过信号量控制容器的最大值。
 * @Link TODO
 **/
public class SemaphoreDemo {
    private final Set<String>  set;
    private final Semaphore sem;

    public SemaphoreDemo(int bound){
        this.set = Collections.synchronizedSet(new HashSet<>());
        sem = new Semaphore(bound);
    }

    public boolean add(String value) throws InterruptedException{
        //获取许可
        sem.acquire();
        boolean wasAdded = false;
        try {
            wasAdded = set.add(value);
            return wasAdded;
        }finally {
            if(!wasAdded){
                //释放许可
                sem.release();
            }
        }
    }

    public boolean remove(String value){
        boolean remove = set.remove(value);
        if(remove){
            sem.release();
        }
        return remove;
    }

}
