package process1.task.sync;

import com.google.common.util.concurrent.SettableFuture;
import lombok.Getter;
import lombok.Setter;
import process1.factory.FutureFactory;
import process1.task.AbstractTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

abstract
public class SyncTask<T> extends AbstractTask<T> implements FutureFactory,Runnable {
  //异步 -> 同步。 将Future的结果放在该对象中
  private SettableFuture settableFuture;
  @Getter
  @Setter
  private int weight = 1;

  @Override
  public Future getFuture() {
    return this.settableFuture != null ? this.settableFuture : null;
  }

  /**
   * 核心接口 - 执行主要逻辑
   * @return
   */
  public abstract T doExecute();

  @Override
  public void execute() {
    this.settableFuture = SettableFuture.create();
    //设置Future -- 结果
    this.settableFuture.set(this.doExecute());
  }

  @Override
  public void sendRequest() {
    if(!this.match()) {
      this.markSuccess();
    } else if (this.markInProcess()) {
      //执行当前请求
      DefaultThreadPoolEngine.execute(this);
    }
  }

  @Override
  public void run() {
    try {
      this.execute();
    }catch (Exception e) {
      e.printStackTrace();
    }

    int state = 2;
    Future syncFuture = null;

    try {
      syncFuture = this.getFuture();
    } catch (Exception e) {
      e.printStackTrace();
      state = 3;
    }

    Object syncResult = null;

    try {
      syncFuture.get(this.getTimeout(), TimeUnit.MILLISECONDS);
    } catch (Exception e) {
      e.printStackTrace();
    }

    if (syncFuture == null) {
      this.markSuccess();
    } else {
      try {
        //出发回调接口
        this.callback((T)syncResult);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      if (state == 3) {
        this.markFailure();
      } else {
        this.markSuccess();
      }
    }
  }
}
