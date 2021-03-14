package concurrency.example.bank;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName Teller
 * @Author bin
 * @Date 2020/8/24 上午10:48
 * @Decr TODO
 *      银行出纳员
 * @Link TODO
 **/
public class Teller implements Runnable,Comparable<Teller> {

    private static int counter = 0;
    private final int id = counter ++;

    //客户服务
    private int customersServed = 0;
    private CustomerLine customers;
    //服务客户专线
    private boolean servingCustomerLine = true;

    public Teller(CustomerLine cq) {customers = cq;}

    public void run() {
        try {
            while (!Thread.interrupted()) {
                //接待顾客
                Customer customer = customers.take();
                //服务顾客时间
                TimeUnit.MILLISECONDS.sleep(customer.getServiceTime());
                synchronized (this) {
                    //服务顾客的数量
                    customersServed ++ ;
                    //服务客户完成，释放出纳员，可提供服务
                    while (!servingCustomerLine){
                        wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println(this + "Interrupted");
        }
        System.out.println(this + "terminating");
    }

    //做其它事情
    public synchronized void doSomethingElse(){
        customersServed = 0;
        servingCustomerLine = false;
    }

    //服务顾客
    public synchronized void servingCustomerLine(){
        servingCustomerLine = true;
        notifyAll();
    }

    @Override
    public String toString() {
        return "Teller "  +id + " ";
    }

    public String shortString() {
        return "T" + id;
    }

    //使用优先级队列
    public int compareTo(Teller o) {
        return customersServed < o.customersServed ? -1 :
                (customersServed == o.customersServed) ? 0:1;
    }

}
