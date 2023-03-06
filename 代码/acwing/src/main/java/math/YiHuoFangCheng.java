package math;

import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/11/16 10:35
 * @Description: 高斯消元求解异或方程
 */
public class YiHuoFangCheng {

    public static void print(int[][]a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static int guass(int[][] a) {
        int n = a.length;
         int r = 0, c = 0;
         for (; c < n ; c++) {
             // 1. 找到首元非0的行
             int t = r;
             for (int i = r; i < n; i++) {
                 if (a[i][c] == 1) {
                     t = i;
                     break;
                 }
             }
             if (a[t][c] == 0) continue;        // 首元为0
             // 2. 交换到第r行
             for (int j = c; j <= n; j++) {
//                 swap(a[t][j], a[r][j]);
                int tmp = a[t][j];
                a[t][j] = a[r][j];
                a[r][j] = tmp;
             }
             // 3. 使用第r行进行消元, 把i行c列消元成0，即对r行进行异或
             for (int i = r + 1; i < n; i++) {
                 if (a[i][c] == 0) continue;
                 for (int j = n; j >= c; j--) {
                     a[i][j] ^= a[r][j];
                 }
             }
             r++;
         }
         if (r < n) {
             for (int i = r; i < n; i++) {
                 if (a[i][n] != 0)
                     return 2;
             }
             return 1;
         }
//         print();
         // 把j行i列消元成0
//         for (int i = n - 1; i > 0; i--) {
//             for (int j = 0; j <= i - 1; j++) {   // 等号
//                 if (a[j][i] == 0) continue;
//                 a[j][n] ^= a[i][n];
//             }
//         }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (a[i][j] == 0) continue;
                a[i][n] ^= a[j][n]; // 每行尾列 * i,j行本列商
            }
        }
//         print();
         return 0;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] a = new int[n][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n; j++) {
                a[i][j] = sc.nextInt();
            }
        }
        int x = guass(a);
        if (x == 0) {                   // 唯一解
            for (int i = 0; i < n; i++) System.out.println(a[i][n]);
        } else if (x == 1) {            // 无穷解
            System.out.println("Multiple sets of solutions");
        } else {                        // 无解
            System.out.println("No solution");
        }
    }
}
