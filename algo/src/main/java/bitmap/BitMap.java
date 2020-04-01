package bitmap;

/**
 * @ClassName BitMap
 * @Author bin
 * @Date 2019/12/13 下午3:55
 * @Decr TODO
 *      Java 中char类型占 16bit，也就是2个字节
 *
 *
 *      位图- 是一种「特殊」的散列表。
 *
 *      举例: 申请一个大小为1亿、数据类型为布尔类型的数组。
 *              将1千万整数作为数组下标，将对应的数组值设置为true。
 *              比如整数5对应 「下标5」的数组值设置为true，也就是 array[5] =true;
 *
 *       位图通过数组下标来定位数据，所有访问效率特别高。
 *
 *
 * @Link TODO
 **/
public class BitMap {

    private char[] bytes;
    private int nbits;

    public BitMap(int nbits){
        this.nbits = nbits;

        //将int转换成char字节
        this.bytes = new char[nbits/16+1];
    }


    //新增
    public void set(int k){

        //大于 最大值，则失败
        if(k > nbits)return;

        //数组的下标
        int byteIndex = k/16;


        //数组存储的元素
        int bitIndex  = k%16;

        //将元素存储在 数组中
        bytes[byteIndex] |= (1 << bitIndex);
    }


    public boolean get(int k){
        //大于 最大值，则不存在
        if(k > nbits)return false;

        int byteIndex = k/16;

        int bitIndex = k%16;

       return (bytes[byteIndex] & (1 << bitIndex)) != 0 ;
    }

    public static void main(String[] args) {
        BitMap bitMap = new BitMap(10);

        bitMap.set(5);
        bitMap.set(6);
        bitMap.set(7);
        bitMap.set(1);


        for(int i=0;i<bitMap.bytes.length;i++){
            System.out.println(bitMap.bytes[i]);
        }



        //Java位运算练习

        //左移 - 将5左移2位：
        System.out.println(5 << 2);

        //右移 -
        System.out.println(5 >> 2);


        //与（&）
        System.out.println(5 & 3); // 结果为1


        //或
        System.out.println(5 | 3);

        //位异或( ^ )
        System.out.println(5 ^ 3);//结果为6


        //位非( ~ )
        System.out.println(~5);


        /**
         * 由位运算操作符衍生而来的有：

         &= 按位与赋值

         |=  按位或赋值

         ^= 按位非赋值

         >>= 右移赋值

         >>>= 无符号右移赋值

         <<= 赋值左移
         */


        int a = 5;
        a &= 3;
        System.out.println(a);//结果是1

    }
}
