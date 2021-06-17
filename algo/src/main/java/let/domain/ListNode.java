package let.domain;

/**
 * @ClassName ListNode
 * @Author bin
 * @Date 2021/4/7 上午10:22
 * @Decr TODO
 * @Link TODO
 **/
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
