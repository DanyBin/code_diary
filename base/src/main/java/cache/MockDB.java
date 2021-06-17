package cache;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

import java.util.Collections;
import java.util.List;

/**
 * @ClassName MockDB
 * @Author bin
 * @Date 2021/3/29 下午6:37
 * @Decr TODO
 * @Link TODO
 **/
public class MockDB {
    public static List<String> getCity (String cityId) {
        System.out.println("getting from DB,cityId:" + cityId + " please wait...");
        List<String> returnList = null;
        try {
            switch (cityId) {
                case "0101" :
                    returnList = ImmutableList.of("北京","上海");
                    break;
            }
        } catch (Exception e) {

        }
        return Optional.fromNullable(returnList).or(Collections.EMPTY_LIST);
    }
}
