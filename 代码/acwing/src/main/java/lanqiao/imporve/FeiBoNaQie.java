package lanqiao.imporve;

import java.util.Scanner;
import lanqiao.FibQuick;
import string.KMP;

/**
 * @author: Zekun Fu
 * @date: 2022/10/19 21:17
 * @Description: 斐波那契串
 */
public class FeiBoNaQie {
    /*
    *
    *
    *   0               1        0
    *   1               1        1
    *   10              1        2
    *   101             1        3
    *   10110           2        4
    *   10110101        3        5
    *   1010110110101   5        6
    *   1010110110101110110101 8 7
    *
    * */


    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        long n = Long.valueOf(sc.nextLine());
        String s = sc.nextLine();
        String pre ="0";
        StringBuilder cur = new StringBuilder("1");
        for (int i = 0; i < n; i++) {
            String tmp = cur.toString();        // 放到元空间
            cur.append(pre);
            pre = tmp;
            // 这里使用KMP进行优化
            if (KMP.contains(cur.toString().toCharArray(), s.toCharArray())) {
                System.out.println(FibQuick.solve(n - i - 1, Long.MAX_VALUE));
                break;
            }
        }
    }

}
