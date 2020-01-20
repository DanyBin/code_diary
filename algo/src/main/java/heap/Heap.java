package heap;

/**
 * @ClassName Heap
 * @Author bin
 * @Date 2019/11/21 下午7:42
 * @Decr TODO
 *      堆是一种完全二叉树。
 *          特点: 每个节点的值都大于等于（或小于等于）其子树节点的值。
 *               因此被分为「大顶堆」与「小顶堆」
 *
 *         通过数组来存储堆，通过下标，来找节点的左右子节点和父节点
           如下标为 i 的节点。左节点= i*2  右节点=i*2 +1  父节点 = i/2
 *
 *          操作
 *              插入: 堆与新插入的数据放到数组的最后，然后从下往上堆化。
 *              删除: 删除的是堆顶数据。将数组的最后一个元素放在堆顶，然后从上往下堆化。
 *
 *           堆排序,分为两部。建堆与排序
 *              1.建堆-将下标 2/n 到 1 的节点，从上往下堆化，形成堆数据结果
 *              2.排序-迭代地将堆顶数据放在堆的尾部，并将堆减少，然后堆化，重复这个过程。
 *
 *       注意：这种堆化方式，会浪费一个存储空间
 *
 * @Link TODO
 **/
public class Heap {
    private int[] arr;//用于存储堆元素
    private int n; //元素个数最大值
    private int count; //堆中已经存储的元素

    public Heap(int length){
        arr = new int[length + 1];
        n = length;
        count = 0;
    }


    /**
     * 插入元素
     * 1. 元素与父节点进行对比
     * 2. 如果满足条件，则交换数据
     * 3. 如果不满足条件，则停止
     */
    public void insert(int data){
        if(n == count){
            System.out.println("堆中已满");
            return;
        }
        ++count;

        //新元素，放在数组尾
        arr[count] = data;

        //获取下标
        int i = count;

        //必须有父节点 && 该元素必须大于 该元素父节点
        while (i/2 > 0 && arr[i] > arr[i/2]) {

            //交换元素
            int tmp = arr[i / 2];
            arr[i / 2] = arr[i];
            arr[i] = tmp;

            //获取父节点 下标
            i=i/2;
        }
    }


    /**
     * 大顶堆- 删除最大值
     * 1.把最后一个节点放在 堆顶
     * 2.在进行比较与交换操作，满足堆
     */
    public void removeMax(){
        if(count ==0){
            System.out.println("堆中数据为空");
            return;
        }
        //将最后一个节点，放在堆顶
        arr[1] = arr[count];
        --count;
        heapify(arr,n,1);
    }

    /**
     * 采用从上向下进行比较。 也就是节点 与 左右子节点进行比较与交换操作
     * @param arr
     * @param n
     * @param i
     */
    public static void heapify(int arr[],int n,int i){
        while (true){
            //节点-索引
            int maxPos = i;

            //节点 与 该节点 左节点进行对比。
            if(i*2 <= n && arr[i] < arr[i*2]) maxPos = i*2;

            // 左节点 与 右节点进行对比。如果小于则，替换成右节点
            if(i*2+1 <=n && arr[maxPos] < arr[i*2+1]) maxPos =i*2+1;

            //无法条件满足，跳出
            if(maxPos == i)break;

            //满足条件，则交换元素
            int tmp = arr[i];
            arr[i] = arr[maxPos];
            arr[maxPos] = tmp;

            //更新下标
            i = maxPos;
        }
    }


    /**
     * 建堆-  对一个数组，创建成堆结构
     * 1. 从非叶子节点开始  n/2
     * @param array
     * @param n
     */
    public static void buildHeap(int[] array,int n){
        for(int i = n/2; i >=1 ; --i){
            heapify(array,n,i);
        }
    }


    /**
     * 实现堆排序
     * @param array 无序的数组
     * @param n  数组中的个数
     */
    public static void sort(int[] array , int n){

        //将数组——> 大顶堆
        buildHeap(array,n);
        int k = n;

        //遍历
        while (k > 1){
            //将最后一个节点，放在堆顶
           int tmp = array[k];
           array[k] = array[1];
           array[1] =tmp;
           -- k;
           //重新构建堆
           heapify(array,k,1);
        }

        for(int a:array){
            System.out.println(a);
        }
    }

    public static void main(String[] args) {
        int[] data = {0,2,11,5,7,8,1,224,9};
        sort(data,data.length-1);
    }
}
