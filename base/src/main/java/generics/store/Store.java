package generics.store;

import java.util.ArrayList;

/**
 * @ClassName Store
 * @Author bin
 * @Date 2020/7/9 上午10:39
 * @Decr TODO
 * @Link TODO
 **/
public class Store extends ArrayList<Aisle> {
    public Store(int nAisle,int nShelves,int nProduct){
        for(int i=0;i < nAisle ;i ++){
            add(new Aisle(nShelves,nProduct));
        }
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        for(Aisle aisle : this){
            for(Shelf shelf:aisle){
                for(Product product : shelf){
                    builder.append(product);
                    builder.append("\n");
                }
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Store(4,5,6));
    }
}
