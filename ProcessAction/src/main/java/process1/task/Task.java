package process1.task;

public interface Task<T> {
  //名称
  String getTaskName();
  //执行
  void execute();
  //回调
  boolean callback(T var1) throws InterruptedException;

  //失败
  void failed(Throwable var1);

  //准入条件
  boolean match();

}
