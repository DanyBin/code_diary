package tree;

/**
 * 给定节点数为 n 二叉树的前序遍历和中序遍历结果，请重建出该二叉树并返回它的头结点。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建出如下图所示。
 *
 */
public class Code_04_ReConstructBinaryTree {

  /**
   *
   * @param preNums 前序
   * @param inNums  中叙
   * 1. 首先了解前序与中叙
   * 2. 先按照脑中的思考，还原树
   *         1
   *      /   \
   *     2     3
   *    / \    /\
   *   4   7  5  6
   *            /
   *           8
   *  3. 定位元素的位置
   *  通过两个的位置确定具体的元素
   *  3.1 preNums[0],inNums[3] = root
   *  3.2 左子树
   *       preNums[1,3] = [2,4,7]
   *       inNums[0,2] = [4,7,2]
   *      继续左子树
   *        preNums[2,2] = [4]
   *        inNums[0,0] = [4]
   *        终止条件 - 2=2 || 0 = 0
   *      右子树
   *        preNums[3,3] = [7]
   *        inNums[1,1] = [7]
   *
   *
   * */
  public static void main(String[] args) {
    final Code_04_ReConstructBinaryTree code_04_reConstructBinaryTree = new Code_04_ReConstructBinaryTree();
    code_04_reConstructBinaryTree.reConstructBinaryTree(new int[]{1,2,4,7,3,5,6,8},new int[]{4,7,2,1,5,3,8,6});
  }

  int[] preNums;
  int[] inNums;

  public TreeNode reConstructBinaryTree(int[] preNums,int[] inNums) {
    this.preNums = preNums;
    this.inNums = inNums;
    final TreeNode treeNode = buildTree(0, 0, inNums.length-1);
    System.out.println(treeNode);
    return treeNode;
  }

  public TreeNode buildTree(int preStartIndex,int inStartIndex,int inEndIndex) {

    if (preStartIndex > preNums.length  -1 || inStartIndex > inEndIndex) {
      return null;
    }
    final int preNum = preNums[preStartIndex];
    final TreeNode treeNode = new TreeNode(preNum);
    int index = 0;
    for (int i = inStartIndex; i <= inEndIndex; i++) {
      if (preNum == inNums[i]) {
        index = i;
        break;
      }
    }
    treeNode.left = buildTree(preStartIndex+1,inStartIndex,index-1);
    treeNode.right = buildTree(preStartIndex +index - inStartIndex +1,index + 1,inEndIndex);
    return treeNode;
  }
}
