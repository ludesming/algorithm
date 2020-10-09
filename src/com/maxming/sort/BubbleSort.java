package com.maxming.sort;

import java.util.Arrays;

/**
 * 冒泡排序 时间复杂度 O(n^2) 最优O(n)
 * 空间复杂度O(1)
 * 算法稳定性：稳定
 * 原理：2个相邻的进行对比，前面比后面大，则交换位置。这样就能确保第一次循环后，
 *      可以获取到最大值为最后一个，然后下一轮就仅排序最大值之外的值，以此类推
 * 适合于基本已经排序，且元素个数比较小的场景
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] sortInt = {7,8,9,10,12,13,163,17,19,22,45,89,101,122,132,142,162,167};
        //optmize2Sort(sortInt);
        //sort(sortInt);
        //optmizeSort(sortInt);
        cocktailSort(sortInt);
        //optmize3Sort(sortInt);
        verifySort(sortInt);
    }

    /**
     * 基础冒泡算法实现
     * @param sortInt
     * @return
     */
    public static int[] sort(int[] sortInt){
        int len = sortInt.length;
        int num = 0;
        int oNum = 0;
        for (int i = len - 1; i >=0 ; i--) {
            for (int j = 0; j < i; j++,num++) {
                if (sortInt[j] > sortInt[j + 1]) {
                    oNum++;
                    sortInt[j+1] = sortInt[j+1]^sortInt[j];
                    sortInt[j] = sortInt[j]^sortInt[j+1];
                    sortInt[j+1] = sortInt[j+1]^sortInt[j];
                }
            }
        }
        System.out.println("num=" + num + "，oNum=" + oNum);
        return sortInt;
    }
    /**
     * 优化场景一：
     * 优化已经排序后还在继续冒泡问题
     *  本轮排序中元素没有交换 则isSorted = true 直接跳出大循环，避免后续无意义的重复
     * @param sortInt
     */
    public static int[] optmizeSort(int[] sortInt){
        int len = sortInt.length;
        int num = 0;
        int oNum = 0;
        for (int i = len-1; i >= 0 ; i--) {
            boolean isSorted = true;
            for (int j = 0; j < i ; j ++,num++) {
                if (sortInt[j] > sortInt[j + 1]) {
                    oNum++;
                    isSorted = false;
                    sortInt[j] = sortInt[j]^sortInt[j + 1];
                    sortInt[j + 1] = sortInt[j + 1]^sortInt[j];
                    sortInt[j] = sortInt[j]^sortInt[j + 1];
                }
            }
            if (isSorted) {
                oNum++;
                break;
            }
        }
         System.out.println("num=" + num + "，oNum=" + oNum);
        return sortInt;
    }

    /**
     * 优化场景二：
     * 部分已经有序了，下一轮的时候还是会遍历
     * 方案：记录有序和无序数据的边界，有序的部分在下一轮就不用遍历了
     * 其中增加了场景一的情况优化
     * @param sortInt
     * @return
     */
    public static int[] optmize2Sort(int[] sortInt) {
        int len = sortInt.length;
        int num = 0;
        int sortLen = 0;
        int oNum = 0;
        for (int i = len - 1; i >=0; i--) {
            int sortnum = 0;
            for (int j = sortLen; j < i; j++,num++) {

                if (sortInt[j] > sortInt[j+1]) {
                    sortnum++;
                    oNum++;
                    sortInt[j] ^=sortInt[j+1];
                    sortInt[j+1] ^=sortInt[j];
                    sortInt[j] ^=sortInt[j+1];
                }
                if (sortnum == 1) {
                    oNum++;
                    sortLen = j-1 > 0 ? j-1 : 0;
                    sortnum++;
                    continue;
                }
            }
            if (sortnum == 0 ){
                oNum++;
                break;
            };
        }
         System.out.println("num=" + num + "，oNum=" + oNum);
        return sortInt;
    }

    /**
     * 优化场景三：
     * 只有一个元素不对，但需要走完全部轮排序
     * 方案：
     * 鸡尾酒排序：元素的比较和交换是双向的，就像摇晃鸡尾酒一样。
     * 先找到最小的数字，把他放到第一位，再找到最大的数字，把他放到最后一位，
     * 再找到第二小的数字，把它放到第二位，在找到第二大的数字，把它放到倒数第二位
     * @param sortInt
     * @return
     */
    public static int[] optmize3Sort(int[] sortInt) {
        int len = sortInt.length;
        int num = 0;
        int b = 0;
        int oNum = 0;
        boolean isSorted = true;
        for (int i = len-1; i >= 0; i--,b++) {
            isSorted = true;
            for (int j = b; j < i; j++,num++) {
                if (sortInt[j] > sortInt[j+1]) {
                    isSorted = false;
                    //取到最大值
                    oNum++;
                    sortInt[j] ^=sortInt[j+1];
                    sortInt[j+1] ^=sortInt[j];
                    sortInt[j] ^=sortInt[j+1];
                }
            }
            i--;
            for (int m = i;m > b; m--,num++) {
                if (sortInt[m-1] > sortInt[m]) {
                    oNum++;
                    isSorted = false;
                    //取到最小值
                    sortInt[m] ^=sortInt[m-1];
                    sortInt[m-1] ^=sortInt[m];
                    sortInt[m] ^=sortInt[m-1];
                }
            }
            if (isSorted) {
                oNum++;
                break;
            }
            System.out.println("第"+i+"次排序结果："+ Arrays.toString(sortInt));
        }
         System.out.println("num=" + num + "，oNum=" + oNum);
        return sortInt;
    }

    /**
     * 百度百科的鸡尾酒排序，计算得知总运行次数同普通冒泡排序
     *
     * @param src
     * @return
     */
    public static int[] cocktailSort(int[] src)
    {
        int len = src.length;
        int num = 0;
        int oNum = 0;
        //将最小值排到队尾
        for(int i = 0 ; i < len/2 ; i++)
        {
            for(int j = i ; j < len-i-1 ; j++,num++)
            {
                if(src[j] < src[j+1])
                {
                    oNum++;
                    int temp = src[j];
                    src[j] = src[j+1];
                    src[j+1] = temp;
                }
               // System.out.println("交换小"+Arrays.toString(src));
            }
            //将最大值排到队头
            for(int j = len-1-(i+1); j > i ; j--,num++)
            {
                if(src[j] > src[j-1])
                {
                    oNum++;
                    int temp = src[j];
                    src[j] = src[j-1];
                    src[j-1] = temp;
                }
               // System.out.println("交换大"+Arrays.toString(src));
            }
           System.out.println("第"+i+"次排序结果："+ Arrays.toString(src));

        }
         System.out.println("num=" + num + "，oNum=" + oNum);
        return src;
    }
    /**
     * 验证排序结果
     * @param sortInt
     */
    private static void verifySort(int[] sortInt){
        for (int value : sortInt) {
            System.out.print(value + ",");
        }
    }
}
