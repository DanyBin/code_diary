package let;

import com.google.common.collect.Maps;

import java.nio.ByteBuffer;
import java.util.Map;
import java.util.Stack;

/**
 * @ClassName Solution
 * @Author bin
 * @Date 2021/3/1 下午7:58
 * @Decr TODO
 * @Link TODO
 **/
public class Solution {

    Map<Integer, Integer> indexMap;
    /**
     *
     */
    Stack<Integer> tail = new Stack<>();
    Stack<Integer> head = new Stack<>();

    public static int findRepeatNumber(int[] nums) {
        int[] t = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            //记录下标的方式
            if (t[nums[i]] != 0) {
                return nums[i];
            }
            t[nums[i]] = i + 1;

        }
        return 0;
    }

    //比较 后一位 横轴，要是大于，继续增加横轴，否则增加纵轴
    public static boolean findNumberIn2DArray(int[][] matrix, int target) {

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int t1 = matrix.length;
        int t2 = matrix[0].length;
        int m = 0, n = t2 - 1;

        //获取右上角元素. 根据从小到大的元素进行比较
        while (m < t1 && n >= 0) {
            if (matrix[m][n] == target) {
                return true;
            }

            if (matrix[m][n] < target) {
                m++;
            } else {
                n--;
            }
        }
        return false;
    }

    //"%20"
    public static String replaceSpace(String s) {
        if (s == null) {
            return s;
        }
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                stringBuilder.append("%20");
            } else {
                stringBuilder.append(chars[i]);
            }
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
//        int[] t = {3, 1, 2, 3};
//        int[][] m  ={{1,4,7,11,15},
//                     {2,5,8,12,19},
//                     {3,6,9,16,22},
//                     {10,13,14,17,24},
//                     {18,21,23,26,30}};
//
//        System.out.println(findNumberIn2DArray(m,2));
//        //System.out.println(findRepeatNumber(t));
//        System.out.println(replaceSpace("    "));
        Solution solution = new Solution();
//        Solution.ListNode listNode1 =  solution.new ListNode(3);
//        Solution.ListNode listNode2 = solution.new ListNode(2);
//        Solution.ListNode listNode3 = solution.new ListNode(1);
//
//        int[] preorder =  {3,9,20,15,7};
//        int[] inorder  = {9,3,15,20,7};
//        solution.buildTree(preorder,inorder);
//
//
//
//        solution.appendTail(1);
//        solution.appendTail(2);
//        System.out.println(solution.deleteHead());
//
//
//        System.out.println(solution.fib(44));
//        System.out.println(solution.numWays(7));
//        int[] number = {3,4,5};
//        System.out.println(solution.minArray(number));

//        char[][] board = {{'a','b','c','e'},{'s','f','c','s'},{'a','d','e','e'}};
//        String word = "bfce";
//        solution.exist(board,word);
//        System.out.println(solution.movingCount(1,2,1));
//        //所有位数之和
//        System.out.println(solution.bitSum(11));

//        System.out.println(solution.cuttingRope(120));
        System.out.println(solution.hammingWeight(12));
        System.out.println(solution.myPow(-1,-2147483648));
        solution.printNumbers(3);

        Solution.ListNode listNode1 =  solution.new ListNode(4);
        Solution.ListNode listNode2 = solution.new ListNode(5);
        Solution.ListNode listNode3 = solution.new ListNode(1);
        Solution.ListNode listNode4 = solution.new ListNode(9);
        listNode1.next = listNode2;
        listNode2.next =listNode3;
        listNode3.next = listNode4;

