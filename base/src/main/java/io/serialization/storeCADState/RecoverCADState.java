package io.serialization.storeCADState;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * @ClassName RecoverCADState
 * @Author bin
 * @Date 2020/7/30 下午3:34
 * @Decr TODO
 *      恢复
 * @Link TODO
 **/
public class RecoverCADState {
    public static void main(String[] args) throws Exception {
        String file = "/Users/bin/gitStudy/code_diary/base/src/main/resources/CADState.out";
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));

        List<Class<? extends Shape>> shapeTypes = (List<Class<? extends Shape>>) in.readObject();
        Line.deserializeStaticState(in);
        List<Shape> shapes = (List<Shape>) in.readObject();
        System.out.println(shapes);
    }
}
