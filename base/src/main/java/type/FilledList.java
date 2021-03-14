package type;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName FilledList
 * @Author bin
 * @Date 2020/7/7 下午2:26
 * @Decr TODO
 * @Link TODO
 **/
public class FilledList<T> {
    private Class<T> type;
    public FilledList(Class<T> type){
        this.type = type;
    }

    public List<T> create(int el){
        List<T> list = new ArrayList<T>();
        try {
            for(int i=0;i < el; i++){
                list.add(type.newInstance());
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        FilledList<CountedInteger> fl = new FilledList<CountedInteger>(CountedInteger.class);
        System.out.println(fl.create(15));
    }
}
