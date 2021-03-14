package generics.exception;

import java.util.List;

/**
 * @ClassName Proccessor2
 * @Author bin
 * @Date 2020/7/13 下午7:38
 * @Decr TODO
 * @Link TODO
 **/
public class Proccessor2 implements Proccessor<Integer,Failure2> {
    static int count = 2;
    public void proccess(List<Integer> resultCollector) throws Failure2 {
        if(count -- == 0){
            resultCollector.add(47);
        }else {
            resultCollector.add(11);
        }
        if(count < 0){
            throw  new Failure2();
        }
    }
}
