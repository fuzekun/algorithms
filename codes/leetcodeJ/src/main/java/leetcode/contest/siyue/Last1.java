package leetcode.contest.siyue;

import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2023/4/23 10:04
 * @Description:
 */
public class Last1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int[][][] rec = new int[n][n][4];

        // input
        s = s[1].split(",");
        for (int i = 0, id = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 4; k++) {
                    int x = Integer.parseInt(s[id]);
                    id++;
                    rec[i][j][k] = x;
                }
            }
        }

        // 规则，双指针交换 bg和ed列，直到bg >= ed结束
        int bg = 0, ed = n - 1;
        while (bg < ed) {

            // 对于每一行交换两个数字
            for (int i = 0; i < n; i++) {
                int[] tmp = rec[i][bg];
                rec[i][bg] = rec[i][ed];
                rec[i][ed] = tmp;
            }

            bg++;
            ed--;
        }

        // out
        for (int i = 0, id = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 4; k++) {
                    if (id != s.length)
                        System.out.print(rec[i][j][k] + ",");
                    else System.out.println(rec[i][j][k]);
                    id++;
                }
            }
        }
    }
}
/**
 *
 * 3 2,3,4,5,2,2,2,2,3,3,3,3,4,4,4,4,5,5,5,5,6,6,6,6,7,7,7,7,8,8,8,8,9,9,9,9
 * 1 1,2,3,4
 * */
