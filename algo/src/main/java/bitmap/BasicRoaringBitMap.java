package bitmap;

import org.roaringbitmap.ImmutableBitmapDataProvider;
import org.roaringbitmap.RoaringBitmap;
import org.roaringbitmap.buffer.ImmutableRoaringBitmap;
import org.roaringbitmap.longlong.Roaring64NavigableMap;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.math.BigInteger;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.BitSet;

/**
 * @ClassName BasicRoaringBitMap
 * @Author bin
 * @Date 2019/12/13 下午5:33
 * @Decr TODO
 *     参考:https://github.com/RoaringBitmap/RoaringBitmap 的使用。
 *     -- 工程化的bitmap工具
 *
 * @Link TODO
 **/
public class BasicRoaringBitMap {

    public static void main(String[] args) throws Exception {

        /*------    创建bitmap 的方式    */
        RoaringBitmap rr = RoaringBitmap.bitmapOf(1,2,3,1000,7);
        RoaringBitmap rr2 = new RoaringBitmap();
        


        /*------------添加元素-----------*/

        rr2.add(4000L,5000L); //将4000L 到 5000L 之间的所有整数，加到bitmap中


        /*------------对Bitmap的基本操作---------*/

        System.out.println(rr.select(2));   //返回当前「下标」元素
        System.out.println( rr.rank(1000));//返回元素「下标索引」 （索引位置由1开始）
        System.out.println(rr.contains(1000));//是否包含某个元素
        System.out.println(rr.getCardinality());//返回bitmap的基数
        System.out.println(rr.getLongCardinality()); // 返回bitmap的基数

        /*-----------多个bitmap操作-------------*/

        RoaringBitmap or = RoaringBitmap.or(rr, rr2); //or(合并两个bitmap)
        rr.or(or);

        boolean equals = or.equals(rr);// 判断bitmap是否相同
        if(!equals) throw new RuntimeException("bug");


        /*------以下是具体的例子-------*/

        //计算方式，对维度不做处理。 作为联合key的存储。对于value 做bitmap
        Long userId3= 17932874668L;
        Long userId4= 977656672L;

        Roaring64NavigableMap roaring64NavigableMap = Roaring64NavigableMap.bitmapOf(userId3);
        roaring64NavigableMap.add(userId4);
        //comporess-压缩
        roaring64NavigableMap.runOptimize();

        //序列化 bitmap -> bytes[]
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);

        roaring64NavigableMap.serialize(dos);
        dos.close();
        System.out.println(bos.toByteArray().length);


        //反序列化byte[] -> bitmap
        Roaring64NavigableMap roaring64NavigableMap1 = new Roaring64NavigableMap();
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        DataInputStream dataInputStream = new DataInputStream(bis);


        roaring64NavigableMap1.deserialize(dataInputStream);
        System.out.println(roaring64NavigableMap1.contains(userId3));
        System.out.println(roaring64NavigableMap1.contains(userId4));


        RoaringBitmap r1 = RoaringBitmap.bitmapOf(5,10,11,2,4);
        RoaringBitmap r2 = RoaringBitmap.bitmapOf(8);

        r1.or(new RoaringBitmap(null));
        System.out.println("or 操作-> 并集"+r1.toString());

        r1.add(8);
        r1.and(r2);

        System.out.println("and 操作-> 交集"+r1.toString());

        System.out.println("contain -> "+r1.contains(9));



    }
}
