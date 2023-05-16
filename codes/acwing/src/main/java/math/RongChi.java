package math;

import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/11/24 12:17
 * @Description: 容斥原理
 */
public class RongChi {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] a = new int[m];
        for (int i = 0; i < m; i++) {
            a[i] = sc.nextInt();
        }
        int ans = 0;
        for (int i = 1; i < 1 << m; i++) {
            long x = 1, cnt = 0;
            for (int j = 0; j < m; j++) {
                if ((i >> j & 1) == 1) {
                    cnt++;
                    x *= a[j];
                    if (x > n) {
                        cnt = -1;
                        break;
                    }
                }
            }
            //                System.out.println(x);
            if (cnt == -1) continue;
            if (cnt % 2 == 1) ans += n / x;
            else ans -= n / x;
        }
        System.out.println(ans);
    }
}
