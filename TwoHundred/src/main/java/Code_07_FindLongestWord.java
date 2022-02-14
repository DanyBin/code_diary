import java.util.List;
/**
 * Input:
 * s = "abpcplea", d = ["ale","apple","monkey","plea"]
 *
 * Output:
 * "apple"
 *
 * 最长子序列
 */
public class Code_07_FindLongestWord {
  public static void main(String[] args) {
    double i1 = 1.0D;
    System.out.println(i1 == 1.0);

  }

  public static String findLongestWord(String s, List<String> list) {
      String tmp = "";
      for (String target : list) {
        int l = tmp.length();
        int h = target.length();
        if (l > h || (l == h && tmp.compareTo(target) < 0)) {
          continue;
        }
        if (isSubstr(tmp,target)) {
          tmp = target;
        }
      }
      return tmp;
  }

  public static boolean isSubstr(String s,String target) {
     int i = 0;
     int j = 0;
     while ( i < s.length() && j < target.length()) {
       //字符相等时
       if (s.charAt(i) == target.charAt(j)) {
         j ++;
       }
       i++;
     }
     return i == target.length();
  }



}
