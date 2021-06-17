import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName MapTest
 * @Author bin
 * @Date 2020/11/11 下午8:12
 * @Decr TODO
 * @Link TODO
 **/
public class MapTest {

    private   static final int _1MB = 1024 * 1024;
    private static final String MARK = "^";
    private static final String MARK_1 = "\\^";

    public static void main(String[] args) {
        String s = "4^1616620964";
        String s1= "5#134";
        if (s.contains(MARK)) {
            String[] split = s.split(MARK_1);
            System.out.println(split[0]);
        }

        System.out.println(s1.split("#")[1]);



        //sceneTest();
    }
    public static void sceneTest(){
       // int i = 53801035;
        long start,end = 0;
        System.gc();
        start = Runtime.getRuntime().freeMemory();
        Map<String,List<SceneData>> map = Maps.newConcurrentMap();

        String[] sceneList = {"negativeHealthPoi","outOfDistancePoi","explosiveSpu"};

//        for(String scene : sceneList) {
//            List<SceneData> list =  Lists.newArrayList();
//            for(int i = 0; i <100000; i ++){
//                long currentTime = System.currentTimeMillis();
//                Timestamp timestamp = new Timestamp(currentTime);
//                int j = 10000104+i;
//                list.add(new SceneData(scene,String.valueOf(j),"{}",timestamp,1));
//            }
//            map.put(scene,list);
//        }

        List<log> objects = Lists.newArrayList();
        for(int i=0; i < 33195443;i ++){
            log l = new log(i,2055441+1,6857523+1,2," {\"startTime\":\"1605024000000\",\"endTime\":\"1636560000000\",\"promoteRatio\":\"0.3\",\"abRatio\":\"100\"}",0);
            objects.add(l);
        }

        System.gc();
        end = Runtime.getRuntime().freeMemory();

        System.out.println((start-end)/_1MB  + " " + objects.size());

    }
}
