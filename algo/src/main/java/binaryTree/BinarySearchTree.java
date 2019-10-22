package binaryTree;

import org.junit.Test;
import until.TreePrintUtil;

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
 *      删除操作-
 *              1.「要删除节点」没有子节点，更新父节点指针为null即可
 *              2.「要删除节点」只有一个节点（左子节点/右子节点），更新父节点指针 ——> 「要删除节点」的下一个节点
 *              3.「要删除节点」有两个节点，
 *                  a. 找到右子树中最小节点，把它替换成「要删除节点」
 *                  b. 删除最小节点
 *
 * @Link TODO
 **/
public class BinarySearchTree {

    BinaryTreeNode root;

    @Test
    public void test() {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        int[] array = {8, 4, 7, 2, 9, 5, 10};
        for (int v : array) {
            binarySearchTree.insert(v);
        }
        TreePrintUtil.pirnt(binarySearchTree.root);
        binarySearchTree.delete(4);
        TreePrintUtil.pirnt(binarySearchTree.root);


    }


    /**
     * 删除操作
     *
     * @param data
     */
    public Boolean delete(int data) {
        BinaryTreeNode p = this.root;
        BinaryTreeNode pp = null;    //记录父节点
        while (p != null && p.data != data) {
            pp = p;
            if (data > p.data) {
                p = p.right;
            } else {
                p = p.left;
            }
        }

        if (p == null) {
            return false;
        }


        //删除两个节点
        if (p.left != null && p.right != null) {
            BinaryTreeNode minP = p.right;  //右子树
            BinaryTreeNode minPP = p;
            while (minP.left != null) {
                minPP = minP;
                minP = minP.left;
            }

            p.data = minP.data; //替换数据
            p = minP;           //删除minP -- 删除最小节点。 采用下面的方法
            pp = minPP;

        }

        //删除叶子节点或一个节点
        BinaryTreeNode child; //p的子节点
        if (p.left != null) child = p.left;
        else if (p.right != null) child = p.right;
        else child = null;

        if (pp == null) root = child; //删除根节点
        else if (pp.left == p) pp.left = child;
        else pp.right = child;

        return true;
    }

    /**
     * 新增操作
     *
     * @param value
     */
    public void insert(int value) {
        BinaryTreeNode newNode = new BinaryTreeNode(value);
        if (root == null) {
            root = newNode;
            return;
        }
        BinaryTreeNode tree = this.root;
        while (tree != null) {
            if (value < tree.data) {
                if (tree.left == null) {
                    tree.left = newNode;
                    return;
                }
                tree = tree.left;
            } else {
                if (tree.right == null) {
                    tree.right = newNode;
                    return;
                }
                tree = tree.right;
            }
        }
    }
    /**
     * 查找操作
     * @param value
     * @return
     */
    public BinaryTreeNode find(int value) {
        BinaryTreeNode tree = this.root;
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


    private static class BinaryTreeNode implements TreePrintUtil.TreeNode {
        int data;
        BinaryTreeNode left;
        BinaryTreeNode right;

        public BinaryTreeNode(int data){
            this.data = data;
        }


        @Override
        public String toString() {
            return "[" + data + "]";
        }

        @Override
        public String getPrintInfo() {
            return toString();
        }

        @Override
        public TreePrintUtil.TreeNode getLeftChild() {
            return left;
        }

        @Override
        public TreePrintUtil.TreeNode getRightChild() {
            return right;
        }
    }
}
