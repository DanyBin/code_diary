package lombok;

/**
 * @ClassName T2
 * @Author bin
 * @Date 2021/5/12 下午7:40
 * @Decr TODO
 * @Link TODO
 **/
@ToString(callSuper = true)
public class T2 extends T1 {
  private String s = "s";
  private String y = "y";

  public static void main(String[] args) {
    Class<Integer> t = Integer.TYPE;
    Class<Integer> t2 = Integer.TYPE;

    boolean assignableFrom = t.isAssignableFrom(t2);


    System.out.println(assignableFrom);
  }
}
