package com.maxming.sort;

/**
 * 双轴快排尝试实现
 * 这是对快速排序的优化算法，最终可参考Arrays.sort()方法
 * 下面是我个人理解的双轴快排实现法，确认结果
 */
public class DoubleQuickSort {
    public static int[] sort(int[] unsortInts) {
        int len = unsortInts.length;
        doubleQuickSort(unsortInts, 0, 0,len - 1);
        return unsortInts;
    }

    private static int[] doubleQuickSort(int[] unsortInts, int low, int mid, int high) {
        if (low >= high) {
            return unsortInts;
        }
        if (low < mid){
           // int baseFirstIndex = getBaseIndex(unsortInts,low, mid, high);
        }
        if (mid < high){
           // int baseSecodIndex = getBaseIndex();
        }
        return unsortInts;
    }
}
