package string;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/9/1 10:33
 * @Description: KMP算法
 */
public class KMP {
    // s是模板串, 看s中是否包含t
    public static boolean contains(char[]s, char[] t) {
        int n = t.length, m = s.length;
        int[] nx = new int[n + 1];
        int j = 0, k = -1, i = 0;
        // 求解nx数组, 从0开始匹配, -1表示需要移动到下一个字符。
        nx[j] = -1;
        while (j < n) {
            if (k == -1 || t[j] == t[k]) {
                j++; k++;
                if (j != n && t[j] == t[k]) nx[j] = nx[k];//
                else nx[j] = k;         // 注意这里的j可能等于n，因为从n开始的
            } else k = nx[k];
        }
        j = 0;
        while (i < m) {
            if (j == -1 || s[i] == t[j])  {
                i++;
                j++;
            } else j = nx[j];
            if (j == n) {
                return true;
            }
        }
        return false;
    }

    public static void runKMP(char[] s, char[] t, BufferedWriter bw) throws Exception { // source串， pattern串
        System.out.println(Arrays.toString(s));
        int n = t.length, m = s.length;
        int[] nx = new int[n + 1];
        int j = 0, k = -1, i = 0;
        // 求解nx数组, 从0开始匹配, -1表示需要移动到下一个字符。
        nx[j] = -1;
        while (j < n) {
            if (k == -1 || t[j] == t[k]) {
                j++; k++;
                if (j != n && t[j] == t[k]) nx[j] = nx[k];//
                else nx[j] = k;         // 注意这里的j可能等于n，因为从n开始的
            } else k = nx[k];
        }
        System.out.println(Arrays.toString(nx));
        j = 0;
        while (i < m) {
            if (j == -1 || s[i] == t[j])  {
                i++;
                j++;
            } else j = nx[j];
            if (j == n) {
                 bw.write(i-n+" ");
                j = nx[j];
            }
        }
        bw.flush();
    }

    public static void main(String[] args) throws Exception{
        // 使用缓存读写，否则会超时
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Integer n = Integer.parseInt(br.readLine()); // 读取一行，否则分隔符会被当成字符串
        String s1 = br.readLine();
        Integer m = Integer.parseInt(br.readLine());
        String s2 = br.readLine();
        char[] t = s1.toCharArray();
        char[] s = s2.toCharArray();

        runKMP(s, t, bw);
    }
}
