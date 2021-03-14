package containers.hash;

import java.util.*;

/**
 * @ClassName SimpleHashMap
 * @Author bin
 * @Date 2020/7/22 下午7:18
 * @Decr TODO
 * @Link TODO
 **/
public class SimpleHashMap<K,V> extends AbstractMap<K,V>{

    //初始化数组的大小
    static final int SIZE = 997;

    //"槽位（Slot）"
    @SuppressWarnings("unchecked")
    LinkedList<MapEntry<K,V>>[] buckets = new LinkedList[SIZE];

    @Override
    public V put(K key,V value){
        V oldValue = null;

        //1. 计算散列码
        int index = Math.abs(key.hashCode()) % SIZE;

        //2. 冲突时
        if(buckets[index] == null){
            buckets[index] = new LinkedList<MapEntry<K, V>>();
        }
        LinkedList<MapEntry<K,V>> bucket = buckets[index];
        MapEntry<K,V> pair = new MapEntry<K, V>(key,value);
        boolean found = false;

        //判断冲突
        ListIterator<MapEntry<K, V>> it = bucket.listIterator();
        while (it.hasNext()){
            MapEntry<K,V> iPair = it.next();
            if(iPair.getKey().equals(key)){
                oldValue = iPair.getValue();
                it.set(pair);
                found = true;
                break;
            }
        }

        if(!found){
            buckets[index].add(pair);
        }
        return oldValue;
    }

    public V get(Object key){
        //1.计算 散列码
        int index = Math.abs(key.hashCode()) %SIZE;
        //2. 通过散列码作为数组下标，获取Pair
        if(buckets[index] == null) return null;
        for(MapEntry<K,V> pair :buckets[index]){
            if(pair.getKey().equals(key)){
                return pair.getValue();
            }
        }
        return null;
    }

    public Set<Entry<K, V>> entrySet() {
        Set<Map.Entry<K,V>> set = new HashSet<Entry<K, V>>();
        for(LinkedList<MapEntry<K,V>> bucket : buckets){
            if(bucket == null) continue;
            for(MapEntry<K,V> pair : bucket){
                set.add(pair);
            }
        }
        return set;
    }

    public static void main(String[] args) {
        SimpleHashMap<String,String> m = new SimpleHashMap<String, String>();
        m.put("a","b");
        m.put("c","d");
        System.out.println(m);
        System.out.println(m.get("a"));
        System.out.println(m.entrySet());

    }
}

