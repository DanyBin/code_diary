package process1.task.callback;

import lombok.Getter;
import lombok.Setter;
import process1.task.AbstractTask;
import rpc.InvocationCallback;

public class RPCCallBackTask<T> extends AbstractTask<T> implements InvocationCallback {
  @Getter
  @Setter
  private int weight = 3;

  @Override
  public void onSuccess(Object var1) {
    try {
      this.callback((T)var1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    this.markSuccess();
  }

  @Override
  public void onFailure(Throwable var1) {
    this.failed(var1);
    this.markFailure();
  }

  @Override
  public void sendRequest() {
    if (!this.match()) {
      this.markSuccess();
    } else if (this.markInProcess()) {
      try {
        this.execute();
      } catch (Exception e) {
        this.markFailure();
      }
    }
  }



}
