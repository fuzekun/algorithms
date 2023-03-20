package leetcode.categories.dp;

import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2023/3/19 17:50
 * @Description: 美团3.18笔试最后一题
 *
 * dp[j][k] = max(dp[j - price[i][0]][k] + 1, dp[j - price[i][1]][k - 1] + 1,
 * dp[j][k] + 1); // 不选，选择优惠价格，选择不优惠价格
 */
public class MeituanDp {

    private static int max(int x, int y) {
        return Math.max(x, y);
    }
    public static void main(String[] args) {
        int n, money, qcnt;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        money = sc.nextInt();
        qcnt = sc.nextInt();
        int[][] prices = new int[n][n];
        for (int i = 0; i < n; i++) {
            prices[i][0] = sc.nextInt();
            prices[i][1] = sc.nextInt();
        }
        int[][] dp = new int[money + 1][qcnt + 1];
        for (int i = 0; i < n; i++) {
            int p0 = prices[i][0], p1 = prices[i][1];
            for (int j = money; j >= 0; j--) {
                for (int k = qcnt; k >= 0; k--) {
                    // 选择不优惠的价格
                    if (j >= p0) {
                        dp[j][k] = max(dp[j - p0][k] + 1, dp[j][k]);
                    }
                    // 选择优惠的价格
                    if (j >= p1 && k >= 1) {
                        dp[j][k] = max(dp[j][k], dp[j - p1][k - 1] + 1);
                    }
                }
            }
        }
        // 计算在最多数量的情况下的最小值
        int max_cnt = dp[money][qcnt];
        for (int i = 0; i <= money; i++) {
            // 找到一个能够购买这些商品的价格，就是最小的价格。优惠券当然越多越好了
            if (dp[i][qcnt] == max_cnt) {
                System.out.println(max_cnt + " " + i);
                return ;
            }
        }
    }
}
