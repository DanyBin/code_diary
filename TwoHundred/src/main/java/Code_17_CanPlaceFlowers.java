/**
 *Input: flowerbed = [1,0,0,0,1], n = 1
 * Output: True
 * 题目描述：flowerbed 数组中 1 表示已经种下了花朵。花朵之间至少需要一个单位的间隔，求解是否能种下 n 朵花。
 */
public class Code_17_CanPlaceFlowers {
  public boolean canPlaceFlowers(int[] flowerbed,int n) {
    int len = flowerbed.length;
    int cnt = 0;
    for (int i =0; i < flowerbed.length; i++) {
      if (flowerbed[i] == 1) {
        continue;
      }
      int prev = i == 0 ? 0 : flowerbed[i-1];
      int next = i == len - 1 ? 0 : flowerbed[i+1];
      //判断前后指针都为空的情况，那么就可以种下花朵
      if (prev == 0 && next == 0) {
        cnt++;
        flowerbed[i] = 1;
      }
    }
    return cnt >= n;
  }
}
