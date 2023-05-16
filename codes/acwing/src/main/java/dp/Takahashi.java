package dp;

import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/10/25 12:00
 * @Description:
 * https://atcoder.jp/contests/abc251/tasks/abc251_e
 *
 * 输入 n (2≤n≤3e5) 和长为 n 的数组 a (1≤a[i]≤1e9)，下标从 1 开始。
 * 有 n 只动物围成一圈，你可以花费 a[i] 喂食动物 i 和 i+1。特别地，你可以花费 a[n] 喂食动物 n 和 1。
 * 输出喂食所有动物需要的最小花费。
 *
 * 来自灵神的10/25日刷题
 * 就是打家劫舍，状态机模型的dp
 * 怎么处理环？
 */
public class Takahashi {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextLong();
        }
        long[][] dp = new long[n + 1][2];
        // 第一种情况花费a[n - 1]
        dp[0][0] = a[n - 1];
        dp[0][1] = a[n - 1] + a[0];
        for (int i = 1; i < n - 1; i++) {
            // 上一个必须要选
            dp[i][0] = dp[i - 1][1];
            // 上一个可以选可以不选
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][1]) + a[i];
        }
        long ans = Math.min(dp[n - 2][0], dp[n - 2][1]);
        // 第二种情况不话费a[n - 1]
        dp[0][0] = Long.MAX_VALUE;
        dp[0][1] = a[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][1];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][1]) + a[i];
        }
        ans = Math.min(ans, dp[n - 1][0]);
        System.out.println(ans);
    }
}
