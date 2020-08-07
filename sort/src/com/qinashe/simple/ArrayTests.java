package com.qinashe.simple;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 与数组相关的简单算法
 */
public class ArrayTests {

    /*
        两数组的交集
     */
    @Test
    public void test00() {
        int[] arr1 = {2, 3, 4, 5, 1};
        int[] arr2 = {3, 5, 4, 1, 0, 6};
        int[] a = test02(arr1, arr2);
        System.out.println(Arrays.toString(a));
    }
    public int[] test01(int[] arr1, int[] arr2) {
        int[] n = arr1.length < arr2.length ? arr1 : arr2;
        int[] m = arr1.length > arr2.length ? arr1 : arr2;
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n.length; i++) {
            map.put(n[i], 0);
        }
        int count = 0;
        for (int i = 0; i < m.length; i++) {
            if (map.containsKey(m[i])) {
                map.put(m[i], 1);
                count++;
            }
        }
        int[] num = new int[count];
        count=0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 0) {
                num[count] = entry.getKey();
                count++;
            }
        }
        return num;

    }
    //若两数组有序，如何优化，更快求交集
    public int[] test02(int[] arr1, int[] arr2) {
        Arrays.sort(arr1);
//        System.out.println(Arrays.toString(arr1));
        Arrays.sort(arr2);
        int[] num = arr1;
        int i = 0,j = 0,count=0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] > arr2[j]) {
                j++;
            } else if (arr1[i] < arr2[j]) {
                i++;
            } else {
                num[count] = arr1[i];
                i++;
                j++;
                count++;
            }
        }
//        System.out.println(Arrays.toString(num));
        num = Arrays.copyOf(num, count);
        return num;
    }


    /*
        最长公共前缀
        查找字符串数组，若没有返回""
     */
    @Test
    public void test10() {
        String[] sts = {"asdqwe123","asdqwe12dfg","asdqwe1jh"};
        String s = test11(sts);
        System.out.println(s);
    }
    public String test11(String[] strs) {
        if (null == strs || strs.length == 0) {
            return "";
        }
        String baseStr = strs[0];
        for (int i = 1; i < strs.length; i++) {
            String compareStr = strs[i];
            while (!compareStr.startsWith(baseStr)) {
                baseStr = baseStr.substring(0, baseStr.length() - 1);
                if (baseStr.length() == 0) {
                    return "";
                }
            }
        }
        return baseStr;
    }
}
