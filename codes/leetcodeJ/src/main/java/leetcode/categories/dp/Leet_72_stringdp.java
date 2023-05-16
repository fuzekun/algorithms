package leetcode.categories.dp;

import leetcode.everyDay.Nowanber.Leet754;

/**
 * @author: Zekun Fu
 * @date: 2023/3/9 10:03
 * @Description: 编辑距离
 * 两个字符串匹配的问题
 *
 * dp[i][j] : 表示从s[:i], t[:j]的最少操作次数
 * dp[0][i] = i:初始地一行，表示如果s为空，需要i次可以变成word2
 *
 * 如果s[i - 1] == t[j - 1] : dp[i][j] = d[i - 1][j - 1]
 * 否则 dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
 */
public class Leet_72_stringdp {
    public int minDistance(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= m; i++) {
            dp[0][i] = i;
        }
        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
        }
        char[] s = word1.toCharArray();
        char[] t = word2.toCharArray();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s[i - 1] == t[j - 1]) dp[i][j] = dp[i - 1][j - 1];
                else dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;       // 三种操作，增加，删除，替换
            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) {
        Leet_72_stringdp tmp = new Leet_72_stringdp();
        int ans = tmp.minDistance("horse", "ros");
        System.out.println(ans);
    }
}
