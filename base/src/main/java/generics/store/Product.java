package generics.store;

import generics.Generator;

import java.util.Random;

/**
 * @ClassName Product
 * @Author bin
 * @Date 2020/7/9 上午10:31
 * @Decr TODO
 * @Link TODO
 **/
public class Product {
    private final int id;
    private String desc;
    private double price;

    public Product(int id,String desc,double price){
        this.id = id;
        this.desc = desc;
        this.price = price;
    }
    @Override
    public String toString(){
        return id + " :  " + desc + " price :    "+ price;
    }
    public void priceChange(double change){
        price += change;
    }

    public static Generator<Product> generator = new Generator<Product>() {
        private Random rand = new Random(47);
        public Product next() {
            return  new Product(rand.nextInt(1000),"Test",Math.round(rand.nextDouble()*1000)+0.99);
        }
    };
}
