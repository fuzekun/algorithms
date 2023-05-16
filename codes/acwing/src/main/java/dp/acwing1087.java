package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * @author: Zekun Fu
 * @date: 2023/1/2 19:31
 * @Description: 修剪草坪
 *
 * 1. 如果中断了，可以直接在选择k只
 * 2. 如果没有中断，不能再直接选择
 */
public class acwing1087 {

//    private static final int N = (int) 1e5 + 5;
    private static long[] f;
    private static long[] s;
    private static long g(int i ) {
        if (i == 0) return 0;
        return f[i - 1] - s[i];
    }
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] input =  in.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        s = new long[n + 2];
        s[0] = 0;
        for (int i = 1; i <= n; i++) {
            s[i] = s[i - 1] + Integer.parseInt(in.readLine());
        }
        f = new long[n + 1];
        Deque<Integer>que = new ArrayDeque<>();
        que.addLast(0);
        for (int i = 1; i <= n; i++) {
            if (que.getFirst() < i - m) que.pollFirst();
            f[i] = Math.max(f[i - 1], g(que.getFirst()) + s[i]);
            while (!que.isEmpty() && g(que.getLast()) <= g(i)) que.pollLast();
            que.addLast(i);
        }
        System.out.println(f[n]);
    }
}
