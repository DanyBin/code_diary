package atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName ConcurrentStack
 * @Author bin
 * @Date 2020/6/12 上午11:25
 * @Decr TODO
 *      实现非阻塞的栈结构
 * @Link TODO
 **/
public class ConcurrentStack<E> {

    AtomicReference<Node<E>> top = new AtomicReference<>();

    //插入元素-
    public void push(E item){
        Node<E> newHead = new Node<>(item);
        Node<E> oldHead;
        do {
            oldHead = top.get();
            newHead.next = oldHead;
        }while (!top.compareAndSet(oldHead,newHead));
    }

    //获取栈顶元素
    public E pop(){
        Node<E> oldHead;
        Node<E> newHead;

        do {
            oldHead = top.get();
            if(oldHead == null){
                return null;
            }
            newHead = oldHead.next;
        }while (!top.compareAndSet(oldHead,newHead));
        return oldHead.item;
    }

    private static class Node<E> {
        public final  E item;
        public Node<E> next;
        public Node(E item){
            this.item = item;
        }
    }
}
