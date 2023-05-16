package leetcode.contest.yiyue;

import leetcode.utils.ChangeToArrayOrList;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

/**
 * @author: Zekun Fu
 * @date: 2023/4/30 8:09
 * @Description: 103场双周赛
 *
 * 第二题思维不全面
 * 两个子数组，求的是最大公共子集。但是第一写成了最大公共前缀了。
 * 第二题：写循环的时候，经常性的错误，比如n写成m等循环结束条件的错误。i写成j等循环变量的错误等。
 *
 */
public class Last1 {


    private int[] fa = new int[105];
    private int[] cnt = new int[105];
    private int getF(int x) {
        if (fa[x] == x) return x;
        fa[x] = getF(fa[x]);
        return fa[x];
    }
    public int findMaxFish(int[][] grid) {
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int n = grid.length, m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int u = i * m + j;
                cnt[u] = grid[i][j];
                fa[u] = u;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int u = i * m + j;
                if (grid[i][j] == 0) continue;
                for (int k = 0; k < 4; k++) {
                    int ni = i + dirs[k][0], nj = j + dirs[k][1];
                    if (ni >= 0 && ni < n && nj >= 0 && nj < m && grid[ni][nj] != 0) {
                        int v = ni * m + nj;
                        int fu = getF(u), fv = getF(v);
                        if (fu == fv) continue;
                        fa[fu] = fv;
                        cnt[fv] += cnt[fu];
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n * m + 1; i++) {
            ans = Math.max(ans, cnt[i]);
        }
        return ans;
    }

    private long[] sum;
    private long[] add;
//    private int mod;
    private void build(int u, int l, int r) {
        if (l == r) {
            sum[u] = l;         // 初始[0, 1, 2...n - 1]
            return ;
        }
        int mid = l + r >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
        push_up(u);
    }
    private void push_up(int u) {
        sum[u] = sum[u << 1] + sum[u << 1 | 1];
    }
    // 更新自己，然后打上蓝标记，不用向下了
    private void push_down(int u, int l, int r, long x) {
        sum[u] += x * (r - l + 1);
        add[u] += x;
    }
    // 自己已经被更新了，直接下推懒标记
    private void push_down(int u, int l, int r) {
        if (add[u] == 0) return ;
        int mid = l + r >> 1;
        push_down(u << 1, l, mid, add[u]);
        push_down(u << 1 | 1, mid + 1, r, add[u]);
        add[u] = 0;
    }
    private void update(int u, int l, int r, int L, int R, long x) {
        if (L <= l && r <= R) {
            push_down(u, l, r, x);
            return ;
        }
        int mid = l + r >> 1;
        push_down(u, l, r);
        if (L <= mid) update(u << 1, l, mid, L, R, x);
        if (R > mid) update(u << 1 | 1, mid + 1, r, L, R, x);
        push_up(u);
    }
    // 单点求和，不用求区间的和，直接返回叶子节点上的值就行了
    private long get(int u, int l, int r, int L, int R) {
        if (L <= l && r <= R) {
            return sum[u];
        }
        push_down(u, l, r);
        int mid = l + r >> 1;
        long ans = 0;
        if (L <= mid) ans += get(u << 1, l, mid, L, R);
        if (R > mid) ans += get(u << 1 | 1, mid + 1, r, L, R);
        return ans;
    }
    /**
     *
     *  把id加上x = 1
     * */
    private void update(int u, int l, int r, int id, int x) {
        if (l == id && r == id) {
            sum[u] += x;
            return ;
        }
        int mid = l + r >> 1;
        if (id <= mid) update(u << 1, l, mid, id, x);
        else update(u << 1 | 1, mid + 1, r, id, x);
        push_up(u);
    }
    class PR implements Comparable<PR> {
        public int a, b;

        public PR(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo( PR o) {
            return Integer.compare(this.a, o.a);
        }
    }
    public long countOperationsToEmptyArray(int[] nums) {
        // 1. 找到当前数组最小值的下标i，这个初始可以排序 + pair解决。
        // 2. 找到i此时对应的下标i，可以通过单点查找的方式进行。树状数组，维护当前下标对应的坐标是多少
        // 2. ans += id + 1, 数组[0, i - 1]下标都加上mod - id - 1, [i + 1, n - 1]下标都减去id + 1
        int n = nums.length;
        int mod = n;
        sum = new long[n * 4];
        add = new long[n * 4];
//        build(1, 0, n - 1);
        PR[] pr = new PR[n];
        for (int i = 0 ; i < n; i++) {
            pr[i] = new PR(nums[i], i);
        }
        Arrays.sort(pr);

        long ans = n;
        int pre = 0;
        for (int i = 0; i < n; i++) {
            // 1. 找到当前需要删除的id
            int id = pr[i].b;
            // 2. 计算需要移动的步数
            if (id >= pre) {
                ans += id - pre - get(1, 0, n - 1, pre, id);
            }
            else {
                ans += n - 1 - pre - get(1, 0, n - 1, pre, n - 1) + id + 1 - get(1, 0, n - 1, 0, id);
            }
            update(1, 0, n - 1, id, 1);
            pre = (id + 1) % n;
        }
        return ans;
    }
    public static void main(String[] args) {
        Last1 l = new Last1();
        int[] nums = {2,-15,17,15};
        System.out.println(l.countOperationsToEmptyArray(nums));
    }
}
