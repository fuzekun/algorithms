package lanqiao.base;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/10/5 13:33
 * @Description: 杨辉三角
 */
public class Triangle {

    public static void main(String[] args) {
        final int maxn = 100;
        int[][] dp = new int[maxn][maxn];
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], 0);
        }
        dp[1][1] = 1;
        dp[2][1] = dp[2][2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
            }
        }

        // 如果从1开始，就不是小于i了，应该是等于i了
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                if (j != i) System.out.print(dp[i][j] + " ");
                else System.out.println(dp[i][j]);
            }
        }
    }
}
