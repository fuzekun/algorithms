package chapter9;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/8/3 15:50
 * @Description: 城市里的间谍
 * 1. 题目地址:https://www.luogu.com.cn/problem/UVA1025
 * 2. 测试数据使用udebug
 *
 * 注意事项
 * 1. 提交时候修改类名为Main
 * 2. 去掉包名
 * 3. 去掉抛出异常。
 * 4. 输入输出是否多余，是否有重定向问题。
 */
public class Spayder {
    static final int INF = 0x3f3f3f3f;
    static int n, T, m1, m2, kase;
    static int[] t = new int[55];
    static boolean [][] trainl = new boolean [55][10010];
    static boolean [][] trainr = new boolean [55][10010];
    static int[][] dp = new int[10010][55];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        while (n != 0) {
            kase++;
            for (int i = 0; i <= n; i++) {
                Arrays.fill(trainl[i], false);
                Arrays.fill(trainr[i], false);
            }
            T = sc.nextInt();
//            System.out.println("T = " + T + " n = " + n);
            for (int i = 1; i <= n - 1; i++) t[i] = sc.nextInt();
            m1 = sc.nextInt();
//            System.out.println("m1 = " + m1);
            for (int i = 0; i < m1; i++) {
                int sum = sc.nextInt();
                for (int j = 1; j <= n; j++) {
                    trainl[j][sum] = true;
                    sum += t[j];
                }
            }
            m2 = sc.nextInt();
//            System.out.println("m2 = " + m2);
            for (int i = 0; i < m2; i++) {
                int sum = sc.nextInt();
//                System.out.println(sum);
//                System.out.println("i = " + i);
                for (int j = n; j >= 1; j--) {
//                    if (i == 4) System.out.printf("%d %d\n", sum, j);
                    trainr[j][sum] = true;
                    sum += t[j - 1];
                }
            }
            for (int i = 1; i <= n - 1; i++) dp[T][i] = INF;
            dp[T][n] = 0;
            for (int i = T - 1; i >= 0; i--) {
                for (int j = 1; j <= n; j++) {
                    dp[i][j] = dp[i + 1][j] + 1;
                    if (j < n && trainl[j][i] && i + t[j] <= T)
                        dp[i][j] = Math.min(dp[i + t[j]][j + 1], dp[i][j]);
                    if (j > 1 && trainr[j][i] && i + t[j - 1] <= T)
                        dp[i][j] = Math.min(dp[i][j], dp[i + t[j - 1]][j - 1]);
                }
            }
//            for (int i = T; i >= 0; i--) {
//                for (int j = 1; j <= n; j++) {
//                    System.out.printf("%d ", dp[i][j]);
//                }
//                System.out.println();
//            }
            // 使用try...with语句，更加方便
            try(FileWriter fw = new FileWriter(new File("spyder.out"), true)) {
                fw.write("Case Number " + kase + ": ");
                if (dp[0][1] >= INF) fw.write("impossible\n");
                else fw.write(dp[0][1] + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
//            System.out.printf("Case Number %d: ", kase);
//            if (dp[0][1] >= INF) System.out.println("impossible");
//            else System.out.println(dp[0][1]);
            n = sc.nextInt();
        }
    }
}
