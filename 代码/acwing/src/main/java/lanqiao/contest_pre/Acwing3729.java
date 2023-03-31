package lanqiao.contest_pre;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 *
 * 差分
 * */
public class Acwing3729 {

    // 暴力骗分
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out =  new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(in.readLine());
        while (T-- != 0) {
            int n = Integer.parseInt(in.readLine());
            String[] input = in.readLine().split(" ");
            int[] nums = new int[n + 1];
            List<Integer> ans = new ArrayList<>();
            int m = 0;
            for (int i = 1; i <= n; i++) {
                int a = Integer.parseInt(input[i - 1]);
//            for (int j = n; j > Math.min(n - i, a); j--) {
//                nums[i] = 1;
//            }
                m++;
                int bg = Math.max(0, m - a);
                nums[bg] += 1;
                nums[m] -= 1;
//                ans.add(0);
//                int m = ans.size();
//                for (int j = m - 1; j >= Math.max(0, m - a); j--) {
//                    ans.set(j, 1);
//                }
            }
            int cur = 0;
            for (int i = 0; i < n; i++) {
                cur += nums[i];
                out.write((cur > 0 ? 1 : 0) + " ");
            }
            out.write("\n");
        }
        out.flush();
        out.close();
        in.close();
    }
}
