package queue;

import org.junit.Test;

/**
 * @ClassName QueueCycleArray
 * @Author bin
 * @Date 2019/9/23 下午7:48
 * @Decr TODO
 * 循环队列
 * 基于数组实现循环队列
 * 思路——考虑边界条件，当tail+1 = head 即表示队列已经满了。(也就是队尾% 变成队头时)
 * 怎么判断(tail+1)%Array.lenght == head 即表示数据已经满
 * @Link TODO
 **/
public class QueueCycleArray {
    int[] array;
    int head;
    int tail;

    public QueueCycleArray() {
        array = new int[5];
        head = 0;
        tail = 0;
    }

    /**
     * 入队操作-
     *
     * @param value
     */
    public void enqueue(int value) {
        if ((tail + 1) % array.length == head) {
            System.out.println("队列已满");
            return;
        }
        array[tail] = value;
        tail = (tail + 1) % array.length;
    }

    public int dequeue() {
        if (head == tail) {
            System.out.println("队列为空");
            return -1;
        }
        int i = array[head];
        head = (head + 1) % array.length;
        return i;
    }

    @Test
    public void test() {
        QueueCycleArray queueArr = new QueueCycleArray();
        queueArr.enqueue(1);
        queueArr.enqueue(2);
        queueArr.enqueue(3);
        queueArr.enqueue(4);
        System.out.println(queueArr.dequeue());
        System.out.println(queueArr.dequeue());
        queueArr.enqueue(5);
        queueArr.enqueue(6);
        queueArr.enqueue(7);
        System.out.println(queueArr.dequeue());
        System.out.println(queueArr.dequeue());

    }
}
