package lanqiao.imporve;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/10/14 19:17
 * @Description: 木棍
 *
 * 很重要的性质：长度相等，那么应该是和的约数。
 * 那么问题就转换成了最小满足的约数。
 * 那么问题就成了对于每一个约数判断是否满足条件
 * 或者反过来说，划分的个数越多，就越有可能
 *
 * n <= 64
 * a[i] <= 50
 * 50 * 64
 *
 * 最小应该是maxv, 第二小是maxv + minv,
 * maxv + minv1, minv2...
 */
public class Sticks {
    private static int n;
    private static int[] vis = new int[100];
    private static boolean check2(int cur, int sum, int used, int limit, int[] a) {
        if (cur == n) return used == n;                 // 一旦用了n个，sum一定为0，因为是整除的
        if (used == n) return true;
        for (int i = cur; i < n; i++) {
            if (vis[i] == 1 || sum + a[i] > limit) continue;
            if (i > 0 && vis[i - 1] == 0 && a[i] == a[i- 1]) continue; // 剪枝2
            if (sum + a[i] == limit) {
                vis[i] = 1;
                if (check2(0, 0, used + 1, limit, a)) return true; // 需要从头看是否还有没有用过的
                vis[i] = 0;                                      // 这里不要忘了回溯
                return false;                                   // 剪枝3，不允许前面有空桶
            }
            else {
                vis[i] = 1;
                if (check2(cur + 1, sum + a[i], used + 1, limit, a)) return true;
                vis[i] = 0;
                if (sum == 0) return false;                     // 剪枝3，不允许有空桶
            }
        }
        return false;
    }
    public static boolean check(int[] a, int limit, int vis[], int sum, int used) {
        if (used == n) return sum == 0;
        for (int i = 0; i < n; i++) {
            if (vis[i] == 1 || sum + a[i] > limit) continue;
            vis[i] = 1;
            if (check(a, limit, vis, (sum + a[i]) % limit, used + 1))
                return true;
            vis[i] = 0;
            while (i + 1 < n && a[i] == a[i + 1]) i++;            // 剪枝2，如果相等,上一个用了不行，这个也肯定不行
            if (sum == 0) break;                 // 剪枝3，对于空桶来说，不用继续向下遍历了，第一个不行，后边都不行
        }
        return false;
    }

    public static boolean dfs(int clen, int cnum, int cur, int limit, int k, int[] a)// 当前累计的长度,当前凑成的根数,当前找的位置,
    {
        if (cur >= n)
            return false;
        if (cnum == k)
            return true;// (这里边界条件不再只是遍历到叶子)
        for (int i = cur; i < n; i++) {
            if (vis[i] != 0||(i!=0&&vis[i-1]==0&&a[i]==a[i-1])) {continue;}//已经被选上了,或者前一个没被选上,后一个和它等长的肯定也不行
            if (clen + a[i] == limit)// 找到凑成的最后一根,说明这组完成
            {
                vis[i] = 1;
                if (dfs(0, cnum + 1, 0, limit, k, a))//必须从头再开始
                    return true;// 当其余的都找齐了
                vis[i] = 0;
                return false;
            }
            if (clen + a[i] < limit) {//没凑成
                vis[i] = 1;//选上它
                if (dfs(clen + a[i], cnum, cur + 1, limit, k, a))//从后面开始
                    return true;
                vis[i] = 0;
                if (clen == 0)
                    return false;// 表示返回了最开始的那一层还是没有办法,说明选这个长度是错误的

            }

        }
        return false;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            n = sc.nextInt();
            Arrays.fill(vis, 0);
            int[] a = new int[n];
            if (n != 0) {
                int flag=0;
                int sum = 0;
                for (int i = 0; i < n; i++) {
                    a[i] = sc.nextInt();
                    sum += a[i];
                }
                for (int i = 0; i < n; i++) {//排序
                    for (int j = 0; j < n - i - 1; j++) {
                        if (a[j] < a[j + 1]) {
                            int t;
                            t = a[j];
                            a[j] = a[j+ 1];
                            a[j+ 1] = t;

                        }

                    }
                }

                for (int len = a[0]; len <= sum/2 ; len++) {//注意,当len>sum/2的时候只有一种情况,这里可以节省时间
                    if (sum % len != 0)//必须是整倍数
                        continue;
                    if (dfs(0, 0, 0, len, sum / len, a))
                    {flag=len;break;}
                }

                if(flag!=0) System.out.println(flag);
                else System.out.println(sum);

            }
            else {
                return;
            }
        }

    }
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        n = sc.nextInt();
//        while (n != 0) {
//            int[] a = new int[n];
//            int sum = 0;
//            for (int i = 0; i < n; i++) {
//                a[i] = sc.nextInt();
//                sum += a[i];
//            }
//            // 逆序排序
//            Arrays.sort(a);
//            for (int i = 0, j = n - 1; i < j; i++, j--) {
//                int tmp = a[i];
//                a[i] = a[j];
//                a[j] = tmp;
//            }
//            int flag = 0;
//            for (int i = a[0]; i <= sum / 2; i++) {
//                if (sum % i == 0) {
//                    Arrays.fill(vis, 0);
//                    if (dfs(0, 0, 0, i, sum / i, a)) { // 第一个可以整除，并且可以组合成功的
//                        flag = i;
//                        break;
//                    }
//                }
//            }
//            if (flag != 0) System.out.println(flag);
//            else System.out.println(sum);
//            n = sc.nextInt();
//            if (n == 0) break;
//        }
//    }
}
