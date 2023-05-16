package lanqiao.chongci.queue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import utils.InAndOutUitl;

/**
 * 
 * 
 * 最大子序列和
 * 单调队列优化dp
 * f[i] : 表示i为右端点，且长度不超过m的连续子区间和的最大值
 * f[i] = max(si - sj) (1 =< i - j <= m)
 * f[i] = si - min(sj) (1 =< i - j <= m)
 * 所以对于每一个i，需要维护一个大小不超过m的滑动窗口，求最小值
 * */
public class Pre_sum_max {
	public static void main(String[] args) throws Exception{
		InAndOutUitl util = new InAndOutUitl();
		int n = util.nextInt();
		int m = util.nextInt();
		int[] nums = new int[n];
		for (int i = 0; i < n; i++) nums[i] = util.nextInt();
		Deque<Integer>que = new ArrayDeque<>();
		int[] sum = new int[n + 1];
		
		for (int i = 0; i < n; i++) {
			sum[i + 1] = sum[i] + nums[i]; 
		}
		
		int[] f = new int[n + 1];
		Arrays.fill(f, Integer.MIN_VALUE);

		que.add(0);
		for (int i = 1; i <= n; i++) {
			// 过期 [j, i)i - j <= m
			while (!que.isEmpty() && i - que.peekFirst() > m) que.pollFirst();
			
			// 求解, 注意，此时一定有值在队列中
			f[i] = Math.max(f[i - 1], sum[i] - sum[que.peekFirst()]);
			

			// 不满足单调
			while(!que.isEmpty() && sum[que.peekLast()] >= sum[i]) que.pollLast();
			
			que.add(i);
		}
		
		util.write(f[n] + "");
		util.flush();
	}
}
