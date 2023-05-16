package leetcode.everyDay.siyue;
import leetcode.utils.ChangeToArrayOrList;

import java.util.*;

/**
 * @author: Zekun Fu
 * @date: 2023/4/28 9:09
 * @Description:
 */
public class Leet1172 {

    public static void main(String[] args) {
        String[] ops = {"DinnerPlates","push","push","popAtStack","pop","push","push","pop","pop"};
        int[][] opnums = ChangeToArrayOrList.changeTo2DIntArray("[[1],[1],[2],[1],[],[1],[2],[],[]]");
        DinnerPlates d = new DinnerPlates(0);
        for (int i = 0; i < ops.length; i++) {
            if (ops[i].equals("DinnerPlates")) {
                System.out.print("null ");
                d = new DinnerPlates(opnums[i][0]);
            }
            else if (ops[i].equals("push")) {
                System.out.print ("null ");
                d.push(opnums[i][0]);
            }
            else if (ops[i].equals("pop")) {
                System.out.print(d.pop() + " ");
            }
            else {
                System.out.print(d.popAtStack(opnums[i][0]) + " ");
            }
        }
    }
}

class DinnerPlates {


    Stack<Integer>[] list;
    int curPop = 0;
    int capacity;
    PriorityQueue<Integer> curPushQ;        // 现在可以放数的栈的id
    PriorityQueue<Integer> curPopQ;
    Set<Integer>inPush;
    Set<Integer>inPop;
    int last;
    public DinnerPlates(int capacity) {
        final int maxlen = 200005;
        list = new Stack[maxlen];
        this.capacity = capacity;
        curPushQ = new PriorityQueue<>();
        curPopQ = new PriorityQueue<>((x, y)->Integer.compare(y, x));
        inPush = new HashSet<>();
        inPop = new HashSet<>();
        last = -1;

        // 初始所有的栈都可以用, 所有栈都为空
        for (int i = 0; i < maxlen; i++) {
            curPushQ.add(i);
            inPush.add(i);
        }

    }

    public void push(int val) {
        int curPush = curPushQ.peek();
        Stack<Integer>st = list[curPush];
        // 找到第一个没有满，或者没有初始化的栈
        while (st != null && st.size() == capacity) {
            curPushQ.poll();
            inPush.remove(curPush);

            curPush = curPushQ.peek();
            st = list[curPush];
        }

        // 初始化栈
        if (st == null)
            st = list[curPush] = new Stack<>();
        st.add(val);
        last = Math.max(last, curPush);

//        list[curPush] = st;
//        // 更新pop点
//        if (!inPop.contains(curPush)) {
//            inPop.add(curPush);
//            curPopQ.add(curPush);
//        }
    }

    public int pop() {
        return popAtStack(last);
    }
    public int pop_pre() {

        if (curPopQ.isEmpty()) return -1;

        int curPop = curPopQ.peek();
        Stack<Integer>st = list[curPop];
        // 找到第一个没有空
        while (st.isEmpty()) {
            curPopQ.poll();
            inPop.remove(curPop);

            if (curPopQ.isEmpty()) return -1;

            curPop = curPopQ.peek();
            st = list[curPop];
        }

        int ans = st.pop();
        // 栈不满了，可以放入新的
        if (!inPush.contains(curPop)) {
            inPush.add(curPop);
            curPushQ.add(curPop);
        }

        return ans;
    }

    public int popAtStack_pre(int index) {
        // 超界或者空栈
        Stack<Integer>st = list[index];
        if (st == null || st.isEmpty()) return -1;

        // 出栈
        int ans = st.pop();

        // 栈不满，可以放入新的
        if (!inPush.contains(index)) {
            inPush.add(index);
            curPushQ.add(index);
        }
        return ans;
    }

    public int popAtStack(int index) {
        // 超界或者空栈
        if (last == -1 || index > last) return -1;
        Stack<Integer>st = list[index];
        if (st.isEmpty()) return -1;

        // 出栈
        int ans = st.pop();

        // 栈不满，可以放入新的
        if (!inPush.contains(index)) {
            inPush.add(index);
            curPushQ.add(index);
        }

        // 最后一个栈，栈空，向前移动
        if (index == last && st.empty()) {
            while (last >= 0 && list[last].empty())
                last--;
        }

        return ans;
    }
}

