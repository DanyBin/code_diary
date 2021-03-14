package concurrency.example.bank;

/**
 * @ClassName Customer
 * @Author bin
 * @Date 2020/8/24 上午10:39
 * @Decr TODO
 *      定义顾客对象
 * @Link TODO
 **/
public class Customer {
    private final int serviceTime;
    public Customer(int tm) {
        serviceTime = tm;
    }

    public int getServiceTime () { return serviceTime;}

    @Override
    public String toString() {
        return "[" + serviceTime + "]";
    }
}
