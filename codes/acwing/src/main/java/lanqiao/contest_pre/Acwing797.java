package lanqiao.contest_pre;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.Buffer;
import java.util.Scanner;

public class Acwing797 {


    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = in.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        int[] nums = new int[n + 2];
        input = in.readLine().split(" ");
        int pre = 0;
        for (int i = 1; i <= n; i++) {
            int cur = Integer.parseInt(input[i - 1]);
            nums[i] = cur - pre;
            pre = cur;
        }
        for (int i = 0; i < m; i++) {
            input = in.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            int x = Integer.parseInt(input[2]);
            nums[a] += x;
            nums[b + 1] -= x;
        }
        int cur = 0;
        for (int i = 1; i <= n; i++) {
            cur += nums[i];
            out.write(cur + " ");
        }
        out.write("\n");
        out.flush();
        out.close();
        in.close();
    }
}
