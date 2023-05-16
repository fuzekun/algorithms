package leetcode.everyDay.December;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/12/6 10:17
 * @Description: 12月1-6号的每日一题
 *
 * 包括灵神的 + leetcode的每日一题
 */
public class E_1_6 {


    public static long getMin(long[] a, long flag) {
        int n = a.length;
        long add = 0;
        long cnt = 0;
        for (int i = 0; i < n; i++, flag *= -1) {
            long cur = a[i] + add;
            if (flag * cur <= 0) {
                long sub = flag - cur;
                add += sub;
                cnt += Math.abs(sub);
            }
//            add += a[i];
//            if (flag * add <= 0) {
//                cnt += Math.abs(flag - add);
//                add = flag;
//            }
        }
        return cnt;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        long[] a = new long[n];
        String[] inputN = in.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(inputN[i]);
            if (i >= 1) a[i] += a[i - 1];
        }
        System.out.println(Math.min(getMin(a, -1), getMin(a, 1)));
    }
}
