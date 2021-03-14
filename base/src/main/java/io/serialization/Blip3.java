package io.serialization;

import java.io.*;

/**
 * @ClassName Blip3
 * @Author bin
 * @Date 2020/7/29 下午12:18
 * @Decr TODO
 *      Externalizable 无法自动序列化，必须收到序列化
 * @Link TODO
 **/
public class Blip3 implements Externalizable {
    private int i;
    private String s;

    public Blip3(){
        System.out.println("defual");
    }

    public Blip3(String s, int i){
        this.i = i;
        this.s = s;
    }

    @Override
    public String toString(){
        return s + i;
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Blip3 writeExternal");
        //注意-将对象的信息写出
        out.writeObject(s);
        out.writeInt(i);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Blip3 readExternal");
        //注意-将对象的恢复
        s = (String)in.readObject();
        i = in.readInt();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String outFile = "/Users/bin/gitStudy/code_diary/base/src/main/resources/Blip3.out";
        Blip3 b3 = new Blip3("string",47);
        System.out.println(b3);

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(outFile));
        out.writeObject(b3);
        out.close();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(outFile));
        b3 = (Blip3)in.readObject();
        System.out.println(b3);
    }
}
