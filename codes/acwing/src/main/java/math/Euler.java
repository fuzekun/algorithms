package math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/11/7 12:17
 * @Description: 欧拉函数,求1-n中与n互素的数字的个数
 */
public class Euler {
//    private static final int maxn = 1000000;
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] st = new int[n + 1];
        int[] primes = new int[n + 1];
        int[] phi = new int[n + 1];
        int cnt = 0;
        phi[1] = 1;
        for (int i = 2; i <= n; i++) {
            if (st[i] == 0) {
                primes[cnt++] = i;
                phi[i] = i - 1;
            }
            for (int j = 0; primes[j] <= n / i; j++) {
                st[primes[j] * i] = 1;
                if (i % primes[j] == 0) {   // pj也是i的余数，所以直接pj
                    phi[primes[j] * i] = phi[i] * primes[j];
                    break;
                }                           // pj不是i的余数乘以pj * (pj - 1) / pj
                phi[primes[j] * i] = phi[i] * (primes[j] - 1);
            }
        }
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 1; i <= n; i++) {
            out.write(phi[i] + "\n");
        }
        out.flush();
        out.close();
    }
}
