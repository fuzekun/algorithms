package leetcode.categories.dataStructure.xianduanshu;

import leetcode.utils.Judge;
import leetcode.utils.PrintArrays;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Zekun Fu
 * @date: 2023/2/28 11:04
 * @Description:
 */
public class Leet315 {

    private int[] sum;
    private int maxn;
//    private void build (int u, int l, int r) {
//        if (l > r) throw new IllegalArgumentException(String.format("build中的[l, r] = [%d, %d]", l, r));
//        if (l == r) sum[u] =
//    }
    private void push_up(int u) {
        sum[u] = sum[u << 1] + sum[u << 1 | 1];
    }
    private void update(int u, int l, int r, int p, int x) {
        if (p < l || p > r) throw new IllegalArgumentException(String.format("p = %d 超过了 [l, r] = [%d %d]", p, l, r));
        if (l == r) {
            sum[u] = x;
            return ;
        }
        int mid = l + r >> 1;
        if (p <= mid) update(u << 1, l, mid, p, x);
        else update(u << 1 | 1, mid + 1, r, p, x);
        push_up(u);
    }
    private int query(int u, int l, int r, int L, int R) {
        if (R < L) throw new IllegalArgumentException(String.format("L > R了 [L, R] = [%d, %d]", L, R));
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
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        int maxn =  Integer.MIN_VALUE, minn = Integer.MAX_VALUE;
        for (int x : nums) {
            maxn = Math.max(maxn, x);
            minn = Math.min(minn, x);
        }
//        sum = new int[(maxn - minn + 1) * 4];
////        build(1, minn, maxn);
//        for (int i = nums.length - 1; i >= 0; i--) {
//            if (nums[i] == minn) ans[i] = 0;
//            else ans[i] = query(1, minn, maxn, minn, nums[i] - 1);
//            update(1, minn, maxn, nums[i], 1);                      // 给nums[i] 加上1
//        }
//        return Arrays.stream(ans).boxed().collect(Collectors.toList());
        // 树状数组解法
        int diff = -minn + 1;               // 偏移量映射到[1, ]
        maxn = this.maxn = maxn + diff;
        sum = new int[maxn + 1];
        for (int i = nums.length - 1; i >= 0; i--) {
            int cur = nums[i] + diff;
            ans[i] = csum(cur - 1);
            add(cur, 1);
        }
        return Arrays.stream(ans).boxed().collect(Collectors.toList());
    }

    private int lowbit(int u) {
        return u & -u;
    }
    private void add(int u, int x) {
        if (u <= 0) throw new IllegalArgumentException(String.format("树状数组更新的下标必须 >= 1, 给出的等于 %d ", u));
        for (int i = u; i <= maxn; i += lowbit(i)) {
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

    public static void main(String[] args) {
        Leet315 leet315 = new Leet315();
        int[] nums = {26,78,27,100,33,67,90,23,66,5,38,7,35,23,52,22,83,51,98,69,81,32,78,28,94,13,2,97,3,76,99,51,9,21,84,66,65,36,100,41};
        int[] expect = {10,27,10,35,12,22,28,8,19,2,12,2,9,6,12,5,17,9,19,12,14,6,12,5,12,3,0,10,0,7,8,4,0,0,4,3,2,0,1,0};
        List<Integer>ans = leet315.countSmaller(nums);
        if (Judge.judge1DIntArray(ans.stream().mapToInt(Integer::intValue).toArray(), expect)) {
            System.out.println("成功!");
        }
    }
}
