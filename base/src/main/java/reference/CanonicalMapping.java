package reference;

import java.util.WeakHashMap;

/**
 * @ClassName CanonicalMapping
 * @Author bin
 * @Date 2020/7/23 下午7:19
 * @Decr TODO
 * @Link TODO
 **/
public class CanonicalMapping {
    public static void main(String[] args) {
        int size = 1000;
        Key[] keys = new Key[size];
        WeakHashMap<Key,Value> map = new WeakHashMap<Key, Value>();

        for(int i=0; i<size;i ++) {
            Key key = new Key("key"+i);
            Value value = new Value("value"+i);

            if(i%3 == 0){
                keys[i] = key;
            }
            map.put(key,value);
        }
        System.gc();
        System.out.println(map);
    }
}
