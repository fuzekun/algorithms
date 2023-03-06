package math;

import java.io.*;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/11/5 10:34
 * @Description: 筛选质数因子
 */
public class ShaiZhiShuYinZi {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception{
        int m = Integer.valueOf(in.readLine());
        for (int j = 0; j < m; j++) {
            int n = Integer.valueOf(in.readLine());
            for (int i = 2; i <= n / i; i++) {
                int cnt = 0;
                while (n % i == 0) {
                    n /= i;
                    cnt++;
                }
                if (cnt != 0) out.write(i + " " + cnt + "\n");
            }
            if (n != 1) out.write(n + " " + 1);
            out.write("\n");
        }
        out.flush();
        out.close();
    }
}
