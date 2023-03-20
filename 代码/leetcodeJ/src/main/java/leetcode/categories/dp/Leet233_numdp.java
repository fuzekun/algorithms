package leetcode.categories.dp;

import java.util.Arrays;

/**
 * @author: Zekun Fu
 * @date: 2023/3/20 14:26
 * @Description:
 * 统计数字1的个数
 */
public class Leet233_numdp {
    private int[][] dp;
    private char[] s;
    private int dfs(int cur, int nums, boolean isNum, boolean isLimit) {
        if (cur == s.length)
            return nums;

        int res = dp[cur][nums];
        if (res >= 0 && !isLimit && isNum) return res;
        res = 0;
        // 跳过
        if (!isNum) res += dfs(cur + 1, nums, false, false);
        // 选择
        int up = isLimit ? s[cur] - '0' : 9;
        for (int i = isNum ? 0 : 1; i <= up ; i++) {
            res += dfs(cur + 1, nums + (i == 1 ? 1 : 0), true, isLimit && i == up);
        }
        if (!isLimit && isNum) dp[cur][nums] = res;
        return res;
    }
    public int countDigitOne(int n) {
        s = Integer.toString(n).toCharArray();
        dp = new int[s.length][1024];
        for (int i = 0; i < s.length; i++) Arrays.fill(dp[i], -1);
        return dfs(0, 0, false, true);
    }
    public static void main(String[] args) {
        int n = 13;
        Leet233_numdp t = new Leet233_numdp();
        System.out.println(t.countDigitOne(n));
    }
}
