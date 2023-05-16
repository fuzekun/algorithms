package lanqiao.chongci.dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * 加分二叉树
 * 
 * */
public class Acwing479 {
	
	private static int[][] dp;
	private static int[] nums;
	private static int[][] g;
	/**
	 * 
	 * [l, r]
	 * [l, i - 1] 是左子树, [i + 1, r]是右子树
	 * 
	 * */
	private static int dfs(int l, int r) {
		// 叶子结点的分数就是本身
		if (r == l) {
			return dp[l][r] = nums[l];
		}
		int ans = dp[l][r];
		if (ans != -1) return ans;
		
		for (int i = l; i <= r; i++) {
			int lv, rv;
			// 如果左右子树有一个为空，那么dj = 1
			if (i == l) lv = 1;
			else lv = dfs(l, i - 1);
			if (i == r) rv = 1;
			else rv = dfs(i + 1, r);
			if (ans < lv * rv + nums[i]) {
				ans = lv * rv + nums[i];
				g[l][r] = i;
			}
		}
		return dp[l][r] = ans;
	}
	/**
	 * 
	 * 下标从0开始，所以需要加上1表示结点
	 * */
	private static void print(int l, int r) {
		if (l == r) {
			System.out.print((l + 1) + " ");
			return ;
		}
		// 如果l作为根最大的话
		if (dp[l][r] == dp[l + 1][r] + nums[l]) {
			// 输出根和右子树
			System.out.print((l + 1) + " ");
			print(l + 1, r);
			// 输出一次就行了
			return ;
		}
		// 如果中间的结点作为根最大
		for (int i = l + 1; i < r; i++) {
			if (dp[l][i - 1] * dp[i + 1][r] + nums[i] == dp[l][r]) {
				// 输出根结点和左右子树
				System.out.print((i + 1) + " ");
				print(l, i - 1);
				print(i + 1, r);
				// 输出字典序最小的方案
				return ;
			}
		}
		// 如果最右边的最大
		if (dp[l][r] == dp[l][r - 1] + nums[r]) {
			// 输出根和左子树
			System.out.print((r + 1) + " ");
			print(l, r - 1);
		}
	}
	private static void print2(int l, int r) {
		if (l == r) {
			System.out.print((l + 1) + " ");
			return ;
		}
		int k = g[l][r];
		System.out.print((k + 1) + " ");
		if (l < k) print2(l, k - 1);
		if (r > k) print2(k + 1, r);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		nums = new int[n];
		dp = new int[n][n];
		g = new int[n][n];
		for (int i = 0; i < n; i++) Arrays.fill(dp[i], -1);
		for (int i = 0; i < n; i++) nums[i] = sc.nextInt();
		// 空树的情况
		if (n == 0) {
			System.out.println(1);
			System.out.println();
			return ;
		}
		System.out.println(dfs(0, n - 1));
		print2(0, n - 1);
	}
}
