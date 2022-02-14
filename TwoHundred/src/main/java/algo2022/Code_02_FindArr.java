package algo2022;

/**
 * 给定一个二维数组，其每一行从左到右递增排序，从上到下也是递增排序。给定一个数，判断这个数是否在该二维数组中。
 *
 * Consider the following matrix:
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 *
 * Given target = 5, return true.
 * Given target = 20, return false.
 *
 */
public class Code_02_FindArr {

  public static void main(String[] args) {
    final boolean b = find(new int[][]{{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}}, 11);
    System.out.println(b);
  }

  /**
   * 双层for循环
   * @param nums
   * @param key
   * @return
   */
  public static boolean find(int[][] nums,int key) {
    if (nums.length == 0) {
      return false;
    }
    n = nums.length - 1;
    m = nums[0].length - 1;
    dfs(0,0,key,nums);
    return flag;
  }

  static boolean flag = false;
  static int n = 0 ;
  static int m = 0;
  static int[][] diretion = {{1,0},{0,1}};//向下 or 向左走
  /***
   * 深度优先搜索
   */
  private static void dfs(int i,int j,int key,int[][] nums) {
    if (i < 0 || j < 0 || i > n || j > m ) {
      return;
    }
    if (nums[i][j] == key) {
      flag = true;
      return;
    }
    //向下 / 左走
    for (int[] k : diretion) {
      dfs(i + k[0],j + k[1],key,nums);
    }
  }
}
