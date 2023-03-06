package lanqiao.imporve;

import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/10/20 23:21
 * @Description: 移动，算法训练
 */
public class YiDong {

    private static final int maxn = (int)1e6 + 5;
    private static int[] a = new int[maxn];
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        int cur = 0;
        for (int i = 0; i < m; i++) {
            int x = sc.nextInt();
            cur = (n + x + cur) % n;
//            while (cur < 0) cur = (cur + n) % n;
            for (int j = 0; j < k; j++) {
                if (j != k - 1)
                    System.out.print(a[(cur + j) % n] + " ");
                else System.out.println(a[(cur + j) % n]);
            }
        }
    }
}
