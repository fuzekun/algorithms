package lanqiao.chongci.dp;

import java.util.Scanner;

/**
 * 
 * 
 * 能量项链
 * dp[l][r]：表示区间为[l, r]可以释放的最大总能量
 * dp[l][r] = dp[l][k] + dp[k + 1][r] + nums[l] * nums[k + 1] * nums[r]这种。
 * 
 * 技巧：
 * 环拆链复制
 * 直接遍历长度为n的dp求最大值
 * 
 * */
public class Acwing320 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] nums = new int[2 * n];
		for (int i = 0; i < n; i++) {
			nums[i] = sc.nextInt();
			nums[i + n] = nums[i];
		}
		
		
		long ans = 0;
		long[][] dp = new long[2 * n][2 * n];
		for (int len = 2; len <= n; len++) {
			// 长度最少是2，j <= 2n - 2; 所以 j < 2n - 1;
			for (int i = 0; i < 2 * n - len; i++) {	// j = i + len - 1 < 2 * n - 1， i < 2n - len
				int j = i + len - 1;		// [i, j]， 
				for (int k = i; k < j; k++) {	// [i, k], [k + 1, j] k [i, j)
					dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k + 1][j] + nums[i] * nums[k + 1] * nums[j + 1]);
				}
				if (len == n) ans = Math.max(ans, dp[i][j]);
			}
		}
		
		System.out.println(ans);
	}
}
