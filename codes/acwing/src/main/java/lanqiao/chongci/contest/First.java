package lanqiao.chongci.contest;

import java.security.AccessControlContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class First {
	public int findTheLongestBalancedSubstring(String s) {
		int n = s.length();
		String ans = "";
		for (int len = 0; len <= n / 2; len++) {
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < len; i++) builder.append("0");
			for (int i = 0; i < len; i++) builder.append("1");
			if (s.contains(builder.toString())) ans = builder.toString();
		}
		return ans.length();
    }
	public List<List<Integer>> findMatrix(int[] nums) {
		List<List<Integer>>ans = new ArrayList<>();
		// 每次扫描一遍数组，如果还有没有用的数字，并且和已经放入不冲突的话，直接放入
		int n = nums.length;
		int[] vis = new int[n];
		while (true) {
			Set<Integer>tmp = new HashSet<>();
			for (int i = 0; i < n; i++) {
				if (vis[i] == 1 || tmp.contains(nums[i])) continue;
				vis[i] = 1; 
				tmp.add(nums[i]);
			}
			tmp.stream().forEach(System.out::println);
			System.out.println();
			if (tmp.size() == 0) break;
			ans.add(tmp.stream().collect(Collectors.toList()));
		}
		return ans;
    }
	private int[] reward1, reward2;
	private int[][] dp;
	private int minv = -0x3f3f3f3f;
	private int dfs(int cur, int k) {
		if (cur == reward1.length) {
			if (k != 0) return minv;
			return 0;
		}
		if (dp[cur][k] != 0) return dp[cur][k];
		if (k == 0) return dp[cur][k] = dfs(cur + 1, k) + reward2[cur];

		int t1 = dfs(cur + 1, k - 1) + reward1[cur];
		int t2 = dfs(cur + 1, k) + reward2[cur];
		dp[cur][k] = Math.max(t1, t2); 
		return dp[cur][k];
	}
	class PR implements Comparable<PR>{
		int val;
		int id;
		// 反向排序
		@Override
		public int compareTo(PR o) {
			// TODO Auto-generated method stub
			return Integer.compare(o.val, this.val);
		}
		public PR(int val, int id) {
			this.id = id;
			this.val = val;
		}
	}
	public int miceAndCheese(int[] reward1, int[] reward2, int k) {
		int p1 = 0, p2 = 0;
		int n = reward1.length;
		PR[] nums1 = new PR[n];
		PR[] nums2 = new PR[n];
		for (int i = 0; i < n; i++) {
			nums1[i] = new PR(reward1[i], i);
			nums2[i] = new PR(reward2[i], i); 
		}
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		int[] vis = new int[n];
		int ans = 0;
		int cnt1 = 0, cnt2 = 0;
		while (cnt1 < k && cnt2 < n - k && p1 < n && p2 < n) {
			while (p1 < n && vis[nums1[p1].id] == 1) p1++;
			while (p2 < n && vis[nums2[p2].id] == 1) p2++;
			if (p1 >= n || p2 >= n) break;
			int maxv = Math.max(nums1[p1].val, nums2[p2].val);
			if (maxv == nums1[p1].val) {
				vis[nums1[p1].id] = 1;
				cnt1++;
				p1++;
			}
			else {
				vis[nums2[p2].id] = 1;
				cnt2++;
				p2++;
			}
			ans += maxv;
		}
		while (p1 < n && cnt1 < k) {
			if (vis[nums1[p1].id] == 0) {
				ans += nums1[p1].val;
				cnt1++;
			}
			p1++;
		}
		while (p2 < n && cnt2 < n - k) {
			if (vis[nums2[p2].id] == 0) {
				ans += nums2[p2].val;
				cnt2++;
			}
			p2++;
		}
		return ans;
	}
	public static void main(String[] args) {
		First tFirst = new First();
//		int ans = tFirst.findTheLongestBalancedSubstring("00111");
//		System.out.println(ans);
		int ans = tFirst.miceAndCheese(new int[] {1,1,3,4}, new int[] {4,4,8,8}, 2);
		System.out.println(ans);
	}
}
