package com.open.jun.practice.leetcode;

/**
 * @Author: 王俊
 * @Desctription: leetCode第二题
 * @Date: Created in 2024/4/27 下午3:56
 * @Version: 1.0
 */
public class T2 {

    public static void main(String[] args) {

    }

    /**
     * 将两个链表表示的数字相加
     * 两个链表中的每个节点都代表一个数字位，链表从个位到高位排列。
     *
     * @param l1 第一个链表，表示一个非负整数。
     * @param l2 第二个链表，表示一个非负整数。
     * @return 返回一个链表，表示两个输入链表数字相加后的结果。
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ans = new ListNode();
        ListNode p = ans;
        int pre = 0;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + pre;
            p.next = new ListNode(sum % 10) ;
            pre = sum / 10;
            l1 = l1.next;
            l2 = l2 .next;
            p = p.next;
        }
        while (l1 != null) {
            int sum = l1.val + pre;
            p.next = new ListNode(sum % 10);
            pre = sum / 10;
            p = p.next;
            l1 = l1.next;
        }
        while (l2 != null) {
            int sum = l2.val + pre;
            p.next = new ListNode(sum % 10);
            pre = sum / 10;
            p = p.next;
            l2 = l2.next;
        }
        if(pre != 0) {
            p.next = new ListNode(pre);
        }

        return ans.next;
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
