package leetcode.everyDay.siyue;

import java.util.*;

/**
 * @author: Zekun Fu
 * @date: 2023/4/28 11:44
 * @Description:
 */
public class Leet1172_2 {
}


class D{

    PriorityQueue<Integer>pushId;
    Set<Integer> inQ ;
    int last;
    int cap;
    List<Stack<Integer>>list;
    public D(int capacity) {
        last = -1;
        pushId = new PriorityQueue<>();
        inQ = new HashSet<>();
        list = new ArrayList<>();
        cap = capacity;
    }

    public void push(int val) {
        // 如果所有都满了
        if (pushId.isEmpty()) {
            // 如果栈的大小为空，创建新的栈，否则使用旧的栈。
            if (list.size() <= last) {
                Stack<Integer>st = new Stack<>();
                st.add(val);
                list.add(st);
            }
            else {
                Stack<Integer>st = list.get(0);
                st.clear();
                st.add(val);
            }
            pushId.add(0);
            inQ.add(0);
            last = 0;
        }
        else {
            int curPush = pushId.peek();
            Stack<Integer> st = list.get(curPush);
            while (st.size() == cap) {
                if (pushId.isEmpty()) break;
                curPush = pushId.poll();
                st = list.get(curPush);
            }
        }
    }

//    public int pop() {
//
//    }
//
//    public int popAtStack(int index) {
//    }
}
