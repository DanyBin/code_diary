package containers.hash;

import java.util.Map;

/**
 * @ClassName MapEntry
 * @Author bin
 * @Date 2020/7/22 下午5:31
 * @Decr TODO
 * @Link TODO
 **/
public class MapEntry<K,V> implements Map.Entry<K,V> {
    private K key;
    private V value;

    public MapEntry(K key,V value){
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }
    @Override
    public V setValue(V value) {
        V result = value;
        this.value = value;
        return result;
    }

    @Override
    public int hashCode(){
        return (key ==null ? 0:key.hashCode()) ^ (value == null ? 0:value.hashCode());
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof MapEntry)){
            return false;
        }
        MapEntry me = (MapEntry)o;

        return (key == null ? me.getKey() == null : key.equals(me.getKey())) &&
                (value == null ?me.getValue() == null : value.equals(me.getValue()));
    }

    @Override
    public String toString(){return key + "=" + value;}
}
