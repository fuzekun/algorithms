package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author: Zekun Fu
 * @date: 2023/3/11 14:58
 * @Description:
 * 密码脱落
 * 最长回文子序列
 */
public class Acwing_1222 {

    public static int longestPSeq(String s) {

        int n = s.length();
        char[] chars = s.toCharArray();
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], 0);
            dp[i][i] = 1;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (chars[i] == chars[j]) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                }
                else dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }
        return dp[0][n - 1];
    }

    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s = in.readLine();
        System.out.println(s.length() - longestPSeq(s));
        in.close();
    }
}
