package generics.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ProccessRuner
 * @Author bin
 * @Date 2020/7/13 下午7:29
 * @Decr TODO
 * @Link TODO
 **/
public class ProccessRuner<T,E extends Exception> extends ArrayList<Proccessor<T,E>> {

    List<T> processAll() throws E {
        ArrayList<T> resultCollector = new ArrayList<T>();
        for(Proccessor<T,E> proccessor:this){
            proccessor.proccess(resultCollector);
        }
        return resultCollector;
    }
}
