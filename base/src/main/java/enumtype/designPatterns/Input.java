package enumtype.designPatterns;

import java.util.Random;

/**
 * @ClassName Input
 * @Author bin
 * @Date 2020/8/4 上午11:43
 * @Decr TODO
 *          状态机的例子= 自动售货机
 * @Link TODO
 **/
public enum  Input {
    NICKEL(5),DIME(10),QUARTER(25),DOLLAR(100),
    TOOTHPASTE(200),CHIPS(75),SODA(100),SOAP(50),
    ABORT_TRANSACTION {
        @Override
        public int amount(){
            throw  new RuntimeException("ABORT amount");
        }
    },
    STOP {
        @Override
        public int amount(){
            throw  new RuntimeException("STOP amount");
        }
    }
    ;

    int value;
    Input(int value) {this.value = value;}
    Input(){};
    int amount(){return value;}

    static Random rand = new Random(47);
    public static Input randomSelection () {
        return values()[rand.nextInt(values().length-1)];
    }
}
