package dp;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author: Zekun Fu
 * @date: 2022/9/21 11:02
 * @Description:
 * 状态压缩dp问题之：桶放球问题。
 */
public class leet698 {

    private boolean dfs(int[] bucket, int cur, int[]nums, int n, int k, int s) {
        if (cur == n) {
            return true;
        }

        for (int i = 0; i < k; i++) {
            // 剪枝1
            if (bucket[i] + nums[cur] > s) continue;
            bucket[i] += nums[cur];
            if (dfs(bucket, cur + 1, nums, n, k, s)) return true;
            bucket[i] -= nums[cur];
            // 剪枝2
            while (i + 1 < k && bucket[i + 1] == bucket[i]) i ++;
        }
        return false;
    }
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int n = nums.length;
        int s = 0;
        for (int i = 0; i < n; i++) {
            s += nums[i];
        }
        if (s % k != 0) return false;
        s /= k;
        Arrays.sort(nums);
        for (int i = 0, j = n - 1; i < j; i++, j--) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
        int[] bucket = new int[k];
        return dfs(bucket, 0, nums, n, k, s);
    }
    public static void main(String[] args) {
        leet698 l = new leet698();
        int[] nums = {1,2,3,4};
        System.out.println(l.canPartitionKSubsets(nums, 3));
    }
}
