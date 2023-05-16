package leetcode.categories.dataStructure.xianduanshu;

import leetcode.utils.ChangeToArrayOrList;

/**
 * @author: Zekun Fu
 * @date: 2023/2/28 10:24
 * @Description:
 * 单点修改，区间求和模板，可以使用线段树
 */
public class Leet_307 {

    private int[] sum;
    private int n;
    public Leet_307(int[] nums) {
        this.n = nums.length;
        init(n);
//        build(1, 1, n, nums);
        build(nums);
    }

    public void update(int index, int val) {
//        update(1, 1, n, index + 1, val);
        int pre = csum(index + 1) - csum(index);
        int diff = val - pre;
        add(index + 1, diff);
    }

    public int sumRange(int left, int right) {
//        return query(1, 1, n, left + 1, right + 1);
        return csum(right + 1) - csum(left);            // [left, right] = sum[right] - sum[left - 1] == > sum[right + 1] - sum[left + 1 - 1]
    }

    private void init(int n) {
        this.sum = new int[n * 4];
    }
    private void push_up(int u) {
        sum[u] = sum[u << 1] + sum[u << 1 | 1];
    }
    private int lowbit(int u) {
        return u & -u;
    }
    private void build(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            add(i + 1, nums[i]);
        }
    }
    private void add(int u, int x) {
        if (u <= 0) throw new IllegalArgumentException(String.format("树状数组更新的下标必须 >= 1, 给出的等于 %d ", u));
        for (int i = u; i <= n; i += lowbit(i)) {
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

    private void build(int u, int l, int r, int[] nums) {
        if (l == r) {
            sum[u] = nums[l - 1];
            return ;
        }
        int mid = l + r >> 1;
        build(u << 1, l, mid, nums);
        build(u << 1 | 1, mid + 1, r, nums);
        push_up(u);
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

    public static void main(String[] args) {
        String[] op = {"NumArray", "sumRange", "update", "sumRange"};
        int[] nums = new int[]{1, 3, 5};
        String ss = "[[0, 2], [1, 2], [0, 2]]";
        int[][] intervals = ChangeToArrayOrList.changeTo2DIntArray(ss);
        Leet_307 leet_307 = null;
        int i = 0;
        for (String s : op) {
            if (s.equals("NumArray")) {
                leet_307 = new Leet_307(nums);
                System.out.println("null");
            }
            else if (s.equals("sumRange")) {
                int l = intervals[i][0], r = intervals[i][1];
                System.out.println(leet_307.sumRange(l, r));
                i++;
            }
            else {
                int l = intervals[i][0], r = intervals[i][1];
               leet_307.update(l,r);
                System.out.println("null");
               i++;
            }
        }
    }

}
