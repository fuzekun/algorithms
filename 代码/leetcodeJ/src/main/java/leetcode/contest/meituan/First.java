package leetcode.contest.meituan;

import leetcode.utils.ChangeToArrayOrList;

import java.io.BufferedReader;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2023/3/18 9:56
 * @Description:
 */
public class First {

    // 二维前缀和
    private final static int maxn = 1005;
    private static int[][] rec = new int[maxn + 1][maxn + 1];
    private static int[][] sum = new int[maxn + 1][maxn + 1];
    private static int getSum(int x1, int y1, int x2, int y2){
        return sum[x2][y2] - sum[x1 - 1][y2] - sum[x2][y1 - 1] + sum[x1 - 1][y1 - 1];
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int a = Integer.parseInt(input[1]);
        int b = Integer.parseInt(input[2]);
        int maxx = 0, maxy = 0;
        for (int i = 0; i < n; i++) {
            input = sc.nextLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);
            rec[x][y] += 1;
            maxx = Math.max(maxx, x);
            maxy = Math.max(maxy, y);
        }
        // 求前缀和,
        for (int i = 1; i <= maxn;i++) {
            for (int j = 1; j <= maxn; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + rec[i][j];
            }
        }
        // 遍历所有，求前缀和
        int ans = 0;
        for (int i = 1; i <= maxn - a; i++) {
            for (int j = 1; j <= maxn - b; j++) {
                int x2 = i + a, y2 = j + b;
                ans = Math.max(ans, getSum(i, j, x2, y2));
            }
        }
        System.out.println(ans);
    }
}
