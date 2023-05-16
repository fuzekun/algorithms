package leetcode.categories.dataStructure.xianduanshu;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author: Zekun Fu
 * @date: 2023/2/21 9:21
 * @Description:
 * 反转对
 * 线段树: 单点修改，区间求和
 * 树状数组 +  离散化
 */
public class Leet493 {

    private int idx;
    private int[] sum;
    HashMap<Long, Integer>mp;
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

    public int reversePairs(int[] nums) {
        mp = new HashMap<>();
        int n = nums.length;
        long[] allnums = new long[n * 2];
        for (int i = 0, j = 0; i < n; i++, j += 2) {
            allnums[j] = nums[i];
            allnums[j + 1] = (long)nums[i] * 2;
        }
        Arrays.sort(allnums);
        for (long x : allnums) {
            if (mp.containsKey(x)) continue;
            mp.put(x, ++idx);
        }
        this.sum = new int[idx + 1];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            long pre = (long)nums[i] * 2, cur = nums[i];
            if (!mp.containsKey(pre)) throw new IllegalArgumentException("pre没有被离散化到!, 爆int了");
            ans += csum(idx) - csum(mp.get(pre));
            add(mp.get(cur), 1);
        }
        return ans;
    }

    public static void main(String[] args) {
//        int[] nums = {2147483647,2147483647,-2147483647,-2147483647,-2147483647,2147483647};
        int[] nums = {2147483647,2147483647,2147483647,2147483647,2147483647,2147483647};           // nums[i] * 2 大于了最大值，应该使用long强转
        Leet493 leet493 = new Leet493();
        int ans = leet493.reversePairs(nums);
        System.out.println(ans);
    }
}
