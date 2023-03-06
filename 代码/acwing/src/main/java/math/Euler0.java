package math;

import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/11/7 12:36
 * @Description: 质因数分解求解欧拉函数
 */
public class Euler0 {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        for (int k = 0; k < a; k++) {
            int n = sc.nextInt();

            int ans = n;
            for (int i = 2; i <= n / i; i++) {
                if (n % i == 0) {
                    ans = ans / i * (i - 1);
                    while (n % i == 0) n /= i;
                }
            }
            if (n != 1) ans =  ans / n * (n - 1);
            System.out.println(ans);
        }
    }
}
