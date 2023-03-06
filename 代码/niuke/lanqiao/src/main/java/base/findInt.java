package lanqiao.base;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/10/5 13:45
 * @Description: 蓝桥杯查找整数
 */
public class findInt {
    public static void main(String[] args) throws Exception{
//        Scanner sc = new Scanner(System.in);
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        String[] numbsers = bf.readLine().split(" ");
        final int maxn = 1004;
        int[] a = new int[maxn];
        for (int i = 0; i < n; i++) a[i] = Integer.parseInt(numbsers[i]);
        int i = 0;
        int k = Integer.parseInt(bf.readLine());
        // 看询问的下标是i还是i + 1
        for (i = 0; i < n; i++) {
            if (a[i] == k) {
                System.out.println(i + 1);
                break;
            }
        }
        if (i == n) System.out.println(-1);
//        int maxv = Integer.MIN_VALUE;
//        int minv = Integer.MAX_VALUE;
//        int sum = 0;
//        for (int i = 0; i < n; i++) {
//            int a = Integer.parseInt(numbsers[i]);
//            maxv = Math.max(maxv, a);
//            minv = Math.min(minv, a);
//            sum += a;
//        }
//        System.out.println(maxv + "\n" + minv + "\n" + sum);
    }
}
