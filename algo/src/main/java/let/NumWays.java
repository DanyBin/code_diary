package let;

/**
 * @ClassName NumWays
 * @Author bin
 * @Date 2021/4/7 上午10:53
 * @Decr TODO
 *      变形的斐波纳切
 * @Link TODO
 **/
public class NumWays {

    public int numWays(int n) {
        if (0 == n ||  1 == n) {
            return 1;
        }
        return numWays(n-1) + numWays(n - 2);
    }
}
