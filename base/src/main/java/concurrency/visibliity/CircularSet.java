package concurrency.visibliity;

/**
 * @ClassName CircularSet
 * @Author bin
 * @Date 2020/8/13 上午11:05
 * @Decr TODO
 * @Link TODO
 **/
public class CircularSet {
    private int[] array;
    private int len;
    private int index ;

    public CircularSet(int size){
        array = new int[size];
        len = size;

        for(int i=0; i< size ;i ++){
            array[i] = -1;
        }
    }

    public synchronized void add(int i) {
        array[index] = i;
        index = ++ index %len;
    }

    public synchronized boolean contains(int val) {
        for(int i=0; i< len ;i++){
            if(array[i] ==  val) return true;
        }
        return false;
    }
}
