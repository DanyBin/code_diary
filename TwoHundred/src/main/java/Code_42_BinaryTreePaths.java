import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

/**
 * 你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径。
 *
 * 叶子节点 是指没有子节点的节点。
 *
 * 输入：root = [1,2,3,null,5]
 * 输出：["1->2->5","1->3"]
 */
public class Code_42_BinaryTreePaths {

  public List<String> binaryTreePaths(TreeNode treeNode) {
    if (null == treeNode) {
      return Collections.emptyList();
    }
    List<String> ret = Lists.newArrayList();
    dfs(treeNode,new StringBuffer(),ret);
    return ret;
  }

  private void dfs(TreeNode treeNode,StringBuffer buffer,List<String> ret) {
    //终止条件
    if (null == treeNode) {
      ret.add(buffer.toString());
      return;
    }
    //通过递归遍历Node
    buffer.append(treeNode.val).append("->");
    //先走左节点
    dfs(treeNode.left,buffer,ret);
    //回撤节点
    buffer.deleteCharAt(buffer.length() - 1);

    dfs(treeNode.right,buffer,ret);
    //回撤节点
    buffer.deleteCharAt(buffer.length() - 1);
  }
}
