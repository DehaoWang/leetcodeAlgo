package com.leetcode.java.lc1;

import java.util.Arrays;

/**
 * Created by wangdehao on 18/4/11.
 */
public class TwoSum {

    public static void main(String[] args){
        int[] nums = {3, 2, 4};
        int target = 6;
        int[] result = twoSum(nums, target);
        for(int i = 0; i < result.length; i++){
            System.out.println(result[i]);
        }
    }

    /**
     * algorithm
     */
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Arrays.sort(nums);
        int i = 0;
        int j = nums.length-1;

        boolean found = false;
        while(i < j){
            System.out.println("i="+i+",j="+j);
            System.out.println("ni="+nums[i]+",nj="+nums[j]);
            if(nums[i] + nums[j] < target){
                i++;
            }
            else if(nums[i] + nums[j] > target){
                j--;
            }
            else {
                result[0] = i;
                result[1] = j;
                break;
            }
        }
        return result;
    }

}
