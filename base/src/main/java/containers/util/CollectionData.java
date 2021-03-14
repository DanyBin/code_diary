package containers;

import generics.Generator;

import java.util.ArrayList;

/**
 * @ClassName CollectionData
 * @Author bin
 * @Date 2020/7/21 上午11:55
 * @Decr TODO
 *  适配器
 * @Link TODO
 **/
public class CollectionData<T> extends ArrayList<T> {
    public CollectionData(Generator<T> gen,int quantity){
        for(int i=0; i< quantity;i ++){
            add(gen.next());
        }
    }

    public static <T> CollectionData<T> list(Generator<T> gen, int quantity){
        return new CollectionData<T>(gen,quantity);
    }
}
