package concurrency.cooperation;

/**
 * @ClassName WaitPersion
 * @Author bin
 * @Date 2020/8/18 下午7:56
 * @Decr TODO
 *       生产者与消费者 示例
 * @Link TODO
 **/
public class WaitPersion  implements Runnable{

    private Restaurant restaurant;
    public WaitPersion(Restaurant restaurant) {this.restaurant = restaurant;}
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this){
                    while (restaurant.meal == null){
                        wait();
                    }
                }
                System.out.println("waitperson got " + restaurant.meal);
                synchronized (restaurant.chef) {
                    restaurant.meal = null;
                    restaurant.chef.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
