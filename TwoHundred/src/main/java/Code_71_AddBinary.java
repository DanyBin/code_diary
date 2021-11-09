/**
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 *
 * 输入为 非空 字符串且只包含数字 1 和 0
 *
 * 输入: a = "11", b = "1"
 * 输出: "100"
 */
public class Code_71_AddBinary {

  public String addBinary(String a,String b) {
    int i = a.length() - 1;
    int j = b.length() - 1;
    int carry = 0;
    StringBuilder sb = new StringBuilder();
    while (carry == 1 || i >= 0 || j >= 0) {
      if (i >= 0 && a.charAt(i--) == '1') {
        carry ++;
      }

      if (j >= 0 && b.charAt(j--) == '1') {
        carry ++;
      }
      sb.append(carry%2);
      carry/=2;
    }
    return sb.reverse().toString();
  }
}
