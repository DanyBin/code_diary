package io.serialization.storeCADState;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName StoreCADState
 * @Author bin
 * @Date 2020/7/30 下午3:27
 * @Decr TODO
 *          对于static变量，Class初始化后 -> 进行修改->序列化 。反序列化时，static的变量是没有改变。
 *          需要在序列化时，手动写入static变量，反序列化时，才能看到 修改后的值。
 * @Link TODO
 **/
public class StoreCADState {

    public static void main(String[] args) throws Exception{
        String file = "/Users/bin/gitStudy/code_diary/base/src/main/resources/CADState.out";
        List<Class<? extends Shape>> shapeTypes = new ArrayList<Class<? extends Shape>>();

        shapeTypes.add(Circle.class);
        shapeTypes.add(Square.class);
        shapeTypes.add(Line.class);

        List<Shape> shapes = new ArrayList<Shape>();
        for(int i=0; i < 10; i++){
            shapes.add(Shape.randomFactory());
        }

        //修改static的变量
        for(int i=0; i < 10; i++){
            shapes.get(i).setColor(Shape.GREEN);
        }

        //序列化
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        //修改static变量后，要实现序列化，要手动实现
        out.writeObject(shapeTypes);
        Line.serilaizeStaticState(out);
        out.writeObject(shapes);

        System.out.println(shapes);
    }
}
