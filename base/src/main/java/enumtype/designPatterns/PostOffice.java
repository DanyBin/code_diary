package enumtype.designPatterns;

/**
 * @ClassName PostOffice
 * @Author bin
 * @Date 2020/8/4 上午11:26
 * @Decr TODO
 *         责任链- 通过enum定义各个解决策略，对于每一封邮件都按照顺序解决。
 *         其中一个处理成功，则返会。否则所有策略全部失败，判定为死信
 * @Link TODO
 **/
public class PostOffice {
    enum MailHandler {

        GENERAL_DELIVERY {
            boolean handle(Mail m) {
                switch (m.generalDelivery) {
                    case YES:
                        System.out.println("使用 generalDelivery" + m);
                        return true;
                    default:
                        return false;
                }
            }
        },
        MACHINE_SCAN {
            boolean handle(Mail m) {
                switch (m.scannability) {
                    case UNSCANNABLE:return false;
                    default:
                        switch (m.address) {
                            case INCORRECT:return false;
                            default:
                                System.out.println("自动交付" + m);
                                return true;
                        }
                }
            }
        },
        VISUAL_INSPECTION {
            boolean handle(Mail m) {
                switch (m.readability) {
                    case ILLEGIBLE:return false;
                    default:
                        switch (m.address) {
                            case INCORRECT: return false;
                            default:
                                System.out.println("正常交付"+m);
                                return true;
                        }
                }
            }
        },
        RETURN_TO_SENDER {
            boolean handle (Mail m) {
                switch (m.returnAddress) {
                    case MISSING:return false;
                    default:
                        System.out.println("回信"+m);
                        return true;
                }
            }
        };

        abstract boolean handle(Mail m);
    }

    static void handle(Mail m) {
        for(MailHandler mail : MailHandler.values()){
            if(mail.handle(m)){
                return;
            }
        }
        System.out.println( m +" is a dead letter (死信)");
    }

    public static void main(String[] args) {
        for(Mail mail : Mail.generator(10)){
            System.out.println(mail.details());
            handle(mail);
            System.out.println("------------");
        }
    }
}
