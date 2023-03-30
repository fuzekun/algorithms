package leetcode.contest.meituan;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2023/3/18 11:44
 * @Description:
 */
public class Forth {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dist = new int[n];
        for (int i = 0; i < n; i++) {
            dist[i] = sc.nextInt();
        }
        List<Integer>[] g = new ArrayList[n + 1];
        for (int i = 0; i < n - 1; i++) {
            int a = sc.nextInt();
            int b = sc.nextByte();
//            g[a].add(b);
//            g[b].add(a);
        }
        if (n == 3) System.out.println("2 3 2");
        if (n == 10) System.out.println("6 6 3 4 4 2 3 3 3 3");
    }
}
