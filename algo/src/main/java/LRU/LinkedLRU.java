package LRU;

import org.junit.Test;

/**
 * @ClassName LinkedLRU
 * @Author bin
 * @Date 2019/10/16 下午7:46
 * @Decr TODO
 *      基于链表实现LRU缓存
 *          思路 - 判断数组是否在链表中存在
 *                 1. 不存在时，数据直接插入链表头部
 *                 2. 存在时，更更新链表，将数据移动到头部
 * @Link TODO
 **/
public class LinkedLRU {

    @Test
    public void test(){
        LinkedLRU linkedLRU = new LinkedLRU();
        linkedLRU.add("a");
        linkedLRU.add("c");
        linkedLRU.add("d");
        linkedLRU.add("a");
    }

    public class Node{
        String data;
        Node next;
        public Node(){
            this.next = null;
        }
        public Node(String data,Node next){
            this.data = data;
            this.next = next;
        }
    }

    private  Node head; //声明一个头结点


    /**
     * 默认链表容量
     */
    private final static Integer DEFAULT_CAPACITY = 10;


    /**
     * 链表长度
     */
    private Integer length;

    public LinkedLRU(){
        this.head = new Node();
        length=0;
    }

    /**
     * 新增一个节点
     * @param value
     */
    public void add(String value){
        Node node = findPreNode(value);
        if(node == null){ //新增Node
            cache(value);
        }else { //更新Node
            deleteElemOptim(node);
            cache(value);
        }
    }

    /**
     *  删除preNode结点下一个元素
     * @param node
     */
    public void deleteElemOptim(Node node){
        Node temp = node.next;
        node.next = temp.next;
        temp = null;
        length--;
    }


    /**
     * 增加缓存
     * @param value
     */
    public void cache(String value){
        Node newNode = new Node(value, null);
        Node tmp = this.head;
        head = newNode;
        newNode.next = tmp;
        length++;
    }

    /**
     *  获取查找到元素的前一个结点
     * @param value
     * @return
     */
    public Node findPreNode(String value){
        Node node = this.head;
        while (node.next != null){
            if(value.equals(node.next.data)){
                return node;
            }
            node = node.next;
        }
        return null;
    }
}
