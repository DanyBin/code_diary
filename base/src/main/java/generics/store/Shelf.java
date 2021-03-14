package generics.store;

import generics.Generators;

import java.util.ArrayList;

/**
 * @ClassName Shelf
 * @Author bin
 * @Date 2020/7/9 上午10:36
 * @Decr TODO
 * @Link TODO
 **/
public class Shelf extends ArrayList<Product> {
    public Shelf(int nProduct){
        Generators.fill(this,Product.generator,nProduct);
    }
}
