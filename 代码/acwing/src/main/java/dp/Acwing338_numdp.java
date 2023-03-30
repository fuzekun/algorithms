package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2023/3/20 13:11
 * @Description:
 * 数字dp
 * dp[i] = dp[i] * 10 + (s[cur] - '0' == x)
 * 表示不受限制情况下，是数字的情况下，有的次数
 * dp[i][cnt]:
 * 前i位的情况下，数字x的个数为cnt的时候。非受限制的状态的个数。
 *
 *
 */
public class Acwing338_numdp {

    private static int[][] dp = new int[20][1024];
    private static char[] s;
    public static void main(String[] args) {
        int a, b;
        Scanner sc = new Scanner(System.in);
        a = sc.nextInt();
        b = sc.nextInt();

        while (a != 0 && b != 0) {

            // 坑点：a和b之间，没说a和b的大小关系
            if (a > b) {
                int tmp = a;
                a = b;
                b = tmp;
            }

            for (int x = 0; x < 10; x++) {
                // 计算后
                s = Integer.toString(b).toCharArray();
                for (int i = 0; i < s.length; i++)
                    Arrays.fill(dp[i], -1);
                int after = dfs(0, 0, x, false, true);

                // 计算前
                s = Integer.toString(a - 1).toCharArray();
                for (int i = 0; i < s.length; i++)
                    Arrays.fill(dp[i], -1);
                int pre = dfs(0, 0, x, false, true);

                // out
                System.out.print((after - pre) + " ");
            }
            System.out.println();

            // in
            a = sc.nextInt();
            b = sc.nextInt();
        }
    }
    private static int dfs(int cur, int nums, int x, boolean isNum, boolean isLimit) {
        if (cur == s.length) {
            return nums;
        }

        int res = dp[cur][nums];
        if (!isLimit && isNum && res >= 0) return res;
        res = 0;

        // 不是数字，可以继续跳过
        if (!isNum) res += dfs(cur + 1, nums, x, false, false);
        // 不跳过
        int up = isLimit ? s[cur] - '0' : 9;
        for (int i = isNum ? 0 : 1; i <= up; i++) {
            int add = i == x ? 1 : 0;
            res += dfs(cur + 1, nums + add, x, true, isLimit && i == up);
        }

        // 记录
        if (!isLimit && isNum) dp[cur][nums] = res;
        return res;
    }
}
