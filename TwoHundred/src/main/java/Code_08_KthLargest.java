import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 题目描述：找到倒数第 k 个的元素。
 * Input: [3,2,1,5,6,4] and k = 2
 * Output: 5
 */
public class Code_08_KthLargest {

  //排序
  public static int findKthLargest(int[] nums, int k) {
    Arrays.sort(nums);
    return nums[nums.length - k];
  }
  //堆
  public static int findKthLargestHeap(int[] nums, int k) {
    PriorityQueue<Integer> queue = new PriorityQueue<>();
    for (int val : nums) {
      queue.add(val);
      if (queue.size() > k) {
        queue.poll();
      }
    }
    return queue.peek(); //头元素
  }

  public static int findKthLargestFast(int[] nums, int k) {
    k = nums.length - k;
    int l = 0, h = nums.length - 1;
    while (l < h) {
      //分片
      final int j = partition(nums, l, h);
      if (j == k) {
        break;
      } else if (j < k) {
        l = j + 1;
      } else {
        h = j - 1;
      }
    }
    return nums[k];
  }
  public static int partition(int[] a,int l, int h) {
    int i = l; int j = h + 1;
    while (true) {
      //
      while (a[++i] < a[l] && i < h);
      while (a[--j] > a[l] && j > l);

      if (i >= j) {
        break;
      }
      swap(a,i,j);
    }
    swap(a,l,j);
    return j;
  }
  public static void swap(int[] a ,int i, int j) {
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }
}
