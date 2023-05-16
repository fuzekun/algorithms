package pset;

import java.util.HashMap;
import java.util.Set;

/**
 * @author: Zekun Fu
 * @date: 2023/4/27 15:54
 * @Description:
 * 杨辉三角
 * 问第一次出现n是第几个数
 */

/**
 * @author: Zekun Fu
 * @date: 2023/4/27 15:54
 * @Description:
 * 杨辉三角
 * 问第一次出现n是第几个数
 */
public class yanghui {
    private static long[][] c = new long [1000][1000];
    private static HashMap<Long, Long>mp = new HashMap<>();

    private static void init() {
        long cnt = 0;
//        long maxv = 0;
        // 算到100最大值就已经很大了
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    c[i][j] = 1;
                }
                else c[i][j] = c[i - 1][j] + c[i - 1][j - 1];
                cnt++;
//                maxv = Math.max(maxv, c[i][j]);
                if (!mp.containsKey(c[i][j])) mp.put(c[i][j], cnt);
            }
        }
        System.out.println(cnt);
    }
    public static long solve(long n) {
        return mp.getOrDefault(n, n * (n + 1) / 2 + 2);
    }
    public static void main(String[] args) {

//        System.out.println(maxv);
//        long n = sc.nextLong();
//        System.out.println(mp.getOrDefault(n, n * (n + 1) / 2 + 2));
//        Main main = new Main();
         init();
         int flag = 1;
         for (int i = 1; i <= (int)1e4; i++) {
             long rel = Main.main(i);
             long ans = solve(i);
             if (rel != ans) {
                 System.out.println(i + ":rel = " + rel + ", ans = " + ans);
                 flag = 0;
                 break;
             }
         }
         if (flag == 1) System.out.println("相同");
    }
}

class Main {
    public static long main(int n) {
        long[] arr =new long[44725];
        arr[0]=1;
        long k=1L;
        if (n == 1) {
            return 1;
        }
        for (int i = 1;i<44725; i++) {
            for (int j = i; j>=i-16&&j>=1; j--) {
                arr[j] += arr[j - 1];
                if (arr[j] == n) {
//                    System.out.println(k + i-j + 1);
                    return k + i - j + 1;                   // 1->i, 2->i - 1... j->i - j + 1
                }
            }
            k+=(i+1);
        }
        return (1 + n) * n / 2 + 2;
//        System.out.println(((1 + n) * n / 2) + 2);
    }
}
