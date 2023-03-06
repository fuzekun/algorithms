package leetcode.categories.dataStructure.xianduanshu;

import leetcode.utils.ReadData;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author: Zekun Fu
 * @date: 2023/2/28 15:42
 * @Description: 区间和的个数
 *
 * 离散化 + 树状数组
 */
public class leet327 {


    private int idx = 1;
    private int[] sum, lc, rc;
    HashMap<Long, Integer> mp;
    private int lowbit(int u) {
        return u & -u;
    }
    private void add(int u, int x) {
        if (u <= 0) throw new IllegalArgumentException(String.format("树状数组更新的下标必须 >= 1, 给出的等于 %d ", u));
        for (int i = u; i <= idx; i += lowbit(i)) {
            sum[i] += x;
        }
    }
    private int csum(int x) {
        if (x < 0) throw new IllegalArgumentException(String.format("树状数组的求和下标必须 >= 0, 给出的等于 %d ", x));
        int res = 0;
        for (int i = x; i > 0; i -= lowbit(i)) {
            res += sum[i];
        }
        return res;
    }

    public int countRangeSum(int[] nums, int lower, int upper) {

        mp = new HashMap<>();
        int n = nums.length;
        long[] sum = new long[n + 1];
        sum[0] = 0;
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        long[] allnums = new long[(n + 1) * 3];
        for (int i = 0, j = 0; i <= n; i++, j += 3) {
            allnums[j] = sum[i];
            allnums[j + 1] = sum[i] - lower;
            allnums[j + 2] = sum[i] - upper;
        }
        Arrays.sort(allnums);
        idx = 0;
        for (long x : allnums) {
            if (mp.containsKey(x)) continue;
            mp.put(x, ++idx);
        }
        this.sum = new int[idx * 4];
        int ans = 0;
        for (int i = 0; i <= n; i++) {
            long l = sum[i] - upper, r = sum[i] - lower;
            if (!mp.containsKey(l) || !mp.containsKey(r) || !mp.containsKey(sum[i])) throw new IllegalArgumentException("l 或者 r 或者 sum[i] 没有被离散化到!, 爆int了");
            ans += query(1, 1, idx, mp.get(l), mp.get(r));        // [l, r]
            update(1, 1, idx, mp.get(sum[i]), 1);
//            ans += csum(mp.get(r)) - csum(mp.get(l) - 1);           // sum[l, r] - sum[r] - sum[l - 1]
//            add(mp.get(sum[i]), 1);
        }
        return ans;
    }
    // 超时的离散化
//    public int countRangeSum(int[] nums, int lower, int upper) {
//
//        mp = new HashMap<>();
//        int n = nums.length;
//        long[] sum = new long[n + 1];
//        sum[0] = 0;
//        for (int i = 0; i < n; i++) {
//            sum[i + 1] = sum[i] + nums[i];
//        }
//        long[] allnums = new long[(n + 1) * 3];
//        for (int i = 0, j = 0; i <= n; i++, j += 3) {
//            allnums[j] = sum[i];
//            allnums[j + 1] = sum[i] - lower;
//            allnums[j + 2] = sum[i] - upper;
//        }
//        Arrays.sort(allnums);
//        int id = 0;
//        for (long x : allnums) {
//            if (mp.containsKey(x)) continue;
//            mp.put(x, ++id);
//        }
//        this.sum = new int[id * 4];
//        this.lc = new int[id * 4];
//        this.rc = new int[id * 4];
//        int ans = 0;
//        for (int i = 0; i <= n; i++) {
//            long l = sum[i] - upper, r = sum[i] - lower;
//            if (!mp.containsKey(l) || !mp.containsKey(r) || !mp.containsKey(sum[i])) throw new IllegalArgumentException("l 或者 r 或者 sum[i] 没有被离散化到!, 爆int了");
//            ans += query(1, 1, id, mp.get(l), mp.get(r));        // [l, r]
//            update(1, 1, id, mp.get(sum[i]), 1);
////            ans += csum(mp.get(r)) - csum(mp.get(l) - 1);           // sum[l, r] - sum[r] - sum[l - 1]
////            add(mp.get(sum[i]), 1);
//        }
//        return ans;
//    }
//    private void push_up(int u) {
//        sum[u] = sum[lc[u]] + sum[rc[u]];
//    }
//    private void update(int u, long l, long r, long p, long x) {
//        if (p < l || p > r) throw new IllegalArgumentException(String.format("p = %d, [l, r] = [%d, %d]", p, l, r));
//        if (l == r) {
//            sum[u] += x;
//            return ;
//        }
//        long mid = l + r >> 1;
//        if (p <= mid) {
//            if (lc[u] == 0) lc[u] = ++idx;
//            update(lc[u], l, mid, p, x);
//        }
//        else {
//            if (rc[u] == 0) rc[u] = ++idx;
//            update(rc[u], mid + 1, r, p, x);
//        }
//        push_up(u);
//    }
//    private int query(int u, long l, long r, long L, long R) {
//        if (u == 0) return 0;
//        if (R < l || L > r) throw new IllegalArgumentException(String.format("[L, R] = [%d, %d], [l, r] = [%d, %d]", L, R, l, r));
//        if (l == r) {
//            return sum[u];
//        }
//        long mid = l + r >> 1;
//        int ans = 0;
//        if (L <= mid)
//            ans += query(lc[u], l, mid, L, R);
//        if (R > mid)
//            ans += query(rc[u], mid + 1, r, L, R);
//        return ans;
//    }
    private void push_up(int u) {
        sum[u] = sum[u << 1] + sum[u << 1 | 1];
    }
    private void update(int u, int l, int r, int p, int x) {
        if (p < l || p > r) throw new IllegalArgumentException(String.format("p = %d 超过了 [l, r] = [%d %d]", p, l, r));
        if (l == r) {
            sum[u] += x;
            return ;
        }
        int mid = l + r >> 1;
        if (p <= mid) update(u << 1, l, mid, p, x);
        else update(u << 1 | 1, mid + 1, r, p, x);
        push_up(u);
    }
    private int query(int u, int l, int r, int L, int R) {
        if (L > r || R < l) throw new IllegalArgumentException(String.format("[l, r] = [%d %d] 和 [L, R] = %d 没有交集", l, r, L, R));
        if (L <= l && r <= R) {
            return sum[u];
        }
        int mid = l + r >> 1;
        int ans = 0;
        if (L <= mid) ans = query(u << 1, l, mid, L, R);
        if (R > mid) ans += query(u << 1 | 1, mid + 1, r, L, R);
        return ans;
    }

    public static void main(String[] args) throws Exception{
//        int[] nums = {-2,5,-1};
        leet327 leet493 = new leet327();
        int[] nums = ReadData.getArray();
        int ans = leet493.countRangeSum(nums, -5733, 87456);
        System.out.println(ans);
    }
}
