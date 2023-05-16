package lanqiao.chongci;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;

/**
 * @author: Zekun Fu
 * @date: 2023/3/26 19:22
 * @Description: 翻硬币
 * 结合上面题目，只能从左向右进行反转。
 *
 */
public class Acwing1208 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] s = sc.nextLine().toCharArray();
        char[] target = sc.nextLine().toCharArray();
        Map<Character, Character>reverse = new HashMap<>();
        reverse.put('*', 'o');
        reverse.put('o', '*');
        // 每次从左到右找到第一个不一样的反转就是最小的, 因为最左边的只能由这个反转得到
        int cnt = 0;
        int n = s.length;
        for (int i = 0; i < n - 1; i++) {
            if (s[i] != target[i]) {
                cnt++;
                s[i + 1] = reverse.get(s[i + 1]);
            }
        }
        System.out.println(cnt);
    }
}
