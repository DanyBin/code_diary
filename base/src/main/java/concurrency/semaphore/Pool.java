package concurrency.semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * @ClassName Pool
 * @Author bin
 * @Date 2020/8/21 下午7:14
 * @Decr TODO
 *      对象池的实现
 * @Link TODO
 **/
public class Pool<T> {
    private int size;
    private List<T> items = new ArrayList<T>();

    //跟踪对象的状态
    private volatile boolean[] checkedOut;
    private Semaphore available;

    public Pool(Class<T> tClass,int size) {
        this.size = size;
        checkedOut = new boolean[size];

        available = new Semaphore(size,true);

        //将对象加入对象池
        for(int i=0; i< size ;i ++){
            try {
                items.add(tClass.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    //获取新的对象
    public T checkOut() throws InterruptedException {
        available.acquire();
        return getTtem();
    }

    //将对象重新写入容器
    public void checkIn(T x){
        if(releaseItem(x)) {
            available.release();
        }
    }

    //获取元素
    private synchronized T getTtem(){
        for(int i=0;i < size; i++){
            if(!checkedOut[i]) {
                checkedOut[i] = true;
                return items.get(i);
            }
        }
        return null;
    }



    private synchronized boolean releaseItem(T item) {
        int index = items.indexOf(item);

        if(index == -1) return false; //没有元素在容器中
        if(checkedOut[index]) {
            checkedOut[index] = false;
            return true;
        }
        return false;
    }
}
