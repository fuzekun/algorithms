package lanqiao.chongci.dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * 
 * 石子合并
 * 
 * 区间dp的问题，使用dfs进行解决
 * 
 * 时间复杂度O(n ^ 3)
 * 状态n ^ 2种，转移n - 1种
 * 
 * */




public class Acwing282 {
	
	private static int[] nums;
	private static int[] sum;
	private static int[][] dp;
	/**
	 * 
	 * 	表示从[l, r)合并的最大的val是多少
	 * 1. 如果 r == l + 1可以直接计算
	 * 2. 枚举划分的点，划分成两堆[l, i) [i, r) i应该[l + 1, r)
	 * 3. [l ,r) -> (l, r]
	 * */
	private static int dfs(int l, int r) {
		// 合并一堆石子的代价为0
		if (r == l + 1) {
			return 0;
		}
		int ans = dp[l][r];
		if (ans != -1) return ans;
		
		ans = Integer.MAX_VALUE;
		for (int i = l + 1; i < r; i++) {
			ans = Math.min(ans, dfs(l, i) + dfs(i, r));
		}
		dp[l][r] = ans + sum[r] - sum[l];
		return dp[l][r];
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		nums = new int[n];
		sum = new int[n + 1];
		dp = new int[n + 1][n + 1];
		for (int i = 0; i < n + 1; i++) Arrays.fill(dp[i], -1);
		for (int i = 0; i < n; i++) {
			nums[i] = sc.nextInt();
			sum[i + 1] = sum[i] + nums[i];
		}
		System.out.println(dfs(0, n));
	}
}
