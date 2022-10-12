package lanqiao;

import java.lang.reflect.Array;
import java.util.*;

import static lanqiao.acwing_1303_fib.mul;
import static lanqiao.acwing_1303_fib.quick_pow;

/**
 * @author: Zekun Fu
 * @date: 2022/10/9 10:34
 * @Description: 第314场周赛
 *
 * 之后采用斐波那契数列的优化方式
 *
 * f(i, j)
 * [4, 4, 4]
 * [4]
 * [4]
 * [4]
 * [4]
 * [4]
 * 为什么需要vis[rev][b],这个是使用的以i为顶面的
 * 那么如果定义为j为底面，那么不应该是vis[a][b]了嘛
 */
public class acwing1217_Touzi {
    private static int[] revs = {0, 4, 5, 6, 1, 2, 3};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        long [][] vis = new long[7][7];
        for (int i = 1; i <= 6; i++) {
            Arrays.fill(vis[i], 4);
        }
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            vis[revs[a]][b] = 0;
            vis[revs[b]][a] = 0;
        }
        final long mod = (long)1e9 + 7;
        long[][] dp = new long[7][7];
        for (int i = 0; i < 7; i++)
            Arrays.fill(dp[i], 0);
        Arrays.fill(dp[0], 4);
        long[][] curvis = quick_pow(vis, n - 1, mod);
        dp = mul(dp, curvis, mod);

        long ans = 0;
        for (int i = 1; i <= 6; i++) ans = (ans + dp[0][i]) % mod;
        System.out.println(ans);
    }
}
