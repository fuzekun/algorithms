package leetcode.categories.dataStructure.xianduanshu;

import leetcode.utils.ChangeToArrayOrList;
import leetcode.utils.PrintArrays;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author: Zekun Fu
 * @date: 2023/2/27 15:20
 * @Description: 天际线
 *
 * 1. 扫描线
 * 2. 线段树
 */
public class Leet218 {
    private class Segment implements Comparable<Segment>{
        public int x, y;
        public int diff;

        public Segment(int x, int y, int diff) {
            this.x = x;
            this.y = y;
            this.diff = diff;
        }
        @Override
        public int compareTo(Segment o) {
            return Integer.compare(this.x, o.x);
        }
    }
    public List<List<Integer>> getSkyline(int[][] buildings) {
        int n = buildings.length;
        int m = n * 2;
        Segment[] seg = new Segment[m];
        for (int i = 0, j = 0; i < n; i++) {
            int[] build =  buildings[i];
            int x1 = build[0], x2 = build[1], h = build[2];
            seg[j++] = new Segment(x1, h, 1);
            seg[j++] = new Segment(x2, h, -1);
        }
        Arrays.sort(seg);
        List<List<Integer>> ans = new ArrayList<>();
        HashMap<Integer, Integer>mp = new HashMap<>();
        PriorityQueue<Integer>que = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) return -1;                                     // 自动拆包
                else if (o1.equals(o2)) return 0;
                else return 1;
            }
        });
        que.add(0);
        mp.put(0, 1);
        int pre = 0;
        for (int i = 0; i < m; ) {
            int j = i;
            // 处理相同的边界
            while (j < m && seg[i].x == seg[j].x) {
                int h = seg[j].y, diff = seg[j].diff;
                if (mp.containsKey(h)) mp.put(h, mp.get(h) + diff);
                else mp.put(h, diff);
                // 入队
                if (mp.get(h) > 0) que.add(h);
                // 出队
                while (!que.isEmpty() && mp.getOrDefault(que.peek(), 0) == 0) que.poll();
                j++;
            }
            if (pre != que.peek()) {
                pre = que.peek();
                Integer[] tmp = new Integer[]{seg[i].x, pre};
                ans.add(Arrays.asList(tmp));
            }
            i = j;
        }
        return ans;
    }

    public static void main(String[] args) {
        Leet218 leet218 = new Leet218();
        String arr = "[[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]";
        int[][]tmp = ChangeToArrayOrList.changeTo2DIntArray(arr);
        List<List<Integer>>list = leet218.getSkyline(tmp);
        for (List<Integer>t : list) {
            PrintArrays.print1DObjArray(t.toArray(new Integer[t.size()]));
        }
    }
}
