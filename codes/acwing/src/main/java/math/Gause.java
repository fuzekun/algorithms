package math;

import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/11/14 15:12
 * @Description: 高斯消元
 * 对于每一列
 * 1. 找到本列最大值所在的行，主元行
 * 2. 如果，主元为0， 直接continue, 否则将主元行，交换到当前首行
 * 3. 将主元行的主元变成1
 * 4. 使用主元行，将其他行的本列消元成0
 *
 * 5. 进行判断，如果扩展矩阵的秩大于原矩阵的秩无解，否则看r == n : 唯一解:无穷解。
 * 6. 如果有唯一的解，将矩阵化为行最简形。
 */
public class Gause {

    /*
    *   return
    * 0 : 唯一的解，1无穷多解，2；无解
    * */
    private static final double exp = 1e-10;
    public static void out(double[][] a) {
        for (double[] aa: a) {
            for (double x : aa) {
                System.out.print(x + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static int guass(double[][] a) {
        // 0.遍历每一列
        int n = a.length;
        int r = 0;
        for (int c = 0; c < n; c++) {
            // 1. 找到主元行
            int t = r;              // 一定是从r,不应该从c开始。 r <= c
            for (int i = r; i < n; i++) {
                if (Math.abs(a[i][c]) > Math.abs(a[t][c]))
                    t = i;
            }
//            System.out.println(Math.abs(a[t][c]));
            if (Math.abs(a[t][c]) <= exp) continue;
            // 2. 将主元行交换到最上面
            for (int j = c; j <= n; j++) {
                double tmp = a[r][j];
                a[r][j] = a[t][j];
                a[t][j] = tmp;
            }
            // 3. 主元行本列(c列)置为1
            for (int j = n; j >= c; j--) a[r][j] /= a[r][c];
            // 4. 使用主元行消去其他行
            for (int i = r + 1; i < n; i++) {
                for (int j = n; j >= c; j--) {
                    a[i][j] -= a[r][j] * a[i][c];
                }
            }
//            out(a);
            r++;
        }
        if (n != r) {
            for (int i = r; i < n; i++) {
                if (Math.abs(a[i][n]) > exp) return 2;
            }
            return 1;
        }
//        out(a);
        // 回代答案
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                a[j][n] -= a[j][i] * a[i][n];
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double[][] a= new double[n][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n + 1; j++)
                a[i][j] = sc.nextDouble();
        }
        int x = guass(a);
        if (x == 0) {                       // 唯一的解
            for (int i = 0; i < n; i++) {
                System.out.printf("%.2f\n", Math.abs(a[i][n]) <= exp ? 0 : a[i][n]);
            }
        }
        else if (x == 1) {                  // 无穷多解
            System.out.println("Infinite group solutions");
        }
        else {                              // 无解
            System.out.println("No solution");
        }
    }
}
