package generics.erasure;

/**
 * @ClassName GenericArray
 * @Author bin
 * @Date 2020/7/9 下午2:52
 * @Decr TODO
 *  类型擦拭验证
 * @Link TODO
 **/
public class GenericArray<T> {
    private Object[] array;
    public GenericArray(int sz){
        array = new Object[sz];
    }

    public void put(int index ,T item){
        array[index] = item;
    }

    @SuppressWarnings("unchecked")
    public T get(int index){
        return (T)array[index];
    }

    @SuppressWarnings("unchecked")
    public T[] rep(){
        return (T[])array;
    }

    public static void main(String[] args) {
        GenericArray<Integer> ga = new GenericArray<Integer>(10);
        for(int i=0; i< 5;i++){
            ga.put(i,i);
        }

        // 类型擦拭- 运行时，类型消失
        // ClassCastException: [Ljava.lang.Object; cannot be cast to [Ljava.lang.Integer;
        Integer[] rep = ga.rep();
        System.out.println(rep);
    }
}

