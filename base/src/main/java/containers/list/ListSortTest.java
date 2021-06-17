package containers.list;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ListSortTest
 * @Author bin
 * @Date 2021/4/1 上午11:55
 * @Decr TODO
 * @Link TODO
 **/
public class ListSortTest {

    public static void main(String[] args) {

        List<ItemId> itemIdList  = Lists.newArrayList();
        ListSortTest listSortTest = new ListSortTest();
        for (int i=0 ;i < 100; i++) {
            itemIdList.add(listSortTest.new ItemId(i,0));
        }

        itemIdList.add(10,listSortTest.new ItemId(101,2));
        itemIdList.add(11,listSortTest.new ItemId(102,2));
        itemIdList.add(12,listSortTest.new ItemId(101,2));

        itemIdList.add(20,listSortTest.new ItemId(101,2));
        itemIdList.add(21,listSortTest.new ItemId(102,2));
        itemIdList.add(22,listSortTest.new ItemId(101,2));

        itemIdList.sort(new Comparator<ItemId>() {
            @Override
            public int compare(ItemId o1, ItemId o2) {
                if (o1.type == 0 && o2.type == 0) {
                    System.out.println("差值:"+(o2.sid - o1.sid));
                    return o2.sid - o1.sid;
                }
                return 0;
            }
        });
        itemIdList.stream().forEach(t -> System.out.println(t));
    }

    class ItemId {
        int sid;
        int type;
        public ItemId(int sid,int type) {
            this.sid = sid;
            this.type = type;
        }

        @Override
        public String toString() {
            return "ItemId{" +
                    "sid=" + sid +
                    ", type=" + type +
                    '}';
        }
    }
}
