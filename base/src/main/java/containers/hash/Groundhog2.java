package containers.hash;

/**
 * @ClassName Groundhog2
 * @Author bin
 * @Date 2020/7/22 下午5:11
 * @Decr TODO
 * @Link TODO
 **/
public class Groundhog2 extends Groundhog{
    public Groundhog2(int i) {
        super(i);
    }

    @Override
    public int hashCode(){return number;}

    @Override
    public boolean equals(Object o){
        return o instanceof Groundhog2 && (number == ((Groundhog2)o).number);
    }
}
