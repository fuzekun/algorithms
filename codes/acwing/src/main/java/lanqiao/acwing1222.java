package lanqiao;

import Graph.Acwing_367;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/10/3 12:58
 * @Description: 密码脱落
 */

public class acwing1222 {

    // 预处理
    private String preProcess(String s) {
        char[] chars = s.toCharArray();
        StringBuilder ans = new StringBuilder("#");

        for (char ch : chars) {
            ans.append(ch);
            ans.append("#");
        }
        return ans.toString();
    }

    // 根据位置得到字符串
    private String afterProcess(String s, int[] a) {
        int pos = a[0];
        int maxv = a[1];
        s = s.substring(pos - maxv + 1, pos + maxv);
        char[] chars = s.toCharArray();
        StringBuilder ans = new StringBuilder("");
        for (char ch : chars) {
            if (ch != '#') ans.append(ch);
        }
        return ans.toString();
    }

    // 马拉车算法
    private int[] manacher(String s) {
        // System.out.println(s);
        int n = s.length();
        char[] chars = s.toCharArray();
        int R = -1;
        int C = -1;
        int maxv = Integer.MIN_VALUE;
        int pos = -1;
        int[] p = new int[n];
        Arrays.fill(p, 1);

        for (int i = 0; i < n; i++) {
            p[i] = R > i ? Math.min(p[2 * C - i], R - i) : 1;

            while(i + p[i] < n && i - p[i] >= 0 && chars[i + p[i]] == chars[i - p[i]])
                p[i]++;

            if (p[i] + i > R) {
                R = p[i] + i;
                C = i;
            }

            if (maxv < p[i]) {
                maxv = p[i];
                pos = i;
            }
        }

        int []ans = {pos, maxv - 1};
        return ans;
    }
    public String longestPalindrome(String s) {
        String pre = preProcess(s);
        int a[] = manacher(pre);
        String ans = afterProcess(pre, a);
        return ans;
    }

    // 最长回文子序列
    public static int getLPS(String s) {
        char[] chars = s.toCharArray();
        int length = chars.length;
        // 第一维参数表示起始位置的坐标，第二维参数表示长度，使用 0 表示长度 1
        int[][] lps = new int[length][length];
        for (int i = 0; i < length; i++) {
            lps[i][i] = 1; // 单个字符的最长回文子序列长度为1，特殊对待一下
        }
        // (i + 1) 表示当前循环的子字符串长度
        for (int i = 1; i < length; i++) {
            // j 表示当前循环的字符串起始坐标
            for (int j = 0; i + j < length; j++) {
                // 即当前循环的子字符串坐标为 [j, i + j]
                // 所以第一个字符是 chars[j]，最后一个字符就是 chars[i + j]
                if (chars[j] == chars[i + j]) {
                    lps[j][i + j] = lps[j + 1][i + j - 1] + 2;
                } else {
                    lps[j][i + j] = Math.max(lps[j][i + j - 1], lps[j + 1][i + j]);
                }
            }
        }
        // 最大值一定在以0为起始点，长度为 length - 1 的位置
        return lps[0][length - 1];
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int ans = s.length() - getLPS(s);
        System.out.println(ans);
    }
}
