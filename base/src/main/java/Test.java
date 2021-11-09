import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.hash.*;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName Test
 * @Author bin
 * @Date 2021/4/14 上午11:50
 * @Decr TODO
 * @Link TODO
 **/
public class Test {

    //单机是幂等的
    public static void  main(String[] args) {
        final HashFunction hashFunction = Hashing.md5();
        final HashCode hashCode = hashFunction.hashBytes("测试使用".getBytes(StandardCharsets.UTF_8));
        System.out.println(hashCode.toString());
    }
    static class Person {
        int id;
        String firstName;
        String lastName;
        int birthYear;

        public Person(int id, String firstName, String lastName, int birthYear) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.birthYear = birthYear;
        }
    }
}


