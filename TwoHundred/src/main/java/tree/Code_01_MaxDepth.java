package tree;

/**
 * 给定一个二叉树，找出其最大深度。
 *
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *    3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class Code_01_MaxDepth {
  /**
   * 递归计算节点
   * @param node
   * @return
   */
  public int maxDepth(TreeNode node) {
    if (node == null) {
      return 0;
    }
    return Math.max(maxDepth(node.left),maxDepth(node.right)) + 1;
  }
}
