package lanqiao.chongci.dp;

import java.util.Scanner;
//1:无需package
//2: 类名必须Main, 不可修改

public class Shuzi {
	private static int[][] dp;
	private static int[][] nums;
	private static int minv = -0x3f3f3f3f;
	public static void main(String[] args) {
	     Scanner sc = new Scanner(System.in);
	     int n = sc.nextInt();
	     dp = new int[n + 1][n + 1];
	     nums = new int[n][n];
	     for (int i = 0; i < n; i++) {
	    	 for (int j = 0; j <= i; j++) {
	    		 nums[i][j] = sc.nextInt();
	    	 }
	     }
	     System.out.println(dfs(0, 0));
	     sc.close();
	 }

	 private static int dfs(int x, int y) {

		 
		 if (x == nums.length - 1) {		// 到底部了
//			 if (x - y > 1) return minv;	// 向下走的次数大比向右走的次数多了一次以上
//			 if (Math.abs(x - y) > 2) return minv;
			 if (Math.abs(x - 2 * y) > 1) return minv;
//			 System.out.println(y);
			 return nums[x][y];
		 }
		 int ans = dp[x][y];
		 if (ans != 0) return ans;
		
		 dp[x][y] = ans = Math.max(dfs(x + 1, y + 1), dfs(x + 1, y)) + nums[x][y];
		 
		 return ans;
	 }
}