package leetcode.categories.dataStructure.xianduanshu;

import leetcode.utils.ReadData;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author: Zekun Fu
 * @date: 2023/2/28 15:02
 * @Description:
 *
 * 线段树，动态开点超时了。
 * 使用离散化 + 动态开点，还是超时
 */
public class leet493_2 {
    HashMap<Long, Integer>mp;
    private int[] sum, lc, rc;
    private int idx = 1;
    // 动态开点超时.
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
    // 经过离散化 + 不用动态开点的线段树。
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
    public int reversePairs(int[] nums) {
        mp = new HashMap<>();
        int n = nums.length;
        long[] allnums = new long[n * 2];
        for (int i = 0, j = 0; i < n; i++, j += 2) {
            allnums[j] = nums[i];
            allnums[j + 1] = (long)nums[i] * 2;
        }
        Arrays.sort(allnums);
        int id = 0;
        for (long x : allnums) {
            if (mp.containsKey(x)) continue;
            mp.put(x, ++id);
        }
        int ans = 0;
        this.sum = new int[id * 4];
        for (int i = 0; i < n; i++) {
            long pre = (long)nums[i] * 2, cur = nums[i];
            if (!mp.containsKey(pre)) throw new IllegalArgumentException("pre没有被离散化到!, 爆int了");
            if (mp.get(pre) + 1 <= id)
                ans += query(1,1, id, mp.get(pre) + 1, id);
            update(1, 1, id, mp.get(cur), 1);
        }
        return ans;
    }
//    public int reversePairs(int[] nums) {
//        int n = nums.length;
//        long[] allnums = new long[n * 2];
//        for (int i = 0, j = 0; i < n; i++, j += 2) {
//            allnums[j] = nums[i];
//            allnums[j + 1] = (long)nums[i] * 2;
//        }
//        long maxv = Arrays.stream(allnums).max().getAsLong();
//        long minv = Arrays.stream(allnums).min().getAsLong();
//        int ans = 0;
//        for (int i = 0; i < n; i++) {
//            long pre = (long)nums[i] * 2, cur = nums[i];
//            if (pre + 1 <= maxv)
//                ans += query(1,minv, maxv, pre + 1, maxv);
//            update(1, minv, maxv, cur, 1);
//        }
//        return ans;
//    }

    public static void main(String[] args) throws Exception{
//        int[] nums = {1,3,2,3,1};
//        int[] nums = {2147483647,2147483647,-2147483647,-2147483647,-2147483647,2147483647};
//        int[] nums = {2147483647,2147483647,2147483647,2147483647,2147483647,2147483647};           // nums[i] * 2 大于了最大值，应该使用long强转
        int[] nums = ReadData.getArray();
        System.out.println("数组长度为 : " + nums.length);
        leet493_2 leet493 = new leet493_2();
        int ans = leet493.reversePairs(nums);
        System.out.println(ans);
    }
}
