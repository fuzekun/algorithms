package lanqiao.imporve;

import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/11/22 9:27
 * @Description: 转圈游戏
 */
public class Zhuanquan {


    public static int qui(int a, int b, int mod) {
        long res = 1;
        while (b != 0) {
            if (b % 2 == 1) res = res * a % mod;
            a = (int)((long)a * a % mod);
            b >>= 1;
        }
        return (int)res;
    }

    public static void main(String[] args) {
//        System.out.println(qiu(2, 3,1000));
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        int x = sc.nextInt();
        int q = qui(10, k, n);
        int ans = (int)(((long)x + (long)q * m) % n);
        System.out.println(ans);
    }
}
