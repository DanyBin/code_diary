package util;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTest {
  public static void main(String[] args) {
    final ExecutorService executorService = Executors.newFixedThreadPool(5);
    for (int i= 0; i < 1;i ++) {
      executorService.execute(new Runnable() {
        @Override
        public void run() {
          final Context context = new Context();
          List<Task> taskList = ImmutableList.of(new Task(context),new Task(context),new Task(context),new Task(context));
          context.countDownLatch = new CountDownLatch(taskList.size());
          for (Task task : taskList) {
            executorService.execute(task);
          }

          context.countDownLatch.await();

          System.out.println("执行成功");
        }
      });
    }
  }


  private static class Task implements Runnable {
    Context context;
    public Task(Context context) {
      this.context  = context;
    }
    @Override
    public void run() {
      try {
        System.out.println();
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        context.countDownLatch.countDown();
      }
    }
  }

  private static class Context {
    CountDownLatch countDownLatch;
  }
}
