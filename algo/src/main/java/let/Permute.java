package let;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Permute
 * @Author bin
 * @Date 2021/4/4 下午1:03
 * @Decr TODO
 *  给定一个 没有重复 数字的序列，返回其所有可能的全排列。
     示例:
        输入: [1,2,3]
        输出:
        [
            [1,2,3],
            [1,3,2],
            [2,1,3],
            [2,3,1],
            [3,1,2],
            [3,2,1]
        ]
    思路  1 与 [2,3] 做全排序
         2 与 [1,3]
         3 与 [1,2]

    递归的方式，进行组合
        1
|--------|---------|
|  1，2  |  1，3   |
|--------|---------|
|1，2，3  | 1，3，2 |
 * @Link TODO
 **/
public class Permute {
    public static List<List<Integer>> permute(int[] nums) {
        boolean[] use = new boolean[nums.length];
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums.length,nums,use,0,path,res);
        return res;
    }
    //递归遍历 - 搜索优先
    public static void dfs(int len,int[] nums,
                           boolean[] use,int depth,
                           List<Integer> path,List<List<Integer>> res) {
        //终止条件 - path == len. 递归到头了.也就是一次for 循环数组
        if (depth == len) {
            res.add(new ArrayList(path));
            return;
        }

        //注意- 每次都重新一遍历数组
        for(int i=0; i < len; i++) {
            //过滤已经使用的元素
            if (!use[i]) {
                //添加元素
                path.add(nums[i]);
                //更新元素-已经被使用
                use[i] = true;

                dfs(len,nums,use,depth+1,path,res);

                //递归迭代之后，要记得还原
                use[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        System.out.println(permute(nums));
    }
}
