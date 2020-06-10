package customSynTool;

import javax.annotation.concurrent.ThreadSafe;
import java.nio.BufferOverflowException;

/**
 * @ClassName GrumpyBoundedBuffer
 * @Author bin
 * @Date 2020/6/9 下午12:30
 * @Decr TODO
 *      缓存实现:
 *          将前提条件的失败传递给调用者
 *      问题: 返回给调用者错误，调用者要不断的重试，不能保证FIFO顺序
 * @Link TODO
 **/
@ThreadSafe
public class GrumpyBoundedBuffer<V>  extends BaseBoundedBuffer<V>{

    protected GrumpyBoundedBuffer(int capacity) {
        super(capacity);
    }

    public synchronized void put(V v) throws RuntimeException {
        if(isFull()){
            throw  new RuntimeException("is full");
        }
        doPut(v);
    }

    public synchronized V take(){
        if(isEmpty()){
            throw new RuntimeException("is emtry");
        }
       return take();
    }
}
