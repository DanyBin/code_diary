package concurrency.example.restaurant;

/**
 * @ClassName Order
 * @Author bin
 * @Date 2020/8/24 下午6:59
 * @Decr TODO
 *       订单. 是给服务员的
 * @Link TODO
 **/
public class Order {

    private static int counter = 0;
    private final int id = counter++;

    private final Customer customer;
    private final WaitPerson waitPerson;
    private final Food food;

    public Order(Customer customer,WaitPerson waitPerson,Food food) {
        this.customer = customer;
        this.waitPerson = waitPerson;
        this.food = food;
    }

    public Food item(){return food;}

    public Customer getCustomer(){return customer;}

    public WaitPerson getWaitPerson(){return waitPerson;}

    @Override
    public String toString() {
        return "Order: " + id + " item" + food + " served by:" + waitPerson;
    }
}
