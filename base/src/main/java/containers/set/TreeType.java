package containers.set;

/**
 * @ClassName TreeType
 * @Author bin
 * @Date 2020/7/22 下午2:45
 * @Decr TODO
 * @Link TODO
 **/
public class TreeType extends SetType implements Comparable<TreeType> {

    public TreeType(int n) {
        super(n);
    }
    @Override
    public int compareTo(TreeType treeType){
        return treeType.i < i ? -1 : (treeType.i == i ? 0 : 1);
    }
}
