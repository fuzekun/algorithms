package lanqiao.imporve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/10/28 17:29
 * @Description:
 */
public class ShuZuYiDong {

//    1 2 3 4 5
//    4 5 1 2 3
//    3 4 5 1 2
//    之前的数字都加上了(n - Bi - 1)；
//    之后的数字都减去了(n - Bi - 1)；
//    使用ha是存储每一个数字的位置，找到这个位置，
//    遍历hash表，之前的数字都加上n - 1 - Bi，之后的数字都减去n - 1 - Bi

    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] firstLine = in.readLine().split(" ");
        int n = Integer.valueOf(firstLine[0]);
        int m = Integer.parseInt(firstLine[1]);
        HashMap<Integer, Integer> mp = new HashMap<>();
        int total = 0;              // 循环右移的位数
        for (int i = 0; i < m; i++) {
            int x = Integer.valueOf(in.readLine());
            int b = (x - 1 + total) % n;      // x所在的下标b
            total += (n - 1 - b);             // 现在右移的位数
        }
//        for (int  i = 1; i <= n; i++) mp.put(i, i - 1);
//        for (int i = 0; i < m; i++) {
//            int x = Integer.valueOf(in.readLine());
//            int b = mp.get(x);
//            for (Integer key : mp.keySet()) { // 循环右移n - i - 1位
//                int p = mp.get(key);
//                p = (p + n - 1 - b) % n;
//                mp.put(key, p);
//            }
//        }
        int[] ans = new int[n];
        for (int i = 1; i <= n; i++) {
            int p = (i - 1 + total) % n;
            ans[p] = i;
        }
        for (int x : ans) out.write(x + " ");
        out.flush();
        out.close();
    }
}
