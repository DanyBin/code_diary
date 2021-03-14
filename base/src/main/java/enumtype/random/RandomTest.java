package enumtype.random;

/**
 * @ClassName RandomTest
 * @Author bin
 * @Date 2020/7/31 下午12:06
 * @Decr TODO
 * @Link TODO
 **/
public class RandomTest {
    public static void main(String[] args) {
        for(int i=0;i < 20 ;i ++){
            System.out.print(Enums.random(Activity.class) + " ");
        }
    }
}
