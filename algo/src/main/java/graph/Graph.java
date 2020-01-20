package graph;


import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName Graph
 * @Author bin
 * @Date 2019/11/22 下午5:53
 * @Decr TODO
 *      图是一种非线性表数据结构
 *          1 图中的元素叫做 「顶点 Vertex」
 *          2 顶点与其他顶点的连接 叫做「边 edge」
 *          3 定点的度 degree- 跟顶点相连接的边的条数
 *
 *       「有向图」 - 边是有方向的。 「无向图」 - 边没有方向的。
 *
 *        有向图中，有入度（in -degree）和出度（out- degree）
 *                顶点的入度-表示有多少条边指向这个顶点。
 *                顶点的出度-表示以该顶点指向其他顶点的边数。
 *
 *         带权图(weighted graph)=每个边都有权重（weight）
 *
 *
 *
 *        图的存储数据结构
 *
 *        邻接矩阵（adjacency Matric）存储方法- 底层依赖一个二维数组.
 *                  优点: 存储方式简单、直接，基于数组，获取两个顶点的关系高效、方便计算
 *                  缺点: 浪费存储空间
 *
 *        邻接表(adjacency List)存储方法- 利用链表实现存储
 *                  优点: 存储节省空间
 *                  缺点: 计算效率底
 *
 *
 *
 * @Link TODO
 **/
public class Graph {    //无向图

    private int v; //顶点的个数

    private LinkedList<Integer> adj[]; //邻接表-使用数据组存储链表

    public Graph(int v){
        this.v = v;
        adj = new LinkedList[v];

        //创建邻接表
        for(int i=0; i< v;i++){
            adj[i] = new LinkedList<Integer>();
        }
    }

    /**
     * 边要存储两次
     * @param s 启始顶点
     * @param t 终止顶点
     */
    public void addEdge(int s,int t){
        adj[s].add(t);
        adj[t].add(s);
    }


    public static void main(String[] args) {
        Graph graph = new Graph(10);
        graph.addEdge(1,2);
        graph.addEdge(1,3);
        graph.addEdge(1,4);
        graph.addEdge(2,4);
        graph.addEdge(3,5);
        graph.addEdge(4,6);
        graph.addEdge(5,7);
        graph.addEdge(6,8);

        graph.bfs(1,8);
        graph.dfs(2,8);

    }

    /**
     * 广度优先搜索（Breadth-First-Search)
     * 先查找离顶点最近的，然后是次近的，以此往外搜索
     *
     * s 到t的最短路径
     * @param s 起始顶点
     * @param t 终止顶点
     */
    public void bfs(int s , int t){

        //1. 顶点相同，不搜索
        if(s == t){
            return;
        }

        //2. 记录已经被访问的节点，避免重复访问。如果订单被访问，则为true
        boolean[] visited = new boolean[v];

        visited[s] = true;

        //3. 存储已经被访问，但相连的顶点还没有被访问的顶点。  记录顶点的功能
        Queue<Integer> queue = new LinkedList();

        queue.add(s);

        //4 . 记录搜索路径。记录是该顶点，从那个前驱节点 而来
        int[] prev = new int[v];


        //默认前驱节点为-1
        for(int i=0;i < v;i ++){
            prev[i] = -1;
         }


         //遍历顶点
         while (queue.size() !=0 ){

             // 获取顶点
             Integer w = queue.poll();

             // 遍历该顶点 对应所有 顶点
             for(int i=0 ; i < this.adj[w].size(); i++){

                 //获取其他节点
                 int q = adj[w].get(i);

                 //校验顶点是否被遍历
                 if(!visited[q]){

                     //记录- q顶点是由 w顶点遍历而来
                     prev[q] = w;

                     //判断该顶点是否== 目标顶点
                     if(q == t){
                        print(prev,s,t);
                        return;
                     }

                     //记录被访问的节点
                     visited[q] = true;
                     queue.add(q);
                 }

             }

         }
    }


    /**
     *
     * 递归打印 s-> t 的路径
     * @param prev
     * @param s
     * @param t
     */
    private void print(int[] prev,int s, int t ){
        if(prev[t] != -1 && t != s){
            print(prev,s,prev[t]);
        }

        System.out.println(t + " ");
    }


    /**
     * 广度优先搜索-Depth-First-Search
     * 使用回溯思想，不断的尝试，是否正常
     *
     * @param s
     * @param t
     */
    boolean found = false; //找到 s->t 的路径，终止递归

    public void dfs(int s,int t){
        found = false;
        boolean[] visited = new boolean[v];
        int[] prev = new int[v];
        for(int i=0;i < v; i++){
            prev[i] = -1;
        }

        recurDfs(s,t,visited,prev);
        print(prev,s,t);

    }

    /**
     * 递归搜索
     * @param w 出发顶点
     * @param t 终止顶点
     * @param visited 已经访问的顶点
     * @param prev    记录前驱顶点
     */
    private void  recurDfs(int w,int t,boolean[] visited,int[] prev){

        //找到顶点 终止递归
        if(found == true){
            return;
        }

        //记录已经访问的顶点
        visited[w] = true;

        //当找到顶点时
        if(w == t){
            found = true;
            return;
        }

        for(int i=0;i < this.adj[w].size();i++){
            Integer q = this.adj[w].get(i);
            if(!visited[q]){

                //记录前驱节点
                prev[q] = w;
                recurDfs(q,t,visited,prev);
            }
        }
    }
}
