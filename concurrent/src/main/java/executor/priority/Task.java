package executor.priority;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @ClassName Task
 * @Author bin
 * @Date 2021/4/6 下午3:38
 * @Decr TODO
 * @Link TODO
 **/
public abstract class Task  implements Runnable,Comparable<Task> {

    private int value;

    public Task(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Task o) {
        Integer left = this.getValue();
        Integer right = this.getValue();
        return left.compareTo(right);
    }

}
