package com.maxming.sort;

/**
 * 选择排序
 * 思想：从无序的数组中选出一个最小（大）值，放到起始位，然后再找到第二小（大），放到有序列最末尾
 * 时间复杂度：O(n^2)
 * 最优：O(n)
 * 空间复杂度：O(1)
 * 算法稳定性：不稳定
 */
public class SelectionSort {

    public static int[] sort(int[] unsortSort){
        int len = unsortSort.length;
        for (int i = 0; i < len-1; i++) {
            int minValueIndex = 0;
            int temp = unsortSort[i];
            for (int j = i+1; j < len; j++) {
                if (temp > unsortSort[j]) {
                    temp = unsortSort[j];
                    minValueIndex = j;
                }
            }
            if (temp != unsortSort[i]) {
                unsortSort[i] ^= unsortSort[minValueIndex];
                unsortSort[minValueIndex] ^= unsortSort[i];
                unsortSort[i] ^= unsortSort[minValueIndex];
            }
        }
        return unsortSort;
    }
}
