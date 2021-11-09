/**
 *
 */
public class Code_69_ConvertToBase {
  /**
   * 给定一个整数 num，将其转化为 7 进制，并以字符串形式输出。
   * @param num
   * @return
   */
  public String convertToBase7(int num) {
    if (num == 0) {
      return "0";
    }
    StringBuilder sb = new StringBuilder();
    //考虑为负数的情况
    boolean isNegative = num < 0;
    if (isNegative) {
      num = -num;
    }

    while (num > 0) {
      sb.append(num % 7);
      num/= 7;
    }
    final String string = sb.reverse().toString();
    return isNegative ? "-" + string : string;
  }

  /**
   * 给定一个整数，编写一个算法将这个数转换为十六进制数。对于负整数，我们通常使用 补码运算 方法。
   *
   * 注意:
   *
   * 十六进制中所有字母(a-f)都必须是小写。
   * 十六进制字符串中不能包含多余的前导零。如果要转化的数为0，那么以单个字符'0'来表示；对于其他情况，十六进制字符串中的第一个字符将不会是0字符。 
   * 给定的数确保在32位有符号整数范围内。
   * 不能使用任何由库提供的将数字直接转换或格式化为十六进制的方法。
   *
   * 来源：力扣（LeetCode）
   * 链接：https://leetcode-cn.com/problems/convert-a-number-to-hexadecimal
   * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
   * @return
   */
  public String toHex(int num) {
    char[] map = {'0','1','2','3','4','5','6','7','8','9','a', 'b', 'c', 'd', 'e', 'f'};
    if (num == 0) {
      return "0";
    }
    StringBuilder builder = new StringBuilder();
    while (num !=0) {
      builder.append(map[num & 0b1111]);
      num >>>= 4;// 因为考虑的是补码形式，因此符号位就不能有特殊的意义，需要使用无符号右移，左边填 0
    }
    return builder.reverse().toString();
  }

  /**
   * 给你一个整数 columnNumber ，返回它在 Excel 表中相对应的列名称。
   *
   * 例如：
   *
   * A -> 1
   * B -> 2
   * C -> 3
   * ...
   * Z -> 26
   * AA -> 27
   * AB -> 28
   *
   * 来源：力扣（LeetCode）
   * 链接：https://leetcode-cn.com/problems/excel-sheet-column-title
   * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
   * @return
   */
  public String convertToTitle(int n) {
    if (n == 0) {
      return "";
    }
    n -- ;
    return convertToTitle( n / 26) +(char) (n%26 + 'A');
  }
}
