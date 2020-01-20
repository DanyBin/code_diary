package base;

/**
 * @ClassName Orderly
 * @Author bin
 * @Date 2019/11/26 下午5:01
 * @Decr TODO
 *      有序性 问题.
 *
 *      由于初始化经过编译，执行路径，
 *      初始化对象的引用地址赋值成功，但该地址的对象未创建。
 *      故在使用该对象时，会抛出 NullPointerException
 *
 * @Link TODO
 **/
public class Orderly {

    static Orderly instance;

    static Orderly getInstance(){
        if(instance != null){
            synchronized (Orderly.class){
                if(instance == null){
                    instance = new Orderly();
                }
            }
        }
        return instance;
    }

    public void printTest(){
        System.out.println("有序性导致多线程的问题");
    }


    public static void main(String[] args) {

        Thread th1 = new Thread(() -> {
            getInstance().printTest();
        });

        Thread th2 = new Thread(() -> {
            getInstance().printTest();
        });


        th1.start();
        th2.start();
    }
}
