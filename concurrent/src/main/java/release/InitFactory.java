package release;

/**
 * @ClassName InitFactory
 * @Author bin
 * @Date 2020/6/13 下午6:05
 * @Decr TODO
 *
 * @Link TODO
 **/
public class InitFactory {
    private static class ObjectHolder{
        public static Object object = new Object();
    }

    public static Object getInstance(){
        return ObjectHolder.object;
    }

}
