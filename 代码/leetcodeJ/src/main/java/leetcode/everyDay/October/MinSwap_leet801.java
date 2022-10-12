package leetcode.everyDay.October;

import leetcode.utils.ReadData;

/**
 * @author: Zekun Fu
 * @date: 2022/10/11 15:44
 * @Description: 最小交换次数
 *
 * 看数据量：动态规划或者贪心
 * 每次有两种交换的选择，a->b或者b->a
 *
 *  1 2 3 4 6 8 9
 *  1 2 4 5 5 6 7\
 *
 *  [0,4,4,5,9]
 * [0,1,6,8,10]
 *
 *[3,3,8,9,10]
 * [1,7,4,6,8]
 */
public class MinSwap_leet801 {
    public int minSwap2(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int pre = -1;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            // 一定是一个比之前大，另一个比之前的小
            if (nums1[i] <= pre) {
                ans ++;
                pre = nums2[i];
            }
            else pre = nums1[i];
        }
        pre = (int)1e9 + 7;
        int ans12 = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (nums1[i] >= pre) {
                pre = nums2[i];
                ans12++;
            }
            else pre = nums1[i];
        }
        ans = Math.min(ans, ans12);
        pre = -1;
        int ans2 = 0;
        for (int i = 0; i < n; i++) {
            if (nums2[i] <= pre) {
                ans2++;
                pre = nums1[i];
            }
            else pre = nums2[i];
        }
        pre = (int)1e9 + 7;
        int ans22 = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (nums2[i] >= pre) {
                ans22++;
                pre = nums1[i];
            } else pre = nums2[i];
        }
        ans2 = Math.min(ans2, ans22);
        return Math.max(ans, ans2);
    }

    public int minSwap(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[][] dp = new int[n][2];

        dp[0][0] = 0;
        dp[0][1] = 1;
        for (int i = 1; i < n; i++) {
            if (nums1[i] > nums1[i - 1] && nums2[i] > nums2[i - 1]) {
                dp[i][0] = dp[i - 1][0];
                dp[i][1] = dp[i - 1][1] + 1;
            }
            if (nums1[i] > nums2[i - 1] && nums2[i] > nums1[i - 1]){
                dp[i][0] = dp[i - 1][1];
                dp[i][1] = dp[i - 1][0] + 1;
            }
        }
        return Math.min(dp[n - 1][0], dp[n - 1][1]);
    }

    public static void main(String[] args) throws Exception{
        int[] num1 = ReadData.getArray();
        int[] num2 = ReadData.getArray("int1d_");
        System.out.println(new  MinSwap_leet801().minSwap(num1, num2));
    }
}
