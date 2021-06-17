package let;

/**
 * @ClassName ReplaceSpace
 * @Author bin
 * @Date 2021/4/7 上午10:16
 * @Decr TODO
 *      替换字符串。 包括空格
 *
 *      思路。迭代，但空格必须使用%20来代替
 *
 * @Link TODO
 **/
public class ReplaceSpace {
    public String replaceSpace(String s) {
        if (null == s) {
            return null;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i =0;i < s.length();i ++) {
            if (s.charAt(i) == ' ') {
                stringBuilder.append("%20");
            } else {
                stringBuilder.append(s.charAt(i));
            }
        }
        return stringBuilder.toString();
    }
}
