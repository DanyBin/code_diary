package enumtype.multiplex;

import static enumtype.multiplex.Outcome.*;

/**
 * @ClassName RoShamBo2
 * @Author bin
 * @Date 2020/8/5 上午10:30
 * @Decr TODO
 *  通过构造器初始化 每个enum实例，并以"一组"结果作为参数。
 * @Link TODO
 **/
public enum  RoShamBo2 implements Competitor<RoShamBo2> {

    PAPER(DRAW,LOSE,WIN),
    SISSORS(WIN,DRAW,LOSE),
    ROCK(LOSE,WIN,DRAW);

    private Outcome vP,vS,vR;

    RoShamBo2(Outcome p,Outcome s,Outcome r){
        this.vP = p;
        this.vS = s;
        this.vR = r;
    }

    @Override
    public Outcome compete(RoShamBo2 it) {
        switch (it) {
            default:
            case PAPER:return vP;
            case SISSORS:return vS;
            case ROCK:return vR;
        }
    }

    public static void main(String[] args) {
        RoshamBo.play(RoShamBo2.class,2);
    }
}
