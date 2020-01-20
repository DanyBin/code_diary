package heap;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName FileMerger
 * @Author bin
 * @Date 2019/11/22 下午12:01
 * @Decr TODO
 *      使用堆实现 文件合并
 * @Link TODO
 **/
public class FileMerger {


    String path = "";
    int count;
    String outpath = "";
    Map<String,File> map =  new HashMap(); //存储文件名与数据的对应关系

    public static void main(String[] args) {
        int[] array = new int[12];


        //读取数据，并存入数组
        FileMerger fileMerger = new FileMerger();
        fileMerger.file(fileMerger.path,array,fileMerger.count);

        //进行堆排序
        sort(array,fileMerger.count);

        //获取数组中最大值
        int data = fileMerger.removeMax(array, fileMerger.count);

        //输出数据
        fileMerger.writerFile(fileMerger.outpath,String.valueOf(data));

    }

    /**
     * 读取当前路径下的所有文件
     * @param path
     */
    public void file(String path,int[] arr,int conut){
        File file = new File(path);
        File[] files = file.listFiles();

        for(File f : files){
            if(f.isFile()){
                String content = readFile(f);

                if(content == null){
                    continue;
                }

                this.map.put(content,f);

                if(arr.length == conut){
                    System.out.println("数据已满");
                    return;
                }
                ++count;
                arr[conut] = Integer.valueOf(content);
            }
        }
    }

    /**
     * 读取字符串
     * @param file
     */
    public String readFile(File file){

        //读取文件，并将第一个字符放入数组中
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            String line;

            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
            line =  reader.readLine(); //读取一行记录

            if(line == null){
                System.out.println("无文件读取");
                return null;
            }
            reader.close();
            fileInputStream.close();
            return line;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){

        }
        return null;
    }

    /**
     * 写出文件
     * @param file
     * @param content
     */
    public void writerFile(String file,String content){
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file,true));
            bufferedWriter.write(content);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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


    /**
     * 删除堆中最大值
     */
    public int removeMax(int[] array,int count){
        if(count == 0){
            System.out.println("数组为空");
            return -1;
        }

        int max = array[1];

        //重新进行堆排序
        array[1] = array[count];
        --count;
        heapify(array,count,1);
        return max;
    }

}
