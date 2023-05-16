package lanqiao.imporve;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author: Zekun Fu
 * @date: 2022/10/13 14:58
 * @Description: 24点
 * 1. 第一个是否减去乘以或者加上？
 * 2. 除法必须要整除
 * 3. 括号可以转化成4个数字的全排列
 *
 *
 */
public class DianOf24 {
    // 24种全排列
    private static int[][] b = new int[24][4];
    private static int[] a = new int[4];
    private static int idx = 0;
    private final static char[] opr = {'+', '*', '-', '/'};
    private static int id;
    public static void generate_permutation(int n,int A[],int cur)
    {
        if(cur == n){
            b[idx++] = Arrays.copyOf(A, n);
        }
        else for(int i = 1;i <= n;i++){
            int ok = 1;
            for(int j = 0;j < cur;j++){
                if(A[j] == i)ok = 0;
            }
            if(ok == 1){
                A[cur] = i;
                generate_permutation(n, A, cur + 1);
            }
        }
    }
    private static int cal(int a, int b, char op) {

        if (op == '+') return a + b;
        if (op == '-') return a - b;
        if (op == '*') {
            if (a == 0) return b;
            return a * b;
        }
        if (b !=  0 && a % b == 0) return a / b;
        return 0;                           // 如果是0代表无法操作
    }
    private static Set<Integer> dfs2(int cur) {
        if (cur == 4) return new HashSet<>();
        Set<Integer>child = dfs2(cur + 1);
        Set<Integer>ans = new HashSet<>();
        for (Integer x: child) {
            for (int i = 0; i < 4; i++) {
                int y = cal(a[b[id][cur] - 1], x, opr[i]);
                if (y != 0)
                    ans.add(y);
            }
        }
        if (ans.isEmpty()) ans.add(a[b[id][cur] - 1]);
        return ans;
    }
    private static int dfs(int cur, int total) {
        if (cur == 4) {
            if (total <= 24) return total;
            return 0;
        }
        int ans = 0;
        Set<Integer>back = dfs2(cur);
        for (Integer x : back) {
            for (int i = 0; i < 4; i++) {
                int calAns = cal(total, x, opr[i]);
                if (calAns > 24) continue;
                ans = Math.max(ans, calAns);
            }
        }
        for (int i = 0; i < 4; i++) {
            int calAns = cal(total, a[b[id][cur] - 1], opr[i]);
            if (calAns == 0) continue;          // 不可以整除
            ans = Math.max(ans, dfs(cur + 1, calAns));
        }
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // 生成1-4的全排列
        generate_permutation(4, new int[4], 0);
        while (n-- != 0) {
            for (int i = 0; i < 4; i++) {
                a[i] = sc.nextInt();
            }
            int ans = 0;
            for (int i = 0; i < idx; i++) {
                id = i;
                ans = Math.max(dfs(0, 0), ans);
            }
            System.out.println(ans);
        }
    }
}
/*
1
3
3
3
3
*/


/*
1
1
1
1
10

(10 + 1) * (1 + 1)： 很明显这种没法表示,也就是说，括号没法计算

如果想要计算，应该加上一个函数：

dfs2(cur) : 返回从cur -> 4的所有可能结果,相当于从这加上了一个括号


1
11
13
11
5

*/