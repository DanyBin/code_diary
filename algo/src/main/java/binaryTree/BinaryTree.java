package binaryTree;

/**
 * @ClassName BinaryTree
 * @Author bin
 * @Date 2019/10/21 下午6:55
 * @Decr TODO
 *       树(tree)
 *          「树」的每个元素都叫做「节点」;用来相连邻节点之间的关系，叫做"父子关系"
 *          节点分为- 根节点、兄弟节点、父节点、子节点、叶子节点。
 *
 *       树的"高度": 节点到叶子节点的「最长路径」(边数) -- height 相当于从下向上度量。例如楼层的高度。
 *       树的"深度": 根节点到这个节点所经历的「边的个数」-- depth  相当于从上向下度量。例如水中鱼的深度。
 *       树的"层树": 树的"深度"+1
 *       树的"高度": 根节点的高度
 *
 *       二叉树(Binary Tree)
 *           每个节点最多有两个"叉"，也就是两个子节点，分别是左子节点和右子节点。
 *
 *       满儿叉树: 除叶子节点外，每个节点都有左右两个子节点。
 *       完全二叉树: 最后一层的叶子节点靠左排列
 *
 *       二叉树的存储方式
 *          1. 基于指针或引用的二叉链式存储法
 *          2. 基于数组的顺序存储法 - 把根节点存储在下标i =1 的位置，一次顺序存储。2*i = 左节点， 2*i+1 = 右节点
 *
 *       完成二叉树，基于数组存储是最节省内存的一种方式。而非完全二叉树会浪费较多的数组存储空间。故出现"完成二叉树"
 *
 *       二叉树的遍历，有三种，前叙遍历、中叙遍历、后序遍历
 *              1. 前叙遍历 - 对于树中任意节点，先打印这个节点， 然后再打印它的左子树，最后打印它的右子树
 *              2. 中叙遍历 - 对于树中任意节点，先打印它的左子树, 然后再打印它本身，最后打印它的右子树
 *              3. 后序遍历 - 对于树中任意节点，先打印它的左子树，然后在打印它的右子树，最后打印这个节点本身
 *
 *      二叉树的遍历 其实就是一个递归的过程，递归公式如下
 *              1. 前叙公式- preOrder(r) = print t -> preOrder(r->left) -> preOrder(r-right)
 *              2. 中叙公式- inOrder(r) = inOrder(r-> left) -> print t -> inOrder(r -> right)
 *              3. 后叙公式- postOrder(r) = postOrder(r->left) -> postOrder(r->right) -> print t
 *
 *
 *
 * @Link TODO
 **/
public class BinaryTree {

    public class Node{
        int data;
        Node left;
        Node right;
    }

    /**
     * 前叙遍历
     * @param root
     */
    public void preOrder(Node root){
        System.out.println(root.data);
        preOrder(root.left);
        preOrder(root.right);
    }

    /**
     * 中叙遍历
     */
    public void inOrder(Node root){
        inOrder(root.left);
        System.out.println(root.data);
        inOrder(root.right);
    }

    /**
     * 后叙遍历
     * @param root
     */
    public void postOrder(Node root){
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.data);
    }

}
