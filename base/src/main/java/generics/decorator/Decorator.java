package generics.decorator;

/**
 * @ClassName Decorator
 * @Author bin
 * @Date 2020/7/13 下午7:52
 * @Decr TODO
 *      装饰器
 * @Link TODO
 **/
public class Decorator extends Basic {
    protected Basic basic;
    public Decorator(Basic basic){this.basic = basic;}
    @Override
    public void set(String val){basic.set(val);}

    @Override
    public String get(){return basic.get();}
}
