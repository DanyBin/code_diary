package skipList;

/**
 * @ClassName SkipList
 * @Author bin
 * @Date 2019/10/11 下午7:41
 * @Decr TODO
 * @Link TODO
 *      跳表（Skip List）
 *          将链表改造为 "二分"查找类似的形式。 以下例子就是跳表的构建过程
 *      例子:
 *        原始单链表:[1,next] ->[2,next] -> [3,next]-> [4,next]->[5,next] ->[6,next]-> [7,next] ->[8,next]->[9,next]
 *
 *       「一级索引」:[1,next] -----------> [3,next] ----------->[5,next] ----------->[7,next] ----------->[9,next] (对于原始的单链表每两个结点提取到上一级)
 *
 *       「二级索引」:[1,next] --------------------------------->[5,next] -------------------------------->[9,next](对于一级索引每两个结点提取到上一级)
 *
 *        以此类推
 *
 *        查询操作- 「给定值=4」时，二级索引判断，在1->5 之间，下降到 一级索引中 3->5 之间，最后将到原始链表中。
 *                  时间复杂度 = O(logN)
 *                  空间复杂度O(n). n个大小的链表，需要n个大小的索引空间
 *        插入操作-  链表的插入操作是O(1) ，只需更新指针即可。对于跳表来说，「查找到具体的插入位置为O(logN)」。故整个时间复杂度为O(logN)
 *        删除操作-  时间复杂度也是O(1),更新指针即可。在查询时，时间复杂度为O(logN)
 *
 *
 *        Redis中的有序集合是通过「跳表」来实现的。
 *        核心操作-
 *          1. 插入一个数据
 *          2. 删除一个数据
 *          3. 查找一个数据
 *          4. 按照区间查询数据
 **/
public class SkipList {
    private  static final int MAX_LEVEL = 16; //索引最大深度为16


    public class  Node{
        private int data = -1;
        private Node[] forwards = new Node[MAX_LEVEL]; //创建数组 存放索引结点。 最大为16
        private int maxLevel = 0;

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("{ data: ");
            builder.append(data);
            builder.append("; levels: ");
            builder.append(maxLevel);
            builder.append(" }");

            return builder.toString();
        }
    }
}
