package containers.util;

/**
 * @ClassName StringAddress
 * @Author bin
 * @Date 2020/7/21 上午11:49
 * @Decr TODO
 * @Link TODO
 **/
public class StringAddress {
    private String s;
    public StringAddress(String s){this.s = s;}
    @Override
    public String toString(){
        return super.toString()+" "+s;
    }
}
