import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @ClassName LogWriter
 * @Author bin
 * @Date 2020/5/29 下午12:00
 * @Decr TODO
 *      实现日志功能
 * @Link TODO
 **/
public class LogWriter {
    private final BlockingQueue<String> queue;
    private final LoggerThread logger;

    private boolean isShutdown;
    private int res;

    public LogWriter(PrintWriter writer){
        this.queue = new LinkedBlockingQueue<>(5);
        this.logger = new LoggerThread(writer);
    }

    public void start(){
     Runtime.getRuntime().addShutdownHook(new Thread(){
        @Override
        public void run(){
            LogWriter.this.stop();
        }
     });
     logger.start();;
    }

    public void stop(){
        synchronized (this){isShutdown = true;}
        logger.interrupt();
    }

    public void log(String msg) throws InterruptedException{
        synchronized (this){
            if(isShutdown){
                throw new IllegalStateException("");
            }else {
                ++ res;
            }
        }
        queue.put(msg);
    }



    private class LoggerThread extends Thread{
        private final PrintWriter writer;

        public LoggerThread(PrintWriter writer){
            this.writer = writer;
        }

        @Override
        public void  run(){
            try {
                while (true){
                    synchronized (LogWriter.this){
                        if(isShutdown && res == 0 ){
                            break;
                        }
                    }
                    String msg = queue.take();
                    synchronized (LogWriter.this) {--res;}
                    writer.println(msg);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                writer.close();
            }
        }
    }
}
