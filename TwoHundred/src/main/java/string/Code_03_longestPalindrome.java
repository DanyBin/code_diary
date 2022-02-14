package string;

/**
 * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
 *
 * 在构造过程中，请注意区分大小写。比如"Aa"不能当做一个回文字符串。
 *
 * 注意:
 * 假设字符串的长度不会超过 1010。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindrome
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 输入:
 * "abccccdd"
 *
 * 输出:
 * 7
 *
 * 解释:
 * 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
 *
 */
public class Code_03_longestPalindrome {
  public static void main(String[] args) {
    final Code_03_longestPalindrome palindrome = new Code_03_longestPalindrome();
    System.out.println(palindrome.longestPalindrome("abccccdd"));
  }
  /**
   * 举例分析
   * abccccdd
   * 存在回文必须是偶数的char
   * @param s
   * @return
   */
  public int longestPalindrome(String s) {
    //26的char
    int[] alpha = new int[26];
    for (int i = 0 ;i < s.length(); i++) {
      //计算每个字符串的数量
      alpha[s.charAt(i) - 'a'] ++;
    }

    int cnt = 0;
    //是否存在奇数
    boolean odd = false;
    for (int i : alpha) {
      if (i%2 == 0) {
        cnt += i;
      } else {
        odd = true;
      }
    }
    return odd ? cnt + 1 : cnt;
  }
}
