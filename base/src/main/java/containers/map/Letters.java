package containers.map;

import generics.Generator;

import java.util.Iterator;

/**
 * @ClassName Letters
 * @Author bin
 * @Date 2020/7/21 下午12:21
 * @Decr TODO
 * @Link TODO
 **/
public class Letters implements Generator<Pair<Integer,String>>,Iterable<Integer>{

    private int size = 9;
    private int number = 1;
    private char letter = 'A';


    public Pair<Integer, String> next() {
        return new Pair<Integer, String>(number++," "+letter++);
    }

    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            public boolean hasNext() {
                return number < size;
            }

            public Integer next() {
                return number++;
            }
            public void remove(){
                throw new UnsupportedOperationException();
            }
        };
    }
}
