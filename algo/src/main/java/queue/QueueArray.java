package queue;

import org.junit.Test;

/**
 * @ClassName QueueArray
 * @Author bin
 * @Date 2019/9/23 下午5:07
 * @Decr TODO
 * 队列
 * 一种受限的数据结构，头部插入数据，尾部删除数据。 就如同排队一样
 * 可以基于数据实现或链表实现
 * <p>
 * 基于数组实现队列
 * @Link TODO
 **/
public class QueueArray {
    int[] array; //初始化数组
    int head;
    int tail;

    public QueueArray() {
        array = new int[5];
        head = 0;
        tail = 0;
    }

    /**
     * 入队操作-有限数组
     *
     * @param value
     */
    public void enqueue(int value) {
        if (tail == array.length) {
            System.out.println("队列已满");
            return;
        }
        array[tail] = value;
        tail++;
    }

    /**
     * 出队操作-有限数组
     *
     * @return
     */
    public int dequeue() {
        if (head == tail) {
            System.out.println("队列为空");
            return -1;
        }
        int value = array[head];
        head++;
        return value;
    }

    @Test
    public void test() {
        QueueArray queueArr = new QueueArray();
        queueArr.enqueue(1);
        queueArr.enqueue(2);
        queueArr.enqueue(3);
        queueArr.enqueue(4);
        System.out.println(queueArr.dequeue());
        System.out.println(queueArr.dequeue());
        System.out.println(queueArr.dequeue());
        System.out.println(queueArr.dequeue());
        System.out.println(queueArr.dequeue());
        System.out.println(queueArr.dequeue());
        queueArr.enqueue(5);
        queueArr.enqueue(6);
        queueArr.enqueue(7);

    }
}
