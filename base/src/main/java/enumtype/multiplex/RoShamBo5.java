package enumtype.multiplex;

import java.util.EnumMap;

/**
 * @ClassName RoShamBo5
 * @Author bin
 * @Date 2020/8/5 上午11:51
 * @Decr TODO
 *     使用EnumMap实现多路分发
 * @Link TODO
 **/
public enum  RoShamBo5 implements Competitor<RoShamBo5> {

    ROCK,SCISSORS,PAPER;


    static EnumMap<RoShamBo5,EnumMap<RoShamBo5,Outcome>> table = new EnumMap<RoShamBo5, EnumMap<RoShamBo5, Outcome>>(RoShamBo5.class);

    static {
        for(RoShamBo5 it : RoShamBo5.values()){
            table.put(it,
                    new EnumMap<RoShamBo5, Outcome>(RoShamBo5.class));
        }
        ininRow(PAPER,Outcome.DRAW,Outcome.LOSE,Outcome.WIN);
        ininRow(SCISSORS,Outcome.WIN,Outcome.DRAW,Outcome.LOSE);
        ininRow(ROCK,Outcome.LOSE,Outcome.WIN,Outcome.DRAW);
    }

    static void ininRow(RoShamBo5 it,Outcome p,Outcome s,Outcome r){
        EnumMap<RoShamBo5,Outcome> row = RoShamBo5.table.get(it);
        row.put(RoShamBo5.PAPER,p);
        row.put(RoShamBo5.SCISSORS,s);
        row.put(RoShamBo5.ROCK,r);
    }


    //多路分发，一次分发了两次
    public Outcome compete(RoShamBo5 competitor) {
        return table.get(this).get(competitor);
    }

    public static void main(String[] args) {
        RoshamBo.play(RoShamBo5.class,10);
    }

}
