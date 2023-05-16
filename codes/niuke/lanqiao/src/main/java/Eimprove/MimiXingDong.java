package lanqiao.Eimprove;

import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/11/2 12:10
 * @Description: 秘密行动
 * 1. 向下不消耗时间
 * 2. 跳跃不消耗时间
 * 3. 类似打家劫舍，状态机模型，0表示前一个不跳的最小值，1表示跳的最小值
 * 4. 跳两层不一定比一层更加快 8 5 7，这样跳一层更快
 * 3 5 1 8 4
 * 3 5
 * 0 0 0
 *
 *
 *
 * 可以由前一个和前两个转移
 *
 */
public class MimiXingDong {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n + 1];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        int[][] dp = new int[n + 2][2];
        dp[0][0] = a[0];
        dp[0][1] = 0;
        dp[1][0] = a[1];
        dp[1][1] = 0;
        for (int i = 2; i < n; i++) {
            dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1]) + a[i];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 2][0]);
        }
        System.out.println(Math.min(dp[n - 1][0], dp[n - 1][1]));
    }
}
