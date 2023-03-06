package lanqiao.imporve;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/11/21 10:38
 * @Description: 印章
 *
 * 1. dp[n][m]:表示买n枚，集齐m种印章的概率
 *
 * 当前这一种是一种新印章的概率 (n - j) / n
 * 当前这一种是一种旧印章的概率 (j) / n
 * dp[n][m] = dp[n][m - 1] + dp[n][m - 1]
 *
 * 1. dp[i][j - 1] = dp[i - 1][j - 1] * ((j - 1) / n)
 * -> dp[i][j] = dp[i - 1][j] * (j / n)
 *
 *  2. dp[i][j] = dp[i - 1][j - 1] * ((n - j + 1) / n)
 *
 *  综上
 *  dp[i][j] = dp[i - 1][j] * (j / n) + dp[i - 1][j - 1] * ((n - j + 1) / n)
 *
 *  每一种印章有两种选择
 *  1. 抽到新的
 *  2. 抽到旧的
 *
 * 可以大体看作是背包模型。
 *
 * 2 3的时候
 *
 * i == 1的时候
 * 0 : 0
 * 1 : 1
 *
 * i == 2的时候
 * 1 : 1 * 0.5
 * 2 : 0.5
 *
 * i 等于3的时候
 * 1 = 1
 * 2 = 0.5 + 0.5  = 1
 *
 *
 */
public class YinZhang {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        double[] dp = new double[n + 1];
        Arrays.fill(dp, 0);
        dp[1] = 1;
        double d = (double)n;
        for (int i = 2; i <= m; i++) {
            double[] pre = Arrays.copyOf(dp, n + 1);
            for (int j = 1; j <= Math.min(n, i); j++) {
                dp[j] = pre[j - 1] * ((double) (n - j + 1) / d) + pre[j] * ((double)j / d);
            }
        }
        System.out.printf("%.4f", dp[n]);
    }
}
