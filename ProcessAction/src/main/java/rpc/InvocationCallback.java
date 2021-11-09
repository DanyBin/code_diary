package rpc;

public interface InvocationCallback {
  void onSuccess(Object var1);

  void onFailure(Throwable var1);
}
