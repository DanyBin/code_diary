package forkJoin;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @ClassName WordCount
 * @Author bin
 * @Date 2020/1/13 上午11:44
 * @Decr TODO
 * @Link TODO
 **/
public class WordCount {
    static class MR extends RecursiveTask<Map<String,Integer>>{

        private String[] fc;
        private int start,end;

        MR(String[] fc,int fr,int to){
            this.fc =fc;
            this.start=fr;
            this.end = to;
        }

        @Override
        protected Map<String, Integer> compute() {
            if(end - start == 1){
                return calc(fc[start]);
            }else {
                int mid = (start+ end) /2;
                MR mr = new MR(fc, start, mid);
                mr.fork();
                MR mr1 = new MR(fc, mid, end);

                return merge(mr1.compute(),mr.join());
            }
        }


        private  Map<String,Integer> calc(String line){
            HashMap<String, Integer> map = new HashMap<>();
            String[] words = line.split("\\s+");
            for(String w:words){
                Integer integer = map.get(w);
                if(integer == null){
                    map.put(w,1);
                }else {
                    map.put(w,integer+1);
                }
            }
            return map;
        }

        private Map<String,Integer> merge(Map<String,Integer> r1,Map<String,Integer> r2){
            HashMap<String, Integer> result = new HashMap<>();
            result.putAll(r1);
            r2.forEach((k,v)-> {
                Integer integer = result.get(k);
                if(integer == null){
                    result.put(k,v);
                }else {
                    Integer c = integer+v;
                    result.put(k,c);
                }
            });
            return result;
        }
    }

    public static void main(String[] args) {
        String[] fc = {"hello word","hello tt","hello mm","hello ss","hello ww"};
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        MR mr = new MR(fc, 0, fc.length);

        Map<String, Integer> result = forkJoinPool.invoke(mr);

        result.forEach((k,v)->{
            System.out.println(k +":"+v);
        });
    }
}
