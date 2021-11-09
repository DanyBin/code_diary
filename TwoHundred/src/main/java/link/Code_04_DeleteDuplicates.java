package link;

/**
 * 从有序链表中删除重复节点
 */
public class Code_04_DeleteDuplicates {
  private static class Node {
    int val;
    Node next;
    public Node(int val) {
      this.val = val;
    }
  }

  public Node deleteDuplicates(Node node) {
    if (node == null || node.next == null) {
      return node;
    }
    node.next = deleteDuplicates(node.next);
    return node.val == node.next.val ? node.next : node;
  }

}
