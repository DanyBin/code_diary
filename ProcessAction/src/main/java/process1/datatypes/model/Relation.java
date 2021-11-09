package process1.datatypes.model;

//节点之间的关系
public class Relation {
  //调用成功
  public static final Relation INVOKE_SUCCESS = new Relation();

  //调用失败
  public static final Relation INVOKE_FAILURE = new Relation();

  //调用-总是
  public static final Relation INVOKE_ALWAYS = new Relation();

}
