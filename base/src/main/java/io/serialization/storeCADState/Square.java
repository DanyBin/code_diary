package io.serialization.storeCADState;

/**
 * @ClassName Square
 * @Author bin
 * @Date 2020/7/30 下午3:13
 * @Decr TODO
 * @Link TODO
 **/
public class Square extends Shape {
    private static int color;

    @Override
    public void setColor(int newColor) {
        color = newColor;
    }

    @Override
    public int getColor() {
        return color;
    }

    //构造器中初始化
    public Square(int x, int y, int dim){
        super(x,y,dim);
        color = RED;
    }

}
