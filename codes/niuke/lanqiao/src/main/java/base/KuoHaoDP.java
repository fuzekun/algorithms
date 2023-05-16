package lanqiao.base;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author: Zekun Fu
 * @date: 2022/10/4 21:40
 * @Description: 括号匹配问题https://www.acwing.com/problem/content/1072/
 *
 *
 * [(]) ->[()]()
 * [(][[))->[([])][[]](()) 6个
 *
 */


public class KuoHaoDP {
    public static boolean match(char l, char r) {
        if (l == '(' && r == ')') return true;
        if (l == '[' && r == ']') return true;
        return false;
    }
    public static int m2(String s) {
        char[] chars = s.toCharArray();
//        Stack<Character> st = new Stack<>();
        int n = s.length();
        final int INF = 0x3f3f3f3f;
        int[][]dp = new int[n + 1][n + 1];

        for (int len = 1; len <= n; len ++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                dp[i][j] = INF;
                if (match(chars[i], chars[j]))
                    dp[i][j] = dp[i + 1][j - 1];
                if (j >= 1) dp[i][j] = Math.min(dp[i][j], Math.min(dp[i + 1][j], dp[i][j - 1]) + 1);
                // 区间dp  ()[()()]
                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j]);
                }
            }
        }
        return dp[0][n - 1];
    }
    static int m1(String s) {

        char[] chars = s.toCharArray();
//        Stack<Character> st = new Stack<>();
        int n = s.length();
        final int INF = 0x3f3f3f3f;
        int[][]dp = new int[n + 1][n + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], INF);
            for (int j = 0; j < n; j++) {
                // 这个是为了如果只有一个的情况下，加一个肯定可以了。
                if (i == j) dp[i][j] = 1;
                // 如果长度为1的时候
                if (i > j) dp[i][j] = 0;
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {   // 这里不考虑长度为1的情况，初始化为1了已经
                if (match(chars[i], chars[j]))  // 为什么长度为2的时候没有单独考虑?因为长度为2的时候, 匹配直接为0，不匹配就执行下面的会为2
                    dp[i][j] = dp[i + 1][j - 1];
                // 这里应该注意，j >= 1的时候才能减去
                if (j >= 1) dp[i][j] = Math.min(dp[i][j], Math.min(dp[i + 1][j], dp[i][j - 1]) + 1);
                // 区间dp  ()[()()]
                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j]);
                }
            }
        }
        return dp[0][n - 1];
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(m1(s));
    }
}
