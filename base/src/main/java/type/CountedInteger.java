package type;

/**
 * @ClassName CountedInteger
 * @Author bin
 * @Date 2020/7/7 下午2:28
 * @Decr TODO
 * @Link TODO
 **/
public class CountedInteger {
    private static long counter;
    private final long id = counter ++;
    @Override
    public String toString(){return Long.toString(id);}
}
