package concurrency.visibliity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SerialNumberChecker
 * @Author bin
 * @Date 2020/8/13 上午11:09
 * @Decr TODO
 * @Link TODO
 **/
public class SerialNumberChecker {

    private static final int SIZE = 10;
    private static CircularSet set = new CircularSet(1000);

    private static ExecutorService exec = Executors.newCachedThreadPool();

    static class SerialChecker implements Runnable {

        public void run() {
            while (true){
                int serial = SerialNumberGenerator.nextSerialNumber();
                if(set.contains(serial)){
                    System.out.println("Duplicate: " + serial);
                    System.exit(0);
                }
                set.add(serial);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for(int i=0; i< SIZE; i++){
            exec.execute(new SerialChecker());
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
