package lanqiao.base;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/10/4 10:27
 * @Description: acwings中的密码脱落
 *https://www.acwing.com/problem/content/1224/
 *复杂dp问题
 * 答案是n - 最长的回文子序列
 */
public class PassHuiWen {

    public static int longestPSeq(String s) {
//        ababcbaba :
//
//        dp[i][j] : 表示以i开始以j结尾的最长回文子序列的长度。
//        dp[i][j] = dp[i + 1][j - 1] + 2 if s[i] == s[j] else
//            max(dp[i + 1][j], dp[i][j - 1])
//        dp[]
        int n = s.length();
        char[] chars = s.toCharArray();
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], 0);      // i > j的时候初始化为0
            dp[i][i] = 1;                   // i == j的时候初始化为1
        }
        // i < j的时候进行下面的计算
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) { // 不用考虑长度为1的情况，上面已经初始化掉了
                // 完全不需要特殊处理，因为等于2的时候，相等为2，不相等为1，初始化已经i > j 时候为0，i == j的时候为1了
//                if (i + 1 > j - 1) {                // 长度小于3的时候单独处理
//                    if (chars[i] == chars[j]) dp[i][j] = 2;
//                    continue;
//                }
                if (chars[i] == chars[j]) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                }
                else dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }
        return dp[0][n - 1];
    }
    public static int m2(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int[][] f = new int[n + 1][n + 1];
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (len == 1) {
                    f[i][j] = 1;
                    continue;
                }
                f[i][j] = 1;
                if (chars[i] == chars[j]) f[i][j] = f[i + 1][j - 1] + 2;
                f[i][j] = Math.max(Math.max(f[i + 1][j], f[i][j + 1]), f[i][j]);
            }
        }
        return f[0][n - 1];
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
//        System.out.println(s);
        System.out.println(s.length() - m2(s));
    }
}
