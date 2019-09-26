package sorted;

import org.junit.Test;

import java.util.Arrays;

/**
 * @ClassName AnalysisSouceCode
 * @Author bin
 * @Date 2019/9/26 下午5:05
 * @Decr TODO
 * @Link TODO
 **/
public class AnalysisSouceCode {


    /**
     * 待合并的序列的最大数量
     */
    private static final int MAX_RUN_COUNT = 67;

    /**
     * 待合并的序列的最大长度
     */
    private static final int MAX_RUN_LENGTH = 33;

    /*
     * 如果参与排序的数组长度小于这个值，优先使用快速排序而不是归并排序
     */
    private static final int QUICKSORT_THRESHOLD = 286;

    /**
     * 如果参与排序的数组长度小于这个值，有限考虑插入排序，而不是快速排序
     */
    private static final int INSERTION_SORT_THRESHOLD = 47;

    /**
     * 对于int[] 的Array.sort 的源码分析
     */
    @Test
    public void  intFun(){
            //长度小于 < 47 时
        int[] array = {7,6,5,4,3,1,2};
        sort(array,0,array.length-1,null,0,0);
    }


    /**
     * 指定数据排序方式
     * @param a     数组
     * @param left  第一个元素下标
     * @param right 最后一个元素下标
     * @param work
     * @param workBase
     * @param workLen
     */
    static void sort(int[] a, int left, int right, int[] work, int workBase, int workLen) {
        // Use Quicksort on small arrays
        //数据量小于286 时，使用快速排序
        if (right - left < QUICKSORT_THRESHOLD) {
            sort(a, left, right, true);
            return;
        }


        /*
         * Index run[i] is the start of i-th run
         * (ascending or descending sequence).
         */
        /**
         * 采用归并排序。第run[i] == 第i个有序数列开始的位置
         */
        int[] run = new int[MAX_RUN_COUNT + 1];
        int count = 0; run[0] = left; //初始化-从数组第一位开始。 存储是数组a的索引

        // Check if the array is nearly sorted
        //检查数组是否接近有序状态
        for (int k = left; k < right; run[count] = k) {
            if (a[k] < a[k + 1]) { // ascending 升序
                while (++k <= right && a[k - 1] <= a[k]);
            } else if (a[k] > a[k + 1]) { // descending - 降序
                while (++k <= right && a[k - 1] >= a[k]);

                //如果是降序的，找出K位置。即相邻两个数据，不是降序时。而是升序。 将数据进行升序排序
                //run(count) = k 。 存储是是数组a 的索引。即就是这个索引的数据，导致无法排序
                for (int lo = run[count] - 1, hi = k; ++lo < --hi; ) {
                    int t = a[lo]; a[lo] = a[hi]; a[hi] = t;
                }
            } else { // equal - 相等
                for (int m = MAX_RUN_LENGTH; ++k <= right && a[k - 1] == a[k]; ) {

                    if (--m == 0) {
                        sort(a, left, right, true);
                        return;
                    }
                }
            }

            /*
             * The array is not highly structured,
             * use Quicksort instead of merge sort.
             */
            if (++count == MAX_RUN_COUNT) {
                sort(a, left, right, true);
                return;
            }
        }

        // Check special cases
        // Implementation note: variable "right" is increased by 1.
        //检查特殊情况
        if (run[count] == right++) { // The last run contains one element - 最后一个有序数列只有最后一个元素
            run[++count] = right; // 那给最后一个元素的后面加一个哨兵。存储 前一位索引 。
        } else if (count == 1) { // The array is already sorted - 整个数组中只有一个有序数列，说明数组已经有序啦，不需要排序了
            return;
        }

        // Determine alternation base for merge
        /**
         * z这块操作没懂
         */
        byte odd = 0;
        for (int n = 1; (n <<= 1) < count; odd ^= 1);

        // Use or create temporary array b for merging
        int[] b;                 // temp array; alternates with a
        int ao, bo;              // array offsets from 'left'
        int blen = right - left; // space needed for b
        if (work == null || workLen < blen || workBase + blen > work.length) {
            work = new int[blen];
            workBase = 0;
        }
        if (odd == 0) {
            System.arraycopy(a, left, work, workBase, blen);
            b = a;
            bo = 0;
            a = work;
            ao = workBase - left;
        } else {
            b = work;
            ao = 0;
            bo = workBase - left;
        }

        // Merging
        /**
         * 合并
         * 最外层循环，直到count为1，也就是栈中待合并的序列只有一个的时候，标志合并成功
         * a 做原始数组，b 做目标数组
         */
        for (int last; count > 1; count = last) {

            // 遍历数组，合并相邻的两个升序序列
            for (int k = (last = 0) + 2; k <= count; k += 2) {

                //合并run[k-2] 与 run[k-1] .也就是两个索引需要排序。
                int hi = run[k], mi = run[k - 1];

                for (int i = run[k - 2], p = i, q = mi; i < hi; ++i) {

                    if (q >= hi || p < mi && a[p + ao] <= a[q + ao]) {
                        b[i + bo] = a[p++ + ao];
                    } else {
                        b[i + bo] = a[q++ + ao];
                    }
                }
                // 这里把合并之后的数列往前移动
                run[++last] = hi;
            }
            // 如果栈的长度为奇数，那么把最后落单的有序数列copy过对面
            if ((count & 1) != 0) {

                for (int i = right, lo = run[count - 1]; --i >= lo;
                     b[i + bo] = a[i + ao]
                        );
                run[++last] = right;
            }

            //临时数组，与原始数组对调，保持a做原始数组，b 做目标数组
            int[] t = a; a = b; b = t;
            int o = ao; ao = bo; bo = o;
        }
    }


