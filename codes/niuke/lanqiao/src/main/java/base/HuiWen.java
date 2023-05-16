package lanqiao.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/10/3 17:11
 * @Description: 基础练习，特殊回文数
 */
public class HuiWen {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 10000; i <= 999999; i++) {
            int sm = 0, x = i;
            List<Integer> tmp = new ArrayList<>();
            while (x != 0) {
                sm += x % 10;
                tmp.add(x % 10);
                x /= 10;
            }
            int flag = 1;
            for (int k = 0, j = tmp.size() - 1; k < j; k++, j--) {
                if (tmp.get(k) != tmp.get(j)) {
                    flag = 0;
                    break;
                }
            }
            if (sm == n && flag == 1) System.out.println(i);
        }
    }
}
