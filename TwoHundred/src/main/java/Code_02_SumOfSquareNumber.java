/**
 * 题目描述：判断一个非负整数是否为两个整数的平方和。
 * Input: 5
 * Output: True
 * Explanation: 1 * 1 + 2 * 2 = 5
 */
public class Code_02_SumOfSquareNumber {
  public static void main(String[] args) {
    sumOfSquare(5);
  }
  public static void sumOfSquare(int input){
    int left = 0;
    int right = input - 1;
    while (left < right) {
      int sum = left*left + right*right;
      if (sum == input) {
        System.out.println(left + " " + right);
        break;
      } else if (sum > input) {
        right --;
      } else {
        left ++;
      }
    }
  }

  public static void sumOfSquare2(int input) {
    int left = 0;
    int right = input - 1;
    while (left < right) {
      int sum = left * left + right * right;
      if (sum == input) {
        System.out.println(left + " " + right);
        break;
      } else if (sum > input) {
        right --;
      } else {
        left ++;
      }
    }
  }

}
