package com.maxming.sort;

/**
 * 插入排序
 * 设计思想：默认第一位为有序序列，后面的序列依次同第一位中数据做对比，然后插入到有序数组中合适的位置中去，从而达到成功排序。
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(1)
 * 算法稳定性：稳定
 * 正序时最优
 * 大于1000的数据不建议使用
 */
public class InsertionSort {

    public static int[] sort(int[] sortInt){
        int len = sortInt.length;
        int num = 0;
        for (int i = 1; i < len; i++) {
            for (int j = i-1; j > 0 ; j--,num++) {
                if (sortInt[i] < sortInt[j]) {
                    sortInt[i] ^= sortInt[j];
                    sortInt[j] ^= sortInt[i];
                    sortInt[i] ^= sortInt[j];
                }
            }
        }
        System.out.println("num=" + num);
        return sortInt;
    }
}
