package generics.exception;

/**
 * @ClassName ThrowGenericException
 * @Author bin
 * @Date 2020/7/13 下午7:40
 * @Decr TODO
 * @Link TODO
 **/
public class ThrowGenericException {
    public static void main(String[] args) {
        ProccessRuner<String, Failure1> runner = new ProccessRuner<String, Failure1>();
        for(int i=0; i< 3 ;i ++){
            runner.add(new Proccessor1());
        }

        try {
            System.out.println(runner.processAll());
        } catch (Failure1 failure1) {
            failure1.printStackTrace();
        }

        ProccessRuner<Integer, Failure2> runner2 = new ProccessRuner<Integer, Failure2>();
        for(int i=0; i< 3 ;i ++){
            runner2.add(new Proccessor2());
        }

        try {
            System.out.println(runner2.processAll());
        } catch (Failure2 failure2) {
            failure2.printStackTrace();
        }
    }
}
