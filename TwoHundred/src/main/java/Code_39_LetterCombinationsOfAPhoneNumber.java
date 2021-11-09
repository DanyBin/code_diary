import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code_39_LetterCombinationsOfAPhoneNumber {

  private static String[] numbers = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};

  public List<String> letterCombinations(String digits) {
    if (null == digits || digits.trim().length() == 0) {
      return Collections.emptyList();
    }
    List<String> ret = new ArrayList<>();
    doCombination(new StringBuffer(),ret,digits);
    return ret;
  }

  public void doCombination(StringBuffer prefix,List<String> ret,String digits) {
    //终止条件 == 长度相同时
    if (prefix.length() == digits.length()) {
      ret.add(prefix.toString());
      return;
    }
    //获取元素
    final int cur = digits.charAt(prefix.length()) - '0';
    String number = numbers[cur];
    for (char c : number.toCharArray()) {
      //添加
      prefix.append(c);
      //递归
      doCombination(prefix,ret,digits);
      //删除
      prefix.deleteCharAt(prefix.length() - 1);
    }
  }
}
