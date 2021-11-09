package link;

import java.util.Stack;

/**
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 输入：l1 = [7,2,4,3], l2 = [5,6,4]
 * 输出：[7,8,0,7]
 */
public class Code_07_AddTwoNumbers {
  private static class Node {
    int val;
    Node next;
    public Node(int val) {
      this.val = val;
    }
  }

  /**
   * 举例分析
   * 7 2 4 3 & 0 5 6 4
   * 3+4 = 7      7 + 5 = 12
   * 4+6 = 10     2 + 6 = 8
   * 2+5 = 7      4 + 4 = 8
   * 7+0 = 7      3 + 0 = 3
   *
   * nodeA + nodeB
   * nodeA.next = nodeB.next
   * 元素个数不相同
   * 交换元素位置相加？
   *
   *
   * @param nodeA
   * @param nodeB
   * @return
   */
  public Node addTwoNumbers(Node nodeA,Node nodeB) {
    final Stack<Integer> stackA = builStack(nodeA);
    final Stack<Integer> stackB = builStack(nodeB);
    int carry = 0;
    Node head = new Node(-1);
    while (!(stackA.isEmpty() && stackB.isEmpty())) {
      final int a = stackA.isEmpty() ? 0 : stackA.pop();
      final int b = stackB.isEmpty() ? 0 : stackB.pop();
      int sum = a + b + carry;
      final Node node = new Node(sum % 10);
      node.next = head.next;
      head.next = node;
      carry = sum / 10;
    }
    return head;
  }

  public Stack<Integer> builStack(Node node) {
    Stack<Integer> stack = new Stack<>();
    while (node != null) {
      //元素押入栈
      stack.push(node.val);
      node = node.next;
    }
    return stack;
  }
}
