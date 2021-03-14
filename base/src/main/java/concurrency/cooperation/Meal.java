package concurrency.cooperation;

/**
 * @ClassName Meal
 * @Author bin
 * @Date 2020/8/18 下午7:55
 * @Decr TODO
 *      生产者与消费者 示例
 * @Link TODO
 **/
public class Meal {
    private final int orderNum;
    public Meal(int orderNum) {this.orderNum = orderNum;}

    @Override
    public String toString() {
        return "Meal " + orderNum;
    }
}
