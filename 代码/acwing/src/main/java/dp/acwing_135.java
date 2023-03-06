package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * @author: Zekun Fu
 * @date: 2023/1/1 20:42
 * @Description: 最大子序列和
 *
 *
 */
public class acwing_135 {


    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] input = in.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        String[] input2 = in.readLine().split(" ");
        int[] s = new int[n + 1];
        s[0] = 0;
        for (int i = 1; i <= n; i++) {
            s[i] = Integer.parseInt(input2[i - 1]);
            s[i] += s[i - 1];
        }
        int res = Integer.MIN_VALUE;
        Deque<Integer> que = new ArrayDeque<>();
        for (int i = 0; i <= n; i++) {
            while (!que.isEmpty() && que.getFirst() < i - m) que.pollFirst();
            if (!que.isEmpty())
                res = Math.max(res, s[i] - s[que.getFirst()]);
            while (!que.isEmpty() && s[que.getLast()] >= s[i]) que.pollLast();
            que.addLast(i);
        }
        System.out.println(res);
    }
}
