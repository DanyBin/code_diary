package byteBuffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

public class ByteBufTest {
  public static void main(String[] args) {
    ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer(9,100);
    print("alocate ByteBuf(9,100)",byteBuf);

    //write方法，改变写指针。writerIndex < capacity时，可以继续写
    byteBuf.writeBytes(new byte[]{1,2,3,4});
    print("wirterBytes[1,2,3,4]",byteBuf);

    //write 方法改变写指针，写完int类型后，写指针增加4
    byteBuf.writeInt(12);
    print("writerInt(12)",byteBuf);

    //wirter方法改变写指针，写完之后 写指针 == capacity时候，buffer不可写
    byteBuf.writeBytes(new byte[]{5});
    print("writeBytes(5)",byteBuf);

    // write 方法改变写指针，写的时候发现 buffer 不可写则开始扩容，扩容之后 capacity 随即改变
    byteBuf.writeBytes(new byte[]{6});
    print("writeBytes(6)",byteBuf);

    //get方法不改变读写指针
    System.out.println("getByte(3) return" + byteBuf.getByte(3));
    System.out.println("getShort(3) return"+ byteBuf.getShort(3));
    System.out.println("getInt(3) return" + byteBuf.getInt(3));

    //Set方法不改变读写指针
    byteBuf.setByte(byteBuf.readableBytes()+1,0);
    print("setByte()",byteBuf);

    //read方法改变读指针
    byte[] dst = new byte[byteBuf.readableBytes()];
    byteBuf.readBytes(dst);
    print("readBytes" + dst.length + ")" ,byteBuf);
  }

  private static void print(String action, ByteBuf buffer) {
    System.out.println("after ===========" + action + "============");
    System.out.println("capacity(): " + buffer.capacity());
    System.out.println("maxCapacity(): " + buffer.maxCapacity());
    System.out.println("readerIndex(): " + buffer.readerIndex());
    System.out.println("readableBytes(): " + buffer.readableBytes());
    System.out.println("isReadable(): " + buffer.isReadable());
    System.out.println("writerIndex(): " + buffer.writerIndex());
    System.out.println("writableBytes(): " + buffer.writableBytes());
    System.out.println("isWritable(): " + buffer.isWritable());
    System.out.println("maxWritableBytes(): " + buffer.maxWritableBytes());
    System.out.println();
  }
}
