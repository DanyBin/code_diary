package enumtype.multiplex;

/**
 * @ClassName Scissors
 * @Author bin
 * @Date 2020/8/5 上午10:14
 * @Decr TODO
 *      剪刀
 * @Link TODO
 **/
public class Scissors implements Item {
    public Outcome compete(Item it) {
        return it.eval(this);
    }

    public Outcome eval(Paper p) {
        return Outcome.LOSE;
    }

    public Outcome eval(Scissors s) {
        return Outcome.DRAW;
    }

    public Outcome eval(Rock r) {
        return Outcome.WIN;
    }

    @Override
    public String toString(){return "Scissors";}
}
