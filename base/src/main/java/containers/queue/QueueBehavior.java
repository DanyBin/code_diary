package containers.queue;

import generics.Generator;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @ClassName QueueBehavior
 * @Author bin
 * @Date 2020/7/22 下午3:40
 * @Decr TODO
 * @Link TODO
 **/
public class QueueBehavior {
    private static int count = 10;
    static <T> void test(Queue<T> queue, Generator<T> gen){
        for(int i=0; i< count; i++){
            queue.offer(gen.next());
        }
        while (queue.peek() != null){
            System.out.println(queue.remove() + " ");
        }
        System.out.println();
    }
    static class Gen implements Generator<String>{
        String[] s = ("one two three four five six seven eight nine ten".split(" "));
        int i;
        public String next(){return s[i++];}
    }

    public static void main(String[] args) {
        test(new LinkedList<String>(),new Gen());
        test(new PriorityQueue<String>(),new Gen());
        test(new ArrayBlockingQueue<String>(count),new Gen());
        test(new PriorityBlockingQueue<String>(),new Gen());
    }
}
