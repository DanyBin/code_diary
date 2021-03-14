package containers.hash;

import java.util.*;

/**
 * @ClassName SlowMap
 * @Author bin
 * @Date 2020/7/22 下午5:19
 * @Decr TODO
 * @Link TODO
 **/
public class SlowMap<K,V> extends AbstractMap<K,V> {
    private List<K> keys = new ArrayList<K>();
    private List<V> valeus = new ArrayList<V>();


    @Override
    public V put(K key,V value){
        V oldValue = get(key);
        if(!keys.contains(key)){
            keys.add(key);
            valeus.add(value);
        }else {
            valeus.set(keys.indexOf(key),value);
        }
        return oldValue;
    }

    @Override
    public V get(Object key){
        if(!keys.contains(key)){
            return null;
        }
        return valeus.get(keys.indexOf(key));
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K,V>> set = new HashSet<Map.Entry<K, V>>();
        Iterator<K> ki = keys.iterator();
        Iterator<V> vi = valeus.iterator();

        while (ki.hasNext()){
            set.add(new MapEntry<K,V>(ki.next(),vi.next()));
        }
        return set;
    }
}
