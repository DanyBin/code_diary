package containers.hash;

import java.util.Random;

/**
 * @ClassName Prediction
 * @Author bin
 * @Date 2020/7/22 下午4:59
 * @Decr TODO
 *      天气
 * @Link TODO
 **/
public class Prediction {
    private static Random rand = new Random(47);
    private boolean shaow = rand.nextDouble() > 0.5;
    @Override
    public String toString(){
        if(shaow){
            return "six more weeks of Winter !";
        }else {
            return "Early Spring";
        }
    }
}
