/**
 * Input:
 * letters = ["c", "f", "j"]
 * target = "d"
 * Output: "f"
 *
 * Input:
 * letters = ["c", "f", "j"]
 * target = "k"
 * Output: "c"
 *
 * 题目描述：给定一个有序的字符数组 letters 和一个字符 target，要求找出 letters 中大于 target 的最小字符，如果找不到就返回第 1 个字符。
 */
public class Code_24_FindSmallestLetterGreaterThanTarget {
  public char nextGreatestLetter(char[] letters, char target) {
    int l = 0;
    int h = letters.length - 1;
    while (l <= h) {
      //求中间值-- 双指针遍历的方式
      int mid = l + (h - l)/2;
      //判断 是否小于。注意边界情况
      if (letters[mid] <= target) {
        l = mid + 1;
      } else {
        h = mid - 1;
      }
    }
    return  l < letters.length ? letters[l] : letters[0];
  }
}
