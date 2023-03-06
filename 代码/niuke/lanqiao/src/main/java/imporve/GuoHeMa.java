package lanqiao.imporve;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/10/18 14:30
 * @Description: 过河马
 */
public class GuoHeMa {

    private static int n, m;
    private static long[][] f;
    private static int[][]dirs =  {{1, 2}, {2, 1}, {1, -2}, {2, -1}};
    private static long mod = (int)1e9 + 7;
    private static boolean check(int a, int b) {
        if (a < 0 || a >= n || b < 0 || b >= m)
            return false;
        return true;
    }
    private static long dfs(int a, int b) {
        if (a == n - 1 && b == m - 1) return 1;
        if (a > n || b > m) return 0;
        long ans = f[a][b];
        if (ans != -1) return ans;
        ans = 0;
        for (int i = 0; i < 4; i++) {
            int na = a + dirs[i][0];
            int nb = b + dirs[i][1];
            if (check(na, nb))
                ans = (dfs(na, nb) + ans) % mod;
        }
        f[a][b] = ans;
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        f = new long[n][m];
        for (int i = 0; i < n; i++)
            Arrays.fill(f[i], -1);
        System.out.println(dfs(0, 0));
    }
}
