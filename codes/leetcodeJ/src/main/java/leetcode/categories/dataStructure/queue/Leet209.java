package leetcode.categories.dataStructure.queue;

import leetcode.categories.dataStructure.stack.Leet962;
import leetcode.utils.ChangeToArrayOrList;
import leetcode.utils.ReadData;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author: Zekun Fu
 * @date: 2023/3/11 17:30
 * @Description: 长度最小的子数组
 * 1. 双指针,因为是正整数。
 * 2. 单调队列
 *
 */
public class Leet209 {
    public int minSubArrayLen(int[] nums, int k) {
        // arrayDeque头去掉可能不是O(1)的复杂度。还有就是
        Deque<Integer> que = new LinkedList<>();
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
        // 没有满足条件的返回0
        return ans == n + 1 ? 0 : ans;
    }
    public static void main(String[] args) throws Exception{
        String s = "[2,1,2,3]";
        int[] nums = ChangeToArrayOrList.changTo1DIntArray(s);
//        nums = ReadData.getArray();
        int k = 3;
        Leet209 tmp = new Leet209();
        int ans = tmp.minSubArrayLen(nums, k);
        System.out.println(ans);
    }
}
