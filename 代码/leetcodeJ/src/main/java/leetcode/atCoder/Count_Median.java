package leetcode.atCoder;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/10/26 10:04
 * @Description: 查找中位数的个数
 *
 * 性质1：中位数一定是连续的
 * 性质2：上下界寻找
 *
 *
 */
public class Count_Median {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] a = new long[n];
        long[] b = new long[n];
        for (int i = 0; i < n; i++)
        {
            a[i] = sc.nextInt();
            b[i] = sc.nextInt();
        }
        Arrays.sort(a);
        Arrays.sort(b);
        if (n % 2 == 1) {
            long minv = a[n / 2];
            long maxv = b[n / 2];
            System.out.println(maxv - minv + 1);
        }
        else {
            long minv = a[n / 2 - 1] + a[n / 2];
            long maxv = b[n / 2 - 1] + b[n / 2];
            System.out.println(maxv - minv + 1);
        }
    }
}
