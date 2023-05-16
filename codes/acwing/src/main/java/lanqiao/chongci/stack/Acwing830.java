package lanqiao.chongci.stack;

import java.util.Stack;

import utils.InAndOutUitl;

/**
 * 
 * 单调栈
 * 求坐标第一个小于它的数字
 * */
public class Acwing830 {
	public static void main(String[] args) throws Exception{
		InAndOutUitl util = new InAndOutUitl();
		int n = util.nextInt();
		Stack<Integer>st = new Stack<>();
		int[] nums = new int[n + 1];
		for (int i = 0; i < n; i++) {
			nums[i] = util.nextInt();
			while (!st.isEmpty() && nums[i] <= nums[st.peek()]) st.pop();
			if (!st.isEmpty()) util.write(nums[st.peek()] + " ");
			else util.write("-1 ");
			st.add(i);
		}
		util.flush();
	}
}
