package dp;

import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/9/1 9:55
 * @Description:
 *
 * 1. 状态机dp，大盗阿福
 * 2. 状态机dp, 设计密码
 */

public class Main {

    // 大盗阿福
    public void stealMaxMoney() {

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();
        while (T != 0) {
            T--;
            int n = sc.nextInt();
            int[] a = new int[n + 1];
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }
            a[n] = 0;
            int[][] dp = new int[n + 1][2];
            dp[0][0] = 0;
            dp[0][1] = a[0];
            for (int i = 1; i < n + 1; i++) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
                dp[i][1] = a[i] + dp[i - 1][0];
            }
            System.out.println(dp[n][0]);
        }
    }

    // 设计密码
    public void designPSW() {
//        f(i, j): 表示当前是第i个待选取的字母，匹配到T中的
//                第j个字母，可以的数量是多少。
//        需要知道上面所有的字符是多少。
//
//        先从思想上来说：
//        动态规划，就是递归的思想：可以由上一个状态，来推出
//                本状态的思想。
//
//        本题目来说：
//        如果上一状态f(i, j)， 本状态是f(i + 1, j)
//                那么就是使用本状态向下一个可以转移的状态
//                        进行填表。
//        如果从当前状态可以转移到下一个状态,
//        那么下一个状态的答案数量就应该加上本状态的数量
//                f[i][j]

    }

    public static void main(String[] args) {
        Main m = new Main();
        m.stealMaxMoney();

    }
}
