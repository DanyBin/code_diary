package linked;

import org.junit.Test;

/**
 * @ClassName linked.LinkedList
 * @Author bin
 * @Date 2019/9/23 下午2:30
 * @Decr TODO
 *      链表
 *          思路-通过指针next-> object 来实现数据的插入与删除
 * @Link TODO
 **/
public class LinkedList<T> {

    Node<T> head = null; //初始化头节点
    @Test
    public void test(){
        LinkedList<T> list = new LinkedList<T>();
        for(int i=0;i < 6;i++){
            list.insetHead(i);
        }
        printLinkedList(list.head);
        reverseLinked(list.head);
        findByInter(list.head);
    }

    /**
     * 链头插入方式，数据为倒叙方式
     * @param value
     * @return
     */
    public Node insertTail(T value,Node<T> node){
        Node newNode = new Node(value, null);
        if(node == null){
            node = newNode;
        }else {
            Node tmp = node;
            node=newNode;
            node.next= tmp;
        }
        return node;
    }

    /**
     * 链尾插入方式，数据为正叙方式
     * @param value
     */
    public void insetHead(int value){
        Node<T> newNode = new Node(value, null);
        if(head == null){
            head =newNode;
        }else {
            Node<T> tmp = this.head;
            while (tmp.next != null){
                tmp = tmp.next; //获取链表中最后一个节点
            }
            newNode.next = tmp.next; //将链表最后一个节点的next(null) 赋值给新节点的next。设置为最后的节点
            tmp.next = newNode; //将原本链表的最后节点next，引用设置为新增的节点
        }
    }

    /**
     * 单链表反转
     *      思路- 遍历链表-倒叙插入即可
     * @param node
     */
    public void reverseLinked(Node<T> node){
        Node<T> reverse = null;
        while (node != null){
            reverse = insertTail(node.value,reverse);
            node =  node.next ;
        }
        printLinkedList(reverse);
    }


    /**
     * 查找链表的中间值
     *      思路-通过快慢遍历方式，快遍历- node.next.next
     *                          慢遍历- node.next
     *                          当快遍历=null，即慢遍历为中间节点
     * @param node
     */

    public void findByInter(Node<T> node){
        Node<T> slowNode = node;
        Node<T> quickNode = node;
        while (slowNode != null){
            quickNode = quickNode.next.next;
            slowNode = slowNode.next;
            if(quickNode == null || quickNode.next == null ){
                break;
            }

        }
        System.out.println(slowNode.value);
    }



    public  void printLinkedList(Node<T> node){
        StringBuilder builder = new StringBuilder();
        while (node.next != null){
            builder.append(node.value).append(",");
            node = node.next;
        }
        System.out.println(builder);
    }

    public class Node<T>{  //结点
        T value;
        Node next;
        public Node(T value,Node node){
            this.value = value;
            this.next = node;
        }
    }
}
