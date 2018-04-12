package com.leetcode.java.lc2;

import com.leetcode.java.model.ListNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangdehao on 18/4/11.
 */
public class AddTwoNumbers {

    /**
     * test cases
     */

    public static void main(String[] args){
        int[] a1 = new int[]{2, 4, 3, 2, 8};
//        int[] a1 = new int[]{5};
        ListNode l1 = getListFromArray(a1);
        printListNode(l1);

        int[] a2 = new int[]{5, 6, 4};
//        int[] a2 = new int[]{5};
        ListNode l2 = getListFromArray(a2);
        printListNode(l2);

        ListNode sumList = addTwoNumbers(l1, l2);
        ListNode sumListGS = addTwoNumbersGS(l1, l2);
//        printListNode(sumList);
        printListNode(sumListGS);
    }

    public static ListNode getListFromArray(int[] a){
        if(a.length <= 0){
            return null;
        }
        ListNode first = new ListNode(a[0]);
        ListNode curr = first;
        for(int i = 1; i < a.length; i++){
            curr.next = new ListNode(a[i]);
            curr = curr.next;
        }
        return first;
    }

    public static void printListNode(ListNode l){
        System.out.println("Printing Node List");
        while (l != null){
            System.out.println(l.val);
            l = l.next;
        }
    }

    /**
     * algorithm
     */

    // submitted: recursive solution
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        return mergedList(l1, l2, carry);
    }

    private static ListNode mergedList(ListNode l1, ListNode l2, int carry) {
        if(l1 == null && l2 == null && carry <= 0){
            return null;
        }
        int sum = carry;

        sum += l1 != null ? l1.val : 0;
        sum += l2 != null ? l2.val : 0;

        carry = sum / 10;
        ListNode curr = new ListNode(sum % 10);

//        if(l1 != null && l2 == null){
//            curr.next = mergedList(l1.next, null, carry);
//        }
//        if(l1 == null && l2 != null){
//            curr.next = mergedList(null, l2.next, carry);
//        }
//        if(l1 != null && l2 != null){
//            curr.next = mergedList(l1.next, l2.next, carry);
//        }

        ListNode next1 = l1 != null ? l1.next : null;
        ListNode next2 = l2 != null ? l2.next : null;
        curr.next = mergedList(next1, next2, carry);

        return curr;
    }

    // good solutions: iterative solution
    public static ListNode addTwoNumbersGS(ListNode l1, ListNode l2) {
        ListNode dummyNode = new ListNode(0);
        ListNode curr = dummyNode;
        ListNode p1 = l1;
        ListNode p2 = l2;
        int carry = 0;
        while(p1 != null || p2 != null){
            int sum = carry;
            sum += p1 != null ? p1.val : 0;
            sum += p2 != null ? p2.val : 0;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            p1 = p1 != null ? p1.next : null;
            p2 = p2 != null ? p2.next : null;
        }
        if(carry > 0){
            curr.next = new ListNode(carry);
        }
        return dummyNode.next;

    }
}
