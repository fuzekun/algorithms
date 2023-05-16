package leetcode.categories.dataStructure.xianduanshu;

import leetcode.utils.ChangeToArrayOrList;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: Zekun Fu
 * @date: 2023/2/28 16:19
 * @Description:更新数组后处理求和查询
 */
public class Leet2569 {
    int[] cnt;
    int[] rev;
    void push_up(int u) {
        cnt[u] = cnt[u << 1] + cnt[u << 1 | 1];
    }
    void build(int u, int l, int r, int[]nums) {
        if (l == r) {
            cnt[u] = nums[l - 1];
            return ;
        }
        int mid = l + r >> 1;
        if (l <= mid) build(u << 1, l, mid, nums);
        if (r > mid) build(u << 1 | 1, mid + 1, r, nums);
        push_up(u);

    }
    void push_down(int u, int l, int r, int x) {
        cnt[u] = (r - l + 1) - cnt[u];                  // 总1 - 原来的1就是
        rev[u] = 1 - rev[u];                            // 反转两次等于不反转
    }
    void push_down(int u, int l, int r) {
        if (rev[u] == 0) return;
        int mid = l + r >> 1;
        push_down(u << 1, l, mid, rev[u]);
        push_down(u << 1 | 1, mid + 1, r, rev[u]);              // 这个应该是子区间的长度
        rev[u] = 0;
    }
    void update(int u, int l, int r, int L, int R) {
        if (L <= l && r <= R) {
            push_down(u, l, r, 1);
            return;
        }
        push_down(u, l, r);
        int mid = l + r >> 1;
        if (L <= mid) update(u << 1, l, mid, L, R);
        if (R > mid) update(u << 1 | 1, mid + 1, r, L, R);
        push_up(u);
    }
    public long[] handleQuery(int[] nums1, int[] nums2, int[][] queries) {
        long sum = Arrays.stream(nums2).mapToLong(Long::valueOf).sum();// 这个应该先转化成long，然后求和
        int n = nums1.length;
        this.cnt = new int[n * 4];
        this.rev = new int[n * 4];
        build(1, 1, n, nums1);
        List<Long> ans = new ArrayList<>();
        for (int[] q : queries) {
            if (q[0] == 1) {
                update(1, 1, n, q[1] + 1, q[2] + 1);
            }
            else if (q[0] == 2) {
                sum += (long)q[1] * cnt[1];
            }
            else {
                ans.add(sum);
            }
        }
        return ans.stream().mapToLong(Long::valueOf).toArray();
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{0,0};
        int[] nums2 = new int[]{0x7fffffff,0x7fffffff };
        int[][] queries = ChangeToArrayOrList.changeTo2DIntArray("[[3,0,0]]");
        Leet2569 leet2569 = new Leet2569();
        long[] ans = leet2569.handleQuery(nums1, nums2, queries);
        System.out.println(Arrays.toString(ans));
    }
}
