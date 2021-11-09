package link;

/**
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 *
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/odd-even-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 1->3->5->2->4->NULL
 */
public class Code_10_OddEvenList {
  private static class Node {
    int val;
    Node next;
    public Node(int val) {
      this.val = val;
    }
  }

  /**
   * 举例分析
   * 输入: 1->2->3->4->5->NULL
   * 输出: 1->3->5->2->4->NULL
   * 奇数Node 与 偶数Node
   * Node = Node.next.next;
   * Node = Node.next;
   *
   * @param node
   * @return
   */
  public Node oddEventList(Node node) {
    Node odd = node;
    Node even = node.next;
    Node evenHead = even;
    while (odd != null && even != null) {
      //奇数
      odd.next = odd.next.next;
      odd = odd.next;
      //偶数
      even.next = even.next.next;
      even = even.next;
    }
    //奇数+偶数
    odd.next = evenHead;
    return node;
  }
}
