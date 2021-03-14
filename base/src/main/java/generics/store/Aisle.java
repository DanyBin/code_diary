package generics.store;

import java.util.ArrayList;

/**
 * @ClassName Aisle
 * @Author bin
 * @Date 2020/7/9 上午10:37
 * @Decr TODO
 * @Link TODO
 **/
public class Aisle extends ArrayList<Shelf> {
    public Aisle(int nShelf,int nProduct){
        for(int i=0;i < nShelf ;i ++){
            add(new Shelf(nProduct));
        }
    }
}
