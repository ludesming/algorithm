package com.maxming.sort;

/**
 * 最大堆排序，适用于做顺序排序，倒序排序使用最小堆排序，原理一致
 * 思想：利用二叉堆的思想获取到最大（小）值，然后一步步做选择排序
 * 步骤：
 * 1、先创建一个堆(最大堆或者最小堆，根据排序方式决定)
 * 2、将堆顶跟堆尾交换
 * 3、把堆的尺寸减一，并继续排序堆，使之符合最大堆，
 * 4、重复步骤2，知道堆的尺寸为1
 *时间复杂度：O(nlogn)
 * 空间复杂度：O(1)
 * 算法稳定性：不稳定。
 * 算法比较，时间复杂度上，堆排序的性能较快速排序查，虽然都为O(nlogn)，但堆排序的系数较大。
 * 优点：算法性能好，且时间复杂度比较稳定，空间复杂度低
 * 适用场景：数据量大且流式输入的场景。
 */
public class MaxHeapSort {

    public static int[] sort(int[] unsortInts){
        int len = unsortInts.length;

        getTrueMaxHeap(unsortInts,len);
        //交换首位和末尾
        for (int k = len - 1; k > 0; k--) {
            swap(unsortInts,0,k);

            //首位交换后需要重新组成最大堆
            getTrueMaxHeap(unsortInts, k);
        }
        return unsortInts;
    }

    //位运算进行数组的2个位置交换
    private static void swap(int[] array, int left, int right) {
        if (array[left] == array[right]) {
            return;
        }
        array[left] ^= array[right];
        array[right] ^= array[left];
        array[left] ^= array[right];
    }

    //获取最大堆
    //从左至右，从下至上，先从最后一个非叶子节点开始
    //二叉堆获取子节点计算方式： 左子节点索引 = 2*parent + 1,右子节点索引=2*parent + 2;
    //二叉堆已知子节点索引，获取父根节点计算方式：parent = (child-1)/2,不区分左右，原因，奇偶数/2的结果
    //二叉堆最后一个叶子节点的索引为：len-1
    //所以求得最后一个非叶子节点为（len-1-1）/2 = len/2-1;
    //此排序仅获取到最上级节点为当前最大值，但是并未保证为最大堆，只取到了最大值结果，
    // 但同时减少了遍历次数，交换次数固定,不影响排序结果
    private static void getMaxHeap(int[] unsortInts, int len) {
        for (int i = len/2 - 1; i >= 0; i--){
            int temp = unsortInts[i];
            //从左边开始向右边遍历
            int leftCIndex = (i<<1) + 1;//左侧子节点索引，肯定存在，无需判断
            int rightCIndex = (i<<1) + 2 < len ? (i<<1) + 2 : leftCIndex;//右侧子节点索引，最后一个子节点未必存在，需判断
            int maxIndex = i;
            if (unsortInts[leftCIndex] > temp) {
                maxIndex = leftCIndex;
                temp = unsortInts[leftCIndex];
            }
            if (unsortInts[rightCIndex] > temp) {
                maxIndex = rightCIndex;
            }
            if (maxIndex == i) {
                continue;
            }
            swap(unsortInts, i, maxIndex);
        }
    }

    //标准的获取到最大堆的算法
    private static void getTrueMaxHeap(int[] unsortInts, int len) {
        for (int i = (len>>1) - 1; i >= 0; i--) {
            //从最后一个节点开始，排序所有节点
            int temp = unsortInts[i];//节点值
            for (int j = (i<<1) + 1; j < len; j = (j<<1) +1) {
                //这个循环意味着，当上级节点较小并交换到子节点的时候，
                // 如果这个时候子节点的值比子节点的子节点更小，则会进一步进行交换，直到形成最大堆
                if (j+1 < len && unsortInts[j] < unsortInts[j+1]){
                    //这意味着右侧子节点较大
                    j++;
                }
                if (temp < unsortInts[j]) {
                    //当右子节点大时，j++,所以此时为右侧子节点
                    //当左侧节点大或相等时，j为左侧子节点
                    //另外，求上级节点时，无需区分左右节点，源于奇偶数/2的关系
                    //此时 交换节点
                    //swap(unsortInts, i, j);
                    unsortInts[i] = unsortInts[j];
                    i = j;
                } else {
                    break;
                }
            }
            unsortInts[i] = temp;
        }
    }
}
