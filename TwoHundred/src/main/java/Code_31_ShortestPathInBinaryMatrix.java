import org.apache.commons.lang3.tuple.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1. 计算在网格中从原点到特定点的最短路径长度
 * [[1,1,0,1],
 *  [1,0,1,0],
 *  [1,1,1,1],
 *  [1,0,1,1]]
 *
 *  给你一个 n x n 的二进制矩阵 grid 中，返回矩阵中最短 畅通路径 的长度。如果不存在这样的路径，返回 -1 。
 *
 * 二进制矩阵中的 畅通路径 是一条从 左上角 单元格（即，(0, 0)）到 右下角 单元格（即，(n - 1, n - 1)）的路径，该路径同时满足下述要求：
 *
 * 路径途经的所有单元格都的值都是 0 。
 * 路径中所有相邻的单元格应当在 8 个方向之一 上连通（即，相邻两单元之间彼此不同且共享一条边或者一个角）。
 * 畅通路径的长度 是该路径途经的单元格总数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shortest-path-in-binary-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code_31_ShortestPathInBinaryMatrix {

  public int shortestPathBinaryMatrix(int[][] grids) {
    if (null == grids || grids.length == 0 || grids[0].length == 0) {
      return -1;
    }

    int m = grids.length;
    int n = grids[0].length;

    //八个方向
    int[][] direction = {{1,-1},{1,0},{1,1},{0,1},{0,0},{0,-1},{-1,1},{-1,0},{-1,-1}};
    //存在节点
    Queue<Pair<Integer,Integer>> queue = new LinkedList<>();
    queue.add(Pair.of(0,0));
    //路径长度
    int pathLength = 0;

    while (!queue.isEmpty()) {
      int size = queue.size();
      pathLength++;
      //遍历队列
      while (size-- >0) {
        //获取元素
        final Pair<Integer, Integer> poll = queue.poll();
        final Integer left = poll.getLeft();
        final Integer right = poll.getRight();

        //判断当前位置元素 为 1
        if (grids[left][right] == 1) {
          continue;
        }

        //判断矩阵已经遍历到结束时
        if (left == m - 1 && right == n -1) {
          return pathLength;
        }

        grids[left][right] = 1; //标记

        //遍历的方式，记录当前节点，可以走的8个方式
        for (int[] d: direction) {
          int t1 = left + d[0];
          int t2 = right + d[1];

          //没有越界 且 之前没有到访过该点 且 该点可走（值为0）
          if (t1 < 0 || t1 >= m || t2 < 0 || t2 > n) {
            continue;
          }
          queue.add(Pair.of(t1,t2));
        }
      }
    }
    return pathLength;
  }
}
