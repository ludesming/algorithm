package com.maxming.sort;

/**
 * 计数排序，非常规比较排序法
 * 适用于整数，且长度为n无序整数数组，大小在一定范围内（0-k）的数值比较集中的情况。，在0-100是性能最好的算法
 * 思想：如同一排已经排好位置的电影院位置，你拿着手上的号牌去找对应的号码就可以，如果是重复的，就在对应位置下号牌数下标+1
 * 实现步骤：
 * 1、找到最大值max，（此一步可以优化为，找到最大值和最小值）,最小值min
 * 2、定义一个数组C, 范围为min-max;
 * 3、将需要排序的数组，按照对应的值i，存放入在C(i)中，重复的值，下标+1
 *
 * 个人见解：感觉这个计数排序非常类似于哈希数组，固定的下标，将值放入对应的桶中，
 * 只是稍微不同的是，哈希算法是将原值进行哈希计算得到固定下标，而计数排序则是原值对应就是下标
 */
public class CountingSort {

    public static int[] sort(int[] unsortInts) {
        int len = unsortInts.length;
        if (len == 0) {
            return unsortInts;
        }
        //先找到最大值和最小值
        int max = unsortInts[0];
        int min = max;
        for (int i = 1; i < len; i++) {
            if (max < unsortInts[i]) {
                max = unsortInts[i];
            }
            if (min > unsortInts[i]) {
                min = unsortInts[i];
            }
        }
        if (min == max) {
            //最大值等于最小值，意味着数组中值全部一致，直接返回
            return unsortInts;
        }

        //定义一个长度为max-min+1长度的数组
        int[] valueArray = new int[max-min+1];

        for (int value : unsortInts) {
            valueArray[value-min]++;
        }
        for(int k = max - min; k >= 0; k--) {
            if (valueArray[k] == 0) {
                continue;
            }
            for(int j = valueArray[k];j > 0 ; j--) {
                unsortInts[--len] = k + min;
            }
        }
        return unsortInts;
    }
}
