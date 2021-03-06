package hashTable;

/**
 * @ClassName Part2_HashMapSource
 * @Author bin
 * @Date 2019/10/15 下午7:29
 * @Decr TODO
 *       设计散列表的要求：
 *          1. 支持快速的查询、插入、删除操作。
 *          2. 内存占用合理，不能浪费过多的内存
 *          3. 性能稳定，极端情况下，散列表的性能也不能退化到无法接受的情况
 *        例子- 将所有数据经过散列函数之后，都放到同一个槽中里。如果是基于链表法解决散列冲突，散列表会退化成链表。查询复杂度就退化成O(n)
 *
 *
 *        设计散列表的思路：
 *          1. 设计一个合理的散列函数。
 *              a. 设计不能过于复杂，否则会消耗很多时间，会影响散列表的性能
 *              b. 散列函数生成的值要尽可能「随机且均匀分布」，避免散列冲突
 *
 *          2. 定义装载因子，并且设计动态扩容策略。(原因:装载因子越大，说明散列表中的元素越多，空闲位置越少，散列冲突的概率越大)
 *              思路-重新申请一个更新的散列表，将数据搬移到这个新散列表中。对于散列表扩容，数据搬移时，都需要重新计算「每个数据的存储位置」
 *
 *              a. 装载因子域值的设计
 *                  如果内存空间不紧张，对于执行效率要求高，可以降低装载因子的域值；
 *                  反之，内存紧张，对执行效率要求不高，可以增加装载因子的域值，甚至大于1
 *
 *              b. 动态扩容的设计
 *                  思路-将「扩容操作穿插在插入操作的过程中」，分批完成。
 *                  执行过程-当有新数据插入时，从老的散列表汇总拿出一个数据放入到新的散列表。（反复重复，直到老的散列表没有数据）。执行效率高
 *
 *          3. 选择合适的散列冲突解决方法
 *              a. 开放寻址法- LinkedHashMap采用
 *                 优点: 能够有效使用CPU的缓存加快查询速度；序列化的比较简单
 *                 缺点: 删除数据比较麻烦，需要特殊标记已经删除掉的数据；所有数据都存放在一个数组中，冲突代价高。
 *                 适合场景: 数据量较小，装载因子小时。
 *
 *              b. 链表法- HashMap采用
 *                 优点: 对于内存的利用率比开放寻址法更高。链表是按需创建;对于装载因子的容忍度更大，甚至大于1
 *                 缺点: 链表对于CPU缓存不友好，影响执行效率。
 *                 适合场景: 适合存储大对象，大数据量的散列表。
 *
 *
 * @Link TODO
 **/
public class Part2_HashMapSource {
    /**
     * 详细参考: https://juejin.im/post/5a7719456fb9a0633e51ae14
     */
}
