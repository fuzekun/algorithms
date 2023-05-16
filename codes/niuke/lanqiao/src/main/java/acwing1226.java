package lanqiao;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/10/4 17:41
 * @Description: 包子凑数
 * https://www.acwing.com/problem/content/1228/
 *
 * 硬币凑数的问题
 * 初始化dp为INF
 * dp[ai] = 1
 * 求dp[S]
 *
 * 这样可以求出来可以凑成的包子数目，但是求不出来不能凑成的包子数目。
 *
 *
 */
public class acwing1226 {

    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a[] = new int[n];
        int d = 0, maxv = 1, secondv = 1;
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            d = gcd(a[i], d);
            if (a[i] > maxv) {
                secondv = maxv;
                maxv = a[i];
            }
            else if (a[i] > secondv) {
                secondv = a[i];
            }
        }
        if (d != 1) System.out.println("INF");
        else {
            maxv = maxv * secondv;
            boolean[] dp = new boolean[maxv + 1];
            Arrays.fill(dp, false);
            int ans = 0;
            for (int i = 0; i < n; i++) dp[a[i]] = true;
            for (int i = 1; i <= maxv; i++) {
                if (!dp[i]) {
                    ans += 1;
                }
                else {
                    for (int j = 0; j < n; j++) {
                        if (i + a[j] <= maxv)
                            dp[i + a[j]] = true;
                    }
                }
            }
            System.out.println(ans);
        }
    }
}