    /**
     * 使用双轴快速排序给指定的数组，进行排序
     * @param a     数组
     * @param left  最小索引
     * @param right 最大索引
     * @param leftmost 指定范围的是否在数组的最左边
     */
    private static void sort(int[] a, int left, int right, boolean leftmost) {
        int length = right - left + 1;

        // Use insertion sort on tiny arrays
        //小数组使用插入排序
        if (length < INSERTION_SORT_THRESHOLD) {
            if (leftmost) {
                /*
                 * Traditional (without sentinel) insertion sort,
                 * optimized for server VM, is used in case of
                 * the leftmost part.
                 */
                /**
                 * 经典的插入排序算法，不带哨兵。做了优化，在leftmost情况下使用
                 */
                for (int i = left, j = i; i < right; j = ++i) {
                    int ai = a[i + 1];
                    while (ai < a[j]) {
                        a[j + 1] = a[j];
                        if (j-- == left) {
                            break;
                        }
                    }
                    a[j + 1] = ai;
                }
            } else {
                /*
                 * Skip the longest ascending sequence.
                 */
                /**
                 * 首先跨过开头的升序的部分
                 */
                do {
                    if (left >= right) {
                        return;
                    }
                } while (a[++left] >= a[left - 1]);

                /*
                 * Every element from adjoining part plays the role
                 * of sentinel, therefore this allows us to avoid the
                 * left range check on each iteration. Moreover, we use
                 * the more optimized algorithm, so called pair insertion
                 * sort, which is faster (in the context of Quicksort)
                 * than traditional implementation of insertion sort.
                 */
                /**
                 * 这里用到了成对插入排序方法，它比简单的插入排序算法效率要高一些
                 * 因为这个分支执行的条件是左边是有元素的
                 * 所以可以直接从left开始往前查找。
                 */
                for (int k = left; ++left <= right; k = ++left) {
                    int a1 = a[k], a2 = a[left];

                    //保证a1>=a2
                    if (a1 < a2) {
                        a2 = a1; a1 = a[left];
                    }

                    //先把两个数字中较大的那个移动到合适的位置
                    while (a1 < a[--k]) {
                        a[k + 2] = a[k]; //这里每次需要向左移动两个元素
                    }
                    a[++k + 1] = a1;

                    //再把两个数字中较小的那个移动到合适的位置
                    while (a2 < a[--k]) {
                        a[k + 1] = a[k]; //这里每次需要向左移动一个元素
                    }
                    a[k + 1] = a2;
                }
                int last = a[right];

                while (last < a[--right]) {
                    a[right + 1] = a[right];
                }
                a[right + 1] = last;
            }
            return;
        }

        // Inexpensive approximation of length / 7 - length / 7 的一种低复杂度的实现, 近似值(length * 9 / 64 + 1)
        int seventh = (length >> 3) + (length >> 6) + 1;

        /*
         * Sort five evenly spaced elements around (and including) the
         * center element in the range. These elements will be used for
         * pivot selection as described below. The choice for spacing
         * these elements was empirically determined to work well on
         * a wide variety of inputs.
         */
        // 对5段靠近中间位置的数列排序，这些元素最终会被用来做轴(下面会讲)
        // 他们的选定是根据大量数据积累经验确定的
        int e3 = (left + right) >>> 1; // The midpoint - 中间值
        int e2 = e3 - seventh;
        int e1 = e2 - seventh;
        int e4 = e3 + seventh;
        int e5 = e4 + seventh;

        // Sort these elements using insertion sort - 这里是手写的冒泡排序，没有for循环
        if (a[e2] < a[e1]) { int t = a[e2]; a[e2] = a[e1]; a[e1] = t; }

        if (a[e3] < a[e2]) { int t = a[e3]; a[e3] = a[e2]; a[e2] = t;
            if (t < a[e1]) { a[e2] = a[e1]; a[e1] = t; }
        }
        if (a[e4] < a[e3]) { int t = a[e4]; a[e4] = a[e3]; a[e3] = t;
            if (t < a[e2]) { a[e3] = a[e2]; a[e2] = t;
                if (t < a[e1]) { a[e2] = a[e1]; a[e1] = t; }
            }
        }
        if (a[e5] < a[e4]) { int t = a[e5]; a[e5] = a[e4]; a[e4] = t;
            if (t < a[e3]) { a[e4] = a[e3]; a[e3] = t;
                if (t < a[e2]) { a[e3] = a[e2]; a[e2] = t;
                    if (t < a[e1]) { a[e2] = a[e1]; a[e1] = t; }
                }
            }
        }

        // Pointers
        int less  = left;  // The index of the first element of center part - 中间区域的首个元素的位置
        int great = right; // The index before the first element of right part  - 右边区域的首个元素的位置

        if (a[e1] != a[e2] && a[e2] != a[e3] && a[e3] != a[e4] && a[e4] != a[e5]) {
            /*
             * Use the second and fourth of the five sorted elements as pivots.
             * These values are inexpensive approximations of the first and
             * second terciles of the array. Note that pivot1 <= pivot2.
             *
             * 使用5个元素中的2，4两个位置，他们两个大致处在四分位的位置上。
             * 需要注意的是pivot1 <= pivot2
             */
            int pivot1 = a[e2];
            int pivot2 = a[e4];

            /*
             * The first and the last elements to be sorted are moved to the
             * locations formerly occupied by the pivots. When partitioning
             * is complete, the pivots are swapped back into their final
             * positions, and excluded from subsequent sorting.
             *
             * 第一个和最后一个元素被放到两个轴所在的位置。当阶段性的分段结束后
             * 他们会被分配到最终的位置并从子排序阶段排除
             */
            a[e2] = a[left];
            a[e4] = a[right];

            /*
             * Skip elements, which are less or greater than pivot values.
             * 跳过一些队首的小于pivot1的值，跳过队尾的大于pivot2的值
             */
            while (a[++less] < pivot1);
            while (a[--great] > pivot2);

            /*
             * Partitioning:
             *
             *   left part           center part                   right part
             * +--------------------------------------------------------------+
             * |  < pivot1  |  pivot1 <= && <= pivot2  |    ?    |  > pivot2  |
             * +--------------------------------------------------------------+
             *               ^                          ^       ^
             *               |                          |       |
             *              less                        k     great
             *
             * Invariants:
             *
             *              all in (left, less)   < pivot1
             *    pivot1 <= all in [less, k)     <= pivot2
             *              all in (great, right) > pivot2
             *
             * Pointer k is the first index of ?-part.
             */
            outer:
            for (int k = less - 1; ++k <= great; ) {
                int ak = a[k];
                if (ak < pivot1) { // Move a[k] to left part
                    a[k] = a[less];
                    /*
                     * Here and below we use "a[i] = b; i++;" instead
                     * of "a[i++] = b;" due to performance issue.
                     */
                    a[less] = ak;
                    ++less;
                } else if (ak > pivot2) { // Move a[k] to right part
                    while (a[great] > pivot2) {
                        if (great-- == k) { //right part - 当great的最小索引的数据都大于 pivot2时，则跳出循环
                            break outer;
                        }
                    }
                    if (a[great] < pivot1) { // a[great] <= pivot2
                        a[k] = a[less];
                        a[less] = a[great];
                        ++less;
                    } else { // pivot1 <= a[great] <= pivot2
                        a[k] = a[great];
                    }
                    /*
                     * Here and below we use "a[i] = b; i--;" instead
                     * of "a[i--] = b;" due to performance issue.
                     */
                    a[great] = ak;
                    --great;
                }
            } // 分割阶段结束出来的位置,上一个outer结束的位置

            // Swap pivots into their final positions
            // 把两个放在外面的轴放回他们应该在的位置上
            a[left]  = a[less  - 1]; a[less  - 1] = pivot1;
            a[right] = a[great + 1]; a[great + 1] = pivot2;

            // Sort left and right parts recursively, excluding known pivots
            // 把左边和右边递归排序，跟普通的快速排序差不多
            sort(a, left, less - 2, leftmost);
            sort(a, great + 2, right, false);

            /*
             * If center part is too large (comprises > 4/7 of the array),
             * swap internal pivot values to ends.
             * 如果中心区域太大，超过数组长度的 4/7。就先进行预处理，再参与递归排序。
             * 预处理的方法是把等于pivot1的元素统一放到左边，等于pivot2的元素统一
             * 放到右边,最终产生一个不包含pivot1和pivot2的数列，再拿去参与快排中的递归。
             */
            if (less < e1 && e5 < great) {
                /*
                 * Skip elements, which are equal to pivot values.
                 */
                while (a[less] == pivot1) {
                    ++less;
                }

                while (a[great] == pivot2) {
                    --great;
                }

                /*
                 * Partitioning:
                 *
                 *   left part         center part                  right part
                 * +----------------------------------------------------------+
                 * | == pivot1 |  pivot1 < && < pivot2  |    ?    | == pivot2 |
                 * +----------------------------------------------------------+
                 *              ^                        ^       ^
                 *              |                        |       |
                 *             less                      k     great
                 *
                 * Invariants:
                 *
                 *              all in (*,  less) == pivot1
                 *     pivot1 < all in [less,  k)  < pivot2
                 *              all in (great, *) == pivot2
                 *
                 * Pointer k is the first index of ?-part.
                 */
                outer:
                for (int k = less - 1; ++k <= great; ) {
                    int ak = a[k];
                    if (ak == pivot1) { // Move a[k] to left part
                        a[k] = a[less];
                        a[less] = ak;
                        ++less;
                    } else if (ak == pivot2) { // Move a[k] to right part
                        while (a[great] == pivot2) {
                            if (great-- == k) {
                                break outer;
                            }
                        }
                        if (a[great] == pivot1) { // a[great] < pivot2
                            a[k] = a[less];
                            /*
                             * Even though a[great] equals to pivot1, the
                             * assignment a[less] = pivot1 may be incorrect,
                             * if a[great] and pivot1 are floating-point zeros
                             * of different signs. Therefore in float and
                             * double sorting methods we have to use more
                             * accurate assignment a[less] = a[great].
                             */
                            a[less] = pivot1;
                            ++less;
                        } else { // pivot1 < a[great] < pivot2
                            a[k] = a[great];
                        }
                        a[great] = ak;
                        --great;
                    }
                }
            }

            // Sort center part recursively - 参加排序
            sort(a, less, great, false);

        } else { // Partitioning with one pivot // 这里选取的5个元素刚好相等，使用传统的3-way快排
            /*
             * Use the third of the five sorted elements as pivot.
             * This value is inexpensive approximation of the median.
             */
            int pivot = a[e3];

            /*
             * Partitioning degenerates to the traditional 3-way
             * (or "Dutch National Flag") schema:
             *
             *   left part    center part              right part
             * +-------------------------------------------------+
             * |  < pivot  |   == pivot   |     ?    |  > pivot  |
             * +-------------------------------------------------+
             *              ^              ^        ^
             *              |              |        |
             *             less            k      great
             *
             * Invariants:
             *
             *   all in (left, less)   < pivot
             *   all in [less, k)     == pivot
             *   all in (great, right) > pivot
             *
             * Pointer k is the first index of ?-part.
             */
            for (int k = less; k <= great; ++k) {
                if (a[k] == pivot) {
                    continue;
                }
                int ak = a[k];
                if (ak < pivot) { // Move a[k] to left part
                    a[k] = a[less];
                    a[less] = ak;
                    ++less;
                } else { // a[k] > pivot - Move a[k] to right part
                    while (a[great] > pivot) {
                        --great;
                    }
                    if (a[great] < pivot) { // a[great] <= pivot
                        a[k] = a[less];
                        a[less] = a[great];
                        ++less;
                    } else { // a[great] == pivot
                        /*
                         * Even though a[great] equals to pivot, the
                         * assignment a[k] = pivot may be incorrect,
                         * if a[great] and pivot are floating-point
                         * zeros of different signs. Therefore in float
                         * and double sorting methods we have to use
                         * more accurate assignment a[k] = a[great].
                         */
                        a[k] = pivot;
                    }
                    a[great] = ak;
                    --great;
                }
            }

            /*
             * Sort left and right parts recursively.
             * All elements from center part are equal
             * and, therefore, already sorted.
             */
            sort(a, left, less - 1, leftmost);
            sort(a, great + 1, right, false);
        }
    }
}
