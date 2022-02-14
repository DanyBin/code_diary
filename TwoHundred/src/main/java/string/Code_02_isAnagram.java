package string;

/**
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * 注意：若s 和 t中每个字符出现的次数都相同，则称s 和 t互为字母异位词。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-anagram
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 *
 * 输入: s = "rat", t = "car"
 * 输出: false
 */
public class Code_02_isAnagram {
  /**
   *
   * @return
   */
  public boolean isAnagram(String s,String t) {
    if (s.length() != t.length()) {
      return false;
    }
    //26个字母的数组
    int[] alpha = new int[26];
    for (int i=0 ; i < s.length();i ++) {
      //负责++
      alpha[s.charAt(i) - 'a'] ++;
      //负责--
      alpha[t.charAt(i) - 'a'] --;
    }
    for (int i : alpha) {
      if (i != 0) {
        return false;
      }
    }
    return true;
  }
}
