package enumtype.multiplex;

/**
 * @ClassName Item
 * @Author bin
 * @Date 2020/8/4 下午7:59
 * @Decr TODO
 *  方法
 *      用作多路分发，
 * @Link TODO
 **/
public interface Item {
    //该方法实现 两路分发的效果
    Outcome compete(Item it);
    //重载eval方法
    Outcome eval(Paper p);
    Outcome eval(Scissors s);
    Outcome eval(Rock r);
}
