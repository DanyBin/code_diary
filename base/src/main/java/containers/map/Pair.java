package containers.map;

/**
 * @ClassName Pair
 * @Author bin
 * @Date 2020/7/21 下午12:11
 * @Decr TODO
 * @Link TODO
 **/
public class Pair<K,V> {
    public final K key;
    public final V value;
    public Pair(K k,V v){
        this.key = k;
        this.value = v;
    }
}
