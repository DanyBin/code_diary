package tree;

import com.google.common.collect.Maps;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class TreeNode {
  Integer val;
  TreeNode left;
  TreeNode right;
  int leftCnt;
  int rightCnt;
  public TreeNode(int val) {
    this.val = val;
  }

  //前序遍历
  public int countSubTree(TreeNode node) {
    if (null == node) {
      return 0;
    }
    System.out.println(node.val);
   return countSubTree(node.left) + countSubTree(node.right) + 1;
  }

  //中叙遍历
  public void inOrder(TreeNode node) {
    if (null != node) {
      inOrder(node.left);
      System.out.println(node.val);
      inOrder(node.right);
    }
  }

  //后叙遍历
  public void postOrder(TreeNode node) {
    if (null != node) {
      postOrder(node.left);
      postOrder(node.right);
      System.out.println(node.val);
    }
  }

  //层序遍历
  public void seqOrder(TreeNode node) {
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(node);
    while (!queue.isEmpty()) {
      for (int i = 0; i < queue.size();i ++) {
        final TreeNode poll = queue.poll();
        System.out.println(poll.val);
        if (null != poll.left) {
          queue.add(poll.left);
        }
        if (null != poll.right) {
          queue.add(poll.right);
        }
      }
    }
  }
  @Override
  public String toString() {
    countSubTree(this);
    return null;
  }

  public static void main(String[] args) {
    final TreeNode treeNode = new TreeNode(1);
    final TreeNode treeNode2 = new TreeNode(2);
    final TreeNode treeNode3 = new TreeNode(3);
    final TreeNode treeNode4 = new TreeNode(4);
    final TreeNode treeNode5 = new TreeNode(5);
    final TreeNode treeNode6 = new TreeNode(6);

    treeNode.left = treeNode2;
    treeNode.right = treeNode3;
    treeNode2.left = treeNode4;
    treeNode3.left = treeNode5;
    treeNode3.right = treeNode6;
//    final int leftCount = treeNode.countSubTree(treeNode.left);
//    final int rightCount = treeNode.countSubTree(treeNode.right);
//    System.out.println(leftCount + " " + rightCount);
    treeNode.seqOrder(treeNode);
  }
}
