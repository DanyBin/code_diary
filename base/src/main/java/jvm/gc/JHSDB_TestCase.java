package jvm.gc;


/**
 * @ClassName JHSDB_TestCase
 * @Author bin
 * @Date 2020/11/5 下午3:28
 * @Decr TODO
 *   启动JHSDB 图形化的命令 -  sudo java -cp /Library/Java/JavaVirtualMachines/jdk1.8.0_101.jdk/Contents/Home/lib/sa-jdi.jar sun.jvm.hotspot.HSDB
 *
 *
 * @Link TODO
 **/
public class JHSDB_TestCase {

    static class Test {
        static ObjectHolder staticObject = new ObjectHolder();
        ObjectHolder instanceObj = new ObjectHolder();

        void foo(){
            ObjectHolder localObj = new ObjectHolder();
            System.out.println("done");
        }
    }
    private static class ObjectHolder {};

    public static void main(String[] args) {
        Test test = new JHSDB_TestCase.Test();
        test.foo();

    }
}
