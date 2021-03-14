package enumtype.map;

import enumtype.set.AlarmPoints;

import java.util.EnumMap;
import java.util.Map;

import static enumtype.set.AlarmPoints.*;

/**
 * @ClassName EnumMaps
 * @Author bin
 * @Date 2020/8/3 上午11:06
 * @Decr TODO
 *         使用EnumMap。其中key 是 enum的每一个实例。
 * @Link TODO
 **/
public class EnumMaps {
    public static void main(String[] args) {
        EnumMap<AlarmPoints,Command> em = new EnumMap<AlarmPoints, Command>(AlarmPoints.class);

        em.put(STATR1, new Command() {
            public void action() {
                System.out.println("STATR1 STATR1");
            }
        });

        em.put(LOBBY, new Command() {
            public void action() {
                System.out.println("LOBBY LOBBY");
            }
        });

        em.put(OFFICE2, new Command() {
            public void action() {
                System.out.println("OFFICE2 OFFICE2");
            }
        });

        for(Map.Entry<AlarmPoints,Command> e : em.entrySet()){
            System.out.println(e.getKey() + ":" );
            e.getValue().action();
        }

        em.get(STATR2).action();
    }
}
