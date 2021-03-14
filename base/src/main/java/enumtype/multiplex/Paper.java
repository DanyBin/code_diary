package enumtype.multiplex;

/**
 * @ClassName Paper
 * @Author bin
 * @Date 2020/8/4 下午8:02
 * @Decr TODO
 *      布
 * @Link TODO
 **/
public class Paper implements Item {
    public Outcome compete(Item it) {
        return it.eval(this);
    }

    public Outcome eval(Paper p) {
        return Outcome.DRAW;
    }

    public Outcome eval(Scissors s) {
        return Outcome.WIN;
    }

    public Outcome eval(Rock r) {
        return Outcome.LOSE;
    }

    @Override
    public String toString(){return "Paper";}
}
