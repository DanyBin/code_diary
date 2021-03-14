package containers.queue;

import java.util.PriorityQueue;

/**
 * @ClassName ToDoList
 * @Author bin
 * @Date 2020/7/22 下午3:49
 * @Decr TODO
 *      优先级队列
 * @Link TODO
 **/
public class ToDoList extends PriorityQueue<ToDoList.ToDoItem> {
    static class ToDoItem implements Comparable<ToDoItem>{
        private char primary;
        private int  secondary;
        private String item;

        public ToDoItem(String td,char pri, int sec){
            primary = pri;
            secondary = sec;
            item = td;
        }
        public int compareTo(ToDoItem o) {
            if(primary > o.primary){
                return +1;
            }
            if(primary == o.primary){
                if(secondary > o.secondary){
                    return +1;
                }else if(secondary == o.secondary){
                    return 0;
                }else {
                    return -1;
                }
            }
            return -1;
        }

        @Override
        public String toString(){
            return Character.toString(primary) + secondary + ":" +item;
        }
    }

    public void add(String td,char pri,int sec){
        super.add(new ToDoItem(td,pri,sec));
    }

    public static void main(String[] args) {
        ToDoList toDoList = new ToDoList();
        toDoList.add("empty",'C',4);
        toDoList.add("feed",'A',2);
        toDoList.add("feed",'B',7);
        while (!toDoList.isEmpty()){
            System.out.println(toDoList.remove());
        }
    }
}
