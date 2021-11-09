package link;

/**
 * 6. 交换链表中的相邻结点
 *
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 */
public class Code_06_SwapPairs {

  private static class Node {
    int val;
    Node next;
    public Node(int val) {
      this.val = val;
    }
  }

  public static void main(String[] args) {
    final Code_06_SwapPairs pairs = new Code_06_SwapPairs();
    Node ret = new Node(-1);
    for (int i = 1;i <= 4;i ++) {
      final Node val = new Node(i);
      pairs.insertTail(ret,val);
    }
    pairs.swapPair(ret.next);
  }

  /**
   * 举例分析方法
   *  【1 2 3 4】
   *   1
   *   1.next = 2
   *   2.next = 1;
   *   node = 2.next.next
   *   3.next = 4
   *   4.next = 3
   *   if (2.next.next == null) 表示没有可以交互的数据。跳出
   * @param node
   * @return
   */
  public Node swapPair(Node node) {
    Node t = node;
    Node ret = new Node(-1);
    while (t != null) {
      final Node cur = new Node(t.val);
      if (t.next != null) {
        final Node next = new Node(t.next.val);
        next.next = cur;
        insertTail(ret,next);
      }else {
        insertTail(ret,cur);
      }
      t = t.next.next;
    }
    return ret.next;
  }
  private void insertTail(Node node,Node val) {
    Node t = node;
    while (true) {
      if (t.next == null) {
        t.next = val;
        break;
      }
      t = t.next;
    }
  }
}

