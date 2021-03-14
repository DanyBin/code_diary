package containers.hash;

/**
 * @ClassName StringHashCode
 * @Author bin
 * @Date 2020/7/22 下午7:56
 * @Decr TODO
 * @Link TODO
 **/
public class StringHashCode {
    public static void main(String[] args) {
        String[] hellos = "Hello Hello".split(" ");
        System.out.println(hellos[0].hashCode());
        System.out.println(hellos[1].hashCode());
    }
}
