package jvm.oom;

/**
 * @ClassName JavaVMStackOOM
 * @Author bin
 * @Date 2020/9/29 下午8:13
 * @Decr TODO
 * VM Args: -Xss2M
 *
 * @Link TODO
 **/
public class JavaVMStackOOM {

    private void dontStop(){
        while (true) {

        }
    }

    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }

    }

    public static void main(String[] args) {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}
