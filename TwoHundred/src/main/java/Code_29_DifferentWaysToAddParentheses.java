import java.util.ArrayList;
import java.util.List;

/**
 *
 * Input: "2-1-1".
 *
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 *
 * Output : [0, 2]
 *
 * 分治：
 * 就是把一个复杂的问题分成两个或更多的相同或相似的子问题，
 * 再把子问题分成更小的子问题……直到最后子问题可以简单的直接求解，
 * 原问题的解即子问题的解的合并
 */
public class Code_29_DifferentWaysToAddParentheses {

  public static void main(String[] args) {
    final Code_29_DifferentWaysToAddParentheses ways = new Code_29_DifferentWaysToAddParentheses();
    final List<Integer> integers = ways.diffWaysToCompute("2-1-1");
    System.out.println(integers);
  }

  private boolean isOp(char c) {
    return '+' == c || '-' == c || '*' == c;
  }

  public List<Integer> diffWaysToCompute(String input) {
    List<Integer> ways = new ArrayList<>();
    for (int i=0;i < input.length(); i++) {
      final char c = input.charAt(i);
      if (isOp(c)) {
        List<Integer> left = diffWaysToCompute(input.substring(0,i));
        List<Integer> right = diffWaysToCompute(input.substring(i+1));
        for (Integer leftValue : left) {
          for (Integer rightValue : right) {
            switch (input.charAt(i)) {
              case '+': ways.add(leftValue + rightValue);break;
              case '-': ways.add(leftValue - rightValue);break;
              case '*': ways.add(leftValue * rightValue);break;
            }
          }
        }
      }
    }
    if (ways.size() == 0) {
      ways.add(Integer.valueOf(input));
    }
    return ways;
  }
}
