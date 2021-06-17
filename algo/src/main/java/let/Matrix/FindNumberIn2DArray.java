package let.Matrix;

/**
 * @ClassName FindNumberIn2DArray
 * @Author bin
 * @Date 2021/4/7 上午10:09
 * @Decr TODO
 *      二维矩阵中，是否包括目标值 .
 *      注意该矩阵是 从左到右，从上到下递增的
 *
 *      思路
 *          通过矩阵的左上角元素，进行迭代遍历
 * @Link TODO
 **/
public class FindNumberIn2DArray {

    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        //终止条件
        if(null == matrix || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }


        int m1 = matrix.length;
        int m2 = matrix[0].length;

        //左上角元素的索引
        int m = 0; //列
        int n = m2 -1; //行

        //遍历条件
        while (m < m1 || n >= 0) {
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
}
