import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列：
 *
 * 序列中第一个单词是 beginWord 。
 * 序列中最后一个单词是 endWord 。
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典 wordList 中的单词。
 * 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，找到从 beginWord 到 endWord 的 最短转换序列 中的 单词数目 。
 * 如果不存在这样的转换序列，返回 0。
 *
 *
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出：5
 * 解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
 *
 *
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * 输出：0
 * 解释：endWord "cog" 不在字典中，所以无法进行转换。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-ladder
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code_33_WordLadder {

  public static void main(String[] args) {
    String beginWord = "hit";
    String endWord = "cog";
    List<String> wordList = Lists.newArrayList(new String[]{"hot","dot","dog","lot","log"});
    final int i = ladderLength(beginWord, endWord, wordList);
    System.out.println(i);
  }


  public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
    Queue<String> queue = new LinkedList<>();
    boolean[] market = new boolean[wordList.size() + 1];
    queue.add(beginWord);
    int depth = 0;
    while (!queue.isEmpty()) {
      final String cur = queue.poll();
      depth ++;
      //遍历
      for(int i = 0; i < wordList.size(); i++) {
        final String v = wordList.get(i);

        if (!isSub(cur,v)) {
          continue;
        }

        if (v.equals(endWord)) {
          return depth;
        }
        //该节点是否已经标记过
        if (market[i]) {
          continue;
        }

        market[i] = true; //标记
        queue.add(v);
      }
    }
    return 0;
  }

  //是否由一个字符串 变化
  private static boolean isSub(String target,String source) {
    int count = 0;
    for (char c : target.toCharArray()) {
      if (source.indexOf(c) != -1) {
        count ++;
      }
    }
    return count == source.length() - 1;
  }
}
