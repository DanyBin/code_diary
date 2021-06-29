package executor.scheduled;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorTest {
  public static void main(String[] args) {
    ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
    scheduledThreadPool.schedule(() -> {
          System.out.println("创建并执行在给定延迟后启用的一次性操作。");
        },
        5,
        TimeUnit.MILLISECONDS
    );

    scheduledThreadPool.scheduleAtFixedRate(() -> {
          System.out.println("以固定周期频率执行任务");
        },
        0,
        5,
        TimeUnit.MILLISECONDS
    );

    scheduledThreadPool.scheduleWithFixedDelay(() -> {
          System.out.println("以固定延迟时间进行执行,本次任务执行完成后，需要延迟设定的延迟时间，才会执行新的任务");
        },
        0,
        5,
        TimeUnit.MILLISECONDS
    );
  }
}
