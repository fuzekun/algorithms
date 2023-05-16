package lanqiao.chongci;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2023/3/26 18:11
 * @Description: 砖块
 * 要么全部变成黑色
 * 要么全部变成白色
 */
public class Acwing3777 {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());
        while (T-- != 0) {
            int n = Integer.parseInt(sc.nextLine());
            String line = sc.nextLine();
            char[] chars = line.toCharArray();
            List<Integer> ans = new ArrayList<>();
            Map<Character, Character> reverse = new HashMap<>();
            reverse.put('W', 'B');
            reverse.put('B', 'W');
            // 全部变成黑色
            for (int i = 0; i < n - 1; i++) {
                if (chars[i] == 'W') {
                    ans.add(i + 1);
                    chars[i + 1] = reverse.get(chars[i + 1]);
                }
            }
            if (chars[n - 1] == 'B') {
                System.out.println(ans.size());
                for (int x : ans) {
                    System.out.print(x + " ");
                }
                if (ans.size() != 0) System.out.println();
                continue;
            }
            ans.clear();
            chars = line.toCharArray();
            // 全部变成白色
            for (int i = 0; i < n - 1; i++) {
                if (chars[i] == 'B') {
                    ans.add(i + 1);
                    chars[i + 1] = reverse.get(chars[i + 1]);
                }
            }
            if (chars[n - 1] == 'W') {
                System.out.println(ans.size());
                for (int x : ans) {
                    System.out.print(x + " ");
                }
                if (ans.size() != 0) System.out.println();
                continue;
            }
            System.out.println(-1);
        }
    }
}
