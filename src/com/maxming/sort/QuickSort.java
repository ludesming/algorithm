package com.maxming.sort;

import java.util.Random;

/**
 * 快速排序
 * 在同等时间复杂度为O(nlogn)的算法（归并排序，堆排序）中，快速排序的系数最低，故相对而言，其速度是最快的
 * 虽然其最差情况为O(n^2),但平均而言，其速度将块
 * 思想：分治法+冒泡。应该算是冒泡排序的递归分治法
 * 设定一个基准值，大于它的放在右边，小于它的放在左边，再对左右分区重复此操作，知道只剩下一个值，就排序完成了
 * 实现步骤：
 * 1、从数列中挑出一个值作为基准值，通常以第一个值为基准值，用临时变量temp 存储起来
 * 2、从后往前，当大于基准值时，high--,当小于基准值时，将array[high]赋给array[low],然后low++，在从前往后遍历，知道low==high，
 *      这个位置就是基准值的位置，array[high] = temp;
 * 3、重复以上动作，知道只剩一个值
 *
 * 时间复杂度：平均O(nlogn),最坏O(n^2),最好O(nlogn)
 * 空间复杂度：O(1)
 * 算法稳定性：不稳定
 */
public class QuickSort {

    public static int[] sort(int[] unsortInts) {
        int len = unsortInts.length;
        quickSort(unsortInts, 0, len - 1);
        return unsortInts;
    }

   private static int[] quickSort(int[] unsortInts, int low, int high) {
        if (low < high) {
            int baseIndex = getBaseIndex(unsortInts, low, high);
            quickSort(unsortInts, low, baseIndex);
            quickSort(unsortInts, baseIndex + 1, high);
        }
        return unsortInts;
   }

   private static int getBaseIndex(int[] unsortInts, int low, int high) {
        //采用首位为基准值
       //int temp = unsortInts[low];
        //采用随机数取基准值
        int tempIndex =  new Random().nextInt(high - low) + low;
        int temp = unsortInts[tempIndex];
        unsortInts[tempIndex] = unsortInts[low];//将首位的值填到基准值所在位置，这样就可以进行整个数组比对
        while (low < high) {
            for (;low < high; high--) {
                if (temp > unsortInts[high]) {
                    //比基准值小，排在基准值前面，填入low下
                    unsortInts[low++] = unsortInts[high];
                    break;
                }
            }

            for (;low < high; low++) {
                if (temp < unsortInts[low]) {
                    //比基准值大，排在基准值后面
                    unsortInts[high--] = unsortInts[low];
                    break;
                }
            }
        }
        unsortInts[low] = temp;
        return low;
   }




}
