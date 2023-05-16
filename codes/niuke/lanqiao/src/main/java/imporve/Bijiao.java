package lanqiao.imporve;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/10/25 23:21
 * @Description: 蓝桥杯算法训练比较
 * 1000 * 1000 * (l1, r1)中小于x的数字个数是有多少，这个可以O(logn)进行解决。解决的办法是什么呢？线段树 + 排序
 * 	[0, r1]小于等于x的数字的个数，减去[0, l1-1]小于等于x的个数。
 * 	那么可以按照r1进行排序，然后采用离线的方式解决。
 * 	具体操作如下
 * 	1. 按照r1进行排序
 * 	2. 吧小于等于r1的所有数字放入树状数组中，也就是给(a[i],1)
 * 	3. 对于每一个x，求树状数组中小于等于x的数字个数。但是这样只能求[0, x]小于等于的个数。
 * 	但是需要求[l, r]中小于等于x的数字个数有多少个。这个需要采用线段树
 * 	{
 * 	    l, r
 * 	    sum
 * 	}
 */

public class Bijiao {
    private static int maxn = 1005;
    private static int[] c = new int[maxn];
    private static int[] a = new int[maxn];
    private static int n, m;
    private static Scanner sc = new Scanner(System.in);
    private static int lowbit(int x) {
        return x & -x;
    }
    private static void add(int x, int num) {
        for (int i = x; i < maxn; i += lowbit(i)) {
            c[i] += 1;
        }
    }
    private static int sum(int x) {
        int res = 0;
        for (int i = x; i != 0; i -= lowbit(i)) {
            res += c[i];
        }
        return res;
    }
    public static void main(String[] args) {
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt() + 1;
        }
        for (int i = 0; i < m; i++) {
            int a1 = sc.nextInt();
            int b1 = sc.nextInt();
            int a2 = sc.nextInt();
            int b2 = sc.nextInt();
            Arrays.fill(c, 0);
            for (int j = a1 - 1; j < b1; j++) {
                add(a[j], 1);
            }
            for (int j = a2 - 1; j < b2; j++) {
                int x = a[j];
                if (j != b2 - 1)
                    System.out.print(sum(x) + " ");
                else System.out.println(sum(x));
            }
        }
    }
}
