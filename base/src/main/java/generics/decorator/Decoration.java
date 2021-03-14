package generics.decorator;

/**
 * @ClassName Decoration
 * @Author bin
 * @Date 2020/7/13 下午8:00
 * @Decr TODO
 * @Link TODO
 **/
public class Decoration {
    public static void main(String[] args) {
        TimeStamped timeStamped = new TimeStamped(new Basic());
        TimeStamped timeStamped1 = new TimeStamped(new SerialNumbered(new Basic()));


        //System.out.println(timeStamped1.getSerialNumber); 不可用

        SerialNumbered serialNumbered = new SerialNumbered(new Basic());
        SerialNumbered serialNumbered1 = new SerialNumbered(new TimeStamped(new Basic()));

    }
}
