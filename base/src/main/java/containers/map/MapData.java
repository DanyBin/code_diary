package containers;

import generics.Generator;

import java.util.LinkedHashMap;

/**
 * @ClassName MapData
 * @Author bin
 * @Date 2020/7/21 下午12:12
 * @Decr TODO
 * @Link TODO
 **/
public class MapData<K,V> extends LinkedHashMap<K,V> {

    public MapData(Generator<Pair<K,V>> gen,int quantity){
        for(int i=0;i < quantity;i ++){
            Pair<K,V> p = gen.next();
            put(p.key,p.value);
        }
    }

    public MapData(Generator<K> genK,Generator<V> genV,int quantity){
        for(int i=0;i < quantity;i ++){
            put(genK.next(),genV.next());
        }
    }

    public MapData(Generator<K> genK,V value,int quantity){
        for(int i=0; i < quantity;i ++){
            put(genK.next(),value);
        }
    }

    public MapData()
}