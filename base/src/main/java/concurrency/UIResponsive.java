package concurrency;

import java.io.IOException;

/**
 * @ClassName UIResponsive
 * @Author bin
 * @Date 2020/8/11 下午3:06
 * @Decr TODO
 * @Link TODO
 **/
public class UIResponsive extends Thread {
    private static volatile double d = 1;
    public UIResponsive() {
        setDaemon(true);
        start();
    }
    public void run(){
        while (true){
            d = d + (Math.PI + Math.E) /d;
        }
    }

    public static void main(String[] args) throws Exception {
        new UIUnResponsive();
        //new UIResponsive();
        System.in.read();
        System.out.println(d);

    }
}
