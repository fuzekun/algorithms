package lanqiao.chongci.Hash;

import java.util.Arrays;

import utils.InAndOutUitl;

/**
 * 
 * 
 * 模拟散列，使用再hash的方式进行求解
 * 这个是hashSet。nums中存储的是key，不是entry
 * 所以应该让nums[find(x)] = x；找的时候让nums[t] != x的继续向下
 * 
 * 使用的时候
 * 1. nums[find(x)] != NULL;  //表示存在 
 * 2. nums[find(x)] = x;		      // 使用x表示x
 * */
public class Acwing840 {
	
	private static final int maxn = (int)2e5 + 5;
	private static final int NULL = 0x3f3f3f3f; 
	private static int[] nums = new int[maxn];
	public static void main(String[] args) throws Exception{
		InAndOutUitl util = new InAndOutUitl();
		Arrays.fill(nums, NULL);
		int n = util.nextInt();
		for (int i = 0; i < n; i++) {
			String[] input = util.nextLine().split(" ");
			int x = Integer.parseInt(input[1]);
			if ("I".equals(input[0])) nums[find(x)] = x;
			else {
				if (nums[find(x)] != NULL) util.write("Yes\n");
				else util.write("No\n");
			}
		}
		util.flush();
	}
	/**
	 * 这个是hashset。存储的是key。
	 * 如果是hashMap,存储的应该是entry
	 * 开放寻址法求hash。
	 * 1. 如果是是插入，直接找到第一个为空的，不可能为x。
	 * 2. 如果是查找，找到第一个为x的，表示成功，找到第一个为NULL的表示失败。
	 * */
	private static int find(int x) {
		// 找到地址
		int t = (x % maxn + maxn) % maxn;
		// 向后查找, nums中存储的是key。所以需要判断nums[
		while (nums[t] != NULL && nums[t] != x) {
			t++;
			if (t == maxn) t = 0;
		}
		return t;
	}
}
