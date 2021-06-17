package let.Matrix;

/**
 * @ClassName IsValidSudoku
 * @Author bin
 * @Date 2021/4/3 上午11:02
 * @Decr TODO
 *      有效数独
 *      重点公式 box = (row/3)*3 + col /3 == 计算3x3数组的递增位置
 *                    (1/3)*3 + 1/3  = 0
 *                    (4/3)*3  + 1/3 = 3,1
 *      通过数组记录下标的方式，来判断元素是否重复
 * @Link TODO
 **/
public class IsValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        //行
        int[][] row = new int[9][9];
        //列
        int[][] col = new int[9][9];
        //3*3的数组
        int[][] box = new int[9][9];

        for(int i=0; i < board.length; i ++) {
            for (int j = 0; j < board[i].length; j ++) {
                //过滤空白
                if (board[i][j] != '.') {
                    //计算元素
                    int num = board[i][j] - '1';
                    //3*3
                    int index_box = (i/3)*3 + j/3;

                    //判断列 - num是0-9
                    if (row[i][num] == 1) {
                        return false;
                    } else {
                        row[i][num] = 1;
                    }

                    if (col[j][num] == 1) {
                        return false;
                    } else {
                        col[j][num] = 1;
                    }

                    if (box[index_box][num] == 1) {
                        return false;
                    } else {
                        box[index_box][num] = 1;
                    }
                }
            }
        }
        return true;
    }
}
