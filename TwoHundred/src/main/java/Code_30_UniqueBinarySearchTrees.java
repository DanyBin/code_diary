import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个数字 n，要求生成所有值为 1...n 的二叉搜索树。
 *
 * Input: 3
 * Output:
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * Explanation:
 * The above output corresponds to the 5 unique BST's shown below:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 *
 *  分治，求局部最小解
 */
public class Code_30_UniqueBinarySearchTrees {
 public List<TreeNode> generateTrees(int input) {
    if (input < 1) {
      return new ArrayList<>();
    }
   return generateSubTrees(1,input);
 }
 public List<TreeNode> generateSubTrees(int s,int n) {
    List<TreeNode> ret = new ArrayList<>();
    //递归终止条件
    if (s > n) {
        ret.add(null);
        return ret;
    }
    for (int i = s; i <= n; i++) {
      //左子数 -- 0 - n 之间的
      final List<TreeNode> left = generateSubTrees(s, i - 1);
      //右子数 -- n - length 之间的
      final List<TreeNode> right = generateSubTrees(i+1, n);
      //遍历左子树
      for (TreeNode l : left) {
        //遍历右子树
        for (TreeNode r : right) {
          //创建当前节点
          final TreeNode treeNode = new TreeNode(i);
          treeNode.left = l;
          treeNode.right = r;
          //节点保存
          ret.add(treeNode);
        }
      }
    }
    return ret;
 }
}