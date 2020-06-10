package customSynTool;

import javax.annotation.concurrent.ThreadSafe;

/**
 * @ClassName ThreadGate
 * @Author bin
 * @Date 2020/6/10 下午8:08
 * @Decr TODO
 *      实现打开与关闭阀门
 *      提供await方法，一直阻塞，直到open 方法中使用notifyAll
 * @Link TODO
 **/
@ThreadSafe
public class ThreadGate {
    private boolean isOpen;
    private int generation;

    public synchronized void close(){
        isOpen = false;
    }

    public synchronized void open(){
        ++generation;
        isOpen =  true;
        notifyAll();
    }

    public synchronized void await() throws InterruptedException {
        int arrivalGeneration = this.generation;
        while (!isOpen && arrivalGeneration == generation ){
            wait();
        }
    }
}
