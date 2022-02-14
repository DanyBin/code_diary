package link;

/**
 *
 * 5. 删除链表的倒数第 n 个节点
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 * 进阶：你能尝试使用一趟扫描实现吗？
 *
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 */
public class Code_05_RemoveNthFromEnd {
  private static class Node {
    int val;
    Node next;
    public Node(int val) {
      this.val = val;
    }
  }


  public static void main(String[] args) {
    final Code_05_RemoveNthFromEnd end = new Code_05_RemoveNthFromEnd();
    Node node = new Node(-1);
    for (int i=1 ; i <= 5 ; i ++) {
      insertTail(node,i);
    }
    end.removeNthFromEnd(node,2);
  }
  //头插入方法
  public static void insertTail(Node node,int val) {
    Node t = node;
    while (true) {
      if (t.next == null) {
        t.next = new Node(val);
        break;
      }
      t = t.next;
    }
  }

  /**
   * 1. 计算链表的长度
   * 举例分析 -- 1   == 1
   *           1 - 2 == 2
   *           1 - 2 - 3 == 3
   *         2 1-  2- 3 - 4 == 4
   *         1 1- 2- 3- 4- 5 == 5
   *
   *         2 - 1 = 1
   *         2 - 2 = 0
   *         2 - 3 = -1
   *         2 - 4 = -2 if (-2 + 2) == 0 时，表示到达节点
   * @param node
   * @return
   */
  public Node removeNthFromEnd(Node node,int n) {
    Node t = node;
    int k = 0;
    while (t != null) {
      k++;
      if (n - k + n == 0) {
        t.next = t.next.next;
        break;
      }
      t = t.next;
    }
    return node.next;
  }
}
