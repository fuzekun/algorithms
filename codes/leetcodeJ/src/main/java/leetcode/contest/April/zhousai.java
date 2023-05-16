package leetcode.contest.April;

import java.util.Map;

/*
*
*   记录4.17 号的周赛
* */
public class zhousai {

    private static String change(String s) {
        s = s.replace("[", "{");
        s = s.replace("]", "}");

        return s;
    }

//    private String changeToArray(char []chars) {
//        /*
//        *  实现字符串，转成多维int数组的操作。
//        * */
//
//    }

    private static String digitSum() {

        String s = "11111222223";
//        s = "000000";
        int k = 3;

        String ans = "";
        while(s.length() > k) {
            char[] chars = s.toCharArray();
            StringBuilder sb = new StringBuilder();
            int cnt = 0;
            int sum = 0;
            for (char ch : chars) {
                sum += ch - '0';
                if (++cnt % k == 0) {
                    sb.append(String.valueOf(sum));
                    System.out.println("sum = " + sum);
                    sum = 0;
                    cnt = 0;
                }
            }
            System.out.println("sumout = " + sum);
            if (cnt != 0) sb.append(String.valueOf(sum));
            s = sb.toString();
            System.out.println("s = " + s);
        }
        return s;
    }
    private static int minimumRounds() {

        int []tasks = {2,2,3,3,2,4,4,4,4,4};
        /*
        *   能完成: %2 == 0 || %3 == 0
        *   除了1以外的所有数字都可以被2和3组合而成。
        *   dp[i] = min(dp[i - 2], dp[i - 3]
        * */

        /*
        *   任意一个数字
        * */
        return 0;
    }

    private static int get(int x) {

        int ans = 0;
        while(x != 0) {
            if (x % 10 == 0) ans++;
            else break;
            x /= 10;
        }
        return ans;
    }
    private static int maxTrailingZeros(int[][] grid) {

        /*
        *
        *   尾随0 ->
        *   1. 以0结尾
        *   2. 路径上有几个5就有几个2
        *
        *   dp(n, m, dir):
        *   dp(n, m, dir) = max(dp(n + 1,
        *
        * 1. 先考虑竖向的
        *   1.1 选择从哪个格子开始走
        *   1.2 选择从什么地方开始拐弯
        * 前缀积：
        * 纵向的乘积，横向的乘积
        *
        * 每个格子考虑四种情况
        * 1. 竖左 ： muls[j][i + 1] * mulh[i][m] / mulh[i][j + 1];
        * 2. 竖右 : muls[j][i + 1] * mulh[i][j + 1] / mulh[i][0]
        * 3. 横上 ： mulh[i][j + 1] * muls[j][i + 1] / muls[j][0]\
        * 4. 横下 : mulh[i][j + 1] * muls[j][n] / muls[j][i + 1]
        * */
        int n = grid.length;
        int m = grid[0].length;
        int [][] mulh = new int[n + 1][m + 1];
        int [][] muls = new int[n + 1][n + 1];
        for (int i = 0; i < n; i++ ) {
            mulh[i][0] = 1;
            for (int j = 1; j <= m; j++) {
                mulh[i][j] = mulh[i][j - 1] * grid[i][j - 1];
            }
        }
        for (int i = 0; i < m; i++) {
            muls[i][0] = 1;
            for (int j = 1; j <= n; j++) {
                muls[i][j] = muls[i][j - 1] * grid[j - 1][i];
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int dr = get(muls[j][i + 1] * mulh[i][m] / mulh[i][j + 1]);
                int dl = Math.max(dr, get(muls[j][i + 1] * mulh[i][j + 1] / mulh[i][0]));
                int ru = Math.max(dl, get(mulh[i][j + 1] * muls[j][i + 1] / muls[j][0]));
                int rd = Math.max(ru, get(mulh[i][j + 1] * muls[j][n] / muls[j][i + 1]));

                ans = Math.max(ans, rd);
            }
        }

        return 0;
    }

