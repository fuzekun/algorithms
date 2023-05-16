package dp;

import java.util.*;

/**
 * @author: Zekun Fu
 * @date: 2023/3/20 20:02
 * @Description:
 * acwing1081 度的数量
 * 1. 首先，将x表示成B进制数
 * 2. 其次，dp[i][j] : 表示不受限制的条件中，前i位中选择j个数字的数字
 * 3. 由于是裸的数字的组合，而不能带上权重，所以说只能直接进行0或者1作为权重。
 * 或者说每一位上只能是0或者1。
 */
public class Acwing1081_numdp {

    private static int x, y, k, b;
    private static int[][] dp = new int[55][55];
    private static List<Integer> s = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        x = sc.nextInt();
        y = sc.nextInt();
        k = sc.nextInt();
        b = sc.nextInt();

        // 求前一个，首先表示成b进制
        x--;
        while (x != 0) {
            s.add(x % b);
            x /= b;
        }
        Collections.reverse(s);
        for (int i = 0; i < s.size(); i++) Arrays.fill(dp[i], -1);
        // 其次计算数量
        int pre = dfs(0, 0, true, false);

        // 求后一个
        s.clear();
        while (y != 0) {
            s.add(y % b);
            y /= b;
        }
        Collections.reverse(s);
        for (int i = 0; i < s.size(); i++) Arrays.fill(dp[i], -1);
        int after = dfs(0, 0, true ,false);
        System.out.println(after - pre);
    }

    private static int dfs(int cur, int cnt, boolean isLimit, boolean isNum) {
        if (cnt > k) return 0;
        if (cur == s.size()) {
            return isNum ? (cnt == k ? 1 : 0) : 0;
        }

        int res = dp[cur][cnt];
        if (res >= 0 && !isLimit && isNum) return res;
        res = 0;

        if (!isNum)
            res += dfs(cur + 1, cnt, false, false);

        // b进制,最大的单个位数是b - 1;
        int up = isLimit ? s.get(cur) : (b - 1);
        // 这一位要么选，要么不选，选只能是1，不能是
        for (int i = isNum ? 0 : 1; i <= Math.min(up, 1); i++) {
            int add = (i != 0 ? 1 : 0);
            res += dfs(cur + 1, cnt + add, isLimit && i == up, true);
        }

        if (isNum && !isLimit) dp[cur][cnt] = res;

        return res;
    }
}
