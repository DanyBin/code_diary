package link;

/**
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 */
public class Code_02_ReverseList {
  private static class Node {
    int val;
    Node next;
    public Node(int val) {
      this.val = val;
    }
  }


  public Node reverseList(Node node) {
    Node head = null;
    while (node != null) {
      if (null == head) {
        head = new Node(node.val);
      } else {
        final Node node1 = new Node(node.val);
        node1.next = head;
        head = node1;
      }
      node = node.next;
    }
    return head;
  }
}
