package release;

import javax.annotation.Resource;
import javax.annotation.concurrent.NotThreadSafe;

/**
 * @ClassName UnsaftLazyInit
 * @Author bin
 * @Date 2020/6/13 下午5:59
 * @Decr TODO
 *      不安全的延时初始化
 * @Link TODO
 **/
@NotThreadSafe
public class UnsaftLazyInit {
    private static UnsaftLazyInit resource;

    public static UnsaftLazyInit getInstance(){
        if(resource == null){
            resource = new UnsaftLazyInit();
        }
        return resource;
    }
}

