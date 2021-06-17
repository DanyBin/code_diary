package lombok;

/**
 * @ClassName T1
 * @Author bin
 * @Date 2021/5/12 下午7:39
 * @Decr TODO
 * @Link TODO
 **/
@ToString
@Getter
public class T1 {
  private double x;
  private double y;

  public static void main(String[] args) {
    System.out.println(new T1());
  }
}
