package algo2022;

import java.util.Stack;

/**
 * 用两个栈来实现一个队列，完成队列的 Push 和 Pop 操作。
 *
 */
public class Code_06_Stack {
    Stack<Integer> in  = new Stack<>();
    Stack<Integer> out = new Stack<>();

    public static void main(String[] args) {
        Code_06_Stack code_06_stack = new Code_06_Stack();
        code_06_stack.push(1);
        code_06_stack.push(2);
        System.out.println(code_06_stack.pop());
    }
    public void push (int i) {
        in.push(i);
    }

    public void outStack() {
        while (!in.empty()) {
            out.push(in.pop());
        }
    }

    public Integer pop() {
        outStack();
        return out.pop();
    }
}
