package reference;

import java.lang.ref.*;
import java.util.LinkedList;

/**
 * @ClassName References
 * @Author bin
 * @Date 2020/7/23 下午5:35
 * @Decr TODO
 * @Link TODO
 **/
public class References {
    private static ReferenceQueue<VeryBig> rq = new ReferenceQueue<VeryBig>();
    public static void checkQueue(){
        Reference<? extends VeryBig>  inq = rq.poll();
        if(inq != null){
            System.out.println("In queue: "+ inq.get());
        }
    }

    public static void main(String[] args) {
        int size = 10;

        LinkedList<SoftReference<VeryBig>> sa = new LinkedList<SoftReference<VeryBig>>();
        for(int i=0;i < size;i ++){
            sa.add(new SoftReference<VeryBig>(new VeryBig("soft"+i),rq));
            System.out.println("just created: "+ sa.getLast());
            checkQueue();
        }

        LinkedList<WeakReference<VeryBig>> wa = new LinkedList<WeakReference<VeryBig>>();
        for(int i=0;i < size;i ++){
            wa.add(new WeakReference<VeryBig>(new VeryBig("weak"+i),rq));
            System.out.println("just created: "+ wa.getLast());
            checkQueue();
        }

        SoftReference<VeryBig> s = new SoftReference<VeryBig>(new VeryBig("solt"));
        WeakReference<VeryBig> w = new WeakReference<VeryBig>(new VeryBig("weak"));
        System.gc();

        LinkedList<PhantomReference<VeryBig>> pa = new LinkedList<PhantomReference<VeryBig>>();
        for(int i=0;i < size;i ++){
            pa.add(new PhantomReference<VeryBig>(new VeryBig("ph"+i),rq));
            System.out.println("just created: "+ pa.getLast());
            checkQueue();
        }
    }
}
