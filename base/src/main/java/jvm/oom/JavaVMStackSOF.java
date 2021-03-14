package jvm.oom;

/**
 * @ClassName JavaVMStackSOF
 * @Author bin
 * @Date 2020/9/29 下午5:34
 * @Decr TODO
 *   VM Args:-Xss128k
 * @Link TODO
 **/
public class JavaVMStackSOF {
    private int stackLength = 1;

    public void stackLeak() {
        stackLength ++ ;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
