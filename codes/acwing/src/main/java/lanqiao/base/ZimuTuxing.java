package lanqiao.base;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/10/5 14:13
 * @Description: 字母图形，基础练习
 *
 * 规律：
 * 第一行从A-(A + m - 1)
 * 第二行BA-
 * 第三行CBA
 * 第四行
 */
public class ZimuTuxing {
    public static char[][] m1 (int n, int m) {
        char[][] ans = new char[n][m];
        for (int i = 0; i < n; i++) {
//            char bg = (char)('A' + i);
//            for (int j = 0; j < i; j++)
//                System.out.print((char)(bg - j));
//            for (int j = 0; j < m - i; j++) {
//                System.out.print((char)('A'+j));
//            }
//            System.out.println();
            char bg = (char) ('A' + i);
            // i 可能会出现大于m的情况。
            for (int j = 0; j < Math.min(i, m); j++) {  // m1(3, 1)会出错
                ans[i][j] = (char) (bg - j);
            }
            for (int j = 0; j < m - i; j++) {
                ans[i][j + i] = (char) ('A' + j);
            }
        }
        return ans;
    }
    public static char[][] m2 (int n, int m) {
        char[][] ans = new char[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j ++) {
                ans[i][j] = (char)('A' + Math.abs(i - j));
            }
        }
        return ans;
    }
    public static boolean check(char[][] a1, char[][] a2) {
        int n = a1.length;
        for (int i = 0; i < n; i++)
            if (!Arrays.equals(a1[i], a2[i]))
                return false;
        return true;
    }
    public static void arrPrint(char[][] chars) {
        for (char[] vec : chars) {
            for (char ch : vec) {
                System.out.print(ch);
            }
            System.out.println();
        }
    }
    public static void test(String[] args) {
        Scanner sc = new Scanner(System.in);
//        m1(2, 1);
        for (int i = 1; i <= 26; i++) {
            for (int j = 1; j <= 26; j++) {
//                arrPrint(m2(i, j));
//                System.out.println();
                if (!check(m1(i, j), m2(i, j))) {
                    System.out.println("i = " + i + " j = " + j);
                    arrPrint(m1(i, j));
                    System.out.println();
                    arrPrint(m2(i, j));
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        for (int i = 0; i < n; i++) {
            char bg = (char)('A' + i);
            // 注意这里不应该小于i,因为i可能大于m，但是不应该输出这么多!!!
            for (int j = 0; j < Math.min(i, m); j++)
                System.out.print((char)(bg - j));
            for (int j = 0; j < m - i; j++) {
                System.out.print((char)('A'+j));
            }
            System.out.println();
        }
    }
}
