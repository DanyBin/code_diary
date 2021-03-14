package enumtype;

/**
 * @ClassName EnumClass
 * @Author bin
 * @Date 2020/7/30 下午7:42
 * @Decr TODO
 *      enum的特性
 *
 * @Link TODO
 **/
public class EnumClass {
    public static void main(String[] args) {
        for(Shrubbery s :Shrubbery.values()){
            //实例按照声明顺序，排列。从0开始
            System.out.println(s + " 对应数字=" + s.ordinal());
            //enum 的比较操作
            System.out.println(s.compareTo(Shrubbery.CRAWLING));
            System.out.println(s.equals(Shrubbery.CRAWLING));
            //获取类型信息
            System.out.println(s.getDeclaringClass());
            System.out.println(s.name());
            System.out.println("-----------------");
        }

        //通过名字，返回enum的实例
        for(String s:"GROUND,CRAWLING,HANGINT".split(",")){
            Shrubbery shrubbery = Enum.valueOf(Shrubbery.class,s);
            System.out.println(shrubbery);
        }
    }
}
