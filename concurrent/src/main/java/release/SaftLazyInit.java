package release;

import javax.annotation.concurrent.ThreadSafe;

/**
 * @ClassName SaftLazyInit
 * @Author bin
 * @Date 2020/6/13 下午6:01
 * @Decr TODO
 * @Link TODO
 **/
@ThreadSafe
public class SaftLazyInit {

    private static SaftLazyInit saftLazyInit;

    public synchronized static SaftLazyInit getInstance(){
        if(saftLazyInit == null){
            saftLazyInit = new SaftLazyInit();
        }
        return saftLazyInit;
    }
}
