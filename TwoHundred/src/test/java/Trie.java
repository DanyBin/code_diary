import com.alibaba.fastjson.JSONObject;

import java.util.*;

/**
 * 现在OSS中存储了多个几万到几百万大小不等的Java API数据文件，
 * 请你设计一个数据结构及算法，
 * 能够根据Java API名称前缀快速检索出对应的Java API名称列表。
 *
 *
 * //例如：文件中有buildDto
 * findByCode
 * buySth，
 *
 * 方法入参["b","bui","bu"]，
 *
 * 输出{"b":["buildDto","buySth"],"bui":["buildDto"],"bu":["buildDto","buySth"]}
 */
public class Trie {
  // trie树的根节点
  private Node root;

  public Trie() {
    this.root = new Node();
  }

  public static void main(String[] args) {
    final Trie trie = new Trie();
    //初始化数据
    trie.initTrie();
    //查询数据
    System.out.println(trie.search("b","bui","bu"));
  }



  public String search(String...args) {
    final JSONObject ret = new JSONObject();
    Arrays.stream(args)
        .forEach(w -> {
          final List<String> list = prefixMatching(w);
          ret.put(w,list);
        });
    return ret.toJSONString();
  }

  private void initTrie() {
    //Todo 假设读到数据并做了以下转换 & 构建本地字典
    String[] data = new String[]{"buildDto","findByCode","buySth"};
    Arrays.stream(data).forEach(this::addNode);
  }

  /**
   * 构建Tire
   * @param apiStr
   */
  private void addNode(String apiStr) {
    Node cur = root;
    final char[] chars = apiStr.toCharArray();
    for (char c : chars) {
      //新增节点
      if (!cur.children.containsKey(c)) {
        cur.children.put(c,new Node());
      }
      cur = cur.children.get(c);
    }
    //设置结束点
    if (!cur.isEnd) {
      cur.isEnd = true;
    }
  }

  /**
   * 前缀匹配&获取结果
   * @param arg
   * @return
   */
  private List<String> prefixMatching(String arg) {
    Node cur = root;
    final char[] chars = arg.toCharArray();
    for (int i = 0 ;i < chars.length; i++) {
      final char c = chars[i];
      if (!cur.children.containsKey(c)) {
        return Collections.emptyList();
      }
      cur = cur.children.get(c);
    }
    List<String> matchList = new ArrayList<>();
    final StringBuilder builder = new StringBuilder(arg);
    dfs(builder,cur,matchList);
    return matchList;
  }

  /**
   * 递归余下的节点
   * @param builder
   * @param node
   * @param retList
   */
  private void dfs(StringBuilder builder,Node node,List<String> retList) {
    Node cur = node;
    if (cur.isEnd) {
      retList.add(builder.toString());
      if (cur.children.isEmpty()) {
        return;
      }
    }

    for (Character c : cur.children.keySet()) {
      builder.append(c);
      dfs(builder, cur.children.get(c),retList);
      builder.delete(builder.length()-1,builder.length());
    }
  }


  //节点
  private static class Node {
    private boolean isEnd;
    //后续节点
    private Map<Character,Node> children;

    public Node(boolean isEnd) {
      this.isEnd = isEnd;
      children = new HashMap<>();
    }
    public Node() {
      this(false);
    }
  }
}
