package fastjson;

import com.alibaba.fastjson.JSONObject;

public class IsDoneBug {
  public static void main(String[] args) {
    final Domain domain = new Domain();
    domain.setName("tt");
    System.out.println(JSONObject.toJSONString(domain));
  }

}
