import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

public class ListEqual {
  public static void main(String[] args) {

    List<String> list1 = Lists.newArrayList();
    list1.add("1");
    list1.add("2");
    list1.add("3");
    List<String> list2 = Lists.newArrayList();
    list2.add("3");
    list2.add("1");
    list2.add("2");
    list2.add("2");


    System.out.println(list1);
    System.out.println(list2);
    final boolean equalCollection = CollectionUtils.isEqualCollection(list1, list2);
    System.out.println(equalCollection);
  }
}
