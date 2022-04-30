package base.tanxin;

import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Tanxin {



    @Test
    public void QujianChoose() {
        /*
        *
        *   区间选点
        * */
        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
        int n = 3;
        int[][] a = {{-1, 1}, {2, 4}, {3, 5}};
//        for (int i = 0; i < n; i++) {
//            a[i][0] = sc.nextInt();
//            a[i][1] = sc.nextInt();
//        }
        Arrays.sort(a, (x, y)->(x[1] - y[1])); // 按照从小到大进行刨析
        for (int[] b : a) {
            System.out.println(b[0] + " " + b[1]);
        }
        int ans = 1, pre = a[0][1];
        for (int i = 1; i < n; i++) {
            if (pre < a[i][0]) {
                ans++;
                pre = a[i][1];
            }
        }
        System.out.println(ans);
    }

    /**
     *  合并果子，huffman树
     *
     */

    public void huffman() {

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Queue<Integer>que = new PriorityQueue<>();
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            que.add(a);
        }

        int ans = 0;
        for (int i = 0; i < n - 1; i++) {
            int x = que.poll(), y = que.poll();
            int t = x + y;
            System.out.println("x = " + x + " y = " + y);
            ans += t;
            que.add(t);
        }
        System.out.println(ans);
    }
}
