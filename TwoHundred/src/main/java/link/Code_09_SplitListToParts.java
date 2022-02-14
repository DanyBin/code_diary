package link;

/**
 * 给你一个头结点为 head 的单链表和一个整数 k ，请你设计一个算法将链表分隔为 k 个连续的部分。
 *
 * 每部分的长度应该尽可能的相等：任意两部分的长度差距不能超过 1 。这可能会导致有些部分为 null 。
 *
 * 这 k 个部分应该按照在链表中出现的顺序排列，并且排在前面的部分的长度应该大于或等于排在后面的长度。
 *
 * 返回一个由上述 k 部分组成的数组。
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/split-linked-list-in-parts
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 输入：head = [1,2,3], k = 5
 * 输出：[[1],[2],[3],[],[]]
 * 解释：
 * 第一个元素 output[0] 为 output[0].val = 1 ，output[0].next = null 。
 * 最后一个元素 output[4] 为 null ，但它作为 ListNode 的字符串表示是 [] 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/split-linked-list-in-parts
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * 输入：head = [1,2,3,4,5,6,7,8,9,10], k = 3
 * 输出：[[1,2,3,4],[5,6,7],[8,9,10]]
 * 解释：
 * 输入被分成了几个连续的部分，并且每部分的长度相差不超过 1 。前面部分的长度大于等于后面部分的长度。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/split-linked-list-in-parts
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code_09_SplitListToParts {
  private static class Node {
    int val;
    Node next;
    public Node(int val) {
      this.val = val;
    }
  }

  /**
   * 举例分析
   * head = [1,2,3,4,5,6,7,8,9,10], k = 3
   *
   * 1. 计算数组的大小 = n
   * 2. n/k = m (具体的段数)
   *    n%k = y (余数)
   * 3. if (y > 0) {
   *   Node1 = m + 1 ; 的长度
   *   y--;
   * } else {
   *   Node2 = m; 的长度
   * }
   *
   * @param node
   * @param k
   * @return
   */
  public Node[] splitListToParts(Node node,int k) {
    int N = 0;
    Node old = node;
    while (old != null) {
      N++;
      old = old.next;
    }
    int mod = N % k;
    int size = N / k;
    Node cur = node;
    Node[] nodes = new Node[k];
    for (int i = 0; cur != null && i < k; i++) {
      nodes[i] = cur;
      int curSize = size + (mod-- > 0 ? 1 : 0);
      for (int j = 0; j < curSize;j ++) {
        cur = cur.next;
      }

      Node next = cur.next;
      cur.next = null;
      cur = next;
    }
    return nodes;
  }

}
