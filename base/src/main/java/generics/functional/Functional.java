package generics.functional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @ClassName Functional
 * @Author bin
 * @Date 2020/7/14 上午11:09
 * @Decr TODO
 * @Link TODO
 **/
public class Functional {

    //元素合并
    public static <T> T reduce(Iterable<T> seq,Combiner<T> combiner){
        Iterator<T> iterator = seq.iterator();
        if(iterator.hasNext()){
            T result = iterator.next();
            while (iterator.hasNext()){
                result = combiner.combine(result, iterator.next());
            }
            return result;
        }
        return null;
    }

    public static <T> Collector<T> forEach(Iterable<T> seq,Collector<T> func){
        for(T t:seq){
            func.function(t);
        }
        return func;
    }

    public static <R,T> List<R> transform(Iterable<T> seq,UnaryFunction<R,T> func) {
        List<R> result = new ArrayList<R>();
        for(T t: seq){
            result.add(func.function(t));
        }
        return result;
    }

    public static <T> List<T> filter(Iterable<T> seq,UnaryPredicate<T> pred){
        List<T> result = new ArrayList<T>();
        for(T t:seq){
            if(pred.test(t)){
                result.add(t);
            }
        }
        return result;
    }

    static class IntergerAdder implements Combiner<Integer>{

        public Integer combine(Integer x, Integer y) {
            return x+y;
        }
    }

    static class IntegerSubtracter implements Combiner<Integer>{

        public Integer combine(Integer x, Integer y) {
            return x-y;
        }
    }

    static class BigDecimalAdder implements Combiner<BigDecimal>{

        public BigDecimal combine(BigDecimal x, BigDecimal y) {
            return x.add(y);
        }
    }
    static class BingIntegerAdder implements Combiner<BigInteger>{

        public BigInteger combine(BigInteger x, BigInteger y) {
            return x.add(y);
        }
    }

    static class AtomicLongAdder implements Combiner<AtomicLong>{

        public AtomicLong combine(AtomicLong x, AtomicLong y) {
            return new AtomicLong(x.addAndGet(y.get()));
        }
    }

    static class BigDecimalUlp implements UnaryFunction<BigDecimal,BigDecimal>{

        public BigDecimal function(BigDecimal x) {
            return x.ulp();
        }
    }

    static class GreaterThan<T extends Comparable<T>> implements UnaryPredicate<T> {

        private T bound;
        public GreaterThan(T bound){this.bound = bound;}

        public boolean test(T x) {
            return x.compareTo(bound) > 0;
        }
    }

    static class MulitplyingIntegerCollector implements Collector<Integer>{

        private Integer val = 1;
        public Integer function(Integer x) {
            val *= x;
            return val;
        }

        public Integer result() {
            return val;
        }
    }

    public static void main(String[] args) {
        List<Integer> li = Arrays.asList(1, 2, 3, 4, 5, 6,7);
        Integer reduce = reduce(li, new IntergerAdder());
        System.out.println(reduce);

        System.out.println(reduce(li,new IntegerSubtracter()));

        System.out.println(filter(li,new GreaterThan<Integer>(4)));

        System.out.println(forEach(li,new MulitplyingIntegerCollector()).result());


    }
}
