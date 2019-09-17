package until;

/**
 * @ClassName ArrayUntil
 * @Author bin
 * @Date 2019/9/17 下午8:01
 * @Decr TODO
 *      数组工具
 * @Link TODO
 **/
public class ArrayUntil {

    /**
     * 打印数组
     * @param array - 数组.int类型
     */
    public static String printArray(int[] array){
        StringBuilder builder = new StringBuilder("print Array:");
        for(int i:array){
            builder.append(i).append(",");
        }
       return builder.toString().substring(0,builder.toString().lastIndexOf(","));
    }
}
