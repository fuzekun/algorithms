package lanqiao.imporve;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/10/13 9:59
 * @Description: 车的放置
 *
 * 八皇后问题简化版：不能在同一行，同一列中
 */
public class CheDeFangZhi {

    private static int n;
    private static int m;
    // 为了放置重复，每一个都从下一行开始放置
    public static int dfs (int[] l, int cur, int curh) {
        if (cur == m) {
            return 1;
        }
        int ans = 0;
        for (int i = curh; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (l[j] == 0) {
                    l[j] = 1;
                    ans += dfs(l, cur + 1, i + 1);
                    l[j] = 0;
                }
            }
        }
        return ans;
    }
    public static int[][][]dp;
    public static int dfs(int curh, int cur) {
        if (cur == m) return 1;
        if (curh >= n) return 0;

        int tmp = dp[m][curh][cur];
        if (tmp >= 0) return tmp;
        tmp = 0;
        // 啥都不放
        tmp += dfs(curh + 1, cur);      // 啥都不放
        // 总共m个，前面放了cur个，还有(n - cur)个位置可以放
        tmp += dfs(curh + 1, cur + 1) * (n- cur);
        dp[m][curh][cur] = tmp;
        return tmp;
    }
    private static int res = 1;
    public static void dfs2(int curl, int[] visc) {
        if (curl == n) return ;
        // 在curh + 1行上面，从这些行中选出一个可以放的放上
        for (int j = 0; j < n; j ++) {
            if (visc[j] == 0) {
                visc[j] = 1;
                // 放上，答案就会多一个
                res ++;
                dfs2(curl + 1, visc);
                visc[j] = 0;
            }
        }
        // 不放
        dfs2(curl + 1, visc);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
//        int ans = 0;
//        for (int i = 0; i <= n; i++) {
//            int[] l = new int[n];
//            m = i;
//            ans += dfs(l, 0, 0);
//        }
        int ans = 0;
        dp = new int[(n + 1)][(n + 1)][(n + 1)];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        for (int i = 0; i <= n; i++) {
            m = i;
            ans += dfs(0, 0);
        }
        System.out.println(ans);

//        int[]vis = new int[n];
//        dfs2(0, vis);
//        System.out.println(res);
    }
}

/*
*
* dp[n][i] = dp[n - 1][i - 1] * n (i >= 2)
*
*   1
*   3 * 3 = 9
*   3 * 4 = 12 + 3 * 2 = 18
*   3 * 2 = 6
* 10 + 18 = 28 + 6 = 34
*
* 也就是说少了dp[n][m][curh] 这种情况
*
* 递归正确，递推出错。md
*
* */
