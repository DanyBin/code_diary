package enumtype.designPatterns;


import java.util.EnumMap;

import static enumtype.designPatterns.Input.*;

/**
 * @ClassName Category
 * @Author bin
 * @Date 2020/8/4 下午12:05
 * @Decr TODO
 *    状态机-   将Input 进行归类
 * @Link TODO
 **/
public enum  Category {
    //金额
    MONEY(NICKEL,DIME,QUARTER,DOLLAR),
    //商品
    ITEM_SELECTION( TOOTHPASTE,CHIPS,SODA,SOAP),
    //退出交易
    QUIT_TRANSACTION(ABORT_TRANSACTION),
    //停止
    SHOT_DOWN(STOP);

    private Input[] inputs;
    Category(Input...type){
        inputs = type;
    }

    private static EnumMap<Input,Category> map = new EnumMap<Input, Category>(Input.class);

    static {
        for(Category c : Category.class.getEnumConstants()){
            for(Input type : c.inputs ){
                map.put(type,c);
            }
        }
    }

    public static Category categorize(Input input){
        return map.get(input);
    }
}
