package concurrency.test;

import concurrency.test.BaseLine;

/**
 * @ClassName SynchronizationComparisons
 * @Author bin
 * @Date 2020/8/25 下午5:08
 * @Decr TODO
 * @Link TODO
 **/
public class SynchronizationComparisons {
    static BaseLine baseLine = new BaseLine();
    static SynchronizedTest sych = new SynchronizedTest();
    static LockTest lock = new LockTest();
    static AtomicTest atomic = new AtomicTest();

    static void test(){
        System.out.print("===================");
        System.out.println(String.format("%-12s : %13d\n","Cycles",Accumulator.cycles));
        baseLine.timedTest();
        sych.timedTest();
        lock.timedTest();
        atomic.timedTest();

        Accumulator.report(sych,baseLine);
        Accumulator.report(lock,baseLine);
        Accumulator.report(atomic,baseLine);

        Accumulator.report(sych,lock);
        Accumulator.report(sych,atomic);
        Accumulator.report(lock,atomic);
    }

    public static void main(String[] args) {
        baseLine.timedTest();

        for(int i= 0;i < 5;i++){
            test();
            Accumulator.cycles *= 2;
        }
    }
}
