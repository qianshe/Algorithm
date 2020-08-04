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
        test01(arr1, 0, arr1.length - 1, arr2, 0, arr2.length - 1);
    }

    public int test01(int[] arr1, int l1, int r1, int[] arr2, int l2, int r2) {
        return 0;
    }
}
