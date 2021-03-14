package enumtype;

import enumtype.random.Enums;

/**
 * @ClassName SecurityCategory
 * @Author bin
 * @Date 2020/8/3 上午10:38
 * @Decr TODO
 *      将一个enum 嵌套在另一个 enum中
 * @Link TODO
 **/
public enum  SecurityCategory {

    STOCK(Security.Stock.class),BOUND(Security.Bond.class);

    Security[] value;
    SecurityCategory(Class<? extends Security> kind){
        value = kind.getEnumConstants();
    }

    //组装成 公共类型
    interface Security {
        enum Stock implements Security {SHORT,LONG,MARGIN}
        enum Bond implements Security {MUNICIPAL,JUNK}
    }

    public Security randomSelection(){
        return Enums.random(value);
    }

    public static void main(String[] args) {
        for(int i=0;i < 10; i++){
            SecurityCategory category = Enums.random(SecurityCategory.class);
            System.out.println(category + ": " + category.randomSelection());
        }
    }
}
