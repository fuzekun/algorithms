package lanqiao.chongci;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2023/3/26 19:29
 * @Description: 费解的开关
 *
 * 递推的意义，就是从头开始，第一个不一样的开始变。
 * 因为第一个没有其他的可以引起它变化的，所以一旦不一样，只能改变它
 *
 *  第一行确定顺序之后，每一行的0只能由下一行进行改变。
 *  最后一行如果还有0，说明没法完成。
 */
public class Acwing95 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        while (n-- != 0) {
            // 输入
            int[][] nums = new int[5][5];
            for (int i = 0; i < 5; i++) {
                char[] input = sc.nextLine().toCharArray();
                for (int j = 0; j < 5; j++) {
                    nums[i][j] = input[j] - '0';
                }
            }

            // 最多为6次
            int res = 7;
            for (int mask = 0; mask < 1 << 5; mask ++) {
                // 进行拷贝
                int[][] backup;
                backup = Arrays.stream(nums).map(int[]::clone).toArray(int[][]::new);

                // 统计数量
                int cnt = 0;
                for (int j = 0; j < 5; j++) {
                    if ((mask >> j & 1) == 1) {
                        cnt++;
                        change(nums, 0, j);
                    }
                }
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (nums[i][j] == 0) {
                            cnt++;
                            change(nums, i + 1, j);
                        }
                    }
                }

                // 更新
                boolean flag = Arrays.stream(nums[4]).sum() == 5;
                if (flag) res = Math.min(res, cnt);
                nums = Arrays.stream(backup).map(int[]::clone).toArray(int[][]::new);
            }

            System.out.println(res > 6 ? -1 : res);

            if (sc.hasNextLine()) sc.nextLine();
        }
    }

    private static void change(int[][] nums, int x, int y) {
        int[][] dirs = {{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int i = 0; i < 5; i++) {
            int nx = x + dirs[i][0];
            int ny = y + dirs[i][1];
            if (nx >= 5 || ny >= 5 || nx < 0 || ny < 0) continue;
            nums[nx][ny] ^= 1;
        }
    }
}
