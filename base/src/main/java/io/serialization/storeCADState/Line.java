package io.serialization.storeCADState;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * @ClassName Line
 * @Author bin
 * @Date 2020/7/30 下午3:15
 * @Decr TODO
 * @Link TODO
 **/
public class Line extends Shape {

    private static int color = RED;

    public static void serilaizeStaticState(ObjectOutputStream os) throws IOException {
        os.writeObject(color);
    }

    public static void deserializeStaticState(ObjectInputStream os) throws IOException, ClassNotFoundException {
        color = (Integer) os.readObject();
    }

    public Line(int x,int y, int dim){
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
