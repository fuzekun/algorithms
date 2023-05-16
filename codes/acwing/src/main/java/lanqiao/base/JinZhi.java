package lanqiao.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/10/3 17:01
 * @Description: 16进制转换十进制
 *
 */
public class JinZhi {

    public static void main(String[] args) {
        char[] num = {'0','1','2','3','4','5','6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Character> ans = new ArrayList<>();
        while (n != 0) {
            ans.add(num[n % 16]);
            n /= 16;
        }
        for (int i = ans.size() - 1; i >= 0; i--) {
            System.out.print(ans.get(i));
        }
        if (ans.size() == 0) System.out.println(0);
        else System.out.println();
    }
}