    private static class Tuple {
        public int numb;           // 5
        public int numc;           // 2
        Tuple() {
            numb = numc = 0;
        }
        public void add(Tuple o) {
            numb += o.numb;
            numc += o.numc;
        }

        @Override
        public String toString() {
            return " numb = " + numb + " numc = " + numc;
        }
    }
    /*
    *   获取质因子中2和5的个数
    * */
    static Tuple getNums(int num) {
        Tuple t = new Tuple();
        while (num % 2 == 0) { t.numb ++; num /= 2;}
        while (num % 5 == 0) { t.numc ++; num /= 5;}
//        System.out.println("num = " + num + " t = (" + t + ")");
        return t;
    }
    private static int maxTrailingZeros2(int [][] grid) {
        /*
        *[j + 1, m - 1] sum[m - 1 + 1] - sum[j + 1 - 1 + 1]
        *
        *
        *  质因子中2的数目 + 素数中5的数目
        *
        *   sum[i][m] - sum[i][j]
        * */


        int n = grid.length, m = grid[0].length;
//        System.out.println("n = " + n + " m = " + m);
        Tuple [][]sumh = new Tuple[n][m + 1];
        Tuple [][]sums = new Tuple[m][n + 1];
        int ans = 0;

        // 预处理一下.得到行和列的前缀和
        for(int i = 0; i < n; i++) {
            sumh[i][0] = new Tuple();
            for (int j = 1; j <= m; j++) {
                Tuple t = new Tuple();
                t.add(sumh[i][j - 1]);
                t.add(getNums(grid[i][j - 1]));
                sumh[i][j] = t;
            }
//            System.out.println("i = " + i + " " + sumh[i][m]);
        }

        for(int i = 0; i < m; i++) {
            sums[i][0] = new Tuple();
            for (int j = 1; j <= n; j++) {
                Tuple t = new Tuple();
                t.add(sums[i][j - 1]);
                t.add(getNums(grid[j - 1][i]));
                sums[i][j] = t;
            }
//            System.out.println("j = " + i + " t = (" + sums[i][n] + ")");
        }


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int dr = Math.min(sums[j][i + 1].numb + sumh[i][m].numb - sumh[i][j + 1].numb,
                                sums[j][i + 1].numc + sumh[i][m].numc - sumh[i][j + 1].numc);

                int dl = Math.min(sums[j][i + 1].numb + sumh[i][j].numb,
                                sums[j][i + 1].numc + sumh[i][j].numc);
                // 向上的包括[i, n - 1], 向左的不包括[0, j - 1]
                int ul = Math.min(sums[j][n].numb - sums[j][i].numb + sumh[i][j].numb,
                        sums[j][n].numc - sums[j][i].numb + sumh[i][j].numc);
                // 向上的包括[i, n - 1], 向右的不包括[j + 1,  m - 1]
                int ur = Math.min(sums[j][n].numb - sums[j][i].numb + sumh[i][m].numb - sumh[i][j + 1].numb,
                        sums[j][n].numc - sums[j][i].numc + sumh[i][m].numc - sumh[i][j + 1].numc);
                ans = Math.max(ans, Math.max(Math.max(Math.max(dl, dr), ul), ur));
            }
        }
        return ans;
    }
    public static void main(String[] args) {
//        System.out.println("Ans = " + digitSum());
//        System.out.println("cnt = " + get(18000));
        String s = "[[899,727,165,249,531,300,542,890],[981,587,565,943,875,498,582,672],[106,902,524,725,699,778,365,220]]";
//        System.out.println(change(s));
        System.out.println("num = 900" + getNums(900));
        int [][]grid = {{899,727,165,249,531,300,542,890},
                {981,587,565,943,875,498,582,672},
                {106,902,524,725,699,778,365,220}};
        System.out.println("ans = " + maxTrailingZeros2(grid));
    }
}
