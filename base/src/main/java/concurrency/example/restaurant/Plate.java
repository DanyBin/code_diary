package concurrency.example.restaurant;

/**
 * @ClassName Plate
 * @Author bin
 * @Date 2020/8/24 下午7:01
 * @Decr TODO
 *      厨师做好菜，放在盘子里
 * @Link TODO
 **/
public class Plate {
    private final Order order;
    private final Food food;

    public Plate(Order ord,Food f){
        this.order = ord;
        this.food = f;
    }

    public Order getOrder(){return order;}
    public Food getFood(){return food;}

    @Override
    public String toString() {
        return food.toString();
    }
}
