package enumtype.method;

import java.util.EnumSet;

/**
 * @ClassName CarWash
 * @Author bin
 * @Date 2020/8/3 上午11:37
 * @Decr TODO
 *      洗车的例子.
 *          使用常量定义相关方法
 * @Link TODO
 **/
public class   CarWash {

    public enum Cycle {
        UNDERBODY {
            void action() {
                System.out.println("喷涂底部");
            }
        },
        WhEELWASH {
            void action(){
                System.out.println("清洗轮胎");
            }
        },
        PREWASH {
            void action(){
                System.out.println("减少污垢");
            }
        },
        BASIC {
            void action(){
                System.out.println("基础清洗");
            }
        };
        abstract void action();
    }

    EnumSet<Cycle> cycles = EnumSet.of(Cycle.BASIC,Cycle.UNDERBODY);
    public void add(Cycle cycle) {cycles.add(cycle);}

    public void washCar() {
        for(Cycle c: cycles){
            c.action();
        }
    }

    @Override
    public String toString(){return cycles.toString();}

    public static void main(String[] args) {
        CarWash wash = new CarWash();
        System.out.println(wash);

        wash.washCar();

        wash.add(Cycle.PREWASH);
        wash.add(Cycle.WhEELWASH);
        System.out.println(wash);
        wash.washCar();
    }
}
