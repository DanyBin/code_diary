import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Input:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 *
 * Output:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 *
 * 题目描述：一个学生用两个分量 (h, k) 描述，h 表示身高，k 表示排在前面的有 k 个学生的身高比他高或者和他一样高。
 *
 * 请你重新构造并返回输入数组people 所表示的队列。返回的队列应该格式化为数组 queue ，
 * 其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/queue-reconstruction-by-height
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 输入：people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
 * 输出：[[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
 * 解释：
 * 编号为 0 的人身高为 5 ，没有身高更高或者相同的人排在他前面。
 * 编号为 1 的人身高为 7 ，没有身高更高或者相同的人排在他前面。
 * 编号为 2 的人身高为 5 ，有 2 个身高更高或者相同的人排在他前面，即编号为 0 和 1 的人。
 * 编号为 3 的人身高为 6 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
 * 编号为 4 的人身高为 4 ，有 4 个身高更高或者相同的人排在他前面，即编号为 0、1、2、3 的人。
 * 编号为 5 的人身高为 7 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
 * 因此 [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]] 是重新构造后的队列。
 *
 * 输入: [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 * 输出:
 * [[7, 0]]
 * [[7, 0], [7, 1]]
 * [[7, 0], [6, 1], [7, 1]]
 * [[5, 0], [7, 0], [6, 1], [7, 1]]
 * [[5, 0], [7, 0], [5, 2], [6, 1], [7, 1]]
 * [[5, 0], [7, 0], [5, 2], [6, 1], [4, 4], [7, 1]]
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/queue-reconstruction-by-height
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code_14_QueueReconstructionByHeight {

  public int[][] reconstructQueue(int[][] people) {
    //排序，
    Arrays.sort(people, new Comparator<int[]>() {
      @Override
      public int compare(int[] o1, int[] o2) {
        if (o1[0] != o2[0]) {
          return o1[0] - o2[0];
        } else {
          return o2[1] - o1[1];
        }
      }
    });
    List<int[]> queue = Lists.newArrayList();
    for (int[] p : queue) {
      //存放位置。按照K
      queue.add(p[1],p);
    }
    return queue.toArray(new int[queue.size()][]);
  }
}
