package lanqiao.imporve;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/10/14 10:41
 * @Description: 士兵杀敌
 */
public class ShiBingShaDi {
    private static final int maxn = (int)1e5 +5;

    private static int[] c = new int[maxn];

    private static int lowbit(int x) {
        return x & -x;
    }
    private static int query(int x) {
        int res = 0;
        for (int i = x; i != 0; i -= lowbit(i))
            res += c[i];
        return res;
    }
    private static void add(int x, int d) {
        for (int i = x; i < maxn; i += lowbit(i))
            c[i] += d;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            Arrays.fill(c, 0);
            int n = sc.nextInt();
            int m = sc.nextInt();
            for (int i = 0; i < n; i++) {
                int a = sc.nextInt();
                add(i + 1, a);
            }
            sc.nextLine();
            for (int i = 0; i < m; i++) {
                String s = sc.nextLine();
                String[] sp = s.split(" ");
                int x = Integer.valueOf(sp[1]);
                int y = Integer.valueOf(sp[2]);
                if (sp[0].compareTo("QUERY") == 0) {
                    System.out.println(query(y) - query(x - 1));
                } else add(x, y);
            }
        }
    }
}
