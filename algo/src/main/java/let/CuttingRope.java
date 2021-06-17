package let;

/**
 * @ClassName CuttingRope
 * @Author bin
 * @Date 2021/4/7 上午11:24
 * @Decr TODO
 *      给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n 都是整数，n>1 并且 m>1），
 *      每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1]
 *      可能的最大乘积是多少？例如，当绳子的长度是 8 时，我们把它剪成长度分别为 2、3、3 的三段，此时得到的最大乘积是 18。
 * @Link TODO
 **/
public class CuttingRope {
    public int cuttingRope(int n) {
        return recursion(n,1,1);
    }


    /**
     *  贪心算法- 遍历求解所有的答案
     * 将n分为m份
     * n/m = 取整 = x
     * n%m = 取余 = y
     * 从数组的角度看
     * [[0-x][x - x+x][x+x,x+x+x+y]]
     * @param n
     * @param m
     * @param max
     * @return
     */
    public int recursion(int n,int m,int max) {
        //终止条件
        if (0 > m || m > n - 1) {
            return max;
        }

        //取正数
        int x = n/m;
        //取余数
        int y = n%m;

        int sum = 1;
        //做正数处理
        for (int i = 0; i < m - y ; i++) {
            sum = sum * x;
        }

        //做余数处理，分解到正数上
        for (int i = 0; i < y; i++) {
            sum = (sum * (x + 1));
        }

        int tmp = sum > max ? sum : max;
        return recursion(n,m+1,tmp);
    }
}
