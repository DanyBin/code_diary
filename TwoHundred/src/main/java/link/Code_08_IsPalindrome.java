package link;

import java.util.Stack;

/**
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 *
 * 输入：head = [1,2,2,1]
 * 输出：true
 *
 * 输入：head = [1,2]
 * 输出：false
 */
public class Code_08_IsPalindrome {

  private static class Node {
    int val;
    Node next;
    public Node(int val) {
      this.val = val;
    }
  }

  /**
   * 举例分析
   * 1 2 2 1
   * stack =  1 2 2 1
   * 若相同
   *
   * 1 2
   * stack = 2 1
   * false
   * @param node
   * @return
   */
  public boolean isPalindrome(Node node) {
    final Stack<Integer> stack = buildStack(node);
    while (node != null) {
      if (stack.pop() != node.val) {
        return false;
      }
      node = node.next;
    }
    return true;
  }
  private Stack<Integer> buildStack(Node node) {
    Node node1 = node;
    final Stack<Integer> stack = new Stack<>();
    while (node1 != null) {
      stack.push(node1.val);
      node1 = node1.next;
    }
    return stack;
  }
}
