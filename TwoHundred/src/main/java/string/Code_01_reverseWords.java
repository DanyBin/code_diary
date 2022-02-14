package string;

import java.util.Stack;

/**
 * 给你一个字符串 s ，逐个翻转字符串中的所有 单词 。
 *
 * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
 *
 * 请你返回一个翻转 s 中单词顺序并用单个空格相连的字符串。
 *
 * 说明：
 *
 * 输入字符串 s 可以在前面、后面或者单词间包含多余的空格。
 * 翻转后单词间应当仅用一个空格分隔。
 * 翻转后的字符串中不应包含额外的空格。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-words-in-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 输入：s = "the sky is blue"
 * 输出："blue is sky the"
 *
 * 输入：s = " hello world "
 * 输出："world hello"
 * 解释：输入字符串可以在前面或者后面包含多余的空格，但是翻转后的字符不能包括。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-words-in-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code_01_reverseWords {

  public static void main(String[] args) {
    final Code_01_reverseWords reverseWords = new Code_01_reverseWords();
    System.out.println(reverseWords.reverseWords(" hello world "));
  }
  /**
   * 举例分析
   * " hello world "
   * 边界条件
   * left  与 right
   *
   * " hello "
   * @param s
   * @return
   */
  public String reverseWords(String s) {
    int left = s.length() - 1;
    int right;

    final StringBuilder builder = new StringBuilder();
    //从后向前找单词
    while (left >= 0) {
      //找到右边界
      while (left >=0 && s.charAt(left) == ' ') {
        left --;
      }
      right = left;
      //存在右边界 小于0的情况
      if (right < 0) {
        break;
      }

      //找到左边界
      while (left >=0 && s.charAt(left) != ' ') {
        left--;
      }
      builder.append(s, left, right+1);
      builder.append(" ");
    }
    return builder.substring(0,builder.length()-1);
  }
}
