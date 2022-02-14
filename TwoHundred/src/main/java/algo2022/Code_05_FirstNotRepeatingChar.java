package algo2022;

/**
 * 在一个字符串中找到第一个只出现一次的字符，并返回它的位置。字符串只包含 ASCII 码字符。
 *
 * Input: abacc
 * Output: b
 * char只有128个
 */
public class Code_05_FirstNotRepeatingChar {

  public static void main(String[] args) {
    firstNoRepeatingChar("abacc");
  }

  public static void firstNoRepeatingChar(String s) {
    int[] nums = new int[128];
    for (char c : s.toCharArray()) {
      nums[c]++;
    }
   for (char c : s.toCharArray()) {
     if (nums[c] == 1) {
       System.out.println(c);
     }
   }
  }
}
