package lanqiao.imporve;

import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/10/17 0:13
 * @Description: xxx的危机
 */
public class WeiJiOfxxx {


    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        int[][] f = new int[n][n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        for (int i = n - 1; i >= 0; i--) { // [i,j]区间dp
            for (int j = i; j < n; j++) {
                if (i == j) f[i][j] = 0;
                else {
                    if (((j - i) & 1) == 1) {
                        f[i][j] = Math.max(f[i + 1][j] + a[i], f[i][j - 1] + a[j]);
                    }
                    else {
                        f[i][j] = Math.min(f[i + 1][j], f[i][j - 1]);
                    }
                }
            }
        }
        System.out.println(f[0][n - 1]);
    }
}
