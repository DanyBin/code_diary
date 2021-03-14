package enumtype.multiplex;

/**
 * @ClassName RoShambo4
 * @Author bin
 * @Date 2020/8/5 上午11:46
 * @Decr TODO
 *      基于RoShambo3 的优化简化版本
 * @Link TODO
 **/
public enum  RoShambo4 implements Competitor<RoShambo4>{

    ROCK {
        public Outcome compete(RoShambo4 competitor) {
            return compete(SCISSPRS,competitor);
        }
    },
    SCISSPRS {
        public Outcome compete(RoShambo4 competitor) {
            return compete(PAPER,competitor);
        }
    },
    PAPER {
        public Outcome compete(RoShambo4 competitor) {
            return compete(ROCK,competitor);
        }
    };

    public Outcome compete(RoShambo4 loser,RoShambo4 opponent) {
        return ((opponent == this) ? Outcome.DRAW :
                (opponent == loser) ? Outcome.WIN:Outcome.LOSE);
    }

    public static void main(String[] args) {
        RoshamBo.play(RoShambo4.class,10);
    }
}
