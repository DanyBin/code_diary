package io.serialization;

import java.io.Serializable;

/**
 * @ClassName Data
 * @Author bin
 * @Date 2020/7/29 上午11:21
 * @Decr TODO
 * @Link TODO
 **/
public class Data implements Serializable {
    private int n;
    public Data(int n) {this.n = n;}
    @Override
    public String toString(){return Integer.toString(n);}
}

