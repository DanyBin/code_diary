package enumtype.multiplex;

/**
 * @ClassName RoShamBo3
 * @Author bin
 * @Date 2020/8/5 上午11:40
 * @Decr TODO
 *      通过常量的方法，实现多路分发，
 *
 * @Link TODO
 **/
public enum  RoShamBo3  implements Competitor<RoShamBo3> {

    PAPER {
        public Outcome compete(RoShamBo3 it) {
            switch (it) {
                default:
                case PAPER:return Outcome.DRAW;
                case SCISSORS:return Outcome.LOSE;
                case ROCK:return Outcome.WIN;
            }
        }
    },
    SCISSORS {
        public Outcome compete(RoShamBo3 it) {
            switch (it) {
                default:
                case PAPER:return Outcome.WIN;
                case SCISSORS:return Outcome.DRAW;
                case ROCK:return Outcome.LOSE;
            }
        }
    },
    ROCK {
        public Outcome compete(RoShamBo3 it) {
            switch (it) {
                default:
                case PAPER:return Outcome.LOSE;
                case SCISSORS:return Outcome.WIN;
                case ROCK:return Outcome.DRAW;
            }
        }
    };


    public abstract Outcome compete(RoShamBo3 it);

    public static void main(String[] args) {
        RoshamBo.play(RoShamBo3.class,10);
    }
}
