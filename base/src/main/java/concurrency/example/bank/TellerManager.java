package concurrency.example.bank;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName TellerManager
 * @Author bin
 * @Date 2020/8/24 上午11:32
 * @Decr TODO
 *      出纳员的管理员
 * @Link TODO
 **/
public class TellerManager  implements Runnable{
    private ExecutorService exec;
    private CustomerLine customers;


    //工作的出纳员
    private PriorityQueue<Teller> workingTellers = new PriorityQueue<Teller>();
    //在做其它事情的出纳员（空闲）
    private Queue<Teller> tellersDoingOtherThings = new LinkedList<Teller>();

    private int adjustmentPeriod;
    private static Random rand = new Random(47);

    public TellerManager(ExecutorService e,CustomerLine customers,int adjustmentPeriod) {
        exec = e;
        this.customers = customers;
        this.adjustmentPeriod = adjustmentPeriod;

        //创建出纳员，开始工作
        Teller teller = new Teller(customers);
        exec.execute(teller);
        workingTellers.add(teller);
    }

    public void adjustTellerNumber() {

        //这是一个控制系统
        //当顾客/柜员比例 > 2时
        if(customers.size() / workingTellers.size() > 2){
            //如果有在做其它事情的柜员，进行召回
            if(tellersDoingOtherThings.size() > 0) {
                Teller teller = tellersDoingOtherThings.remove();
                //服务客户
                teller.servingCustomerLine();
                workingTellers.offer(teller);
                return;
            }

            //创建一个新的柜员
            Teller teller = new Teller(customers);
            exec.execute(teller);
            workingTellers.add(teller);
            return;
        }

        //如果顾客队列很短，减少柜员
        if(workingTellers.size() > 1 && customers.size() / workingTellers.size() < 2) {
            //没有顾客时
            if(customers.size() == 0) {
                while (workingTellers.size() > 1) {
                    reassignOneTeller();
                }
            }
        }
    }

    //柜员做其它事情
    private void reassignOneTeller() {
        Teller teller = workingTellers.poll();
        teller.doSomethingElse();
        tellersDoingOtherThings.add(teller);
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
                adjustTellerNumber();
                System.out.print(customers + "{");
                for(Teller teller : workingTellers) {
                    System.out.print(teller.shortString() + " ");
                }
                System.out.println("}");
            }
        } catch (InterruptedException e) {
            System.out.println(this + "Interrupted");
        }
        System.out.println(this + "terminating");
    }

    @Override
    public String toString() {
        return "TellerManager ";
    }
}
