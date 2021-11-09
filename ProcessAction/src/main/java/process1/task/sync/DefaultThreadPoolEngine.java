package process1.task.sync;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class DefaultThreadPoolEngine {
  //线程池状态
  private static AtomicInteger status = new AtomicInteger(-1);

  //Todo 一下可以使用动态变更的服务- 例如 mt的lion。来动态更新配置

  //线程池的core 与 max的配置
  private static AtomicInteger coreAndMax = new AtomicInteger(0);
  private static volatile ThreadPoolExecutor syncTaskPool;


  public static void open(){
    open(50,100);
  }

  public static void open(int core,int max) {
    //更新线程池的配置
    if (status.compareAndSet(-1,0)) {
      //Todo 增加监听功能
      initCoreAndMax(core,max);
    }
  }

  public static void stop() {
    if (status.get() != -1) {
      status.set(-1);
    }

    if (syncTaskPool != null) {
      syncTaskPool.shutdown();
    }
  }

  /**
   * 初始化线程池
   * @param core
   * @param max
   */
  private static void initCoreAndMax(int core,int max) {
    //int=32位。 左移16位，将core放在高的16位。 max在低16位。并做或的操作。
    //一个字段，保存两种配置
    int core_max = core << 16 | max;
    if (coreAndMax.get() != core_max) {
      //保证原子行。 -- 一系列的操作
      synchronized (coreAndMax) {
        //更新线程池状态
        status.set(0);
        ThreadPoolExecutor syncPool = new ThreadPoolExecutor(core,max,60L, TimeUnit.SECONDS,new LinkedBlockingDeque<>());
        ThreadPoolExecutor oldSyncTaskPool = syncTaskPool;
        syncTaskPool = syncPool;
        coreAndMax.set(core_max);
        status.set(1);
        if (oldSyncTaskPool != null) {
          oldSyncTaskPool.shutdown();
        }
        System.out.println("线程池初始化成功，当前核心线程数" + coreAndMax);
      }
    }
  }

  /**
   * 执行异步任务
   * @param task
   */
  static void execute(SyncTask task) {
    //没有初始化线程池
    if(status.get() == -1) {
      task.run();
      //线程池初始化中
    }else if (status.get() == 0) {
      task.run();
    }//利用线程池达到 最大线程数
    else if (syncTaskPool.getActiveCount() < syncTaskPool.getMaximumPoolSize() && syncTaskPool.getQueue().size() < 1) {
      syncTaskPool.execute(task);
    } else {
      task.run();
    }
  }
}
