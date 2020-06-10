package customSynTool;

/**
 * @ClassName SleepyBoundedBuffer
 * @Author bin
 * @Date 2020/6/10 上午11:55
 * @Decr TODO
 *      通过轮训与休眠的实现简单的阻塞
 * @Link TODO
 **/
public class SleepyBoundedBuffer<V> extends BaseBoundedBuffer<V> {
    Long timeOut = 1000L;

    protected SleepyBoundedBuffer(int capacity) {
        super(capacity);
    }

    public void put(V v) throws InterruptedException{
        while (true){
            synchronized (this){
                if(isFull()){
                    doPut(v);
                }
            }
            Thread.sleep(timeOut);
        }
    }

    public V take() throws InterruptedException{
        while (true){
            synchronized (this){
                if(!isEmpty()){
                   return doTake();
                }
            }
            Thread.sleep(timeOut);
        }
    }

}
