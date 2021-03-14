package enumtype.designPatterns;

import enumtype.random.Enums;

import java.util.Iterator;

/**
 * @ClassName Mail
 * @Author bin
 * @Date 2020/8/3 下午12:03
 * @Decr TODO
 *      通过使用enum，实现责任链的设计模式
 *
 * @Link TODO
 **/
public class Mail {

    enum GeneralDelivery {YES,NO1,NO2,NO3,NO4,NO5}
    enum Scannability{UNSCANNABLE,YES1,YES2,YES3,YES4}
    enum Readability {ILLEGIBLE,YES1,YES2,YES3,YES4}
    enum Address {INCORRECT,OK1,OK2,OK3,OK4,OK5,OK6}
    enum ReturnAddress{MISSING,OK1,OK2,OK3,OK4,OK5}

    GeneralDelivery generalDelivery;
    Scannability scannability;
    Readability readability;
    Address address;
    ReturnAddress returnAddress;

    static long counter = 0;
    long id = counter ++ ;

    @Override
    public String toString(){return "Mail " + id;}

    public String details() {
        return toString()+
                ", GeneralDelivery" + generalDelivery +
                ", Address Scannability" + scannability +
                ", Address Readability" + readability +
                ", Address Address" + address +
                ", ReturnAddress" + returnAddress;
    }

    public static Mail randomMail() {
        Mail m = new Mail();
        m.generalDelivery = Enums.random(GeneralDelivery.class);
        m.scannability = Enums.random(Scannability.class);
        m.readability = Enums.random(Readability.class);
        m.address = Enums.random(Address.class);
        m.returnAddress = Enums.random(ReturnAddress.class);
        return m;
    }

    //创建迭代器
    public static Iterable<Mail> generator (final int count) {
        return new Iterable<Mail>() {
            int n = count;
            public Iterator<Mail> iterator() {
                return new Iterator<Mail>() {
                    public boolean hasNext() {return n-- > 0;}
                    public Mail next() {return randomMail();}
                    public void remove() {throw new UnsupportedOperationException();}
                };
            }
        };
    }

    public class PostOffice {

    }
}
