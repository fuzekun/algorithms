package lanqiao.contest_pre;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 *
 * 差分矩阵
 * */
public class Acwing798 {

    private static void change(int[][] nums, int x1, int y1, int x2, int y2, int x) {
        nums[x1][y1] += x;
        nums[x2 + 1][y1] -= x;
        nums[x1][y2 + 1] -= x;
        nums[x2 + 1][y2 + 1] += x;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] input = in.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        int q = Integer.parseInt(input[2]);
        int[][] nums = new int[n + 2][m + 2];
        // 下表从1开始，要不然不好求前缀和
        for (int i = 1; i <= n; i++) {
            input = in.readLine().split(" ");
            for (int j = 1; j <= m; j++) {
                int x = Integer.parseInt(input[j - 1]);
                change(nums, i, j, i, j, x);
            }
        }
        for (int i = 0; i < q; i++) {
            input = in.readLine().split(" ");
            int[] pos = new int[5];
            for (int j = 0; j < 5; j++) pos[j] = Integer.parseInt(input[j]);
            change(nums, pos[0], pos[1], pos[2], pos[3], pos[4]);
        }
        // 求前缀和
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                nums[i][j] += nums[i - 1][j] + nums[i][j - 1] - nums[i - 1][j - 1];
                out.write(Integer.toString(nums[i][j]) + " ");
            }
            out.write("\n");
        }
        out.flush();
        out.close();
        in.close();
    }
}
