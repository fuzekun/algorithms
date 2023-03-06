package leetcode.contest.eryue;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author: Zekun Fu
 * @date: 2023/2/26 10:36
 * @Description:
 */
public class leet_contest_334 {
    public int[] leftRigthDifference(int[] nums) {
        int n = nums.length;
        int []leftsum = new int[nums.length];
        leftsum[0] = 0;
        for (int i = 1; i < n; i++) {
            leftsum[i] = leftsum[i - 1] + nums[i - 1];
        }
        int right = 0;
        int[] ans = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            ans[i] = Math.abs(leftsum[i] - right);
            right += nums[i];
        }
        return ans;
    }
    public int[] divisibilityArray(String word, int m) {
        int n = word.length();
        int[] ans = new int[n];
        Arrays.fill(ans, 0);
        int cur = 0;
        for (int i = 0; i < word.length(); i++) {
            int c = word.charAt(i) - '0';
            cur = cur * 10 + c;
            if (cur % m == 0) ans[i] = 1;
            cur = cur % m;
        }
        return ans;
    }
    public int maxNumOfMarkedIndices(int[] nums) {
        // 1. 和顺序没有什么关系
        // 2. 贪心，找恰好大于的第一个
        Arrays.sort(nums);
        int n = nums.length, ans = 0;
        int[] vis = new int[n];
        Arrays.fill(vis, 0);
//        for (int i = 0, j = 0;; i++) {
//            if (vis[i] == 1) break;
//            int x = nums[i] * 2;
//            while (j < n && nums[j] < x) j++;
//            if (j < n) {
//                ans += 2;
//                vis[j] = 1;
//            } else break;
//        }
        System.out.println(Arrays.toString(nums));
        System.out.println(n);
        for (int i = n - 1, j = n - 1; i >= 0 && j >= 0; i--) {
            if (vis[i] == 1) {
                System.out.println(i);
                continue;
            }
            while (j >= 0 && nums[j] * 2 > nums[i]) j--;
            if (j >= 0) {
                ans += 2;
                vis[j] = 1;
                j--;
            }
        }
        return ans;
    }
}
