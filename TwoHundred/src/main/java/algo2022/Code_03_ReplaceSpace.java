package algo2022;

/**
 * 将一个字符串中的空格替换成 "%20"。
 *
 * Input:
 * "A B"
 *
 * Output:
 * "A%20B"
 */
public class Code_03_ReplaceSpace {

  public static void main(String[] args) {
    System.out.println(replaceSpace("A B"));
  }

  public static String replaceSpace(String target) {
    StringBuilder ret = new StringBuilder();
    for (char c : target.toCharArray()) {
      //判断空格
      if (c == ' ') {
        ret.append("%20");
      } else {
        ret.append(c);
      }
    }
    return ret.toString();
  }
}
