package let.Matrix;

/**
 * @ClassName MovingCount
 * @Author bin
 * @Date 2021/4/7 上午11:14
 * @Decr TODO
 *          地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，
 *          每一次只能向左，右，上，下四个方向移动一格，
 *          但是不能进入行坐标和列坐标的数位之和大于k的格子。
 *
 *          例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。
 *          但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
 *
 *          思路 - dfs - 暴力遍历。记录已经访问的地址
 * @Link TODO
 **/
public class MovingCount {
    public int movingCount(int m, int n, int k) {
       //记录是否已访问的数组
        int[][] visit = new int[m][n];
        return dfs(m,n,0,0,k,visit);
    }

    /**
     *
     * @param m
     * @param n
     * @param i
     * @param j
     * @param k
     * @param visit
     */
    public int dfs(int m,int n,int i,int j,int k,int[][] visit) {
        //终止条件
        if (0 > i || i > m || 0 > j || j > n || visit[i][j] == 1 || (bitSum(i) + bitSum(j)) > k) {
            return 0;
        }
        //已经访问过
        visit[i][j] = 1;
        return 1 + dfs(m,n,i+1,j,k,visit) +
                dfs(m,n,i,j+1,k,visit) +
                dfs(m,n,i-1,j,k,visit) +
                dfs(m,n,i,j-1,k,visit);
    }

    //计算位数之和
    public int bitSum(int x) {
        int sum = 0;
        while (x != 0) {
            sum += x %10;
            x = x/10;
        }
        return sum;
    }
}
