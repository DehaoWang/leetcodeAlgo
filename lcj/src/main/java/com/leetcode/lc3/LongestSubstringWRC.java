package main.java.com.leetcode.lc3;

import java.util.*;

/**
 * Created by wangdehao on 18/4/11.
 */
public class LongestSubstringWRC {

    /**
     * test cases
     */

    public static void main(String[] args){
//        System.out.println(lengthOfLongestSubstringN2("abcabcbb"));
//        System.out.println(lengthOfLongestSubstring("bbbbbbbb"));
//        System.out.println(lengthOfLongestSubstring("pwwkew"));
//        System.out.println(lengthOfLongestSubstring("abc"));
//        System.out.println(lengthOfLongestSubstring("bziuwnklhqzrxnb"));
//        System.out.println(lengthOfLongestSubstringN2("bziuwnklhqzrxnb"));
        System.out.println(lengthOfLongestSubstringSlidingWindow2N("bziuwnklhqzrxnb"));
        System.out.println(lengthOfLongestSubstringSlidingWindowN("bziuwnklhqzrxnb"));
//        System.out.println(lengthOfLongestSubstringN2("au"));
//
    }

    /**
     * algorithm
     */

    // submitted
    public static int lengthOfLongestSubstring(String s) {
        System.out.println(s);
        if(s.length() <= 1){
            return s.length();
        }
        char[] chars = s.toCharArray();
        int maxLength = lengthOfLongestSubStringRecursive(chars, 0, s.length()-1);

        return maxLength;
    }

    public static int lengthOfLongestSubStringRecursive(char[] chars, int l, int r){
        if(l > r){
            return 0;
        }
        if(l == r){
            return 1;
        }


        int recMax = 0;
        int pivot = (l + r) / 2;
        System.out.println(String.format("l=%d,pivot=%d,r=%d", l, pivot, r));
//        int leftLLS = lengthOfLongestSubStringRecursive(chars, l, pivot-1);
//        int rightLLS = lengthOfLongestSubStringRecursive(chars, pivot+1, r);
//        recMax = Math.max(leftLLS, rightLLS);
//        System.out.println("lLLS="+leftLLS);
//        System.out.println("rLLS="+rightLLS);
        int lrMaxLength = getLrMaxLength(chars, pivot, l, r);
        int rlMaxLength = getRlMaxLength(chars, pivot, l, r);
        int midMax = Math.max(lrMaxLength, rlMaxLength);


        System.out.println("lrML="+lrMaxLength);
        System.out.println("rlML="+rlMaxLength);

        return Math.max(recMax, midMax);
    }

    private static int getLrMaxLength(char[] chars, int pivot, int l, int r) {
        Set<Character> charSet =  new HashSet<Character>();
        System.out.println("pivot="+pivot+", char[pivot]="+chars[pivot]);
        int len = 0;
        for(int i = pivot; i >= l; i --){
            if(charSet.contains(chars[i])){
                break;
            }
            charSet.add(chars[i]);
            len++;
        }
        System.out.println("l-len="+len);

        for(int i = pivot+1; i <= r; i ++){
            if(charSet.contains(chars[i])){
                break;
            }
            charSet.add(chars[i]);
            len++;
        }
        return len;
    }

    private static int getRlMaxLength(char[] chars, int pivot, int l, int r) {
        Set<Character> charSet =  new HashSet<Character>();
        int len = 0;
        for(int i = pivot; i <= r; i ++){
            if(charSet.contains(chars[i])){
                break;
            }
            charSet.add(chars[i]);
            len++;
        }

        for(int i = pivot-1; i >= l; i --){
            if(charSet.contains(chars[i])){
                break;
            }
            charSet.add(chars[i]);
            len++;
        }
        return len;
    }


    // good solutions
    public static int lengthOfLongestSubstringSlidingWindow2N(String s) {
        // based on N2 solution: to expand, there is no need for j to go back
        char[] chars = s.toCharArray();
        int i = 0, j = 0, max = 0;
        Set<Character> charSet = new HashSet<Character>();
        while(i < chars.length && j < chars.length){
            if(!charSet.contains(chars[j])){
                charSet.add(chars[j]);
                j++;
                max = Math.max(max, j-i);
            }else {
                charSet.remove(chars[i]);
                i++;
            }
        }
        return max;
    }

    public static int lengthOfLongestSubstringSlidingWindowN(String s) {
        // based on 2N solution: to expand, there is no need for i to go little by little, instead, i can go to j+1
        System.out.println(s);
        char[] chars = s.toCharArray();
        int i = 0, j = 0, max = 0;
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        while(i < chars.length && j < chars.length){
            System.out.println(String.format("i=%d, j=%d, map=%s", i, j, map.toString()));
            if(!map.containsKey(chars[j])){
                map.put(chars[j], j);
                j++;
                max = Math.max(max, j-i);
            }else {
                i = Math.max(map.get(chars[j])+1, i);
                map.remove(chars[j]);
            }
        }
        return max;
    }

    // init simple solution: N2

    public static int lengthOfLongestSubstringN2(String s) {
        if(s.length() <= 1){
            return s.length();
        }
        char[] chars = s.toCharArray();
        int max = 0;
        for(int i = 0; i < s.length(); i++){
            int len = 0;
            boolean update = false;
            String ss = "";
            Set<Character> charSet = new HashSet<Character>();
            for(int j = i; j < s.length() && !update; j++){
                if(charSet.contains(chars[j])){
                    update = true;
                }else {
                    charSet.add(chars[j]);
                    ss+=chars[j];
                    len++;
                }
                System.out.println(String.format("i=%d, j=%d, ss=%s, len=%d, charSet=%s", i, j, ss, len, charSet.toString()));
            }
            if(len > max){
                max = len;
            }
        }
        return max;
    }

    // N3
    public static int lengthOfLongestSubstringN3(String s) {
        int max = 0;
        for(int i = 0; i < s.length()-1; i++){
            for(int j = i; j < s.length(); j++) {
                String testS = s.substring(i, j);
                if(testS.length() > max && withoutRepeatingChar(testS)){
                    max = testS.length();
                }
            }
        }

        return max;
    }

    private static boolean withoutRepeatingChar(String testS) {
        Set<Character> charSet = new HashSet<Character>();
        char[] chars = testS.toCharArray();
        for(int i = 0; i < chars.length; i++){
            if(charSet.contains(chars[i])){
                return false;
            }else {
                charSet.add(chars[i]);
            }
        }
        return true;
    }


}
