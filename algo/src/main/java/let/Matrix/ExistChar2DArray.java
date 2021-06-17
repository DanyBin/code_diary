package let.Matrix;

/**
 * @ClassName ExistChar2DArray
 * @Author bin
 * @Date 2021/4/7 上午11:00
 * @Decr TODO
 *      在一个二维字符组中，判断word 字符是否存在
 *      二维字符组中，只能向上、向下、向左、向右 。 都是 + 1的方式
 *
 *      思路 - dfs - 深度优先搜索 ，暴力匹配
 * @Link TODO
 **/
public class ExistChar2DArray {

    public boolean exist(char[][] board, String word) {
        for (int i = 0;i < board.length ; i ++) {
            for (int j = 0; j < board[i].length ; j ++) {
                  boolean boo = dfs(board,i,j,word.toCharArray(),0);
                  if (boo) {
                      return true;
                  }
            }
        }
        return false;
    }

    /**
     *
     * @param board
     * @param i =
     * @param j
     * @param c = word 的字符数组
     * @param k = word 字符数组的下标
     * @return
     */
    public boolean dfs(char[][] board,int i,int j, char[] c ,int k){
        //终止条件
        if (0 > i || i > board.length || 0 > j || j > board[0].length || board[i][j] != c[k]) {
            return false;
        }
        //迭代结束
        if (k == c.length - 1) {
            return true;
        }

        //标示已经访问过了
        board[i][j] = '\0';
        boolean res = dfs(board,i+1,j,c,k+1) ||
                dfs(board,i,j+1,c,k+1) ||
                dfs(board,i-1,j,c,k+1) ||
                dfs(board,i,j-1,c,k+1);

        //还原数组
        board[i][j] = c[k];
        return res;
    }
}
