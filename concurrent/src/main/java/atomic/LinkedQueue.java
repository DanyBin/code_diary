package atomic;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName LinkedQueue
 * @Author bin
 * @Date 2020/6/12 下午12:09
 * @Decr TODO
 *      实现非阻塞的链表操作
 * @Link TODO
 **/
@ThreadSafe
public class LinkedQueue<E> {



    private static class Node<E> {
        final E item;
        final AtomicReference<Node<E>> next;

        public Node (E item,Node<E> next){
            this.item = item;
            this.next = new AtomicReference<>(next);
        }
    }

    //哑节点-，头节点 和尾节点初始化时指向该 节点
    private final Node<E> dumy = new Node<E>(null,null);

    //头节点
    private final AtomicReference<Node<E>> head = new AtomicReference<>(dumy);
    //尾节点
    private final AtomicReference<Node<E>> tail = new AtomicReference<>(dumy);

    public boolean put(E item){
        Node<E> newNode = new Node<>(item, null);
        while (true){
            //尾节点
            Node<E> curTail = tail.get();
            Node<E> tailNext = curTail.next.get();

            //当前节点==尾节点
            if(curTail == tail.get()){

                if(tailNext != null){
                    //当前尾节点处于中间位置，更新尾节点的位置。原子更新为 最新的值
                    tail.compareAndSet(curTail,tailNext);
                }else {
                    //尾节点是最后的节点，尝试插入新的节点
                    if(curTail.next.compareAndSet(null,newNode)){
                        //插入操作成功，尝试更新尾节点
                        tail.compareAndSet(curTail,newNode);
                        return true;
                    }
                }
            }
        }
    }
}
