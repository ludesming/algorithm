package com.maxming.sort;

/**
 * 希尔排序
 * 希尔排序是插入排序的变种，区别是希尔排序为不稳定算法
 * 针对插入排序2点优化：
 *  1、插入排序对基本已经排好序的数据，效率高，可以达到线性排序的效率
 *  2、一次遍历排序仅移动一个数据，效率太低
 * 思想：将整个数组分成多个序列，单独进行排序，使其一步步达到基本排序，最后完成全部排序
 * 实现步骤：
 * 1、先取一个增量d1(d1<n)作为第一个增量，目前增量没有确切的数值，把所有相隔d1的记录放到一组中，
 * 然后对各个组内数据进行直接插入排序，这样一次分组和插入排序的过程为希尔排序
 * 2、新的增量d2<d1,重复1
 * 3、重复2，直到所取的增量为1为止
 * 时间复杂度：O(n^1.3),最坏O(n^2),最好O(n)
 * 空间复杂度：O(1)
 * 算法稳定性：不稳定
 */
public class ShellSort {

    public static int[] sort(int[] unsortInt) {
        //本文用/2的方式作为增量因子
        int len = unsortInt.length;
        int num = 0;
        for (int k = len/3 + 1; k > 0; k=(k/3 + 1)) {
            //增量值循环
            for (int i = k; i < len; i++,num++) {
                for (int j = i-k; j >= 0; j-=k) {
                    if (unsortInt[j] > unsortInt[j+k]) {
                        unsortInt[j] ^= unsortInt[j+k];
                        unsortInt[j+k] ^= unsortInt[j];
                        unsortInt[j] ^= unsortInt[j+k];
                    }
                }

            }
            if (k == 1) {
                break;
            }
        }
        System.out.println("num=" + num);
        return unsortInt;
    }
}
