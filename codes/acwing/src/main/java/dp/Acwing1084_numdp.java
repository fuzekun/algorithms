package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2023/3/20 11:13
 * @Description:
 * 数字游戏II
 * 数位dp
 * dp[i][sum] ：表示前i位，和为sum的不受限的、满足和对N取余等于0的数字的个数
 *
 * 注意不能用sum % N表示状态。否则会少计算很多。
 * 当N == 10的时候，sum[100]和sum[10]都是sum[0]
 * 但是实际上。两者表示不一样的状态。
 */
public class Acwing1084_numdp {

    private static int[][]dp;
    private static char[] s;
    private static int mod;
    public static void main(String[] args) {
        int a, b, n;
        Scanner sc = new Scanner(System.in);
        String input;
        while (sc.hasNextLine()) {
            input = sc.nextLine();
            String[] chars = input.split(" ");
            a = Integer.parseInt(chars[0]);
            b = Integer.parseInt(chars[1]);
            n = Integer.parseInt(chars[2]);
            mod = n;
            // 先计算后一个
            s = String.valueOf(b).toCharArray();
            dp = new int[s.length][100];
            for (int i = 0; i < s.length; i++)
                Arrays.fill(dp[i], -1);
            int after = dfs(0, 0, true, false);

            // 再计算前一个
            s = String.valueOf(a - 1).toCharArray();
            for (int i = 0; i < s.length; i++) {
                Arrays.fill(dp[i], -1);
            }
            int pre = dfs(0, 0, true, false);

            System.out.println(after - pre);
        }
    }
    private static int dfs(int cur, int sum, boolean isLimit, boolean isNum) {
        if (cur == s.length) {
            return (isNum && sum % mod == 0) ? 1 : 0;
        }

        // 记忆化搜索
        int res = dp[cur][sum];
        if (!isLimit && isNum && res >= 0) return res;
        res = 0;

        // 如果不是数字，可以跳过
        if (!isNum) res += dfs(cur + 1, sum, false, false);

        // 是数字，从小到大遍历这位
        int up = isLimit ? s[cur] - '0' : 9;
        for (int i = isNum ? 0 : 1; i <= up; i++) {
            res += dfs(cur + 1, sum + i, isLimit && i == up , true);
        }

        // 记忆
        dp[cur][sum] = res;
        return res;
    }
}
