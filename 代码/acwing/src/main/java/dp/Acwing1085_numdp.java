package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2023/3/20 10:49
 * @Description: 数位dp
 * 不要6和2
 *
 * 统计[0, n]中不包含6和2的数量
 * 使用dfs(n) - dfs(m - 1)得到答案
 */
public class Acwing1085_numdp {
    private static int[][] dp;
    private static char[] s;

    public static void main(String[] args) {
        int n, m;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        while (n != 0 && m != 0) {
            // 先计算后面的数字
            s = Integer.toString(m).toCharArray();
            dp = new int[s.length][10];
            for (int i = 0; i < s.length; i++) {
                Arrays.fill(dp[i], -1);
            }
            int after = dfs(0, 0, false, true);

            // 后计算前面的数字
            s = Integer.toString(n - 1).toCharArray();
            for (int i = 0; i < s.length; i++) {
                Arrays.fill(dp[i], -1);
            }
            int pre = dfs(0, 0, false, true);

            // out
            System.out.println(after - pre);

            // in
            n = sc.nextInt();
            m = sc.nextInt();
        }
    }
    private static int dfs(int cur, int pre, boolean isNum, boolean isLimit) {
        if (cur == s.length) {
            return isNum ? 1 : 0;
        }
        // 记忆化
        int res = dp[cur][pre];
        if (isNum && !isLimit && res >= 0)
            return res;
        res = 0;
        // 不是数字，可以跳过
        if (!isNum) res += dfs(cur + 1, pre, false, false);
        // 枚举是数字的情况
        int up = isLimit ? s[cur] - '0' : 9;
        for (int i = isNum ? 0 : 1; i <= up; i++) {
            if (i == 4 || (pre == 6 && i == 2)) continue;
            res += dfs(cur + 1, i, true, isLimit && i == up);
        }
        // 记录
        if (!isLimit && isNum) dp[cur][pre] = res;
        return res;
    }
}
