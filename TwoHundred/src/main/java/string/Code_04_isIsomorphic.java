package string;

/**
 * 给定两个字符串s和t，判断它们是否是同构的。
 *
 * 如果s中的字符可以按某种映射关系替换得到t，那么这两个字符串是同构的。
 *
 * 每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。
 * 不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以映射到自己本身。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/isomorphic-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 输入：s = "egg", t = "add"
 * 输出：true
 *
 * 输入：s = "foo", t = "bar"
 * 输出：false
 *
 * 输入：s = "paper", t = "title"
 * 输出：true
 */
public class Code_04_isIsomorphic {
  /**
   * e t f
   * a g
   * @return
   */
  public boolean isIsomorphic(String s,String t) {
    int[] preIndexS = new int[256];
    int[] preIndexT = new int[256];
    //记录char出现的位置次数
    for (int i = 0 ; i < s.length(); i ++) {
      char indexS = s.charAt(i);
      char indexT = t.charAt(i);
      if (preIndexS[indexS] != preIndexT[indexT]) {
        return false;
      }
      //计算位置出现的次数
      preIndexS[indexS] ++;
      preIndexT[indexT] ++;
    }
    return true;
  }
}
