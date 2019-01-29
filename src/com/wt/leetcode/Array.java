package com.wt.leetcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Array {

    public static void main(String[] args) {
        int[] height = new int[] { 4, 5, 6, 4, 2, 3 };
        System.out.println(largestRectangleArea(height));
    }

    /**
     * 84. Largest Rectangle in Histogram
     * 
     * @param height
     * @return
     */
    public static int largestRectangleArea(int[] height) {
        if(height.length == 0) {
            return 0;
        }
        
        int[] less = new int[height.length];
        int[] right = new int[height.length];
        less[0] = 0;
        right[height.length - 1] = height.length - 1;

        for (int i = 1; i < height.length; i++) {
            int p = i;

            while (p - 1 >= 0 && height[p - 1] >= height[i]) {
                p = less[p - 1];
            }

            less[i] = p;
        }

        for (int i = height.length - 2; i >= 0; i--) {
            int p = i;

            while (p < height.length - 1 && height[p + 1] >= height[i]) {
                p = right[p + 1];
            }

            right[i] = p;
        }

        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            int currArea = height[i] * (right[i] - less[i] + 1);
            maxArea = maxArea >= currArea ? maxArea : currArea;
        }

        return maxArea;
    }

    /**
     * 41. First Missing Positive
     * 
     * Given an unsorted integer array, find the smallest missing positive
     * integer. Example 1:
     * 
     * Input: [1,2,0] Output: 3 Example 2:
     * 
     * Input: [3,4,-1,1] Output: 2 Example 3:
     * 
     * Input: [7,8,9,11,12] Output: 1 Note:
     * 
     * Your algorithm should run in O(n) time and uses constant extra space.
     * 
     * @param nums
     * @return
     */
    public int firstMissingPositiveV1(int[] nums) {
        int len = nums.length;
        if (len <= 0) {
            return 1;
        }

        int result = len + 1;

        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        for (int i = 1; i <= len; i++) {
            set.add(i);
        }

        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) {
                set.remove(nums[i]);
            }
        }

        Iterator<Integer> it = set.iterator();
        while (it.hasNext()) {
            result = it.next();
            break;
        }

        return result;
    }

    /**
     * 41. First Missing Positive
     * 
     * 1、移动数组，让数值与下标匹配
     * 2、遍历数组，找到与下标不匹配的数值
     * 3、根据下标得到最小整数
     * 
     * @param nums
     * @return
     */
    public int firstMissingPositiveV2(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int curNum = nums[i];
            if (curNum <= 0 || curNum > len) {
                continue;
            }
            if (nums[curNum - 1] != curNum) {
                int tmp = nums[curNum - 1];
                nums[curNum - 1] = curNum;
                nums[i] = tmp;
                i--;
            }
        }
        int ans = 1;
        while (ans <= len && nums[ans - 1] == ans) {
            ans++;
        }
        return ans;
    }

    /**
     * 给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。
     *
     * 你可以假设每个输入只对应一种答案，且同样的元素不能被重复利用。
     *
     * 示例:
     *
     * 给定 nums = [2, 7, 11, 15], target = 9
     *
     * 因为 nums[0] + nums[1] = 2 + 7 = 9 所以返回 [0, 1]
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] result = new int[2];

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                result[0] = map.get(target - nums[i]);
                result[1] = i;
            }

            map.put(nums[i], i);
        }

        return result;
    }
}
