package containers.hash;

import java.util.Set;
import java.util.TreeSet;

/**
 * @ClassName Individual
 * @Author bin
 * @Date 2020/7/23 下午3:50
 * @Decr TODO
 * @Link TODO
 **/
public class Individual implements Comparable<Individual>{
    private static long counter = 0;
    private final long id = counter ++ ;
    private String name;

    public Individual(String name){this.name = name;}

    public Individual(){}

    @Override
    public String toString(){
        return this.getClass().getSimpleName() + (name == null ? "":" "+name);
    }

    public long id(){return id;}

    @Override
    public boolean equals(Object o){
        return o instanceof Individual && id == ((Individual) o).id;
    }

    @Override
    public int hashCode(){
        int result = 17;
        if(name != null){
            result = 37*result + name.hashCode();
        }
        result = 37*result + (int)id;
        return result;
    }
    @Override
    public int compareTo(Individual arg) {
        //1。 类型比较
        String first = getClass().getSimpleName();
        String argFirst = arg.getClass().getSimpleName();

        int firstCompare = first.compareTo(argFirst);
        if(firstCompare != 0) return firstCompare;

        //2.通过name属性比较
        if(name != null && arg.name != null){
            int secondCompare = name.compareTo(arg.name);
            if(secondCompare !=0)return secondCompare;
        }
        //3. 通过id属性比较
        return (arg.id < id ? -1: (arg.id == id ? 0 : 1));
    }

    public static void main(String[] args) {
        // 按照字典排序
        Set<Individual> pets = new TreeSet<Individual>();
        pets.add(new Individual("a"));
        pets.add(new Individual("d"));
        pets.add(new Individual("f"));
        pets.add(new Individual("g"));
        pets.add(new Individual("a"));
        System.out.println(pets);
    }
}
