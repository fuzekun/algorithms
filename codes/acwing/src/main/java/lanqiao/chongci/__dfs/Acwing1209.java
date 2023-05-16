package lanqiao.chongci.__dfs;

import java.util.Arrays;
import java.util.Scanner;

import utils.InAndOutUitl;

/**
 * 拿着三百块钱丢着玩？真的有意思。
 * 1. 直接进行dfs，确定三个划分点
 * 2. 
 * */

public class Acwing1209 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();

//		int ans = 0;
//		for (int i = 1; i <= n; i++) {
//			Arrays.fill(vis, 0);
//			int x = i;
//			int flag = 1;
//			while (x != 0) {
//				if (vis[i % 10] == 1) {
//					flag = 0;
//					break;
//				}
//				vis[i % 10] = 1;
//				x /= 10;
//			}
//			if (flag == 1) ans += dfs(0, 0, i);
//		}
		int ans = dfs(0);
		System.out.println(ans);
	}
	private static int n;
	private static int[] vis = new int[10];
//	private static int dfs(int cur, int up, int sum) {
//		if (cur == 9) {
//			int down = 0;
//			for (int i = 0; i < )
//			return sum * n + up 
//		}
//		int ans = 0;
//		for (int i = 1; i <= 9; i++) {
//			if (vis[i] == 0) {
//				vis[i] = 1;
//				ans += dfs(cur + 1, sum * 10 + i, up, down);
//				vis[i] = 0;
//			}
//		}
//		return ans;
//	}
	private static int cal(int i, int j) {
		int ans = 0;
		while (i <= j) ans = ans * 10 + nums[i++];
		return ans;
	}
	private static int[] nums = new int[9];
	private static int dfs(int cur) {
		if (cur == 9) {
			int ans = 0;
			for (int i = 0; i < 7; i++) {
				for (int j = i + 1; j < 8; j++) {
					int a = cal(0, i);
					int b = cal(i + 1, j);
					int c = cal(j + 1, 8);
					
					if (a * c + b == c * n) ans++;
				}
			}
			return ans;
		}
		
		int ans = 0;
		for (int i = 1; i <= 9; i ++) {
			if (vis[i] == 0) {
				vis[i] = 1;
				nums[cur] = i;
				ans += dfs(cur + 1);
				vis[i] = 0;
			}
		}
		return ans;
	}
}
