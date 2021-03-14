package generics.exception;

import java.util.List;

/**
 * @ClassName Proccessor
 * @Author bin
 * @Date 2020/7/13 下午7:27
 * @Decr TODO
 * @Link TODO
 **/
public interface Proccessor<T,E extends  Exception> {
    void proccess(List<T> resultCollector) throws E;
}


