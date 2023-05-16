package lanqiao.imporve;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

/**
 * @author: Zekun Fu
 * @date: 2022/12/19 23:45
 * @Description:
 */
public class ZiXingChe {
    /**
     * 双向链表的实现
     *
     * @author liyanan
     * @date 2020/1/2 13:16
     */
    public static class SNode<T> {
        /**
         * 存储数据
         */
        public T data;
        /**
         * 指向当前结点的前一个结点
         */
        public SNode<T> pre;
        /**
         * 指向当前结点的下一个节点
         */
        public SNode<T> next;

        public SNode() {
        }

        public SNode(T data) {
            this.data = data;
        }
    }

    public static class DoubleLinkedList<T> {

        /**
         * 双向链表的头结点，存储第一个有效结点的基地址
         */
        private SNode<T> head;

        /**
         * 双向链表的有效结点数量
         */
        private int size;

        public DoubleLinkedList() {
            head = null;
            size = 0;
        }

        public int getSize() {
            return size;
        }


        public boolean isEmpty() {
            return size == 0;
        }

        /**
         * 插入结点到双向链表末尾
         *
         * @param newNode
         */
        public void addLast(SNode<T> newNode) {
            if (isEmpty()) {
                head = newNode;
                head.next = null;
                head.pre = null;
                size++;
            } else {
                SNode<T> temp = head;
                // 找到双向链表最后一个有效结点
                while (temp.next != null) {
                    temp = temp.next;
                }
                // 将新结点加入双向链表
                addAfter(temp, newNode);
            }
        }

        /**
         * 将新的节点插入到指定节点后
         *
         * @param preNode 指定节点
         * @param newNode 新的节点
         */
        public void addAfter(SNode<T> preNode, SNode<T> newNode) {
            // 要插入到链表末尾时，不需要维护下一个结点的前驱指针
            if (preNode.next != null) {
                preNode.next.pre = newNode;
            }
            newNode.next = preNode.next;
            newNode.pre = preNode;
            preNode.next = newNode;
            size++;
        }
        /**
         * 将结点插入到新结点前
         */
        public void addPre(SNode<T> afterNode, SNode<T> newNode) {
            // 要插入到链表第一个结点时候，不用维护前一个的后继
            if (afterNode.pre != null) {
                afterNode.pre.next = newNode;
            } else head = newNode;
            newNode.pre = afterNode.pre;
            newNode.next = afterNode;
            afterNode.pre = newNode;
            size++;
        }
        /**
         * 删除数据域为指定值的元素
         *
         * @param e
         */
        public void del(T e) {
            SNode<T> temp = head;
            while (temp != null) {
                if (temp.data.equals(e)) {
                    // 维护 head，head 永远指向双向链表第一个有效结点
                    temp.next.pre = temp.pre;
                    if (temp == head) {
                        head = head.next;
                        head.pre = null;
                    } else {
                        temp.pre.next = temp.next;
                    }
                    return;
                }
                // temp 向后移
                temp = temp.next;
            }
        }

        /**
         * 删除指定位置的结点
         *
         * @param k
         */
        public void del(int k) {
            SNode<T> delNode = find(k);
            delNode.next.pre = delNode.pre;
            if (delNode == head) {
                head = head.next;
                head.pre = null;
            } else {
                delNode.pre.next = delNode.next.next;
            }
        }

        public SNode<T> findO(T x) {
            SNode<T>p = head;
            while (p != null && !p.data.equals(x)) p = p.next;
            assert p != null;
            return p;
        }

        /**
         * 找到双向链表第 k 个结点
         *
         * @param k k 从 0 开始
         * @return
         */
        public SNode<T> find(int k) {
            if (k > size || k < 0) {
                throw new RuntimeException("传入的参数 k 必须大于等于零并且小于等于链表的长度 size");
            }
            int cnt = 0;
            SNode<T> t = head;
            while (cnt != k) {
                cnt++;
                t = t.next;
            }
            return t;
        }

        /**
         * 打印单链表所有有效节点
         *
         * @return
         */
        public String printAll() {
            StringBuilder sb = new StringBuilder();
            SNode<T> temp = head;
            while (temp != null) {
                sb.append(temp.data);
                sb.append(" ");
                temp = temp.next;
            }
            return sb.toString();
        }
    }
     public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        HashMap<Integer, SNode<Integer>>loc = new HashMap<>();
        int n = Integer.parseInt(in.readLine());
        int x = Integer.parseInt(in.readLine());
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
//        HashSet<Integer>set = new HashSet<>();
         SNode<Integer> cur = new SNode<>(x);
         loc.put(x, cur);
         list.addLast(cur);
        for (int i = 0; i < n - 1; i++) {
            String[] input = in.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            int c = Integer.parseInt(input[2]);
            // b是已知结点,

            SNode<Integer> preNode = loc.get(b);
            cur = new SNode<>(a);
            loc.put(a, cur);
            if (c == 1) {
                // 把当前结点放在前一个结点的右边。
                list.addAfter(preNode, cur);
            }
            else {
                // 把当前结点放在前一个结点的左边
                list.addPre(preNode, cur);
            }
        }
//        out.write(list.printAll());
//         System.out.println();
        System.out.println(list.printAll());
    }
}
