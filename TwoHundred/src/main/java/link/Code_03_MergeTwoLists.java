package link;

/**
 * 归并两个有序的链表
 */
public class Code_03_MergeTwoLists {
  private static class Node {
    int val;
    Node next;
    public Node(int val) {
      this.val = val;
    }
  }

  public Node mergeTwoLists(Node node1,Node node2) {
    if (node1 == null) return node2;
    if (node2 == null) return node1;

    if (node1.val < node2.val) {
      node1.next = mergeTwoLists(node1.next,node2);
      return node1;
    } else {
      node2.next = mergeTwoLists(node1,node2);
      return node2;
    }
  }
}
