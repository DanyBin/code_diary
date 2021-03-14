package enumtype.multiplex;

/**
 * @ClassName Outcome
 * @Author bin
 * @Date 2020/8/4 下午7:56
 * @Decr TODO
 *      多路分发
 *          多种交互类型，例如 a.plus(b)时，a 与 b 都不是确定类型，
 *           但Java只能支持一个未知类型的绑定。
 *           可以通过多路分发解决上述问题
 *
 *       OutCome = 为 剪刀、石、布的结果
 * @Link TODO
 **/
public enum  Outcome {
    WIN,LOSE,DRAW
}