//        solution.insertTail(listNode1);
//        solution.insertTail(listNode2);
//        solution.insertTail(listNode3);
        ListNode listNode = solution.deleteNode(listNode1, 1);
    }

    public int[] reversePrint(ListNode head) {
        if (head == null) {
            return new int[0];
        }
        //初始数组的大小？
        ListNode old = null;
        ListNode newH = null;
        int size = 0;


        //倒叙遍历即可
        while (head != null) {
            //记录历史
            old = head.next;
            //设置next节点
            head.next = newH;
            //赋值
            newH = head;
            //恢复原始值
            head = old;
            size++;
        }

        //初始化数组的大小
        int[] tmp = new int[size];
        int i = 0;
        while (newH != null) {
            tmp[i] = newH.val;
            newH = newH.next;
        }

        return tmp;
    }

    /**
     * @param preorder - 前序遍历结果
     * @param inorder  - 中叙遍历结果
     */
    public void buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        //构建节点与位置
        indexMap = Maps.newHashMap();
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        //通过递归构建Tree
        myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
    }

    /**
     * 对于前序遍历 = [根节点，[左子树]，[右子树]]
     * 对于中叙遍历 = [[左子树],根节点,[右子树]]
     * 递归公式
     * 1 . 结束条件  =  左子树的index > 右子树
     * 2.  具体公式
     * 对于前序遍历 -
     * 左子树公式 = 左子树+1 ～ 左子树+左子树数量
     * 右子树公式 = 左子树 + 左子树数量 + 1 ～ 右子树边界
     * 对于中叙遍历 -
     * 左子树公式 =  左子树  ~ 根节点 -1
     * 右子树公式 = 根节点+1 ～ 右子树边界
     *
     * @param preorder
     * @param inorder
     * @param preorder_left_index
     * @param preorder_right_index
     * @param inorder_left_index
     * @param inorder_right_index
     * @return
     */
    public TreeNode myBuildTree(int[] preorder, int[] inorder,
                                int preorder_left_index, int preorder_right_index,
                                int inorder_left_index, int inorder_right_index) {
        //对前序遍历，左节点的index < 右节点的index
        if (preorder_left_index > preorder_right_index) {
            return null;
        }

        //根节点
        TreeNode treeNode = new TreeNode(preorder[preorder_left_index]);

        //获取中叙的根节点的位置
        Integer inorder_root = indexMap.get(preorder[preorder_left_index]);

        //获取左节点的数量
        int size = inorder_root - inorder_left_index;

        //获取左子树
        treeNode.left = myBuildTree(preorder, inorder,
                preorder_left_index + 1, // 前序中-左节点+1
                preorder_left_index + size,  //左总节点数量
                inorder_left_index, // 中叙-保存不变
                inorder_root - 1); //对应根节点减少

        //获取右子树
        treeNode.right = myBuildTree(preorder, inorder, preorder_left_index + size + 1,
                preorder_right_index,
                inorder_root + 1,
                inorder_right_index);
        return treeNode;

    }

    public void appendTail(int value) {
        tail.push(value);
    }

    public int deleteHead() {
        if (head.empty()) {
            while (!tail.empty()) {
                head.push(tail.pop());
            }
        }
        if (head.empty()) {
            return -1;
        }
        return head.pop();
    }

    public int fib(int n) {
//        if (n == 0) {
//            return 0;
//        } else if (n == 1) {
//            return 1;
//        }
//        return (fib(n - 1) + fib(n - 2))%1000000008;

        int a = 0;
        int b = 1, sum;
        for (int i = 0; i < n; i++) {
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return a;
    }

    /**
     * 变形的斐波纳切
     *
     * @param n
     * @return
     */
    public int numWays(int n) {
        if (n == 0) {
            return 1;
        } else if (n == 1) {
            return 1;
        }
        return (numWays(n - 1) + numWays(n - 2)) % 1000000008;
    }

    //二分查找法 - 基于high判断
    public int minArray(int[] numbers) {
        int low = 0, high = numbers.length - 1;
        while (low < high) {
            //二分法
            int min = low + (high - low) / 2;

            if (numbers[min] < numbers[high]) {
                high = min;
            } else if (numbers[min] > numbers[high]) {
                low = min + 1;
            } else {
                high = high - 1;
            }
        }
        return numbers[low];
    }

    public boolean exist(char[][] board, String word) {
        char[] chars = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                boolean recursion = recursion(board, i, j, chars, 0);
                if (recursion == true) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 递归公式 - 横轴x,纵轴y。 以及下标 k
     * 如下标k 迭代到最后一个元素，表示都已经找到了。
     *
     * @param matrix
     * @param i
     * @param j
     * @param chars
     * @param k
     * @return
     */
    public boolean recursion(char[][] matrix, int i, int j, char[] chars, int k) {
        //限制数组长度 & //不相同时，直接返回false。迭代下一个元素
        if (0 > i || i > matrix.length - 1 || 0 > j || j > matrix[i].length - 1 || matrix[i][j] != chars[k]) {
            return false;
        }
        //已经都找到了 - 判断char的下标
        if (k == chars.length - 1) {
            return true;
        }
        //标记元素，已经访问过
        matrix[i][j] = '\0';
        boolean res = recursion(matrix, i + 1, j, chars, k + 1) ||
                recursion(matrix, i - 1, j, chars, k + 1) ||
                recursion(matrix, i, j + 1, chars, k + 1) ||
                recursion(matrix, i, j - 1, chars, k + 1);
        //递归结束，还原
        matrix[i][j] = chars[k];
        return res;

    }

    public int movingCount(int m, int n, int k) {
        int[][] visit = new int[m][n];
        int i = movingR(m, n, k, visit, 0, 0);
        return i;
    }

    public int movingR(int m, int n, int k, int[][] visit, int i, int j) {
        if (0 > i || i > m - 1 || 0 > j || j > n - 1 || 0 > k || k > 20 || (bitSum(i) + bitSum(j)) > k || visit[i][j] == 1) {
            return 0;
        }
        visit[i][j] = 1;
        return 1 + movingR(m, n, k, visit, i + 1, j) +
                movingR(m, n, k, visit, i - 1, j) +
                movingR(m, n, k, visit, i, j + 1) +
                movingR(m, n, k, visit, i, j - 1);
    }

    /**
     * 计算是位数之和
     *
     * @param
     * @return
     */
    public int bitSum(int x) {
        int s = 0;
        while (x != 0) {
            s += x % 10;
            x = x / 10;
        }
        return s;
    }

    public int cuttingRope(int n) {
        return cuttingR(n, 1, 1);
    }

    /**
     * 贪心算法- 遍历求解所有的答案
     * 将n分为m份
     * n/m = 取整 = x
     * n%m = 取余 = y
     * 从数组的角度看
     * [[0-x][x - x+x][x+x,x+x+x+y]]
     *
     * @param n
     * @param m
     * @param max
     * @return
     */
    public int cuttingR(int n, int m, int max) {
        //遍历结束，直接取最大值返回
        if (m < 0 || m > n - 1) {
            return max;
        }
        //取整数
        int x = n / m;
        //对余数做处理
        int y = n % m;
        int sum = 1;
        //做正数处理
        for (int i = 0; i < m - y; i++) {
            sum = (sum * x) % 1000000008;
        }
        //将余数分解，到各个正数中
        for (int i = 0; i < y; i++) {
            sum = (sum * (x + 1)) % 1000000008;
        }
        int tmp = sum > max ? sum : max;
        return cuttingR(n, m + 1, tmp);
    }

    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            //&运算。 n&1 = 1
            count += n&1;
            //向右移动
            n >>= 1;
        }
        return count;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public double myPow(double x, int n) {
        //正数
        if (x == 1) {
            return 1;
        }

        //特殊处理-负数
        if (-1 == x ) {
            if (Integer.MIN_VALUE == n) {
                return 1 ;
            }
            return -1;
        }

        if (n == 0) {
            return 1;
        }
        //特殊处理
        if (Integer.MIN_VALUE == n || Integer.MAX_VALUE == n) {
            return 0;
        }

        //计算n > 0 的情况
        if (n >= 0) {
           return sum(x,n);
        } else {
            //计算 n < 0 的情况
            n = -n;
            double sum = sum(10, n);
            double sum1 = sum(x, n);
            double t  = sum/sum1;
            for (int i=0; i< n; i ++) {
                t *= 0.1;
            }
            return t;

        }
    }

    public double sum(double x, int n){
        double sum =  1.0;
        for (int i = 0; i < n;i++) {
            sum *= x;
        }
        return sum;
    }


    public int[] printNumbers(int n) {
        int var = 1;
        for (int i=0;i < n; i++) {
            var *= 10;
        }
        int[] ret = new int[var-1];
        for (int i = 0;i < var-1 ;i ++) {
            ret[i] = i + 1;
        }
        return ret ;
    }

    /**
     * 注意ListNode 是由对象构造构成的，通过修改对象的指针即可修改 原本的对象
     * @param head
     * @param val
     * @return
     */
    public ListNode deleteNode(ListNode head, int val) {
        if (head.val == val) return head.next;
        //获取前序节点  与 后续节点
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null && cur.val != val) {
            //保证前序节点
            pre = cur;
            //获取后续节点
            cur = cur.next;
        }
        //通过对象指针，修改位置地址，达到修复head对象的方式
        if (cur != null) {pre.next = cur.next;}
        return head;
    }



    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }


}
