package enumtype.set;

import java.util.EnumSet;

import static enumtype.set.AlarmPoints.*;

/**
 * @ClassName EnumSets
 * @Author bin
 * @Date 2020/8/3 上午10:54
 * @Decr TODO
 *          使用EnumSet. 创建了一个Enum[] 数组
 * @Link TODO
 **/
public class EnumSets {

    public static void main(String[] args) {

        //创建EnumSet
        EnumSet<AlarmPoints> points = EnumSet.noneOf(AlarmPoints.class);
        points.add(STATR1);
        System.out.println(points);

        points.addAll(EnumSet.of(STATR1,STATR2,LOBBY,OFFICE1));
        System.out.println(points);

        points = EnumSet.allOf(AlarmPoints.class);
        points.removeAll(EnumSet.of(STATR1,STATR2,LOBBY,OFFICE1));
        System.out.println(points);

        points.removeAll(EnumSet.range(OFFICE2,OFFICE4));
        System.out.println(points);

        points= EnumSet.complementOf(points);
        System.out.println(points);
    }
}
