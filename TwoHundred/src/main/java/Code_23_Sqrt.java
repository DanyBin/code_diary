/**
 * Input: 4
 * Output: 2
 *
 * Input: 8
 * Output: 2
 * Explanation: The square root of 8 is 2.82842..., and since we want to return an integer, the decimal part will be truncated.
 */
public class Code_23_Sqrt {
  public  int mySqrt(int x) {
    if ( x <= 1) {
      return x;
    }

    int l = 1;
    int h = x;
    while (l <= h) {
      int m = l + (h-l)/2;
      int sqrt = x / m ;
      if (sqrt == m) {
        return m;
      } else if(sqrt > m) {
        h = m - 1;
      } else {
        l = m + 1;
      }
    }
    return h;
  }
}
