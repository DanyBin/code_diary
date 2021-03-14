package io.serialization.storeCADState;

/**
 * @ClassName Circle
 * @Author bin
 * @Date 2020/7/30 下午3:10
 * @Decr TODO
 * @Link TODO
 **/
public class Circle extends Shape {

    private static int color = RED;

    public Circle(int x,int y,int dim){
        super(x,y,dim);
    }
    @Override
    public void setColor(int newColor) {
        color = newColor;
    }
    @Override
    public int getColor() {
        return color;
    }
}
