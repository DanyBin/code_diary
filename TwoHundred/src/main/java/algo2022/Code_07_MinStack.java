package algo2022;

import org.w3c.dom.CDATASection;

import java.util.Stack;

/**
 * 实现一个包含min（）函数的栈，该方法返回当前栈中最小的值
 */
public class Code_07_MinStack {
    Stack<Integer> normal = new Stack<>();
    Stack<Integer> minStack = new Stack<>();

    public void push (Integer node) {
        normal.push(node);
        minStack.push(minStack.isEmpty() ? node : Math.min(minStack.peek(),node));
    }
    public Integer top() {
        return normal.pop();
    }

    public Integer min() {
        return minStack.pop();
    }
}
