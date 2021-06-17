package let;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.*;

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
        int[][] m = {{1, 2},
                {4, 5},
                {7, 8},
                {13, 14}};
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
//        System.out.println(solution.NumWays(7));
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

        Solution.ListNode listNode1 = solution.new ListNode(1);
        Solution.ListNode listNode2 = solution.new ListNode(3);
        Solution.ListNode listNode3 = solution.new ListNode(7);
        listNode1.next = listNode2;
        listNode2.next =listNode3;

        Solution.ListNode v2 = solution.new ListNode(3);
        Solution.ListNode v3 = solution.new ListNode(5);
        Solution.ListNode v4 = solution.new ListNode(6);
        v2.next =v3;
        v3.next = v4;

        Solution.ListNode s1 = solution.new ListNode(4);
        Solution.ListNode s2 = solution.new ListNode(8);
        s1.next =s2;

//        solution.insertTail(listNode1);
//        solution.insertTail(listNode2);
//        solution.insertTail(listNode3);
//        ListNode listNode = solution.deleteNode(listNode1, 1);
//        System.out.println(solution.isNumber("959440.94f"));
//        solution.exchange(new int[]{1,2,3,4});
//        ListNode kthFromEnd = solution.getKthFromEnd(listNode1, 3);
//        System.out.println(kthFromEnd);

//        solution.mergeTwoLists(null, v1);

        TreeNode t1 = solution.new TreeNode(1);
        TreeNode t2 = solution.new TreeNode(2);
        TreeNode t3 = solution.new TreeNode(2);
        TreeNode t4 = solution.new TreeNode(4);
        TreeNode t5 = solution.new TreeNode(5);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t3.left = t5;
        t3.right = t4;


        TreeNode tt2 = solution.new TreeNode(2);
        TreeNode tt4 = solution.new TreeNode(4);
        tt2.left = tt4;

        // solution.isSubStructure(t1,tt2);
        // TreeNode treeNode = solution.mirrorTree(t1);
//       solution.first(t1);
//       solution.isSymmetric(t1);
//
//       solution.spiralOrder(m);


        MinStack minStack = solution.new MinStack();
        minStack.push(-3);
        minStack.push(0);
        minStack.push(-2);
        System.out.println(minStack.top());
        minStack.pop();
        System.out.println(minStack.min());

        int[] pushed = {0, 0, 0, 0, 0}, popped = {-1, 0, 0, 0, 0, 0, 1};

//        solution.validateStackSequences(pushed,popped);

