package lanqiao.imporve;

import java.io.*;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/10/28 11:08
 * @Description: 进击的青蛙
 */
public class Jinjideqingwa {
    private static int maxn = (int)1e6 + 5;
    private static int[] dp = new int[maxn];

    public static void main(String[] args)throws Exception {
        BufferedWriter cout = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(cin.readLine()), mod = (int)1e9 + 7;
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            int a = Integer.parseInt(cin.readLine());
            if (a == 1) {
                dp[i] = 0;
                continue;
            }
            for (int j = 1; j <= 3; j++) {
                if (i >= j) dp[i] = (dp[i - j] + dp[i]) % mod;
            }
        }
        if (dp[n] == 0) cout.write("No Way!");
        else cout.write(Integer.toString(dp[n]));
        cout.flush();
        cout.close();
    }
}
/*
5
1
0
0
1
0
*/
