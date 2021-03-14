package test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @ClassName TestSimpleDataFormat
 * @Author bin
 * @Date 2020/9/27 上午11:51
 * @Decr TODO
 * @Link TODO
 **/
public class TestSimpleDataFormat {


    static ThreadLocal<DateFormat> safeSdf = new ThreadLocal<DateFormat>(){
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };


    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        for(int i = 0; i < 10 ; ++i) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(safeSdf.get().parse("2020-11-12 14:20:11"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }
    }
}
