package leetcode.categories.dataStructure.queue;

import leetcode.utils.ChangeToArrayOrList;
import leetcode.utils.ReadData;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author: Zekun Fu
 * @date: 2023/3/11 17:10
 * @Description:
 * 单调队列优化
 * 满足两个条件
 * 1. 队尾插入保持单调
 * 2. 队首满足/不满足条件可以去掉。
 */
public class Leet862 {

    public int shortestSubarray(int[] nums, int k) {
        Deque<Integer> que = new LinkedList<>();                      // arrayDeque头去掉可能不是O(1)的复杂度。还有就是
        int n = nums.length;
        long[] sum = new long[n + 1];
        sum[0] = 0;
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        int ans = n + 1;
        for (int i = 0; i < sum.length; i++) {
            // 对首满足条件可以去掉
            while (!que.isEmpty() && sum[i] - sum[que.peekFirst()] >= k) {
                ans = Math.min(ans, i - que.pollFirst());
            }
            // 队尾保持单调
            while (!que.isEmpty() && sum[que.peekLast()] >= sum[i]) que.pollLast();
            que.addLast(i);
        }
        // 没有满足条件的返回-1
        return ans == n + 1 ? -1 : ans;
    }
    public static void main(String[] args) throws Exception{
        String s = "[2,-1,2,3]";
        int[] nums = ChangeToArrayOrList.changTo1DIntArray(s);
        nums = ReadData.getArray();
        int k = 1000000000;
        Leet862 tmp = new Leet862();
        int ans = tmp.shortestSubarray(nums, k);
        System.out.println(ans);
    }
}
