package io.serialization;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * @ClassName Blip2
 * @Author bin
 * @Date 2020/7/29 下午12:11
 * @Decr TODO
 *   序列化的控制测试时
 * @Link TODO
 **/
public class Blip2 implements Externalizable{

    //主要构造器的权限
    Blip2(){
        System.out.println("Blip2 constructor");
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Blip2 writeExternal");
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Blip2 readExternal");
    }
}
