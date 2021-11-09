package link;

/**
 *给你两个单链表的头节点headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
 *
 * 图示两个链表在节点 c1 开始相交：
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-linked-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *例如以下示例中 A 和 B 两个链表相交于 c1：
 *
 * A:          a1 → a2
 *                     ↘
 *                       c1 → c2 → c3
 *                     ↗
 * B:    b1 → b2 → b3
 *
 */
public class Code_01_GetIntersectionNode {
  private static class Node {
    int val;
    Node next;
    public Node(int val) {
      this.val = val;
    }
  }

  /**
   * O(n2)的方式的遍历即可。 即两个数组中相等的数
   * O(n)的方式。nodeA - 从head节点 nodeB - 从tail节点遍历。若相等时，
   * @param nodeA
   * @param nodeB
   * @return
   */
  public Node getIntersectionNode(Node nodeA,Node nodeB) {
    Node l1 = nodeA;
    Node l2 = nodeB;
    while (l1 != l2) {
      l1 = l1 == null ? nodeB : l1.next;
      l2 = l2 == null ? nodeA : l2.next;
    }
    return l1;
  }
}
