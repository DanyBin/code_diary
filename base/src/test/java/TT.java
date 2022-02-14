import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TT {
  public static void main(String[] args) {

    List<Integer> ret = Lists.newArrayList();
    for (int i = 0; i < 199; i++) {
      ret.add(i);
    }

    int limit = 200;
    if (ret.size() < 200) {
      limit = ret.size();
    }
    final List<Integer> collect = IntStream.range(0, limit)
        .mapToObj(ret::get)
        .collect(Collectors.toList());
    final List<Integer> integers = ret.subList(limit, ret.size());
    System.out.println(collect);
    System.out.println(integers);
  }
}
