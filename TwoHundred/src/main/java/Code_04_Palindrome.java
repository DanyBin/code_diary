/**
 * 题目描述：可以删除一个字符，判断是否能构成回文字符串。
 */
public class Code_04_Palindrome {
  public static void main(String[] args) {
    palindrome("abcba");
  }
  private static void palindrome(String target) {
    int i=0;
    int j = target.length() - 1;
    while (i <= j) {
      final char c = target.charAt(i);
      final char c1 = target.charAt(j);
      if (c != c1) {
        System.out.println("失败");
        break;
      }
      if (i == j) {
        System.out.println("成功");
        break;
      }
      i++;
      j--;
    }
  }
}
