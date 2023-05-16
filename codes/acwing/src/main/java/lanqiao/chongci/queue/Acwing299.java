package lanqiao.chongci.queue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;

import utils.InAndOutUitl;

/**
 * 
 * 
 * 裁剪序列
 * 
 * eclipse的使用技巧
 * 除了enter以外的插入触发器禁止掉。就不会因为空格触发插入触发器了。
 * */
public class Acwing299 {
	public static void main(String[] args) throws Exception {
		InAndOutUitl util = new InAndOutUitl();
		int n = util.nextLong().intValue();
		long m = util.nextLong();
		int[] nums = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			nums[i] = util.nextInt();
		}
		// 表示以i结尾的最小值是多少
		long[] f = new long[n + 1];
		Arrays.fill(f, 0x3f3f3f3f);
//		Deque<Integer>que = new ArrayDeque<>();
//		que.add(0);
		f[0] = 0;
		for (int i = 1; i <= n; i++) {

			// 求当前段的最大值, 在和小于m的情况下, 最少为当前段
			long sum = nums[i], maxv = nums[i];
			for (int j = i - 1; j >= 0 && sum <= m; j--) {
				f[i] = Math.min(f[j] + maxv, f[i]);
				maxv = Math.max(nums[j], maxv);
				sum += nums[j];
			}
		}
		util.write(f[n] + "");
		util.flush();
	}
}
