package atomic;

import jdk.nashorn.internal.ir.annotations.Immutable;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName CASNumberRange
 * @Author bin
 * @Date 2020/6/12 上午11:01
 * @Decr TODO
 * @Link TODO
 **/
public class CASNumberRange {

    // 不可变类
    @Immutable
    private static class IntPair{
        final int lower;
        final int upper;

        public IntPair(int lower,int upper){
            this.lower =lower;
            this.upper = upper;
        }

        public IntPair get(){
            return this;
        }
    }

    private final AtomicReference<IntPair> values = new AtomicReference<>(new IntPair(0,0));

    public int getLower(){return values.get().lower;}
    public int getUpper(){return values.get().upper;}

    public void setLower(int i){
        while (true){
            IntPair oldPair = values.get();
            //大于上界
            if(i > oldPair.upper){
                throw  new IllegalArgumentException("can't set lower to " + i +" > upper ");
            }
            IntPair newPair = new IntPair(i, oldPair.upper);
            if(values.compareAndSet(oldPair,newPair))
                return;
        }
    }

    public void setUpper (int i){
        while (true){
            IntPair oldPair = values.get();
            //小于 下界
            if(i < oldPair.lower){
                throw  new IllegalArgumentException("can't set upper to " + i +" < lower ");
            }
            IntPair newPair = new IntPair(oldPair.lower, i);
            if(values.compareAndSet(oldPair,newPair))
                return;
        }
    }

}
