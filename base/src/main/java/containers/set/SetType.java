package containers.set;

/**
 * @ClassName SetType
 * @Author bin
 * @Date 2020/7/21 下午7:48
 * @Decr TODO
 * @Link TODO
 **/
public class SetType {
    int i;
    public SetType(int n){i=n;}
    @Override
    public boolean equals(Object o){
        return o instanceof SetType && (i == ((SetType)o).i);
    }
}
