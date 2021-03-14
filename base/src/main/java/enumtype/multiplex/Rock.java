package enumtype.multiplex;

/**
 * @ClassName Rock
 * @Author bin
 * @Date 2020/8/5 上午10:15
 * @Decr TODO
 *      石头
 * @Link TODO
 *
 **/
public class Rock implements Item {

    public Outcome compete(Item it) {
        return it.eval(this);
    }

    public Outcome eval(Paper p) {
        return Outcome.WIN;
    }

    public Outcome eval(Scissors s) {
        return Outcome.LOSE;
    }

    public Outcome eval(Rock r) {
        return Outcome.DRAW;
    }

    @Override
    public String toString(){return "Rock";}

}
