package io.serialization.storeCADState;

import java.io.Serializable;
import java.util.Random;

/**
 * @ClassName Shape
 * @Author bin
 * @Date 2020/7/30 下午2:59
 * @Decr TODO
 * @Link TODO
 **/
public abstract class Shape implements Serializable {
    public static final int RED = 1,BLUE = 2,GREEN = 3;
    private int xPos, yPos, dimension;
    private static Random rand = new Random(47);
    private static int counter = 0;

    public abstract void setColor(int newColor);

    public abstract int getColor();

    public Shape(int x,int y,int dim){
        xPos = x;
        yPos = y;
        dimension = dim;
    }

    @Override
    public String toString(){
        return getClass() + "color[" + getColor()+"] xPos[" + xPos + "] yPos[" + yPos +"] dim["+ dimension +"]\n";
    }

    public static Shape randomFactory(){
        int x = rand.nextInt(100);
        int y = rand.nextInt(100);
        int dim = rand.nextInt(100);

        switch (counter ++ % 3){
            default:
            case 0 : return new Circle(x,y,dim);
            case 1 : return new Square(x,y,dim);
            case 2 : return new Line(x,y,dim);
        }

    }

}
