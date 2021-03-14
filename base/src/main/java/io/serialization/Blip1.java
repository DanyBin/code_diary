package io.serialization;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * @ClassName Blip1
 * @Author bin
 * @Date 2020/7/29 下午12:08
 * @Decr TODO
 *      序列化的控制测试时
 * @Link TODO
 **/
public class Blip1  implements Externalizable{
    public Blip1(){
        System.out.println("Blip1 constructor");
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Blip1 writeExternal");
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Blip1 readExternal");
    }
}
