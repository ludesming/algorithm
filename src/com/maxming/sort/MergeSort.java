package com.maxming.sort;

/**
 * 归并排序算法
 * 思想：采用分治法的案例，分而治之
 * 实现方式：把长度为n的输入序列分成2个长度为n/2的子序列
 * 对这两个子序列分别采用归并排序
 * 将两个排序好的子序列合并成一个最终的有序序列
 * 时间复杂度：O(nlogn)，，最坏O(nlogn) 最好 O(nlongn)
 * 空间复杂度O(n)
 * 算法稳定性：稳定
 * 优点：
 * 性能好且稳定，时间复杂度为O(nlogn)
 * 稳定排序，使用场景多
 * 缺点：
 *  非原地排序，空间复杂度高
 */
public class MergeSort {

    public static int[] sort(int[] unsourtInt){
        return mergeSort(unsourtInt,0, unsourtInt.length - 1 );
    }

    public static int[] mergeSort(int[] unsortInt, int left, int right) {
        if (left >= right) {
            return new int[]{unsortInt[left]};
        }
        int mid = (left + right) >> 1;
        int[] leftInts = mergeSort(unsortInt, left, mid);
        int[] rightInts = mergeSort(unsortInt, mid + 1, right);
        //上面的是拆分，在下面的是合并，拆分到最后面每个数组只有一个值，一个值自然是有序的，合并后也会变成有序，
        // 同理一步步递归进来，所以上面的leftInts和rightInts都是有序数组
        //定义一个空数组来装左数组和右数组合并排序后的有序列,
        int[] sortInt = new int[leftInts.length + rightInts.length];
        int a = 0;//sortInt的索引值
        int l = 0;//左侧数组的索引值
        int r = 0;//右侧数组的索引值
        while (l < leftInts.length && r < rightInts.length) {
            if (leftInts[l] > rightInts[r]) {
                sortInt[a++] = rightInts[r++];
                continue;
            }
            sortInt[a++] = leftInts[l++];
        }

        //如果左侧树还有剩余，则剩余部分加到sortInt数组末尾
        while (l < leftInts.length) {
            sortInt[a++] = leftInts[l++];
        }
        //如果右侧树还有剩余，则剩余部分加到sortInt数组末尾
        while (r < rightInts.length) {
            sortInt[a++] = rightInts[r++];
        }

        return sortInt;
    }

}
