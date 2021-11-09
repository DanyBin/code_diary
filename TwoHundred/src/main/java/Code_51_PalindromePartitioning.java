import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 *
 * 回文串 是正着读和反着读都一样的字符串。
 *
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 *
 * 输入：s = "a"
 * 输出：[["a"]]
 *
 */
public class Code_51_PalindromePartitioning {

  public static void main(String[] args) {
    final Code_51_PalindromePartitioning partitioning = new Code_51_PalindromePartitioning();
    final List<List<String>> aab = partitioning.partition("aab");
    System.out.println(aab);
  }

  public List<List<String>> partition(String s) {
    List<List<String>> ret = new ArrayList<>();
    List<String> temp = new ArrayList<>();
    doPartition(ret,temp,s);
    return ret;
  }

  private void doPartition(List<List<String>> ret, List<String> temp,String s) {
    if (s.length() == 0) {
      ret.add(new ArrayList<>(temp));
      return;
    }

    for (int i = 0; i < s.length(); i ++ ) {
      //判断是否为回文字符串.判断的是整个字符串
      if (isPalindrome(s,0,i)) {
        temp.add(s.substring(0,i + 1));
        doPartition(ret,temp,s.substring(i + 1));
        temp.remove(temp.size() - 1);
      }
    }
  }

  private boolean isPalindrome(String s,int begin ,int end) {
    while (begin < end) {
      if (s.charAt(begin++) != s.charAt(end--)) {
        return false;
      }
    }
    return true;
  }
}
