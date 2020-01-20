package forkJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @ClassName ForkJoinDemo
 * @Author bin
 * @Date 2020/1/13 上午11:33
 * @Decr TODO
 *      使用fork/join(分治任务) 实现 斐波那契
 * @Link TODO
 **/
public class ForkJoinDemo {
    static class Fibonacci extends RecursiveTask<Integer>{
        final int n;
        Fibonacci(int n){
            this.n = n;
        }

        @Override
        protected Integer compute() {
            if(n <= 1){
                return n;
            }

            Fibonacci fibonacci = new Fibonacci(n - 1);

            //创建子任务
            fibonacci.fork();

            Fibonacci fibonacci1 = new Fibonacci(n - 2);

            return fibonacci1.compute() + fibonacci.join();
        }
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(3);

        //创建分治任务
        Fibonacci fibonacci = new Fibonacci(30);

        //启动分治任务
        Integer result = forkJoinPool.invoke(fibonacci);
        System.out.println(result);
    }
}
