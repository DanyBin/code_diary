package skipList;

import org.junit.Test;

import java.util.Random;

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
 *
 *
 *       以下跳表的例子- 仅支持 不重复元素
 **/
public class SkipList {
    private  static final int MAX_LEVEL = 16; //索引最大深度为16

    private int levelCount = 1;

    private Node head = new Node();  // 带头链表

    private Random r = new Random();

    @Test
    public void test(){
        SkipList skipList = new SkipList();
        int[] array = {3,4,5,1,2,6};
        for(int i=0;i<array.length;i++){
            skipList.insert(array[i]);
            skipList.printLevel();
        }
        skipList.find(3);
        skipList.delete(3);
        skipList.printAll();
    }

    /**
     * 查找
     * @param value
     * @return
     */
    public Node find(int value) {
        Node p = head;
        //遍历最大的Level层
        for (int i = levelCount - 1; i >= 0; --i) {
            //比较操作- 找到 最后一个小于「给定值」的Node。
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
        }

        // 比较操作 「最后一个小于给定值」的节点「下一个节点」 是否等于「给定值」
        if (p.forwards[0] != null && p.forwards[0].data == value) {
            return p.forwards[0];
        } else {
            return null;
        }
    }

    /**
     * 新增节点
     * @param value
     */
    public void insert(int value) {
        int level = randomLevel(); //随机层数

        //创建新结点
        Node newNode = new Node();
        newNode.data = value;
        newNode.maxLevel = level; //指定层数

        //创建level 大小的Node 数组。 存放Level 个 节点 head
        Node update[] = new Node[level];
        for (int i = 0; i < level; ++i) {
            update[i] = head;
        }

        //赋值操作，用于更新head节点
        Node p = head;

        //遍历Node[] 数组
        for (int i = level - 1; i >= 0; --i) {

            /**
             * 获取 p「当前节点」的「下一个节点」 与 「下一个节点」的数值
             * 1. 当「当前节点」的「下一节点」是存在的 并且 「第一个节点」的值 小于 「给定值」
             * 2. 更新 p节点。 将p 更新为「下一节点」
             * 3. 直到 p「第一节点」 不小于 「给定值」
             */
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }

            /**
             * 将更新后的P节点（最后一个节点。顺序链表中最大值） 添加到Node[] 数组中
             */
            update[i] = p;// 更新update[]
        }

        //遍历Node[] 数组
        for (int i = 0; i < level; ++i) {
            //将新节点的「下一节点」 == null
            newNode.forwards[i] = update[i].forwards[i];

            //将head节点中的「最后一个节点」指针 指向 「新节点」
            update[i].forwards[i] = newNode;
        }
        // 更新层数
        if (levelCount < level) levelCount = level;
    }


    // 随机 level 次，如果是奇数层数 +1，防止伪随机
    private int randomLevel() {
        int level = 1;
        for (int i = 1; i < MAX_LEVEL; ++i) {
            if (r.nextInt() % 2 == 1) {
                level++;
            }
        }

        return level;
    }

    /**
     * 删除操作
     * @param value
     */
    public void delete(int value) {
        Node[] update = new Node[levelCount];
        Node p = head;

        /**
         * 找到 最后一个小于「给定值」 的「最后一个节点」
         */
        for (int i = levelCount - 1; i >= 0; --i) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
            update[i] = p;
        }

        //「最后一个节点」的「下一节点值」== value
        if (p.forwards[0] != null && p.forwards[0].data == value) {

            for (int i = levelCount - 1; i >= 0; --i) {

                //找到与「给定值」相等的「节点」
                if (update[i].forwards[i] != null && update[i].forwards[i].data == value) {

                    //更新节点的指针。 删除「给定值」节点
                    update[i].forwards[i] = update[i].forwards[i].forwards[i];
                }
            }

        }
    }

    public void printAll() {
        Node p = head;
        while (p.forwards[0] != null) {
            System.out.print(p.forwards[0] + " ");
            p = p.forwards[0];
        }
        System.out.println();
    }
    public void printLevel(){
        Node p = head;
        while (p.forwards[0] != null){

            System.out.println("当前节点:" + p.forwards[0] + " ");
            for(int i=0; i< p.forwards.length;i++){
                System.out.println("当前节点的包含的Node数组中的元素"+p.forwards[i]);
            }
            p = p.forwards[0];
        }
    }

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
