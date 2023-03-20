package leetcode.categories.dp;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author: Zekun Fu
 * @date: 2023/3/20 9:12
 * @Description: 数位dp
 * leet 1012
 */
public class Leet_numDp {
    /**
     *
     * leet1012 n中存在至少一个重复数字的个数
     * n - 最多存在0个。也就是: n - 不存在重复数字的个数
     * */
    private int dfs(int cur, int mask, boolean isNum, boolean isLimit, int[][] dp, int[]s) {
        if (cur == s.length) {
            return isNum ? 1 : 0;
        }
        // 已经计算过这个不受限制的个数了
        int res = dp[cur][mask];
        if (!isLimit && isNum && dp[cur][mask] != -1) return res;
        res = 0;
        // 不是数字，可以直接空缺
        if (!isNum) res += dfs(cur + 1, mask, false, false, dp, s);
        // 计算是数字的个数
        int up = isLimit ? s[cur] : 9;                  // 受限到s[i],否则到9
        for (int i = isNum ? 0 : 1; i <= up; i++) {     // 是数字从0，不是最小是1
            if ((mask >> i & 1) == 0) {                 // 不包含这一位
                res += dfs(cur + 1, mask | (1 << i), true, isLimit && i == up, dp, s);
            }
        }
        // 记录
        if (!isLimit && isNum)
            dp[cur][mask] = res;
        return res;
    }
    int numDupDigitsAtMostN(int n) {
        char[] s = String.valueOf(n).toCharArray();
        int m = s.length;
        int[]ss = new int[m];
        for (int i = 0; i < m; i++)
            ss[i] = s[i] - '0';
        int[][] dp = new int[m][1<<10];
        for (int i = 0; i < m; i++) Arrays.fill(dp[i], -1);
        return n - dfs(0, 0, false, true, dp, ss);
    }
    public static void main(String[] args) {

        int n = 63628;
        Leet_numDp t = new Leet_numDp();
        System.out.println(t.numDupDigitsAtMostN(n));
    }
}
