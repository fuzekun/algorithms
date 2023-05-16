package lanqiao;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/10/3 11:11
 * @Description:
 *
 * 蓝桥杯的训练题目：复杂动态规划
 */
public class acwing1047 {

    public static void main(String[] args) throws Exception {
        // 字符流，正常使用的都是字符流，字节流，读取二进制文件的时候使用
//        BufferedInputStream in = new BufferedInputStream(System.in);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//      bufferReader的正确使用方式
        String first = in.readLine();
        String[] firstIn = first.split(" ");
        int n = Integer.parseInt(firstIn[0]);
        int k = Integer.parseInt(firstIn[1]);

        int[][] dp = new int[2][k];
        final int inf = 0x3f3f3f3f;
        for (int i = 0; i < 2; i++)
            Arrays.fill(dp[i], -inf);
        dp[0][0] = dp[1][0] = 0;

        for (int i = 1; i <= n; i++) {
            int a = Integer.parseInt(in.readLine());
            for (int j = 0; j < k; j++) {
                int nx = (a + j) % k;
                dp[i & 1][nx] = Math.max(dp[(i - 1) & 1][j] + a, dp[(i - 1) & 1][nx]);
            }
        }
        System.out.println(dp[n & 1][0]);
    }
}
