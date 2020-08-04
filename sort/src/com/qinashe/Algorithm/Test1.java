package com.qinashe.Algorithm;

import org.junit.Test;

/**
 * 递归
 */
public class Test1 {

    /*
        在两个长度相等的排序数组中找到上中位数
     */
    @Test
    public void test01() {
        int[] arr1 = {2, 3, 4, 5, 6, 7};
        int[] arr2 = {4, 5, 6, 7, 8, 9};
        int i = test01(arr1, 0, arr1.length - 1, arr2, 0, arr2.length - 1);
        System.out.println("上中位数为： " + i);
    }

    public int test01(int[] arr1, int l1, int r1, int[] arr2, int l2, int r2) {
        //判断数组是否为空
        if (arr1 == null || arr2 == null) {
            return -1;
        }
        //取单个数组中位数
        int mid1 = (l1 + r1) / 2;
        int mid2 = (l2 + r2) / 2;
        //若余下数组中只剩一个数，则返回该数
        if (l1 >= r1) {
            return Math.min(arr1[l1], arr2[l2]);
        }
        //元素个数为奇数，记为0，偶数记为1
        int offset = ((r1 - l1 + 1) & 1) ^ 1;
        if (arr1[mid1] < arr2[mid2]) {
            return test01(arr1, mid1 + offset, r1, arr2, l2, mid2);
        } else if (arr1[mid1] > arr2[mid2]) {
            return test01(arr1, l1, mid1, arr2, mid2 + offset, r2);
        } else {
            return arr1[mid1];
        }
    }

    /*
        求两个有序数组的第K小数
     */
    @Test
    public void test02() {
        int[] arr1 = {2, 3, 4, 5};
        int[] arr2 = {4, 5, 6, 7, 8, 9};
        int k = 5;
        int i = test02(arr1, arr2, k);
        System.out.println("第k小的数：" + i);
    }

    public int test02(int[] arr1, int[] arr2, int k) {
        if (arr1 == null || arr1.length < 1) {
            return arr2[k - 1];
        }
        if (arr2 == null || arr2.length < 1) {
            return arr1[k - 1];
        }
        //调用具体算法，计算出第K小的数
        return test02(arr1, 0, arr1.length - 1, arr2, 0, arr2.length - 1, k);
    }

    public int test02(int[] arr1, int l1, int r1, int[] arr2, int l2, int r2, int k) {
        //递归的结束条件
        if (l1 >= r1) {
            return arr2[l2 + k];
        }
        if (l2 >= r2) {
            return arr1[l1 + k];
        }
        if (k == 0) {
            return Math.min(arr1[l1], arr2[l2]);
        }
        int md1 = l1 + k / 2 < r1 ? l1 + k / 2 : r1;
        int md2 = l2 + k / 2 < (r2 - l1) ? l2 + k / 2 : r2;
        if (arr1[md1] < arr2[md2]) {
            return test02(arr1, md1 + 1, r1, arr2, l2, r2, k - k / 2 - 1);
        }
        if (arr1[md1] > arr2[md2]) {
            return test02(arr1, l1, r1, arr2, l2, md2 + 1, k - k / 2 - 1);
        } else {
            return arr1[md1];
        }
    }
}