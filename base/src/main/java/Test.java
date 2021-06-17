import com.google.common.collect.Lists;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName Test
 * @Author bin
 * @Date 2021/4/14 上午11:50
 * @Decr TODO
 * @Link TODO
 **/
public class Test {

    AtomicInteger atomicInteger;



    public static void main(String[] args) throws ParseException {
        double resource_cxr_16_3 = Double.valueOf("2.2938706E-4");
        double resource_ctr_16_3 = 0.0063278438;


        double resource_cxr_16_1 = Double.valueOf("1.1041909E-4");
        double resource_ctr_16_1 = 0.0063848873;
        System.out.println(String.format("%d_%d",1,2));
        List<Map<Integer,String>> list = Lists.newArrayList();

        double t = 1.0;
        if (t%1 == 0) {
            System.out.println("整数");
        }

        System.out.println(Double.class.isInstance(t));
        String s = String.valueOf((int)t);
        System.out.println(s);
        List<Integer> list1 = Lists.newArrayList();
        list1.add(5);
        list1.add(6);
        list1.add(7);
        Collections.sort(list1, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        System.out.println(list1);
    }

}


