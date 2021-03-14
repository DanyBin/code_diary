package generics;

import java.util.Collection;

/**
 * @ClassName Generators
 * @Author bin
 * @Date 2020/7/9 上午10:29
 * @Decr TODO
 * @Link TODO
 **/
public class Generators {
    public static <T> Collection<T> fill(Collection<T> coll,Generator<T> gen,int n){
        for(int i=0; i< n;i ++){
            coll.add(gen.next());
        }
        return coll;
    }
}
