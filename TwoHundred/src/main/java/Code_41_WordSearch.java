import java.util.ArrayList;
import java.util.List;

/**
 * For example,
 * Given board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 * word = "ABCCED", -> returns true,
 * word = "SEE", -> returns true,
 * word = "ABCB", -> returns false.
 *
 * 给定一个m x n 二维字符网格board 和一个字符串单词word 。如果word 存在于网格中，返回 true ；否则，返回 false 。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母不允许被重复使用。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-search
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code_41_WordSearch {

  //四个方向
  int[][] direct = {{1,0},{-1,0},{0,1},{0,-1}};
  int n;
  int m;

  public boolean exist(char[][] board,String word) {
    if (null == board || board.length == 0) {
      return false;
    }
    n = board.length;
    m = board[0].length;
    for (int i=0 ;i < n; i++) {
      for (int j=0; j < m;j++) {
        //用于记录是否走过
        boolean[][] visit = new boolean[n][m];
        //记录单个节点的结果
        List<String> ret = new ArrayList<>();
        dfs(board,visit,i,j,word,new StringBuffer(),ret);
        if (!ret.isEmpty()) {
          return true;
        }
      }
    }
    return false;
  }

  public void dfs(char[][] board, boolean[][] visit, int i, int j, String word, StringBuffer buffer, List<String> ret){
    //终止条件 -- 边界条件
    if (i < 0 || j < 0 || i >= n || j>=m || visit[i][j]) {
      //判断是否相同
      if (buffer.length() == word.length()) {
        if (buffer.toString().equals(word)) {
          ret.add(word);
        }
      }
    }
    visit[i][j] = true;//标记
    buffer.append(board[i][j]);//增加元素
    for (int[] d : direct) {
      //向四个方面走
      dfs(board,visit,i + d[0],j + d[1],word,buffer,ret);
    }
  }
}
