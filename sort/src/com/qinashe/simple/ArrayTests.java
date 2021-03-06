package com.qinashe.simple;

import org.junit.Test;

import java.util.*;

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

    /*
        两数之和
            给定数组nums和一个目标值target，在数组中找出和为目标值的两整数的下标
     */
    @Test
    public void test6() {
        int[] nums = {2, 4, 5, 6, 7, 11};
        int target = 10;
/*        //暴力解法
        int[] ints = test61(nums, target);
        System.out.println(Arrays.toString(ints));
        //空间换时间
        int[] ints1 = test62(nums, target);
        System.out.println(Arrays.toString(ints1));
        */

        //极限
        int[] ints2 = test63(nums, target);
        System.out.println(Arrays.toString(ints2));

    }
    //暴力解法
    public int[] test61(int[] nums,int target) {
        int[] a = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    a[0] = i;
                    a[1] = j;
                    return a;
                }
            }
        }
        return a;
    }

    //空间换时间
    public int[] test62(int[] nums,int target) {
        int[] a = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i],i);
        }
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                a[0] = i;
                a[1] = map.get(target - nums[i]);
                return a;
            }
        }
        return a;
    }
    //极限
    public int[] test63(int[] nums,int target) {
        int[] a = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i],i);
            if (map.containsKey(target - nums[i]) && i != map.get(target - nums[i])) {
                a[0] = map.get(target - nums[i]);
                a[1] = i;
                return a;
            }
        }
        return a;
    }

    /*
        三数之和
        给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
        使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
        注意：答案中不可以包含重复的三元组。
     */
    @Test
    public void test7() {
        int[] nums = {-1,0,1,2,-1,-4};
        List<List<Integer>> lists = test71(nums);
        for (List<Integer> list : lists) {
            Integer[] is = (Integer[]) list.toArray();
            System.out.println(Arrays.toString(is));
        }
    }
    public List<List<Integer>> test71(int[] nums){
        Arrays.sort(nums);
        //准备容器
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int target = 0 - nums[i];
            int l = i + 1;
            int r = nums.length - 1;
            if (nums[i] > 0) {
                break;
            }
            //跳过当前轮最小的数
            if (i == 0 || nums[i] != nums[i - 1]) {
                while (l < r) {
                    if (nums[l] + nums[r] == target) {
                        res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                        //跳过相邻相同的数
                        while (l < r && nums[l] == nums[l + 1]) l++;
                        while (l < r && nums[r] == nums[r - 1]) r--;
                        l++;
                        r--;
                    } else if (nums[l] + nums[r] < target) {
                        l++;
                    } else r--;
                }
            }
        }
        return res;
    }

    /*
        Z字形变换
        将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
        比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
        L   C   I   R
        E T O E S I I G
        E   D   H   N
        之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
     */
    @Test
    public void test8() {
        String str = "LEETCODEISHIRING";
        int numRows=5;
        String s = test81(str, numRows);
        System.out.println(s);
    }
    public String test81(String str, int numRows) {
        if (numRows==1) return str;
        String[] arr = new String[numRows];
        Arrays.fill(arr,"");
        char[] chars = str.toCharArray();
        int len = chars.length;
        //一个周期中遍历的数量
        int period = numRows * 2 - 2;
        for (int i = 0; i < len; i++) {
            int mod = i % period;
            if (mod < numRows) {//竖排上的字符
                arr[mod] += chars[i];
            } else {//竖排中间的字符
                //从下到上
                arr[period - mod] += String.valueOf(chars[i]);
            }
        }
        StringBuilder res = new StringBuilder();
        for (String s : arr) {
            res.append(s);
        }
        return res.toString();
    }

}
