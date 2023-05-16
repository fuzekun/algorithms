package lanqiao.imporve;

import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/10/14 11:17
 * @Description: 蓝桥杯算法训练test
 *
 * 找与他互素的数字
 *  m (1 <= m <= 1000000), K (1 <= K <= 100000000).
 *
 *  对于这种级别的进行找数字，应该是一个二分题目
 *
 * 如果他是素数，那么前K个数字中K - 1个是与他互素的,然后后面有
 *
 * 如果不是，那么
 */
public class Test {

    public static int gcd(int a, int b) {
        if (b == 0) return a;
        else return gcd(b, a % b);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            int cnt = 0, ans = -1;
            for (int i = 1; cnt < k; i++) {
                if (gcd(n, i) == 1) {
                    cnt++;
                    ans = i;
                }
            }
            System.out.println(ans);
        }
    }
}
