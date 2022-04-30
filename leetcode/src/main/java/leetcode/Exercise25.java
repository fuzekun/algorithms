package leetcode;

import com.sun.istack.internal.NotNull;

import java.util.*;

/**
 *  25天的编程练习
 * @author fuzekun
 * @version 1.1
 *
 * 第一天：链表的三道题目,多项式合并
 */
public class Exercise25 {

    private Exercise25() {

    }
    private static class ExerceseHander {
        public static Exercise25 e = new Exercise25();
    }

    public static Exercise25 getInstance() {
        // 可以看作一个静态的成员变量
        return ExerceseHander.e;        // 可以被外部获取到，但是应该注意
    }

    static class PolyNode {
        int coefficient, power;
        PolyNode next = null;

        PolyNode() {
        }

        PolyNode(int x, int y) {
            this.coefficient = x;
            this.power = y;
        }

        PolyNode(int x, int y, PolyNode next) { this.coefficient = x; this.power = y; this.next = next; }
    }

    public PolyNode addPoly(PolyNode poly1, PolyNode poly2) {
        PolyNode head = new PolyNode();
        PolyNode p = head;
        PolyNode p1 = poly1, p2 = poly2;
        while(p1 != null && p2 != null) {
            if (p1.power < p2.power) {
                p.next = p2;
                p = p2;
                p2 = p2.next;
            } else if (p2.power < p1.power) {
                p.next = p1;
                p = p1;
                p1 = p1.next;
            } else {        // 相等
                int x = p1.coefficient + p2.coefficient;
                if (x != 0) {
                    p1.coefficient = x;
                    p.next = p1;
                    p = p1;
                }
                p1 = p1.next;
                p2 = p2.next;
            }
        }
        p.next = p1 == null ? p2 : p1;
        return head.next;
    }
    static PolyNode createList(int[][] list) {
        PolyNode head = new PolyNode();
        PolyNode p = head;
        for (int[] node: list) {
            p.next = new PolyNode(node[0], node[1]);
            p = p.next;
        }
        return head.next;
    }
    static void search(PolyNode head) {
        PolyNode p = head;
        System.out.print("[");
        while(p != null) {
            System.out.print("[");
            System.out.print(p.coefficient + "," + p.power);
            System.out.print("]");
            p = p.next;
        }
        System.out.println("]");
    }

    private static class ListNode {
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

    private static ListNode createList(int[]list) {

        ListNode head = new ListNode();
        ListNode p = head;
        for (int x: list) {
            p.next = new ListNode(x);
            p = p.next;
        }
        return head.next;
    }

    private static void travelList(@NotNull ListNode h) {

        ListNode p = h;
        System.out.print("[");
        while(p.next != null) {
            System.out.print(p.val + ",");
            p = p.next;
        }
        System.out.println(p.val + "]");
    }
    private ListNode reverseList(ListNode l) {
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
    public ListNode plusOne(ListNode head) {
        ListNode p = reverseList(head);
        ListNode tail = p;
        int t = 1;
        while(p != null) {
            int sum = p.val + t;
            p.val = sum % 10;
            t = sum / 10;
            p = p.next;
        }
        ListNode h = reverseList(tail);
        if (t != 0) {
            p = new ListNode(1);
            p.next = h;
            h = p;
        }
        return h;
    }
    public ListNode deleteDuplicatesUnsorted(ListNode head) {
        int n = 0;
        for (ListNode p = head; p != null; p = p.next) n = Math.max(n, p.val);
        int[] cnt = new int[n + 1];
        Arrays.fill(cnt, 0);
        for (ListNode p = head; p != null; p = p.next) cnt[p.val]++;

        ListNode h = new ListNode(), p = head;

        while(p != null) {
            if (cnt[p.val] == 1) {
                ListNode tmp = p;
                p = p.next;
                tmp.next = h.next;
                h.next = tmp;
            }
            p = p.next;
        }
        return h.next;
    }

    public ListNode deleteDuplicates(ListNode head) {
        /*
        *
        *   删除链表中的重复元素, 排好序的
        * */
        ListNode h = new ListNode(-101);
        ListNode p = head, pre = h;
        while(p != null) {
            if (p.val != pre.val) {
                pre.next = p;
                pre = p;
            }
            p = p.next;
        }
        return h.next;
    }

    private static void test4() {
        int[]list = {1,2,3,3,4,4,5};
        ListNode h = createList(list);
        Exercise25 e = getInstance();
        h = e.deleteDuplicates(h);
        travelList(h);
    }

    private static void test3() {
        int[] list = {9, 9, 8, 7, 9};
        ListNode h = createList(list);
        Exercise25 e = getInstance();
        h = e.deleteDuplicatesUnsorted(h);
        travelList(h);
    }

    private static void test2() {
        int[] list = {9,9,9};
        ListNode h = createList(list);
        travelList(h);
        Exercise25 e = getInstance();
        h = e.reverseList(h);
        travelList(h);
        h = e.plusOne(h);
        travelList(h);
    }

    static void test1() {
        String s = "[[9,10],[-8,9],[1,5],[-7,3]]\n" +
                "[[-4,4],[7,3]]";
        s = s.replace("[", "{");
        s = s.replace("]", "}");
        System.out.println(s);
        int[][] list1 = {{9,10},{-8,9},{1,5},{-7,3}};
        int[][] list2 = {{-4,4},{7,3}};
        PolyNode head1 = createList(list1);
        PolyNode head2 = createList(list2);
        search(head1);
        search(head2);
        Exercise25 e = getInstance();
        PolyNode h = e.addPoly(head1, head2);
        search(h);
    }





    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
        test4();
    }
}
