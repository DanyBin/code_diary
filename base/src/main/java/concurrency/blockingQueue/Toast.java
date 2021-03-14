package concurrency.blockingQueue;

/**
 * @ClassName Toast
 * @Author bin
 * @Date 2020/8/19 下午3:31
 * @Decr TODO
 *      一台机器上有三个任务：
 *              制作吐司、给吐司摸黄油、在摸过黄油的吐司上涂果酱
 * @Link TODO
 **/
public class Toast {
    public enum Status {DRY,BUTTERED,JAMMED}

    private Status status = Status.DRY;
    private final int id;

    public Toast(int idn) {id =idn;}

    public void butter() {status = Status.BUTTERED;}
    public void jam(){status = Status.JAMMED;}

    public Status getStatus() {return status;}
    public int getId(){return id;}

    @Override
    public String toString() {
        return "Toast " + id + ": " + status;
    }
}
