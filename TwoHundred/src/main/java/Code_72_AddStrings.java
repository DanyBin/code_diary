import org.mapdb.Atomic;

/**
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。
 *
 * 你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 输入：num1 = "11", num2 = "123"
 * 输出："134"
 *
 * 十进制转换
 */
public class Code_72_AddStrings {
  public String addStrings(String num1,String num2) {
    int i = num1.length() - 1;
    int j = num2.length() - 1;
    int carry = 0;
    StringBuilder sb = new StringBuilder();
    while (carry == 1 || i >= 0 || j >= 0) {
      int x = i < 0 ? 0 : num1.charAt(i--) - '0';
      int y = j < 0 ? 0 : num2.charAt(j--) - '0';
      sb.append((x+y+carry)/10);
      carry = (x+y+carry)/10;
    }
    return sb.reverse().toString();
  }
}
