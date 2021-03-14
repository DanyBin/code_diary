package concurrentTest;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName TestDelay
 * @Author bin
 * @Date 2020/9/18 下午12:09
 * @Decr TODO
 * @Link TODO
 **/
public class TestDelay {

    static class DelayEle implements Delayed {

        private final long delayTime; //延迟时间
        private final long expire; // 到期时间
        private String taskName; //任务名称
        public DelayEle(long delay,String taskName){
            delayTime = delay;
            this.taskName = taskName;
            expire = System.currentTimeMillis() + delay;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.expire-System.currentTimeMillis(),TimeUnit.SECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return (int)(this
                    .getDelay(TimeUnit.SECONDS)- o.getDelay(TimeUnit.SECONDS));
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("DelayedEle{");
            sb.append("delay=").append(delayTime);
            sb.append(", expire=").append(expire);
            sb.append(",taskName=").append(taskName).append("\'");
            sb.append("}");
            return sb.toString();

        }
    }

    public static void main(String[] args) {
        DelayQueue<DelayEle> delayEles = new DelayQueue<>();

        Random random = new Random();
        for(int i=0 ;i < 10 ; i ++) {
            DelayEle ele = new DelayEle(random.nextInt(500),"task:" + i);
            delayEles.offer(ele);
        }

        DelayEle ele = null;

        try {
            for (;;) {
                while ((ele = delayEles.take()) != null){
                    System.out.println(ele.toString());
                }
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
