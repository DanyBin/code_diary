package io.serialization;

import java.io.*;
import java.util.Date;

/**
 * @ClassName LogonWithTransient
 * @Author bin
 * @Date 2020/7/29 下午7:10
 * @Decr TODO
 *      关键字： transient 的使用测试
 * @Link TODO
 **/
public class LogonWithTransient implements Serializable{
    private Date date = new Date();
    private String userName;
    private transient String password;

    public LogonWithTransient(String userName,String password){
        this.userName = userName;
        this.password = password;
    }

    @Override
    public String toString(){
        return new StringBuffer(date.toString()).append("userName = ").append(userName).append(" password= ").append(password).toString();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String file = "/Users/bin/gitStudy/code_diary/base/src/main/resources/LogonWithTransient.out";

        LogonWithTransient l1 = new LogonWithTransient("exception","s");
        System.out.println(l1);
        ObjectOutputStream out =new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(l1);
        out.close();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        l1 = (LogonWithTransient) in.readObject();
        in.close();
        System.out.println(l1);

    }
}