//        solution.addTwoNumbers(listNode1,listNode1);
//
//        solution.lengthOfLongestSubstring("aab");

        System.out.println(solution.findMedianSortedArrays(pushed, popped));
        //System.out.print(solution.longestPalindrome("ibawpzhrunsgfobmenlqlxnprtgijgbeicsuoihnmcekzmvtffmlpzuwlimuuzjhkzppmpqqrfwyrjrsltkypjpcjffpvhtdiwjdonutobpecsiqubiusvwsyhrddqjeqqpgofifmwvmcdjixjvjxrvyabqaqumfqiiqxizmhzevhxutsbgzcfggyyvolwaxfcpjhfpksxvgyxhddcssnxhygzvmyxrxqizzhpluxkautjmieximoskcffimctsfzgmihtoxkltopwobtfjvjymtuknxmsgevkeklprcaudidywwkfuhtatpeeiewczpwiegmpjquayfleczrvzekikbaeocpcurtxhcsysbbsyschxtrucpcoeabkikezvrzcelfyauqjpmgeiwpzcweieeptathufkwwydiduacrplkekvegsmxnkutmyjvjftbowpotlkxothimgzfstcmiffcksomixeimjtuakxulphzziqxrxymvzgyhxnsscddhxygvxskpfhjpcfxawlovyyggfczgbstuxhvezhmzixqiiqfmuqaqbayvrxjvjxijdcmvwmfifogpqqejqddrhyswvsuibuqiscepbotunodjwidthvpffjcpjpyktlsrjrywfrqqpmppzkhjzuumilwuzplmfftvmzkecmnhiousciebgjigtrpnxlqlnembofgsnurhzpwabi"));
        System.out.println(1 / 10);
        System.out.println(solution.reverse(-12345));
        System.out.println(solution.myAtoi("   -42"));
        System.out.println(solution.isMatch("aab", "a*b"));
        System.out.println(solution.letterCombinations("5678"));
        solution.random();
        System.out.println(SupportPoiExtendRecall.class.getSimpleName());
        System.out.println(solution.isValid("([)]"));


        //构建ListNodeList
        List<ListNode> listNodes = new ArrayList<>();
        listNodes.add(listNode1);
        listNodes.add(v2);
        listNodes.add(s1);
        ListNode[] listNodeArray = new ListNode[3];
        listNodeArray[0] = listNode1;
        listNodeArray[1] = v2;
        listNodeArray[2] = s1;
        solution.mergeKLists(listNodeArray);


        //
        //int i = solution.removeDuplicates(new int[]{1, 1, 2});
        solution.divide(-2147483648,-1);

        int[] tmp = new int[]{5,7,7,8,8,10};
        int target = 13;
        solution.searchRange(tmp,target);
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
        ListNode(int x,ListNode next) {
            val = x;
            this.next = next;
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


    public boolean isNumber(String s) {
        if (null == s || s.length() == 0) {
            return false;
        }

        s = s.trim();
        try {
            double aDouble = Double.valueOf(s);
            System.out.println(aDouble);
        } catch (NumberFormatException e) {
            return false;
        }

        //特殊处理
        char c = s.charAt(s.length() - 1);
        if ((c >= '0' && c <= '9') || c == '.') {
            return true;
        }
        return false;
    }

    public int[] exchange(int[] nums) {
        int fast = 0, low = 0;
        while (fast < nums.length) {
            //奇数的位置-fast
            if (nums[fast] % 2 == 1) {
                int tmp = nums[fast];
                nums[fast] = nums[low];
                nums[low] = tmp;
                //偶数的位置
                low++;
            }
            fast++;
        }
        return nums;
    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        //倒叙遍历
        ListNode newNode = null;
        while (head != null) {
            ListNode tmp = head.next;
            head.next = newNode;
            newNode = head;
            head = tmp;
        }

        //倒叙遍历
        ListNode var = null;
        int size = 0;
        while (newNode != null) {
            if (size == k) {
                return var;
            }
            ListNode tmp = newNode.next;
            newNode.next = var;
            var = newNode;
            newNode = tmp;
            size++;
        }
        return var;
    }

    /**
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode newNode = null;
        while (l1 != null || l2 != null) {
            //1.先判断为null的情况
            if (l1 != null && l2 != null) {
                if (l1.val <= l2.val) {
                    ListNode next = l1.next;
                    l1.next = null;
                    newNode = insert(newNode, l1);
                    l1 = next;
                } else {
                    ListNode next = l2.next;
                    l2.next = null;
                    newNode = insert(newNode, l2);
                    l2 = next;
                }
            }

            //增加l2
            if (l1 == null) {
                ListNode next = l2.next;
                l2.next = null;
                newNode = insert(newNode, l2);
                l2 = next;
            }

            //增加l1
            if (l2 == null && l1 != null) {
                ListNode next = l1.next;
                l1.next = null;
                newNode = insert(newNode, l1);
                l1 = next;
            }
        }
        return newNode;
    }

    /**
     * 头部插入
     *
     * @param newNode
     * @param node
     * @return
     */
    public ListNode insert(ListNode newNode, ListNode node) {
        if (newNode == null) {
            newNode = node;
        } else {
            ListNode tmp = newNode;
            while (true) {
                if (tmp.next == null) {
                    tmp.next = node;
                    break;
                }
                tmp = tmp.next;
            }
        }
        return newNode;
    }

    //先叙遍历
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        return (A != null && B != null) && (recur(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B));

    }

    //递归遍历
    public boolean recur(TreeNode A, TreeNode B) {
        if (B == null) return true;
        if (A == null || A.val != B.val) return false;
        return recur(A.left, B.left) && recur(A.right, B.right);

    }


    /**
     * 通过先叙遍历。获取最后一层的即可。
     * 先遍历右节点，添加到左节点中
     * 在遍历左节点，添加到右节点中
     */
    public TreeNode mirrorTree(TreeNode root) {

        if (root == null) {
            return null;
        }
        //1. 创建跟节点
        TreeNode treeNode = new TreeNode(root.val);
        treeNode.left = mirrorTree(root.right);
        treeNode.right = mirrorTree(root.left);
        return treeNode;
    }

    public void first(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root.val);
        first(root.left);
        first(root.right);
    }

    public boolean isSymmetric(TreeNode root) {
        return isSymmetricR(root.left, root.right);
    }

    /**
     * 左右节点同时遍历，进行比较
     * 终止条件 - null
     *
     * @param r1
     * @param r2
     * @return
     */
    public boolean isSymmetricR(TreeNode r1, TreeNode r2) {
        //终止条件
        if (r1 == null && r2 == null) {
            return true;
        }

        if (r1 == null || r2 == null || r1.val != r2.val) {
            return false;
        }

        return isSymmetricR(r1.left, r2.right) && isSymmetricR(r1.right, r2.left);
    }


    public int[] spiralOrder(int[][] matrix) {
        int y = 0;
        int MaxY = matrix[0].length - 1;

        int x = 0;
        int MaxX = matrix.length - 1;

        int k = 0;
        int[] tmp = new int[(MaxX + 1) * (MaxY + 1)];
        while (true) {

            //从左到右
            for (int i = y; i <= MaxY; i++) {
                tmp[k++] = matrix[x][i];
            }
            //判断x轴
            if (++x > MaxX) {
                break;
            }
            //从上倒下
            for (int i = x; i <= MaxX; i++) {
                tmp[k++] = matrix[i][MaxY];
            }

            if (y > --MaxY) {
                break;
            }
            //从右到左
            for (int i = MaxY; i >= y; i--) {
                tmp[k++] = matrix[MaxX][i];
            }

            if (x > --MaxX) {
                break;
            }
            //从下到上
            for (int i = MaxX; i >= x; i--) {
                tmp[k++] = matrix[i][y];
            }

            if (++y > MaxY) {
                break;
            }
        }
        return tmp;
    }

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int k = 0;
        int pre = 0;
        for (int i = 0; i < pushed.length; i++) {
            //入栈
            stack.push(pushed[i]);
            //循环判断-出栈的元素是否在popped中。
            while (!stack.isEmpty() && stack.peek() == popped[k]) {
                stack.pop();
                k++;
            }
        }
        //判断最后栈的元素是否出完
        return stack.isEmpty();
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode newNode = new ListNode(0);
        ListNode tmpNode = newNode;
        while (true) {
            if (l1 == null && l2 == null) {
                break;
            }

            int sum;
            if (l1 == null) {
                sum = l2.val;
            } else if (l2 == null) {
                sum = l1.val;
            } else {
                sum = l1.val + l2.val;
            }

            sum = tmpNode.val + sum;
            tmpNode.val = sum % 10;

            if (l1 != null) {
                l1 = l1.next;
                if (l1 != null && tmpNode.next == null) {
                    tmpNode.next = new ListNode(sum / 10);
                }
            }

            if (l2 != null) {
                l2 = l2.next;
                if (l2 != null && tmpNode.next == null) {
                    tmpNode.next = new ListNode(sum / 10);
                }
            }
            if (tmpNode.next == null && sum / 10 > 0) {
                tmpNode.next = new ListNode(sum / 10);
            }
            tmpNode = tmpNode.next;
        }
        return newNode;
    }

    public int lengthOfLongestSubstring(String s) {
        if (s.isEmpty()) {
            return 0;
        }
        //存放<字符，下标>
        Map<Character, Integer> map = new HashMap<>();
        int max = 0; //最大子串的长度
        int left = 0;//左边界
        for (int i = 0; i < s.length(); i++) {

            //对相同字符串的处理
            if (map.containsKey(s.charAt(i))) {
                //如果有相同。 对原来的边界+1 。例如 abc时 left = 0  abda时。 left = 1
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i); //正在添加元素与位置
            //i=当前位置. left=左边界  +1 元素值
            max = Math.max(max, i - left + 1);
        }
        return max;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int sum = nums1.length + nums2.length;
        int type = 0;
        int mid = sum / 2;
        //奇数
        if (sum % 2 == 1) {
            type = 1;

        }

        //迭代即可，通过记录 num1的下标  与 num2 的下标
        int num1Index = 0;
        int num2Index = 0;
        double value = 0;
        for (int i = 0; i < sum; i++) {
            int tmp;
            if (num1Index > nums1.length - 1) {
                tmp = nums2[num2Index];
                double v = compareValue(i, mid, tmp, type, num1Index, num2Index, nums1, nums2);
                if (0 != v) {
                    value = v;
                    break;
                }
                num2Index++;

            } else if (num2Index > nums2.length - 1) {
                tmp = nums1[num1Index];
                double v = compareValue(i, mid, tmp, type, num1Index, num2Index, nums1, nums2);
                if (0 != v) {
                    value = v;
                    break;
                }
                num1Index++;
            } else {
                if (nums1[num1Index] <= nums2[num2Index]) {
                    tmp = nums1[num1Index];
                    double v = compareValue(i, mid, tmp, type, num1Index, num2Index, nums1, nums2);
                    if (0 != v) {
                        value = v;
                        break;
                    }
                    num1Index++;
                } else {
                    tmp = nums2[num2Index];
                    double v = compareValue(i, mid, tmp, type, num1Index, num2Index, nums1, nums2);
                    if (0 != v) {
                        value = v;
                        break;
                    }
                    num2Index++;
                }
            }
        }
        return value;
    }

    private double compareValue(int i, int mid, int tmp, int type, int num1Index, int num2Index, int[] nums1, int[] nums2) {
        double value = 0;
        //奇数
        if (i == mid && type == 1) {
            value = tmp * 1.0;
        }
        //偶数
        if (i == mid && type == 0) {
            int pre;
            if (num1Index == 0) {
                pre = nums2[--num2Index];
            } else if (num2Index == 0) {
                pre = nums1[--num1Index];
            } else {
                pre = nums1[--num1Index] > nums2[--num2Index] ? nums1[num1Index] : nums2[num2Index];
            }
            value = (pre * 1.0 + tmp * 1.0) / 2;
        }
        return value;
    }

    public String longestPalindrome(String s) {
        HashMap<Character, List<Integer>> map = new HashMap<>();
        int left = 0;
        int right = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                List<Integer> list = map.get(s.charAt(i));

                for (Integer j : list) {
                    if (isStr(j, i, s)) {
                        //校验最大
                        if ((i - j) > (right - left)) {
                            left = j;
                            right = i;
                        }
                    }
                }

                //记录历史出现的位置
                list.add(i);
            } else {
                //只记录第一次出现的位置
                ArrayList<Integer> integers = new ArrayList<>();
                integers.add(i);
                map.put(s.charAt(i), integers);
            }
        }
        return s.substring(left, right + 1);
    }

    public boolean isStr(int min, int max, String s) {
        //特殊情况 aa
        if ((min + 1) == max) {
            return true;
        }

        while (min < max) {
            if (s.charAt(++min) != s.charAt(--max)) {
                return false;
            }
        }
        return true;
    }

    public int reverse(int x) {
        Long value = 0L;
        while (x != 0) {
            value = value * 10 + x % 10;
            x = x / 10;
        }

        if (value > Integer.MAX_VALUE || value < Integer.MIN_VALUE) {
            return 0;
        }
        return value.intValue();
    }

    public int myAtoi(String s) {
        int stats = 0;
        char[] chars = s.toCharArray();
        int size = -1;
        int index = 0;
        for (int i = 0; i < chars.length; i++) {
            //跳出解析。 数值下一个元素 是 空格的情况
            if (chars[i] == ' ' && size != -1) {
                break;
            }

            if (chars[i] != ' ') { //标示空格
                if (stateMachine(stats, chars[i])) {
                    size++;
                    index = i;
                    stats++;
                } else {
                    break;
                }
            }
        }
        if (size == -1) {
            return 0;
        }

        Long value = 0L;
        for (int i = (index - size); i <= index; i++) {
            //45 =减号
            char c = s.charAt(i);
            if (c == '-' || c == '+') {
                continue;
            }
            value = value * 10 + (s.charAt(i) - '0');
            if (value > Integer.MAX_VALUE) {
                if (s.charAt((index - size)) == '-') {
                    return Integer.MIN_VALUE;
                } else {
                    return Integer.MAX_VALUE;
                }
            }
        }
        if (s.charAt((index - size)) == '-') {
            return -value.intValue();
        }
        return value.intValue();

    }

    public boolean stateMachine(int i, char c) {
        //第一层
        if (i == 0 && (c == '-' || c == '+' || (c >= '0' && c <= '9'))) {
            return true;
        }

        //之后的
        if (i > 0 && ((c >= '0' && c <= '9'))) {
            return true;
        }
        return false;
    }

    /**
     * 通过动态规划的方式，解决问题
     * 1. 构建矩阵
     * 注意 矩阵的边界是，由 i = 0 ,j = 1 来递归判断
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        //记录是否相同
        int m = s.length();
        int n = p.length();


        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            //
            for (int j = 1; j <= n; ++j) {
                //特殊处理*号.判断前一位是否为*
                if (p.charAt(j - 1) == '*') {
                    //移动位置
                    f[i][j] = f[i][j - 2];
                    //是否相同。注意j的位置
                    if (match(i, j - 1, s, p)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }

                } else {
                    if (match(i, j, s, p)) {
                        f[i][j] = f[i - 1][j - 1];
                    }

                }
            }
        }
        //注意结果
        return f[m][n];
    }

    public boolean match(int i, int j, String s, String p) {
        //终止条件- 边界
        if (i == 0) {
            return false;
        }

        //对于.的处理
        if (p.charAt(j - 1) == '.') {
            return true;
        }

        //判断前面的元素是否相同
        return s.charAt(i - 1) == p.charAt(j - 1);
    }

    /**
     * 双层for找出最大值
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                //获取最小值
                int min = Math.min(height[i], height[j]);
                max = Math.max(max, min * (j - i));
            }
        }
        return max;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        //排序
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {

            int k = nums.length - 1;
            // 保存本次与上次不同
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int target = -nums[i];

            for (int j = i + 1; j < nums.length; j++) {

                // 需要和上一次枚举的数不相同
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }


                // 需要保证 b 的指针在 c 的指针的左侧
                while (j < k && (nums[j] + nums[k] > target)) {
                    --k;
                }

                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (j == k) {
                    break;
                }

                //相等
                if (nums[j] + nums[k] == target) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[k]);
                    res.add(list);
                }
            }

        }
        return res;
    }

    public List<String> letterCombinations(String digits) {
        if(null == digits || digits.isEmpty()) {
            return new ArrayList<>();
        }
        Map<Integer, List<Character>> map = Maps.newHashMap();
        map.put(2, init("abc"));
        map.put(3, init("def"));
        map.put(4, init("ghi"));
        map.put(5, init("jkl"));
        map.put(6, init("mno"));
        map.put(7, init("pqrs"));
        map.put(8, init("tuv"));
        map.put(9, init("wxyz"));

        int length = 1;
        List<List<Character>> tmp = new ArrayList<>();
        for (int i = 0; i < digits.length(); i++) {
            Character c = digits.charAt(i);
            Integer key = Integer.valueOf(c.toString());
            List<Character> characters = map.get(key);
            length = length * characters.size();
            tmp.add(characters);
        }

        //通矩阵进行数据填充
        char[][] r = new char[length][tmp.size()];
        int innerSize = 1;
        for (int i = 0; i < tmp.size(); i++) {
            int k = 0;
            //内循环
            innerSize = innerSize*tmp.get(i).size();

            //判断边界条件
            while (k < length) {
                for (int j = 0; j < tmp.get(i).size(); j++) {
                    //单个元素要执行的次数
                    int l = length/innerSize;
                    while (l-- > 0) {
                        r[k][i] = tmp.get(i).get(j);
                        k++;
                    }
                }
            }
        }
        List<String> res = new ArrayList<>();
        for (int i = 0; i < r.length; i++) {
            String value = "";
            for (int j = 0; j < r[i].length; j++) {
                Character c = r[i][j];
                value = value + c.toString();
            }
            res.add(value);
        }
        System.out.println(r.toString());
        return res;
    }

    public List<Character> init(String str) {
        List<Character> objects = new ArrayList<>(str.length());
        for (int i = 0; i < str.length(); i++) {
            objects.add(str.charAt(i));
        }
        return objects;
    }

    //使用双Stack的方式来实现
    class MinStack {

        int[] stack = new int[10];
        int index = 0;
        int min = 0;
        int max = 0;
        int k = 0;

        /**
         * initialize your data structure here.
         */
        public MinStack() {

        }

        public void push(int x) {
            //比较大小，更新下标
            if (index > 0) {
                min = stack[min] < x ? min : index;

                max = stack[max] > x ? max : index;
            }

            stack[index++] = x;
        }

        public void pop() {
            //重新更新下标
            if (k == max) {
                //获取 k - index 之间的最大值
                int t = k;
                for (int i = k; i < index; i++) {
                    if (i + 1 < index - 1) {
                        t = stack[i] > stack[i + 1] ? i : i + 1;
                    }
                }
                max = t;
            }

            if (k == min) {
                //获取k - index 之间的最小值
                int t = k;
                for (int i = k; i < index; i++) {

                    t = stack[i] < stack[i + 1] ? i : i + 1;
                    if (i + 1 == index - 1) {
                        break;
                    }
                }
                min = t;
            }
            k++;
        }

        public int top() {
            return stack[max];
        }

        public int min() {
            return stack[min];
        }
    }

    //随机数测试
    public void random() {
        Random random = new Random();
        for (int i=0; i < 10; i ++) {
            //System.out.println(random.nextInt(30));
        }

        Random random1 = new Random();
        for (int i=0; i < 10; i ++) {
            //System.out.println(random1.nextInt(30));
        }
        int i =10;
        while (i -- > 0) {
            System.out.println(random.nextInt(30));
        }


//        for (int i=0;i < 10 ;i ++) {
//            double random1 = Math.random();
//            System.out.println(random1);
//        }

    }

    /**
     * 快慢指针的方式来记录
     * 快指针[n,length]
     * 慢指针[0,length]
     * 要删除的的指针[0,n-1]
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //创建亚节点
        ListNode dummy = new ListNode(0,head);
        ListNode q = head;
        ListNode s = dummy;

        //获取快指针
        for (int i=0; i< n; i++) {
            q = q.next;
        }

        //快慢指针一起迭代
        while (q != null) {
            q = q.next;
            s = s.next;
        }
        s.next = s.next.next;
        return dummy.next;
    }


    public boolean isValid(String s) {
        Map<Character,Character> map = Maps.newHashMap();
        map.put(')','(');
        map.put('}','{');
        map.put(']','[');
        if (s.length()%2 == 1) {
            return false;
        }
        Queue<Character> queue = new LinkedList<>();
        for (int i= 0;i < s.length(); i++) {
            //终止条件
            if (map.containsKey(s.charAt(i))) {
                if (queue.isEmpty() || !queue.peek().equals(map.get(s.charAt(i)))) {
                    return false;
                } else {
                    queue.poll();
                }
            } else {
                queue.offer(s.charAt(i));
            }
        }
        return  queue.isEmpty();
    }

    List<String> list = new ArrayList<>();
    /**
     * 递归
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        if (n < 0) {
            return list;
        }
        //对于 left == 左边字符个数 right == 右边字符个数
        generateR("",n,n);
        return list;
    }
    //[(((] ,[ )))] 通过数组角度看
    public void generateR(String str,int left,int right) {
        if (left == 0 && right == 0) {
            list.add(str);
            return;
        }

        //增加左边字符
        if (left == right) {
            generateR(str+"(",left-1,right);
        } else if (left < right) { //处理left 字符过多的情况
            //先将left字符填充
            if (left > 0) {
                generateR(str+"(",left -1,right);
            }

            //开始填充右边字符
            generateR(str+")",left,right-1);
        }
    }


    public ListNode mergeKLists(ListNode[] lists) {

        if (lists.length == 0) {
            return null;
        }

        if (lists.length == 1) {
            return lists[0];
        }
        ListNode dummy = new ListNode(-1);
        while (true) {
            ListNode current = null;
            int index = -1;
            //获取要插入的节点
            for (int i=0 ;i < lists.length;i ++) {
                ListNode node = lists[i];
                if (current == null) {
                    current = node;
                    index = i;
                } else {
                    if (node == null) continue;
                    if (current.val > node.val) {
                        current = node;
                        index = i;
                    }
                }
            }
            if (current == null) {
                break;
            }
            //更新要插入的节点
            lists[index] = lists[index].next;
            int val =  current.val;
            inserTail(dummy,val);
        }
        return dummy.next;
    }
    public void inserTail (ListNode dummy,int val) {
        ListNode tmp = dummy;
        while (tmp != null) {
            if (tmp.next == null) {
                tmp.next = new ListNode(val);
                break;
            }
            tmp = tmp.next;
        }
    }


    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int len = nums.length - 1;
        for (int i=0;i < nums.length ;) {

            if (i == len) {
                break;
            }

            //重复元素
            if (nums[i] == nums[i+1]) {
                len--;
                //移动位置
                for (int j = i + 1; j < nums.length - 1 ; j ++) {
                    nums[j] = nums[j+1];
                }
            } else {
                i++;
            }
        }
        System.out.println(len);
        return len;
    }

    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    /**
     * leetcode -
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE) {
            System.out.println("===");
        }
        int val = 0;
        boolean flag = true;

        //考虑Integer.Max 与Integer.Min的情况

        if (dividend == 0 || dividend == 0) {
            return 0;
        }
        if (Math.abs(divisor) == 1) {
            if (dividend < 0) {
                if (dividend > Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                } else if (dividend < Integer.MIN_VALUE) {
                    return Integer.MAX_VALUE;
                }
                return - dividend;
            }
            return dividend;
        }

        if (dividend < 0) {
            flag = false;
            dividend = - dividend;
        }
        if (divisor < 0) {
            if (flag){
                flag = false;
            } else {
                flag = true;
            }
            divisor = - divisor;
        }
        while (true) {
            if (dividend >= divisor) {
                dividend = dividend - divisor;
                val++;
            } else {
                break;
            }
        }
        return flag?val:-val;
    }

    /**
     * 遍历的方式实现 O(n) 的方式
     * O(logN)的方式实现。
     * 首先是一个有序数组。 可用二分法的方式进行遍历
     * [ [1-4] , [5-9] ]
     * 求 3 / 7
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int slow = 0;
        int fast = nums.length - 1;

        int index = -1;
        while (slow < fast) {
            //中间点
            int min = slow + (fast-slow)/2;
            if (nums[min] > target) {
                fast = min;
            } else if (nums[min] < target) {
                slow = min + 1;
            } else {
                //找到相等节点
                index = min;
                break;
            }
        }
        int[] res = new int[2];

        //边界条件的判断
        if (index == -1) {
            res[0] = -1;
            res[1] = -1;
        } else if (nums[index+1] == target) {
            res[0] = index;
            res[1] = index+1;
        } else if (nums[index - 1] == target) {
            res[0] = index-1;
            res[1] = index;
        } else {
            res[0] = index;
            res[1] = -1;
        }
        if (nums.length == 1) {
            if (nums[0] == target) {
                res[0] = 0;
                res[1] = 0;
            }
        }

        return res;

    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public class SupportPoiExtendRecall {

    }



}
