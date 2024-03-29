package generics;

/**
 * @ClassName LinkedStack
 * @Author bin
 * @Date 2020/7/8 下午4:23
 * @Decr TODO
 * @Link TODO
 **/
public class LinkedStack<T> {
    private static class Node<U>{
        U item;
        Node<U> next;
        Node(){item = null;next = null;}
        Node(U item,Node<U> next){this.item = item;this.next = next;}

        boolean end(){return  item == null || next == null;}
    }
    private Node<T> top = new Node<T>();

    public void push(T item){
        top = new Node<T>(item,top);
    }

    public T pop(){
        T item = top.item;
        if(!top.end()){
            top = top.next;
        }
        return item;
    }

    public static void main(String[] args) {
        LinkedStack<String> lss = new LinkedStack<String>();
        for(String s:"aa bb cc dd".split(" ")){
            lss.push(s);
        }
        String s;
        while ((s = lss.pop()) != null){
            System.out.println(s);
        }
    }
}
