package com.qinashe.simple;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 与数组相关的简单算法
 */
public class ArrayTests {

    /*
        两数组的交集
     */
    @Test
    public void test0() {
        int[] arr1 = {2, 3, 4, 5, 1};
        int[] arr2 = {3, 5, 4, 1, 0, 6};
        int[] a = test02(arr1, arr2);
        System.out.println(Arrays.toString(a));
        int[] ints = test01(arr1, arr2);
        System.out.println(Arrays.toString(ints));

    }

    public int[] test01(int[] arr1, int[] arr2) {
        int[] n = arr1.length < arr2.length ? arr1 : arr2;
        int[] m = arr1.length > arr2.length ? arr1 : arr2;
        Map<Integer, Integer> map = new HashMap<>();

        for (int value : n) {
            map.put(value, 0);
        }
        int count = 0;
        for (int value : m) {
            if (map.containsKey(value)) {
                map.put(value, 1);
                count++;
            }
        }
        int[] num = new int[count];
        count = 0;
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
        int i = 0, j = 0, count = 0;
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
    public void test1() {
        String[] sts = {"asdqwegfh", "asdqwefqwe", "asdqwgczs"};
        String s = test11(sts);
        System.out.println(s);
    }

    public String test11(String[] sts) {
        if (sts.length < 1) {
            return "";
        }
        //将第一个字符串作为基准
        String prefix = sts[0];
        for (int i = 1; i < sts.length; i++) {
            //每次判断基准字符串长度，若不是公共前缀，则长度减一。
            while (!sts[i].startsWith(prefix)) {
                prefix = prefix.substring(0, prefix.length() - 1);

            }
            System.out.println(prefix);
        }
        return prefix;
    }

    /*
        买卖股票的最佳时机(122)
     */
    @Test
    public void test2() {
        int[] price = {7, 1, 5, 3, 6, 4};
        System.out.println(test21(price));

    }

    public int test21(int[] price) {
        if (price.length < 2) {
            return 0;
        }
        //判断是否有涨幅
        int sum = 0;
        for (int i = 0; i < price.length - 1; i++) {
            if (price[i] < price[i + 1]) {
                sum += price[i + 1] - price[i];
            }
        }
        return sum;
    }

    /*
    旋转数组
     */
    @Test
    public void test3() {
        int[] arr = {1, 2, 3, 4, 5, 6};
        int k = 3;
        arr = test31(arr, k);
        System.out.println(Arrays.toString(arr));
    }

    //介质旋转
    public int[] test31(int[] arr, int k) {
        int[] num = new int[arr.length];
        /*for (int i = 0; i < k; i++) {
            num[i] = arr[arr.length - k + i];
        }*/
        if (k >= 0) System.arraycopy(arr, arr.length - k, num, 0, k);

        int count = 0;
        for (int i = k; i < arr.length; i++) {
            num[i] = arr[count++];
        }
        return num;
    }

    /*
    原地删除-----返回剩余数组长度
     */
    @Test
    public void Test4() {
        int[] arr = {2, 5, 1, 6, 3, 4, 7, 3, 2, 1};
        //要删除的数
        int n = 2;
        System.out.println(arr.length);
        System.out.println(test41(arr, n));
    }

    public int test41(int[] arr, int n) {
        int count = 0;
        int[] num = new int[arr.length];
        int k = 0;
        boolean flag = true;
        /*
           count 记录指定数字出现次数
           k 记录删除后数组下标
           flag 判断此次是否要赋传值
         */
        for (int value : arr) {
            if (value == n) {
                count++;
                flag = false;
            }
            //新数组传值过程
            if (flag) {
                num[k++] = value;
                System.out.println(Arrays.toString(num));
            }
            flag = true;
        }
        int[] number = new int[arr.length - count];
        System.arraycopy(num, 0, number, 0, arr.length - count);
        System.out.println(Arrays.toString(number));
        return arr.length - count;
    }


    /*
    加一
     */
    @Test
    public void test5() {
        int[] num = {9,9,9,9};
        int[] arr=test51(num);
        System.out.println(Arrays.toString(arr));
    }

    public int[] test51(int[] num) {
        num = fan(num);
        int k = 0;//标记位
        while (true) {
            num[k]++;
            if (num[k] == 10) {
                num[k++] = 0;
            }else
                break;
            if (k == num.length) {
                int[] sum = new int[k + 1];
                System.arraycopy(num, 0, sum, 0, k);
                num = sum;
                num[k]++;
                break;
            }
        }
        num = fan(num);
        return num;
    }
    public int[] fan(int[] arr) {
        for (int i = 0; i < arr.length/2; i++) {
            int n = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = n;
        }
        return arr;
    }



}
