package containers;

import java.util.BitSet;
import java.util.Random;

/**
 * @ClassName Bits
 * @Author bin
 * @Date 2020/7/23 下午7:32
 * @Decr TODO
 * @Link TODO
 **/
public class Bits {
    public static void printBitSet(BitSet b){
        System.out.println("bits: "+ b);
        StringBuilder builder = new StringBuilder();
        for(int j =0; j< b.size();j ++){
            builder.append(b.get(j)? "1" : "0");
        }
        System.out.println("bit pattern: " +builder);
    }

    public static void main(String[] args) {
        Random rand = new Random(47);
        byte bt = (byte)rand.nextInt();

        BitSet bitSet = new BitSet();
        for(int i=7; i>=0 ;i --){
            if(((1 << i) & bt) !=0){
                bitSet.set(i);
            }else {
                bitSet.clear(i);
            }
        }
        System.out.println("byte value: "+ bitSet);
        printBitSet(bitSet);
    }
}
