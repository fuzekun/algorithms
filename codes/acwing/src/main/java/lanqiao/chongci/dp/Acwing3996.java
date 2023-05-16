package lanqiao.chongci.dp;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 * 1. 先找到所有的联通快
 * 2. 遍历所有的联通快，以他作为起点
 * 3. 每种有两个操作，选前面或者后面
 * 
 * */


public class Acwing3996 {
	static int n;
	static int[] nums;
	static int m;
	static int[] color;
//	static Map<Integer, Integer>pre;	// 当前连通集合中前一个结点所在的位置
//	static Map<Integer, Integer>after;	// 当前联通集合中后一个结点所在的位置
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		nums = new int[n + 1];
		color = new int[n + 1];
		m = 0;
		for (int i = 0; i < n; i++) {
			nums[i] = sc.nextInt();
			if (i == 0 || nums[i] != nums[i - 1]) color[m++] = nums[i];
		}
		
//		for (int i = 0; i < m; i++) {
////			dfs(i, i, color[i], 0);
//		}
		dp = new int[m + 1][m + 1];
		for (int i = 0; i < m; i++) Arrays.fill(dp[i], -1);
		ans = dfs(0, m - 1);
		System.out.println(ans);
	}
	private static int[][] dp;
	private static int dfs(int l, int r) {
		if (l >= r) {
			return 0;
		}
		if (dp[l][r] >= 0) return dp[l][r];
		dp[l][r] = 0x3f3f3f3f;
		if (color[l] == color[r]) {
			return dp[l][r] = dfs(l + 1, r - 1) + 1;
		}
		return dp[l][r] = Math.min(dfs(l + 1, r) + 1, dfs(l, r - 1) + 1);
	}
	
	/**
	 * 
	 * 计算[l, r]最少需要的次数
	 * */

	/**
	 * 当前连通块的区间范围
	 * 当前连通块的颜色
	 * [l, r]
	 * */
	static int ans = Integer.MAX_VALUE;
	private static void dfs(int l, int r, int c, int cnt) {
		
		// 变成前一种颜色，或者变成后一种颜色
		// 找到前一个颜色所在的位置, 后一个颜色所在的位置。这个可以使用双向链表进行保存
		
		// 看后面有几种颜色，就需要变换几次
		if (l == 0) {
			cnt += m - r - 1;
			ans = Math.min(ans, cnt);
			return ;
//			return n - r - 1;		//(r, n)
		}
		// 同理
		if (r == m - 1) {
			cnt += l;
			ans = Math.min(ans, cnt);
			return ;
//			return l;				// [0, l)
		}
		
//		int ans = Integer.MAX_VALUE;
		// 变换成前一种颜色
//		ans = Math.min(ans, dfs(l - 1, r, color[l - 1]));
		// 变换成后一种颜色
		
		dfs(l - 1, r, color[l - 1], cnt + (color[l - 1] == c ? 0 : 1));
		dfs(l, r + 1, color[r + 1], cnt + (color[r + 1] == c ? 0 : 1));
	}
}
