package reference;

/**
 * @ClassName Element
 * @Author bin
 * @Date 2020/7/23 下午7:14
 * @Decr TODO
 * @Link TODO
 **/
public class Element {
    private String ident;
    public Element(String id){ident = id;}
    @Override
    public String toString(){
        return ident;
    }

    @Override
    public int hashCode(){
        return ident.hashCode();
    }
    @Override
    public boolean equals(Object o){
        return o instanceof Element && ident.equals(((Element) o).ident);
    }

    @Override
    protected void finalize(){
        System.out.println("finalize: "+ getClass().getSimpleName());
    }
}
