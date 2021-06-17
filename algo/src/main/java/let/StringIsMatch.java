package let;

/**
 * @ClassName StringIsMatch
 * @Author bin
 * @Date 2021/4/5 上午11:29
 * @Decr TODO
 *      给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。

        '?' 可以匹配任何单个字符。
        '*' 可以匹配任意字符串（包括空字符串）。
        两个字符串完全匹配才算匹配成功。



     案例1  s =  aa   p =  a? = true
     案例2  s =  aa   p =  a*  = true

     案例3  s = abcd  ab?     = false
     案例4  s = abcddd  p=abcd? = false

     思路，通过构建矩阵完成



 * @Link TODO
 **/
public class StringIsMatch {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        //构建矩阵
        boolean[][] f = new boolean[m+1][n+1];
        //空字符串
        f[0][0] = true;


        //当字符串s为空.判断=*号情况
        for(int j = 1; j<=n; j++) {
            if(s.charAt(j-1) == '*'){
                f[0][j] = true;
            } else {
                break;
            }
        }

        for(int i=0; i <= m; i++) {
            for(int j=1; j<= n; j++) {
                //处理*的部分
                if(p.charAt(j-1) == '*') {
                    //如果P当前是 *，从递归的角度看
                    //可以将S的当前字符去掉(dp[i-1][j])
                    //也可以去掉P的当前字符(dp[i][j-1])
                    f[i][j] = f[i - 1][j] || f[i][j - 1];
                } else {
                    //判断 s 与 p 的当前字符是否相同
                    boolean r = s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?';
                    //需要与之前的结果，并并集？是由上次的结果转化而来的
                    f[i][j] = r && f[i-1][j-1];
                }
            }
        }
        return f[m][n];
    }

}
