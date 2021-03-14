package generics.exception;

import java.util.List;

/**
 * @ClassName Proccessor1
 * @Author bin
 * @Date 2020/7/13 下午7:34
 * @Decr TODO
 * @Link TODO
 **/
public class Proccessor1 implements Proccessor<String,Failure1> {

    static int count = 3;
    public void proccess(List<String> resultCollector) throws Failure1 {
        if(count -- > 1){
            resultCollector.add("hep!");
        }else {
            resultCollector.add("ho!");
        }
        if(count < 0){
            throw new Failure1();
        }
    }
}
