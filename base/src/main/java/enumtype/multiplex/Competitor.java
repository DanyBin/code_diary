package enumtype.multiplex;

/**
 * @ClassName Competitor
 * @Author bin
 * @Date 2020/8/5 上午10:34
 * @Decr TODO
 *      定义：该类型对象可以与另一个Competitor相竞争
 * @Link TODO
 **/
public interface Competitor <T extends Competitor<T>>{
    Outcome compete(T competitor);
}
