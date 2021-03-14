package atomic;

/**
 * @ClassName SimulatedCAS
 * @Author bin
 * @Date 2020/6/11 上午11:19
 * @Decr TODO
 *      模拟CAS操作
 *          1. 需要读写的内存位置V
 *          2. 进行比较的值A
 *          3. 拟写入的新值B
 * @Link TODO
 **/
public class SimulatedCAS {
    private int value;
    public  synchronized int get(){return value;}

    /**
     * 比较并交换操作
     * @param expectedValue 期望值
     * @param newValue      新值
     * @return
     */
    public synchronized int compareAndSwap(int expectedValue,int newValue){
        int oldValue = this.value;
        //如果当前值 等于期望值，值更新变量
        if(oldValue == expectedValue){
            value = newValue;
        }
        //无论操作是否成功，都返回原来的值
        return oldValue;
    }

    public synchronized boolean compareAndSet(int expectedValue,int newValue){
        return (expectedValue == compareAndSwap(expectedValue, newValue));
    }

}
