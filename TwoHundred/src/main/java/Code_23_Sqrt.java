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
      //获取中间值
      int m = l + (h-l)/2;
      //求余数
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

  public int mySqrt2(int x) {
    int l = 1;
    int h = x;
    while (l < h) {
      int mid = l + (h -l)/2;
      int sqrt = mid/x;
      if (sqrt == mid) {
        return mid;
      } else if (sqrt > mid) {
        h = mid - 1;
      } else {
        l = mid + 1;
      }
    }
    return h;
  }
}
