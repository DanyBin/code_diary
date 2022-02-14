package string;

/**
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 *
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *输入：x = 121
 * 输出：true
 *
 * 输入：x = -121
 * 输出：false
 * 解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数
 *
 * 输入：x = 10
 * 输出：false
 * 解释：从右向左读, 为 01 。因此它不是一个回文数。
 */
public class Code_06_isPalindrome {
  public static void main(String[] args) {
    final Code_06_isPalindrome isPalindrome = new Code_06_isPalindrome();
    isPalindrome.isPalindrome(121);
  }

  public boolean isPalindrome(int x) {
    if (x == 0) {
      return true;
    }
    //考虑负数 与 10整除的情况
    if (x < 0 || x%10 == 0) {
      return false;
    }

    int right = 0;
    while (x > right) {
      //最后一位
      right = right * 10 + x%10;
      x /= 10;
    }

    return x == right || x == right/10;
  }
}
