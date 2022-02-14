package string;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 给定一个字符串s，计算具有相同数量 0 和 1 的非空（连续）子字符串的数量，并且这些子字符串中的所有 0 和所有 1 都是连续的。
 *
 * 重复出现的子串要计算它们出现的次数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-binary-substrings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * 输入: "00110011"
 * 输出: 6
 * 解释: 有6个子串具有相同数量的连续1和0：“0011”，“01”，“1100”，“10”，“0011” 和 “01”。
 *
 * 请注意，一些重复出现的子串要计算它们出现的次数。
 *
 * 另外，“00110011”不是有效的子串，因为所有的0（和1）没有组合在一起。
 *
 *
 * 输入: "10101"
 * 输出: 4
 * 解释: 有4个子串：“10”，“01”，“10”，“01”，它们具有相同数量的连续1和0。
 */
public class Code_07_countBinarySubstrings {

  public int countBinarySubstrings(String s){
    int preLen = 0;
    int curLen = 1;
    int count = 0;
    for (int i = 1; i < s.length() ; i ++) {
      if (s.charAt(i) == s.charAt(i-1)) {
        curLen++;
      } else {
        preLen = curLen;
        curLen = 1;
      }

      if (preLen >= curLen) {
        count++;
      }
    }
    return count;
  }

  public static void main(String[] args) {
    List<String> list = new ArrayList<>();
    list.add("1");
    list.add("2");
    list.add("3");
    list.add("4");
    list.add("5");
    list.add("6");
    list.add("7");

    List<String> list1 = new ArrayList<>();
    list1.add("1");
    list1.add("2");
    list1.add("3");
    final int index = list.indexOf("6") + 1;
    list.add(3,list.remove(index));


    final List<String> collect = IntStream.range(0,0).mapToObj(list::get).collect(Collectors.toList());
    System.out.println(list);
  }
}
