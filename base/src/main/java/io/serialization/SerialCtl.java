package io.serialization;

import java.io.*;

/**
 * @ClassName SerialCtl
 * @Author bin
 * @Date 2020/7/29 下午7:54
 * @Decr TODO
 *      自定义序列化实现方法
 *          wirterObject
 *          readObject
 *
 * @Link TODO
 **/
public class SerialCtl  implements Serializable{
    private String name;
    private transient String p;

    public SerialCtl(String name,String p){
        this.name = "No transient " + name;
        this.p = "transient " + p;
    }

    @Override
    public String toString(){
        return name + p;
    }

    private void writerObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeObject(p);
    }

    private void readObject(ObjectInputStream in) throws IOException,ClassNotFoundException {
        in.defaultReadObject();
        p = (String)in.readObject();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String file = "/Users/bin/gitStudy/code_diary/base/src/main/resources/SerialCtl.out";
        SerialCtl s = new SerialCtl("a","b");
        System.out.println(s);
        //字节缓存
        ByteArrayOutputStream out =new ByteArrayOutputStream();
        ObjectOutputStream stream = new ObjectOutputStream(out);
        stream.writeObject(s);

        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(out.toByteArray()));
        SerialCtl s2 = (SerialCtl)in.readObject();
        System.out.println(s2);

    }
}
