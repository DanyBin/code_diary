package concurrency.cyclicBarrier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName HorseRace
 * @Author bin
 * @Date 2020/8/19 下午7:54
 * @Decr TODO
 * @Link TODO
 **/
public class HorseRace {
    static final int FINISH_LINE = 75;

    private List<Horse>  horses = new ArrayList<Horse>();

    private ExecutorService exec = Executors.newCachedThreadPool();

    private CyclicBarrier barrier;

    public HorseRace(int nHorses,final int pause) {
        //创建一个栅栏，等待所有的赛马生成
        barrier = new CyclicBarrier(nHorses, new Runnable() {
            public void run() {
                StringBuilder s = new StringBuilder();

                //赛道的长度
                for(int i=0;i< FINISH_LINE;i ++) {
                    s.append("=");
                }
                System.out.println(s);


                //遍历赛马的步数
                for(Horse horse : horses) {
                    System.out.println(horse.tracks());
                }

                //遍历赛马
                for(Horse horse : horses) {
                    //赛马的步数 》 75 时，获胜
                    if(horse.getStrides() >= FINISH_LINE) {
                        System.out.println(horse + "won!");
                        exec.shutdownNow();
                        return;
                    }
                }

                try {
                    TimeUnit.MILLISECONDS.sleep(pause);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //生成赛马
        for(int i=0; i <nHorses ;i ++) {
            Horse horse = new Horse(barrier);
            horses.add(horse);
            exec.execute(horse);
        }
    }

    public static void main(String[] args) {
        int nHorses = 7;
        int pause = 200;

        new HorseRace(nHorses,pause);
    }

}
