package java8.function;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.*;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @ClassName OptionalTest
 * @Author bin
 * @Date 2021/5/14 上午10:26
 * @Decr TODO
 * @Link TODO
 **/
public class OptionalTest<T extends OptionalTest.Test> {
  T test;

  public static void main(String[] args) {
    OptionalTest optionalTest = new OptionalTest();
    optionalTest.test = optionalTest.t();
    System.out.println(optionalTest.test);
  }

  public T t () {
    String s = "{\"a\":1,\"b\":\"b\"}";
    Class<? extends Test> aClass = this.test.getClass();
    Test test = JSONObject.parseObject(s,this.test.getClass());
    return (T)test;
  }

  static class Test {
    private int a;
    private String b;
  }
}
