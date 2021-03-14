package containers.hash;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;


/**
 * @ClassName SpringDetector
 * @Author bin
 * @Date 2020/7/22 下午5:01
 * @Decr TODO
 * @Link TODO
 **/
public class SpringDetector {
    public static <T extends Groundhog> void detectSpring(Class<T> type) throws Exception{

        //通过反射来实例化
        Constructor<T> constructor = type.getConstructor(int.class);
        Map<Groundhog,Prediction> map = new HashMap<Groundhog, Prediction>();
        for(int i=0;i < 10; i++){
            map.put(constructor.newInstance(i),new Prediction());
        }
        System.out.println(map);

        Groundhog gh = constructor.newInstance(3);
        if(map.containsKey(gh)){
            System.out.println(map.get(gh));
        }else {
            System.out.println("key not found");
        }
    }

    public static void main(String[] args) throws Exception {
        detectSpring(Groundhog.class);
    }
}
