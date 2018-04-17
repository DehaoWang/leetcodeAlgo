package leetcodejava.src.main.java.lc1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangdehao on 18/4/11.
 */
public class TwoSum {

    /**
     * test cases
     */

    public static void main(String[] args){
        int[] nums = {3, 3};
        int target = 6;
        int[] result = twoSumSub(nums, target);
        int[] result2 = twoSumGS(nums, target);
        for(int i = 0; i < result.length; i++){
            System.out.println(result[i]);
            System.out.println(result2[i]);
        }
    }

    /**
     * algorithm
     */

    // submitted
    public static int[] twoSumSub(int[] nums, int target) {
        int[] result = new int[2];
        int len = nums.length;
        int[] numsCopy = new int[len];
        System.arraycopy(nums, 0, numsCopy, 0, len);
        Arrays.sort(numsCopy);
        for(int i = 0; i < numsCopy.length; i++){
            System.out.println(numsCopy[i]);
        }
        int i = 0;
        int j = len-1;

        boolean found = false;
        while(i < j){
            System.out.println("i="+i+",j="+j);
            System.out.println("ni="+numsCopy[i]+",nj="+numsCopy[j]);
            if(numsCopy[i] + numsCopy[j] < target){
                i++;
            }
            else if(numsCopy[i] + numsCopy[j] > target){
                j--;
            }
            else {
                System.out.println(numsCopy[i]);
                System.out.println(numsCopy[j]);
                boolean[] located = new boolean[len];
                result[0] = getIndexFromArray(numsCopy[i], nums, located);
                result[1] = getIndexFromArray(numsCopy[j], nums, located);
                break;
            }
        }
        return result;
    }

    public static int getIndexFromArray(int target, int[] nums, boolean[] located){
        for(int i = 0; i < nums.length; i++){
            if(target == nums[i] && !located[i]){
                located[i] = true;
                return i;
            }
        }
        return -1;
    }

    // good solutions: use value2Index to find complement
    public static int[] twoSumGS(int[] nums, int target){
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i++){
            int complement = target - nums[i];
            if(map.containsKey(complement)){
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return new int[2];
    }
}
