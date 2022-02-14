package tree;

/**
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 本题中，一棵高度平衡二叉树定义为：
 *
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 *
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：true
 *
 * 输入：root = [1,2,2,3,3,null,null,4,4]
 * 输出：false
 */
public class Code_02_isBalanced {

  private boolean ret = true;

  public boolean isBalanced(TreeNode treeNode) {
    maxDepth(treeNode);
    return ret;
  }

  /**
   * 计算树的高度
   * @param treeNode
   * @return
   */
  public int maxDepth(TreeNode treeNode) {
    if (treeNode == null) {
      return 0;
    }
    int l = maxDepth(treeNode.left);
    int r = maxDepth(treeNode.right);
    if (Math.abs(l - r) > 1) {
      ret =false;
    }
    return 1 + Math.max(l,r);
  }
}
