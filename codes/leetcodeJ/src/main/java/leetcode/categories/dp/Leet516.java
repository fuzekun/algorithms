package leetcode.categories.dp;

import leetcode.categories.base.presum.Leet1124;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author: Zekun Fu
 * @date: 2023/3/11 18:06
 * @Description: 最长回文子序列
 *
 * f[i][j]：表示以i开头j结尾的最长回文子序列的长度
 * i == j f[i][j] = 1
 * s[i] == s[j] f[i][j] = f[i + 1][j - 1] + 2
 * 否则 f[i][j] = f[i + 1][j], f[i][j - 1]
 *
 *
 */
public class Leet516 {

    public int longestPSeq(String s) {
        int n = s.length();
        int[][] dp = new int[n + 1][n + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (i == j) dp[i][j] = 1;
                else {
                    if (s.charAt(i) == s.charAt(j))
                        dp[i][j] = dp[i + 1][j - 1] + 2;
                    else dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        Leet516 tmp = new Leet516();
        int ans = tmp.longestPSeq("bbbab");
        System.out.println(ans);
    }

}
