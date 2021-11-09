import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Sets;
import lombok.Data;
import lombok.ToString;

import java.util.Collections;
import java.util.Set;

public class JSONTest {

  public static void main(String[] args) {
    Set<Integer> set = Sets.newHashSet();
    set.add(1);
    final Integer max = Collections.max(set);
    System.out.println(max);
  }
}
