package math;

import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/11/16 11:44
 * @Description: 球形空间产生器，求解球心坐标
 *
 */
public class QiuZuoBiao {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double[][] a = new double[n + 1][n];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < n; j++)
                a[i][j] = sc.nextDouble();
        }
        double[][] b = new double[n][n + 1];
        double apow = 0;
        for (int j = 0; j < n; j++) apow += a[0][j] * a[0][j];
        for (int i = 0; i < n; i++) {
            double c = 0;
            for (int j = 0; j < n; j++) {
                b[i][j] = 2.0 * (a[i + 1][j] - a[0][j]);
                c += a[i + 1][j] * a[i + 1][j];
            }
            b[i][n] = c - apow;
        }
        int x = Gause.guass(b);
        for (int i = 0; i < n; i++) System.out.println(b[i][n]);
    }
}
