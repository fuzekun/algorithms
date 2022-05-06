package leetcode.utils;

import com.sun.istack.internal.NotNull;
import leetcode.everyDay.April.Exercise25;

import java.util.List;

public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val,ListNode next) {
        this.val = val;
        this.next = next;
    }

    private static ListNode createList(int[]list) {

        ListNode head = new ListNode();
        ListNode p = head;
        for (int x: list) {
            p.next = new ListNode(x);
            p = p.next;
        }
        return head.next;
    }

    public static void travelList(@NotNull ListNode h) {
        ListNode p = h;
        System.out.print("[");
        while(p.next != null) {
            System.out.print(p.val + ",");
            p = p.next;
        }
        System.out.println(p.val + "]");
    }
    public ListNode reverseList(ListNode l) {
        ListNode head = new ListNode();
        ListNode p = l;
        while(p != null) {
            ListNode t = p;
            p = p.next;
            t.next = head.next;
            head.next = t;
        }
        return head.next;
    }

    public static void main(String[] args) {
        int[]list = {1,2,3,3,4,4,5};
        ListNode h = createList(list);
        travelList(h);
    }
}
