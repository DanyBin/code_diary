package array.convert;

/**
 * @ClassName ConvertTo
 * @Author bin
 * @Date 2020/7/20 下午8:24
 * @Decr TODO
 *      基于类型转换
 * @Link TODO
 **/
public class ConvertTo {

    public static boolean[] primitive(Boolean[] in){
        boolean[] result = new boolean[in.length];
        for(int i=0; i< in.length ;i ++){
            result[i] = in[i];
        }
        return result;
    }


}
