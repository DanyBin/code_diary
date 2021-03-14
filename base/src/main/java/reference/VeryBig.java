package reference;

/**
 * @ClassName VeryBig
 * @Author bin
 * @Date 2020/7/23 下午5:31
 * @Decr TODO
 * @Link TODO
 **/
public class VeryBig {
    private static final int SIZE = 10000;
    private long[] la = new long[SIZE];
    private String ident;
    public VeryBig(String id){ident = id;}
    @Override
    public String toString(){
        return ident;
    }
    @Override
    protected void finalize(){
        System.out.println("Finalize "+ident);
    }
}
