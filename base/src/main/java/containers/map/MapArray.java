package containers.map;

/**
 * @ClassName MapArray
 * @Author bin
 * @Date 2020/7/22 下午4:08
 * @Decr TODO
 *  基于数组实现 map结构
 * @Link TODO
 **/
public class MapArray<K,V> {
    private Object[][] pairs;
    private int index;
    public MapArray(int length){
        pairs = new Object[length][2];
    }

    public void put(K key,V value){
        if(index >= pairs.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        pairs[index++] = new Object[]{key,value};
    }

    @SuppressWarnings("unchecked")
    public V get(K key) {
        for(int i=0; i< index ;i ++){
            if(key.equals(pairs[i][0])){
                return (V) pairs[i][1];
            }
        }
        return null;
    }
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        for(int i=0;i< index;i++){
            builder.append(pairs[i][0].toString());
            builder.append(" : ");
            builder.append(pairs[i][1].toString());
            if(i < index-1){
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        MapArray<String, String> map = new MapArray<String, String>(6);
        map.put("first","value");
        map.put("secondary","value2");
        System.out.println(map);
        System.out.println(map.get("first"));
    }
}
