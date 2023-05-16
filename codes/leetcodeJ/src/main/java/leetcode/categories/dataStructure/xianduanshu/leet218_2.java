package leetcode.categories.dataStructure.xianduanshu;

import leetcode.utils.ChangeToArrayOrList;
import leetcode.utils.PrintArrays;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author: Zekun Fu
 * @date: 2023/2/27 16:13
 * @Description:
 * 天际线线段树解法
 */
public class leet218_2 {
    private final int maxn = (int)4e6 + 5;
    private int[] lc, rc, maxv, flag;
    private int idx = 1, root = 1;          // 从push_down中开点，所以从1开始
    private void push_up(int u) {
        maxv[u] = Math.max(maxv[lc[u]], maxv[rc[u]]);
    }
    // 只有在完全包含这个区间的时候，才会进行push_down(u, x)操作。
    private void push_down(int u, int x) {
        maxv[u] = Math.max(maxv[u], x);
        flag[u] = Math.max(flag[u], x);
    }
    private void push_down(int u) {
        // 动态开点
        if (lc[u] == 0) lc[u] = ++idx;
        if (rc[u] == 0) rc[u] = ++idx;
        if (flag[u] == 0) return ;
        push_down(lc[u], flag[u]);
        push_down(rc[u], flag[u]);
        flag[u] = 0;
    }
    public void update(int u, int l, int r, int L, int R, int x) {
        if (L > R) throw new IllegalArgumentException(String.format("L = %d > R = %d!", L, R));
        if (L > r || R < l) throw new IllegalArgumentException(String.format("[L, R] = [%d %d] & [l, r] = [%d %d] == null", L, R, l, r));
        if (L <= l && r <= R) {
            push_down(u, x);
            return ;
        }
        push_down(u);
        int mid = (int)(((long)l + r) >> 1);
        if (L <= mid) update(lc[u], l, mid, L, R, x);
        if (R > mid) update(rc[u], mid + 1, r, L, R, x);
        push_up(u);
    }
    public int query(int u, int l, int r, int L, int R) {
        if (L > R) throw new IllegalArgumentException("L > R!");
        if (L > r || R < l) throw new IllegalArgumentException(String.format("[L, R] = [%d %d] & [l, r] = [%d %d] == null", L, R, l, r));
        if (u == 0) return 0;                                       // 没被访问，就一定没有值
        if (L <= l && r <= R) {
            return maxv[u];
        }
        push_down(u);
        int mid = (int)(((long)l + r) >> 1);
        int ans = 0;                                            // 注意是否long, 是否从0开始
        if (L <= mid) ans = query(lc[u], l, mid, L, R);
        if (R > mid) ans = Math.max(ans, query(rc[u], mid + 1, r, L, R));
        return ans;
    }
    private void init() {
        this.flag = new int[maxn];
        this.maxv = new int[maxn];
        this.lc = new int[maxn];
        this.rc = new int[maxn];

    }
    public List<List<Integer>> getSkyline(int[][] buildings) {
        // 1. 线段树动态开点
        // 2. 对于每一个building，让区间[l, r - 1]整体修改成h
        // 3. 遍历所有的x
        // 4. 碰见最大值和前面不相同，就记录[x, cur]
        // 当前的最大高度取决于左边界的高度
        init();
        int maxv = Integer.MAX_VALUE;                   //[0, maxv]一定包含全部[0, maxv - 1]
        List<Integer>xs = new ArrayList<>();
        for (int[] build : buildings) {
            update(root, 0, maxv, build[0], build[1] - 1, build[2]);
            xs.add(build[0]);
            xs.add(build[1]);
        }
        int pre = 0;
        List<List<Integer>> ans = new ArrayList<>();
        Collections.sort(xs);
        for (int x : xs) {
            int h = query(root, 0, maxv, x, x);
            Integer[] tmp = new Integer[]{x, h};
            if (h != pre) ans.add(Arrays.asList(tmp));
            pre = h;
        }
        return ans;
    }
    public static void main(String[] args) {
        leet218_2 leet218_2 = new leet218_2();
        String arr = "[[0,2,3],[2,5,3]]";
        int[][]tmp = ChangeToArrayOrList.changeTo2DIntArray(arr);
        List<List<Integer>>list = leet218_2.getSkyline(tmp);
        for (List<Integer>t : list) {
            PrintArrays.print1DObjArray(t.toArray(new Integer[t.size()]));
        }
    }
}
