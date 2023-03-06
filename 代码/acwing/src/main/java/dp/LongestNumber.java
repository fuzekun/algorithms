package dp;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/10/24 10:39
 * @Description: 最长上升子序列长度为3的个数
 *
 * 1, 2, 2, 3, 3, 3, 3
 * [1, 2, 3, 4]
 * [2,2,2]
 * 给定n和m,求最长上升子序列长度为3的数组个数。
 *
 * f[i][x][y][z]:
 * 长度为i的时候，arr = [x, y, z]的情况下的最长上升子序列长度为3的个数
 * 知道当前的这个数字是j。
 * 1. 为什么计算的时候是小于等于，而不是直接小于
 * 2. 为什么需要从x开始不是从x + 1开始
 * 3. 为什么是else if 而不是if, 因为每一个j只能找到一个位置
 * 要么是第一个要么是第二或者第三个。
 * 4. 为什么初始化f[0][m][m][m] = 1。为什么不初始化其他的，为什么初始为1
 *
 * 最长上升子序列中的Lower_bound找的就是小于等于的第一个。
 * 也就是尽量向后小的那一个进行替换。从本题角度，如果当前
 * 和上一个的数组相同，那么就需要加上上一个的数组的个数。
 *
 *
 */
public class LongestNumber {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int mod = 998244353;
        int n = sc.nextInt(), m = sc.nextInt();
        long[][][][] dp = new long [n + 1][m + 2][m + 2][m + 2];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= m; k++)
                    Arrays.fill(dp[i][j][k], 0);
            }
        }
//        dp[0][m][m][m] = 1;
        for (int i = 0; i < m; i++) {
            dp[1][i][m][m] =  1;
        }
        for (int i = 2; i <= n; i++) {
            for (int x = 0; x <= m; x++) {
                for (int y = x; y <= m; y++) {
                    for (int z = y; z <= m; z++) {
                        long tmp = dp[i - 1][x][y][z];
                        for (int j = 0; j < m; j++) {
                            if (j <= x) {
                                dp[i][j][y][z] = (dp[i][j][y][z] + tmp) % mod;
                            }
                            else if (j <= y) {
                                dp[i][x][j][z] = (dp[i][x][j][z] + tmp) % mod;
                            }
                            else if (j <= z) {
                                dp[i][x][y][j] = (dp[i][x][y][j] + tmp) % mod;
                            }
                        }
                    }
                }
            }
        }
        long ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                for (int k = j + 1; k < m; k++) {
                    ans = (ans + dp[n][i][j][k]) % mod;
                }
            }
        }
        System.out.println(ans);
    }
}
