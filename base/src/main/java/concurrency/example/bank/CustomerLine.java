package concurrency.example.bank;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @ClassName CustomerLine
 * @Author bin
 * @Date 2020/8/24 上午10:41
 * @Decr TODO
 *      定义顾客队列
 * @Link TODO
 **/
public class CustomerLine extends ArrayBlockingQueue<Customer> {
    public CustomerLine(int capacity) {
        super(capacity);
    }

    @Override
    public String toString() {
        if(this.size() == 0) {
            return "[Empty]";
        }

        StringBuilder result = new StringBuilder();
        for(Customer customer : this) {
            result.append(customer);
        }
        return result.toString();
    }
}
