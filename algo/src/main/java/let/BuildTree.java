package let;

import let.domain.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName BuildTree
 * @Author bin
 * @Date 2021/4/7 上午10:31
 * @Decr TODO
 *      根据二叉树的 前序遍历结果 与中叙遍历结果， 还原整个二叉树
 *      思路 - 通过递归的方式。
 *            先构建左子树
 *                  由于前序 与 中叙的 差值等于 左子数的大小
 *            在构建右子树
 * @Link TODO
 **/
public class BuildTree {

    //保存元素对应的下标
    private Map<Integer,Integer> indexMap = new HashMap<>();

    /**
     * @param preorder 前序
     * @param inorder  中叙
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for(int i=0; i < inorder.length; i++) {
            indexMap.put(inorder[i],i);
        }
        return null;
    }

    /**
     *
     * @param preorder 前序结果
     * @param inorder  中叙结果
     * @param p_left_index  前序的left
     * @param p_right_index 前序的right
     * @param in_left_index 中叙的left
     * @param in_right_index 中心的right
     * @return
     */
    public TreeNode recursiveTree(int[] preorder,int[] inorder,int p_left_index,int p_right_index,
                                  int in_left_index,int in_right_index) {
        //终止条件
        if (p_left_index > p_right_index) {
            return null;
        }

        //根节点
        TreeNode root = new TreeNode(preorder[p_left_index]);

        //中叙的根节点
        Integer in_root = indexMap.get(preorder[p_left_index]);

        //获取子节点的数量
        int count = in_root - p_left_index;

        //构建左节点
        root.left = recursiveTree(preorder,inorder,
                p_left_index  + 1,
                p_left_index  + count,
                in_left_index,
                in_root - 1);

        //构建右节点
        root.right = recursiveTree(preorder,inorder,
                p_left_index + count + 1,
                p_right_index,
                in_root - 1,
                in_right_index);
        return root;
    }
}
