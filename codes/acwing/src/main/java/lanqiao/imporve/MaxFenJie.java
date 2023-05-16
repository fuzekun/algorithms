package lanqiao.imporve;

import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/10/13 23:43
 * @Description: 最大分解
 * 直接求每一个数字的最大约数就行了。
 * 直到求的数字是一个素数，然后直接到1就可以了。
 */
public class MaxFenJie {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int ans = 1;
        while (n != 1) {
            int up = (int)Math.sqrt(n + 1);
            int flag = 0;
            for (int i = 2; i <= up; i++) {
                if (n % i == 0) {
                    n /= i;
                    flag = 1;
                    break;
                }
            }
            if (flag == 1) ans += n;
            else break;
        }
        System.out.println(ans);
    }
}

