import java.util.concurrent.*;

public class ThreadTime {
  private String tmp = "11";
  public static void main(String[] args) {
    final ThreadTime threadTime = new ThreadTime();
    threadTime.ss();
  }
  private void ss() {
    this.tt();
    System.out.println("111");
    System.out.println(tmp);
  }
  public void tt () {
    final ExecutorService executorService = Executors.newFixedThreadPool(10);
    final Future<?> future = executorService.submit(() -> {
      System.out.println("执行任务");
      try {
        Thread.sleep(100);
        tmp = "22";
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    try {
      final Object o = future.get(10, TimeUnit.MILLISECONDS);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    } catch (TimeoutException e) {
      e.printStackTrace();
    }
  }
}
