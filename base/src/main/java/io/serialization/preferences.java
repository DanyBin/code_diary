package io.serialization;

import java.util.prefs.Preferences;

/**
 * @ClassName preferences
 * @Author bin
 * @Date 2020/7/30 下午4:16
 * @Decr TODO
 *      Perferences 应用场景： 只能存储基本类型与字符串，并且每个字符串不能改超过8k。
 *                         ： 键-值集合(映射)
 * @Link TODO
 **/
public class preferences {
    public static void main(String[] args) throws Exception {
        //创建节点
        Preferences prefs = Preferences.userNodeForPackage(preferences.class);
        //节点中添加数据
        prefs.put("a","b");
        prefs.put("c","1234");
        prefs.putBoolean("d",true);

        int usageCount = prefs.getInt("UsageCounter",0);
        usageCount ++ ;
        prefs.putInt("UsageCounter",usageCount);

        for (String key:prefs.keys()){
            System.out.println( key + " " +prefs.get(key,null));
        }
    }
}
