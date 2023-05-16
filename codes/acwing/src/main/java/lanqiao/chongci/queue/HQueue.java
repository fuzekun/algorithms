package lanqiao.chongci.queue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Queue;
import java.util.Scanner;
import java.util.Vector;

import utils.InAndOutUitl;

public class HQueue {
	/**
	 * 
	 * 滑动窗口最大值和最小值
	 * */
	public static void main(String[] args) throws Exception{
		InAndOutUitl util = new InAndOutUitl();
		int n = util.nextInt();
		int m = util.nextInt();
		Deque<Integer>minq = new ArrayDeque<Integer>();
		Deque<Integer>maxq = new ArrayDeque<>();
		int[] nums = new int[n];
		for (int i = 0; i < n; i++) {
			nums[i] = util.nextInt();
		}
		
		int[] ans = new int[n];
		for (int i = 0; i < n; i++) { 
			// 过期
			while (!minq.isEmpty() && i - minq.peekFirst() + 1 > m) minq.pollFirst();
			while (!maxq.isEmpty() && i - maxq.peekFirst() + 1 > m) maxq.pollFirst();
			// 违反单调
			while (!minq.isEmpty() && nums[i] <= nums[minq.peekLast()]) minq.pollLast();
			while (!maxq.isEmpty() && nums[i] >= nums[maxq.peekLast()]) maxq.pollLast();
			
			minq.add(i);
			maxq.add(i);
			
			if (i >= m - 1) {
				ans[i] = nums[maxq.peekFirst()];
				util.write(nums[minq.peekFirst()] + " ");
			}
		}
		util.write("\n");
		for (int i = m - 1; i < n; i++) {
			util.write(ans[i] + " "); 
		}
		util.write("\n");
		util.flush();
	}
}
