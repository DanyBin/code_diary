package tree;

/**
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点
 *
 * 示例 :
 * 给定二叉树
 *
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * 返回3, 它的长度是路径 [4,2,1,3] 或者[5,2,1,3]。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/diameter-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code_03_diameterOfBinaryTree {

  private int max = 0;

  public int diameterOfBinaryTree(TreeNode treeNode) {
    maxDepth(treeNode);
    return max;
  }

  public int maxDepth(TreeNode treeNode) {
    if (treeNode == null) {
      return 0;
    }
    int l = maxDepth(treeNode.left);
    int r = maxDepth(treeNode.right);
    max = Math.max(max,l+r);
    return 1 + Math.max(l,r);
  }
}
