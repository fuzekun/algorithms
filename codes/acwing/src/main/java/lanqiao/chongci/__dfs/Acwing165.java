package lanqiao.chongci.__dfs;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 0. 当前使用的缆车数量，当前猫的数量，每一个缆车中放的猫的数量
 * 1. 状态转移，每一只猫可以放在前面的缆车中或者新开一个缆车
 * cur 表示当前的猫的id, cnt表示已经使用了多少缆车了。
 * 放之前的缆车上，可以遍历之前[0, cnt)的每一个缆车，如果可以放下的话，直接放
 * 可以新开一个缆车进行放。
 * 
 * 重要剪枝
 * 1. 如果当前的数量已经大于了ans了，可以直接返回了
 * 2. 优先把重量大的放好。
 * 
 * */
public class Acwing165 {
	private static void dfs(int cur, int cnt) {
		if (cnt >= ans) return ;
		
		if (cur == n) {
			ans = Math.min(ans, cnt);
			return ;
		}
		
//		// 放在前面的篮子里
		for (int i = 0; i < cnt; i++) {
			if (sum[i] + nums[cur] <= w) {
				sum[i] += nums[cur];
				dfs(cur + 1, cnt);
				sum[i] -= nums[cur];
			}
		}
		
//		// 新开一个桶
		sum[cnt] = nums[cur];
		dfs(cur + 1, cnt + 1);
		sum[cnt] = 0;
	}
	private static long[]nums, sum;
	static int n, w;
	private static int ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		w = sc.nextInt();
		nums = new long[n + 1];
		ans = n;
		for (int i = 0; i < n; i++) {
			nums[i] = sc.nextInt(); 
		}
		Arrays.sort(nums);
		for (int i = 0, j = nums.length - 1; i <= j; i++, j--) {
			long tmp = nums[i]; nums[i] = nums[j]; nums[j] = tmp;
		}
		sum = new long[n + 1];
		dfs(0, 1);
		System.out.println(ans);
	}
}
