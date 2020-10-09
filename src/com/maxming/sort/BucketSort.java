package com.maxming.sort;

import java.util.Arrays;
import java.util.Collections;

/**
 * 桶排序
 * 算法思想：将一个数分为n个均匀的区间桶，将待排序的元素数列放入对应的桶区间中，然后再对每个桶分别进行排序，排序后即排序完成
 * 算法步骤：
 * 1、先获取到最大值和最小值
 * 2、计算出区间间隔值d= （最大值-最小值）/(桶数量-1)，，其中桶数量可以随意定义，最好不要超过原始数列的长度，超过了，意味着额外空间的浪费
 * 3、将原始数列的值放入到对应的桶内，对应的方式为：(原始数列值-最小值)/间隔值d
 */
public class BucketSort {

    public static double[] sort(double[] unsortArray) {
        int len = unsortArray.length;
        if (len == 0) {
            return unsortArray;
        }
        //获取到最大值和最小值
        double max = unsortArray[0];
        double min = unsortArray[0];

        for (int i = 1; i < len; i++) {
            if (max < unsortArray[i]) {
                max = unsortArray[i];
            }
            if (min > unsortArray[i]) {
                min = unsortArray[i];
            }
        }
        //计算出间隔值,我们将桶大小设置为数组长度
        double d = (max - min) /(len - 1);

        //将原始数组中的值放入到对应的桶之中
        //创建一个二维数组来作为桶
        double[][] buckets = new double[len][0];
        for (int j = 0; j < len; j++) {
            //获取原始值对应的桶位置
            int index = (int)Math.floor((unsortArray[j] - min)/d);
            buckets[index] = arrayAppend(buckets[index], unsortArray[j]);
         }

        //对每个桶进行独立的排序
        // 可以采用的方法很多，比如归并排序（Collections.sort）,快速排序（Arrays.sort）
        int arrayindex = 0;
        for (int m = 0; m < buckets.length; m++) {
            if (buckets[m].length <= 0) {
                continue;
            }
            Arrays.sort(buckets[m]);

            for (double value : buckets[m]) {
                unsortArray[arrayindex++] = value;
            }
        }
        return unsortArray;
    }

    private static double[] arrayAppend(double[] array, double value){
        array = Arrays.copyOf(array, array.length + 1);
        array[array.length - 1] = value;
        return array;
    }

}
