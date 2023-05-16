package lanqiao.imporve;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author: Zekun Fu
 * @date: 2022/10/12 10:13
 * @Description: 娜神平衡
 */
public class NaShenBalance {


    public static boolean valid(Integer []val1, Integer [] val2, int r) { // 可以使用状态压缩
        int x = 0, y = 0, sum1 = 0, sum2 = 0, flag = 1;
        if (val1.length == 0 || val2.length == 0) {
            for (Integer t : val1) sum1 += t;
            for (Integer t: val2) sum2 += t;
            return Math.abs(sum1 - sum2) <= r;
        }
        sum1 = val1[x++];
        while (Math.abs(sum1 - sum2) <= r && flag == 1) {
            flag = 0;
            if (x < val1.length && Math.abs(sum1 + val1[x] - sum2) <= r) {
                flag = 1;
                sum1 += val1[x];
                x++;
            }
            if (y < val2.length && Math.abs(sum1 - val2[y] - sum2) <= r) {
                flag = 1;
                sum2 += val2[y];
                y++;
            }
        }
        if (x == val1.length && y == val2.length) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int r = sc.nextInt();
        int[] a = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            sum += a[i];
        }
        int first = a[0];
        Arrays.sort(a);
        int minv = Integer.MAX_VALUE;
        List<Integer>ans1 = new ArrayList<>();
        List<Integer>ans2 = new ArrayList<>();

        for (int i = 1; i < (1 << n) - 1; i++) {
            List<Integer>a1 = new ArrayList<>();
            List<Integer>a2 = new ArrayList<>();
            int tmp = 0;
            for (int j = 0; j < n; j++) {
                if ((i >> j & 1) != 0) {
                    tmp += a[j];
                    a1.add(a[j]);
                } else a2.add(a[j]);
            }
            if (!valid(a1.toArray(new Integer[a1.size()]), a2.toArray(new Integer[a2.size()]), r)) continue;
            int flag = 0;
            for (int j = 0; j < a1.size(); j++) {
                if (a1.get(j) == first) {
                    flag = 1;
                }
            }
            if (flag == 1) {
                ans1.addAll(a1);
                ans2.addAll(a2);
            }else {
                ans1.addAll(a2);
                ans2.addAll(a1);
            }
            break;
        }
        for (int i = 0; i < ans1.size(); i++) {
            System.out.print(ans1.get(i));
            if (i != ans1.size() - 1) System.out.print(" ");
            else System.out.println();
        }
        for (int i = 0; i < ans2.size(); i++) {
            System.out.print(ans2.get(i));
            if (i != ans2.size() - 1) System.out.print(" ");
            else System.out.println();
        }
    }
}