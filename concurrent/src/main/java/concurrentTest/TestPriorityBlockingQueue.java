package concurrentTest;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @ClassName TestPriorityBlockingQueue
 * @Author bin
 * @Date 2020/9/18 上午11:21
 * @Decr TODO
 * @Link TODO
 **/
public class TestPriorityBlockingQueue {

    static class Task implements Comparable<Task> {

        private int priority = 0;
        private String taskName;


        @Override
        public int compareTo(Task o) {
            if(this.priority >= o.getPriority()) {
                return 1;
            }else {
                return -1;
            }
        }

        public void doSomeThing() {
            System.out.println(taskName + ":" + priority);
        }

        public String getTaskName() {
            return taskName;
        }

        public Task setTaskName(String taskName) {
            this.taskName = taskName;
            return this;
        }

        public int getPriority() {
            return priority;
        }

        public Task setPriority(int priority) {
            this.priority = priority;
            return this;
        }
    }

    public static void main(String[] args) {

        PriorityBlockingQueue<Task> queue = new PriorityBlockingQueue<>();
        Random random = new Random();

        for(int i=0; i < 10 ;i ++) {
            Task task = new Task();
            task.setPriority(random.nextInt(10));
            task.setTaskName("taskName" + i);
            queue.offer(task);
        }

        while (!queue.isEmpty()) {
            Task poll = queue.poll();
            if(null != poll) {
                poll.doSomeThing();
            }
        }
    }
}
