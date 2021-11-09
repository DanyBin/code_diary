/**
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 */
public class Code_06_LinkedCycle {
  public boolean hasCycle(ListNode head) {
    if (null == head) {
      return false;
    }
    //双指针
    ListNode l1 = head,l2 = head.next;
    while (l1 != null && l2 != null && l2.next != null) {
      if (l1 == l2) {
        return true;
      }
      l1 = l1.next;
      l2 = l2.next.next;
    }
    return false;
  }
}
