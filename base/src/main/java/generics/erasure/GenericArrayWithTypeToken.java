package generics.erasure;

import java.lang.reflect.Array;

/**
 * @ClassName GenericArrayWithTypeToken
 * @Author bin
 * @Date 2020/7/9 下午3:15
 * @Decr TODO
 * @Link TODO
 **/
public class GenericArrayWithTypeToken<T> {
    private T[] array;
    @SuppressWarnings("unchecked")
    public GenericArrayWithTypeToken(Class<T> tClass,int sz){
        array = (T[]) Array.newInstance(tClass,sz);
    }

    public void put(int index ,T item){
        array[index] = item;
    }
    public T[] rep () {return array;}

    public static void main(String[] args) {
        GenericArrayWithTypeToken<Integer> ga = new GenericArrayWithTypeToken<Integer>(Integer.TYPE, 10);
        Integer[] rep = ga.rep();
    }
}
