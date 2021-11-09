/**
 * s = "abc", t = "ahbgdc"
 * Return true.
 */
public class Code_18_IsSubsequence {

  public boolean isSubsequence(String s,String t) {
    for (char a : s.toCharArray()) {
      if (t.indexOf(a) == -1) {
        return false;
      }
    }
    return true;
  }
}
