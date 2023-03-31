package lanqiao.chongci;

import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2023/3/27 9:35
 * @Description:
 * 双指针问题
 *
 * 字符串删减，使得不存在三个以上的连续x
 */
public class Acwing3768_2point {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        String s = sc.nextLine();
        int cnt = 0;
        int ans = 0;
        for (char ch : s.toCharArray()) {
            if (ch == 'x') {
                cnt++;
                if (cnt >= 3) {
                    ans += 1;
                }
            }
            else cnt = 0;
        }
        System.out.println(ans);
    }
}
