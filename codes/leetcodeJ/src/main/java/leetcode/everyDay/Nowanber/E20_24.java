package leetcode.everyDay.Nowanber;

/**
 * @author: Zekun Fu
 * @date: 2022/11/24 10:36
 * @Description: 20到24号的每日一题
 */
public class E20_24 {
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        // 单调栈
        /**
         * 这个题目和前两天的周赛题目2444统计定界子数组的数目
         * 思想很相似。

         */
        int n = nums.length;
        int last1 = -1, last2 = -1;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] > right) { last2 = i; last1 = i; }
            else if (nums[i] >= left) last1 = i;
            ans += last1 - last2;
        }
        return ans;
    }
}
