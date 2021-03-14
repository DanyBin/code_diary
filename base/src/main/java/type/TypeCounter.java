package type;

import java.util.HashMap;

/**
 * @ClassName TypeCounter
 * @Author bin
 * @Date 2020/7/7 下午7:52
 * @Decr TODO
 * @Link TODO
 **/
public class TypeCounter extends HashMap<Class<?>,Integer> {
    private Class<?> baseType;
    public TypeCounter(Class<?> baseType){this.baseType = baseType;}

    public void count(Object obj){
        Class<?> type = obj.getClass();
        //运行使，校验传递的对象是否属于 继承对象
        if(!baseType.isAssignableFrom(type)){
            throw new RuntimeException(obj + " incorrect type:" + type + " ,should be type or subtype of " + baseType);
        }
        countClass(type);
    }
    //递归计数
    private void countClass(Class<?> type){
        Integer integer = get(type);
        put(type,integer==null? 1: integer+1);

        Class<?> superclass = type.getSuperclass();
        if(superclass != null && baseType.isAssignableFrom(superclass)){
            countClass(superclass);
        }
    }
}
