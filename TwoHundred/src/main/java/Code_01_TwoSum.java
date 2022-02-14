import java.util.Arrays;

/**
 * 题目描述：在有序数组中找出两个数，使它们的和为 target。
 */
public class Code_01_TwoSum {
  public static void main(String[] args) {
    int[] numbers = {2, 7, 11, 15};
    int target = 9;
    System.out.println(Arrays.stream(twoSum(numbers,target)).toArray());
  }

  /**
   * 双指针的方式
   * @param numbers
   * @param target
   * @return
   */
  private static int[] twoSum(int[] numbers,int target) {
    int index1 = 0;
    int index2 = numbers.length - 1;

    while (index1 < index2) {
      int sum = numbers[index1] + numbers[index2];
      if (sum == target) {
        return new int[]{numbers[index1],numbers[index2]};
      } else if (sum > target) {
        index2 -- ;
      } else {
        index1 ++;
      }
    }
    return null;
  }

  private static int[] twoSum2(int[] numbers,int target) {
    int index1 = 0;
    int index2 = numbers.length - 1;
    while (index1 < index2) {
      int sum = numbers[index1] + numbers[index2];
      if (sum == target) {
        return new int[]{numbers[index1],numbers[index2]};
      } else if (sum > target) {
        index2 --;
      } else {
        index1 ++;
      }
    }
    return null;
  }
}

