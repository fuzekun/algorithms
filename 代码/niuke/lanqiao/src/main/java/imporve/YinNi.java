package lanqiao.imporve;

import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/10/17 16:17
 * @Description: 最大消费
 */
public class YinNi {

    public static void main(String args[]) {
        int[][] a = new int[105][105];
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = Math.min(a[i][j], sc.nextInt());
            }
        }

        long ans = 0;
        for (int i = 0; i < n; i++) {
            long maxv = 0;
            for (int j = 0; j < m; j++) {
                int c = sc.nextInt();
                maxv = Math.max(maxv, (long)c * a[i][j]);
            }
            ans += maxv;
        }

        System.out.println(ans);
    }
}
