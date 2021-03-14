package thredSynchronize;

/**
 * @ClassName ThreadJoinDemo
 * @Author bin
 * @Date 2019/12/16 下午4:36
 * @Decr TODO
 *      使用join（）方法实现线程同步
 * @Link TODO
 **/
public class ThreadJoinDemo {

    static int x1=0,x2=0;
    public static void main(String[] args) throws InterruptedException{

        for(int i=0; i< 1000; i ++ ){
            Thread t1 = new Thread(() -> {
                x1 ++;
            });

            t1.start();

            Thread t2 = new Thread(() -> {
                x2++;
            });

            t2.start();


            //线程同步
            t1.join();
            t2.join();

            System.out.println("x1="+x1+"   x2="+x2);
        }
    }
}
