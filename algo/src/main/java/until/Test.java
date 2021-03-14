package until;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONPOJOBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Test
 * @Author bin
 * @Date 2020/11/4 下午3:21
 * @Decr TODO
 * @Link TODO
 **/
public class Test {
    public static void main(String[] args) {
        boolean t1 = true;
        boolean t2 = false;

        List<String> var1 = new ArrayList<String>();
        var1.add("1");
        var1.add("2");
        var1.add("3");

        List<String> var2 = new ArrayList<String>();
        var1.add("3");
        var1.add("4");
        var1.add("5");


        List<String> back = var2;
        var2.removeAll(var1);
        var1.removeAll(var2);

        System.out.println(var1);
        System.out.println(var2);

        JSONObject object = new JSONObject();
        if ((new JSONObject()) == null) {
            System.out.println("null");
        }
        if (object.isEmpty()) {
            System.out.println("empty");
        }

        System.out.println(String.format("%s"," id =  ? and city = ? "));
        System.out.println("tttsss");
    }
}
