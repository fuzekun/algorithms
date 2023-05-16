package leetcode.contest.sanyue;

import leetcode.utils.ChangeToArrayOrList;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author: Zekun Fu
 * @date: 2023/3/12 11:15
 * @Description:
 * 1. sum(x, y) > task[3], 说明已经有了
 */
public class Leet_6318 {

    int[] sum;
    int[] add;
    private void push_up(int u) {
        sum[u] = sum[u << 1] + sum[u << 1 | 1];
    }
    private void push_down(int u, int l, int r, int x) {
        sum[u] += (r - l + 1) * x;
        add[u] += x;
    }
    private void push_down(int u, int l, int r) {
        if (add[u] == 0) return ;
        int mid = l + r >> 1;
        push_down(u << 1, l, r,  add[u]);
        push_down(u << 1 | 1, mid + 1, r,add[u]);
        add[u] = 0;
    }
    /**
     *
     * @param L, R是待访问的区间
     * */
    public void update(int u, int l, int r, int L, int R, int v) {
        if (L <= l && r <= R) {
            push_down(u, l, r, v);
            return ;
        }
        push_down(u, l, r);
        int mid = l + r >> 1;
        if (L <= mid) update(u << 1, l, mid, L, R, v);
        if (R > mid) update(u << 1 | 1, mid + 1, r, L, R, v);
        push_up(u);
    }
    public int query(int u, int l, int r, int L, int R) {
        if (L <= l && r <= R) {
            return sum[u];
        }
        push_down(u, l, r);
        int mid = l + r >> 1;
        int ans = 0;
        if (L <= mid) ans = query(u << 1, l, mid, L, R);
        if (R > mid) ans += query(u << 1 | 1, mid + 1, r, L, R);
        return ans;
    }


    public int findMinimumTime(int[][] tasks) {
        Arrays.sort(tasks, (o1, o2)->Integer.compare(o1[1], o2[1]));
        int n = tasks[tasks.length - 1][1];
        sum = new int[n * 4 + 5];
        add = new int[n * 4 + 5];
        int ans = 0;
        for (int[] task : tasks) {
            int l = task[0], r = task[1], dur = task[2];
            int s = 0;
            for (int i = l; i <= r; i++) {
                if (sum[i] != 0)
                    s++;
            }
            if (s >= dur) continue;
            int sub = dur - s;
            ans += sub;
            for (int i = r; i >= l && sub != 0; i--) {
                if (sum[i] == 0) {
                    sum[i] = 1;
                    sub--;
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        String[] ss = {"hey","aeo","mu","ooo","artro"};
        int[][] numss = ChangeToArrayOrList.changeTo2DIntArray("[[10,16,3],[10,20,5],[1,12,4],[8,11,2]]");
        int []nums = ChangeToArrayOrList.changTo1DIntArray("[0, 0, 0]");
        Leet_6318 f = new Leet_6318();
//        System.out.println(f.vowelStrings(ss, 1, 4));
//        System.out.println(f.maxScore(nums));
//        System.out.println(f.beautifulSubarrays(nums));
        System.out.println(f.findMinimumTime(numss));
    }
}
