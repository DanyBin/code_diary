package binaryTree;

/**
 * @ClassName BinarySearchTree
 * @Author bin
 * @Date 2019/10/21 下午7:49
 * @Decr TODO
 *      二叉查找树(Binary Search Tree) 又称 二叉搜索树
 *          定义: 在树中的任意一个节点，「其左子树中的每个节点的值，都要小于这个节点值」
 *                                  「其右子树中的每个节点的值，都要大于这个节点值」
 *
 *      查找操作- 先取根节点，如果「给定值」 == 根节点的值，返回
 *                         如果「给定值」< 根节点的值，则遍历左子树中递归查找
 *                         反之亦然，则遍历右子树中递归查找
 *
 *      插入操作- 新插入的数据一般都是在叶子节点上，
 *               如果「给定值」< 根节点的值，判断&遍历左子树，当左子节点为空时，插入数据
 *               如果「给定值」> 根节点的值，判断&遍历右子树，当右子节点为空时，插入数据
 *               如果「根节点为空」，则之间当称根节点
 *
 * @Link TODO
 **/
public class BinarySearchTree {

    TreeNode root;



    /**
     * 查找操作
     * @param value
     * @return
     */
    public TreeNode find(int value){
        TreeNode tree = this.root;
        while (tree != null){
            if(value < tree.data){
                tree = tree.left;
            }else if(value > tree.data){
                tree = tree.right;
            }else {
                return tree;
            }
        }
        return  null;
    }


    public static class TreeNode{
        int data;
        TreeNode left;
        TreeNode right;

        public TreeNode(int data){
             this.data = data;
        }
    }
}
