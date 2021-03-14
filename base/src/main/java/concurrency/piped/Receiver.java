package concurrency.piped;

import java.io.IOException;
import java.io.PipedReader;

/**
 * @ClassName Receiver
 * @Author bin
 * @Date 2020/8/19 下午4:04
 * @Decr TODO
 * @Link TODO
 **/
public class Receiver implements Runnable {
    private PipedReader in;

    public Receiver(Sender sender) throws IOException {
        in = new PipedReader(sender.getPipedWriter());
    }
    public void run() {
        try {
            while (true) {
                System.out.println("Read: " + (char)in.read() + ",");
            }
        } catch (IOException e) {
            System.out.println(e + " Receiver read execption");
        }
    }
}
