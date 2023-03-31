package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2023/3/20 18:05
 * @Description:
 * 1083 windy数
 */
public class Acwing1083_numdp {

    private static int[][] dp;
    private static char[] s;
    // 初始不能是0, 否则第一个数字不能是0, 1了，也不能为10，否则9就不行了，更不能为负数，否则记忆化失败
    private static final int first = 11;
    public static void main(String[] args) {
        int a, b;
        Scanner sc = new Scanner(System.in);
        String[] input;
        while (sc.hasNextLine()) {
            input = sc.nextLine().split(" ");
            a = Integer.parseInt(input[0]);
            b = Integer.parseInt(input[1]);

            // 计算后一个数字
            s = Integer.toString(b).toCharArray();
            dp = new int[s.length][12];
            for (int i = 0; i < s.length; i++)
                Arrays.fill(dp[i], -1);
            int after = dfs(0, first, false, true);

            // 计算前一个数字
            s = Integer.toString(a - 1).toCharArray();
            for (int i = 0; i < s.length; i++)
                Arrays.fill(dp[i], -1);
            int pre = dfs(0, first, false, true);

            // out
            System.out.println(after - pre);
        }
    }

    private static int dfs(int cur, int pre, boolean isNum, boolean isLimit) {
        if (cur == s.length) {
            return isNum ? 1 : 0;
        }

        // 记忆化
        int res = dp[cur][pre];
        if (res >= 0 && !isLimit && isNum) return res;
        res = 0;

        // 如果不是数字，可以跳过
        if (!isNum) res += dfs(cur + 1, pre, false, false);

        // 枚举是数字的情况
        int up = isLimit ? s[cur] - '0' : 9;
        for (int i = isNum ? 0 : 1; i <= up; i++) {
            if (Math.abs(i - pre) < 2) continue;
            res += dfs(cur + 1, i, true, isLimit && i == up);
        }

        // 记录
        if (!isLimit && isNum) dp[cur][pre] = res;

        return res;
    }
}
