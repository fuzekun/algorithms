package leetcode.contest.May;

import java.lang.reflect.Array;
import java.util.Arrays;

public class M_T_3 {

    /*
    *
    *   第三场周赛
    * */
    // 最大的优质整数
    public String largestGoodInteger(String num) {
        char[] chars = num.toCharArray();
        int pre = -1, cnt = 0;
        int ans = 0;
        int[] val = {000, 111, 222, 333, 444, 555, 666, 777, 888, 999};
        for (char ch:chars) {
            int c = ch - '0';
            if (c == pre) {
                cnt++;
                if (cnt >= 3) {
                    ans = Math.max(ans, val[pre]);
                    System.out.println(val[pre]);
                }
            } else {
                cnt = 1;
            }
            pre = c;
        }
        if (ans == 0) return "000";
        return String.valueOf(ans);
    }


    // 2可以对应1次，两次...很多次
    public int countTexts(String pressedKeys) {
        int mod = (int)1e9 + 7;
        long ans = 1;
        int n = pressedKeys.length();
        int[]dp = new int[n + 5], f = new int[n + 5];

        dp[0] = 1; dp[1] = 1; dp[2] = 2; dp[3] = 4;
        f[0] = 1; f[1] = 1; f[2] = 2; f[3] = 4;
        for (int i = 4; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % mod;
            f[i] = (f[i - 1] + f[i - 2] + f[i - 3] + f[i - 4]) % mod;
        }

        int cnt = 0;
        char pre = '#';
        pressedKeys += "#";         // 保证最后一串乘进去
        for (int i = 0; i < n + 1; i++) {
            char ch = pressedKeys.charAt(i);
            System.out.println("ch = " + ch);
            if (ch == pre) {
                cnt++;
            } else {
                System.out.println("pre = " + pre +  " " + "cnt = " +cnt);
                if (pre == '7' || pre == '9')
                    ans = (ans * f[cnt]) % mod;
                else ans = (ans * dp[cnt]) % mod;
                cnt = 1;
            }
            pre = ch;
        }

        return (int)ans;
    }


    public static void main(String[] args) {
        M_T_3 m = new M_T_3();
//        System.out.println(m.largestGoodInteger("6777133339"));
//        System.out.println(m.countTexts("2"));

        boolean[][][] dp = new boolean[2][2][2];
        Arrays.fill(dp, false);
        System.out.println(dp[0][0][0]);
    }

    int n, m;
    boolean[][][]dp;
    int[][] dirs = {{0, 1}, {1,0}};
    private boolean dfs(char[][] grid, int x, int y, int sum) {
        if (x == n - 1&& y == m - 1) {
            return sum == 0;
        }
        if (sum > m + n - x - y) return false;
        if (dp[x][y][sum]) return false;
        dp[x][y][sum] = true;

        for (int i = 0; i < 2; i++) {
            int nx = x + dirs[i][0];
            int ny = y + dirs[i][1];
            if (nx >= n || ny >= m) continue;
            int nsum = sum + grid[x][y] == '(' ? 1 : -1;
            if(dfs(grid, nx, ny, nsum)) {
                return true;
            }
        }
        return false;
    }
    public boolean hasValidPath(char[][] grid) {
        n = grid.length;
        m = grid[0].length;
        dp = new boolean[n][m][(n + m) / 2];
        Arrays.fill(dp, false);
        if (grid[0][0] != '(' || grid[n - 1][m - 1] != ')')
            return false;
        return dfs(grid, 0, 0, 1);
    }



}
