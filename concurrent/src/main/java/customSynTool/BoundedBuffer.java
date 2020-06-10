package customSynTool;

/**
 * @ClassName BoundedBuffer
 * @Author bin
 * @Date 2020/6/10 下午12:02
 * @Decr TODO
 * @Link TODO
 **/
public class BoundedBuffer<V> extends BaseBoundedBuffer<V> {
    protected BoundedBuffer(int capacity) {
        super(capacity);
    }


    // 阻塞并直到 not-full
    // 通知机制，空变成非空
    public synchronized void put(V v) throws InterruptedException{
        while (isFull())
            wait();
        boolean empty = isEmpty();
        doPut(v);
        if(empty){
            notifyAll();
        }

    }

    //阻塞并直到 not-empty
    //满->非满
    public synchronized V take() throws InterruptedException{
        while (isEmpty())
            wait();
        boolean full = isFull();
        V v = doTake();
        if(full){
            notifyAll();
        }
        return v;
    }
}
