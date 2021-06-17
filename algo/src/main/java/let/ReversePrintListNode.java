package let;

import let.domain.ListNode;

import java.util.stream.Collectors;

/**
 * @ClassName ReversePrintListNode
 * @Author bin
 * @Date 2021/4/7 上午10:20
 * @Decr TODO
 *      打印ListNode的倒叙.
 *
 *      思路 - 将ListNode 做倒叙的迭代即可
 *             注意链表是保存的对象地址
 * @Link TODO
 **/
public class ReversePrintListNode {
    public int[] reversePrint(ListNode listNode) {
        if (null == listNode) {
            return new int[0];
        }

        int size = 0;
        ListNode newNode = null;
        while (listNode != null) {
           ListNode nextNode =  listNode.next;
           listNode.next = null;
           if (null == newNode) {
               newNode = listNode;
           } else {
               listNode.next = newNode;
               newNode = listNode;
           }
           listNode = nextNode;
            size++;
        }

        int[] array = new int[size];
        int i = 0;
        while (newNode != null){
            array[i++] = newNode.val;
            newNode = newNode.next;
        }
        return array;
    }
}
