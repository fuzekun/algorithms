package leetcode.contest.sanyue;

import leetcode.utils.ChangeToArrayOrList;

import java.util.*;

/**
 *
 * 1. 检查边界条件
 * 2. 检查数据范围
 * 3. 检查
 * */
public class Forth {
    public int[] evenOddBit(int n) {
        int odd = 0, even = 0;
        int i = 0;
        while (n != 0) {
            if (i % 2 == 1) {
                odd += n % 2;
            }
            else even += n % 2;
            n /= 2;
            i++;
        }
        return new int[]{even, odd};
    }
    private int[][] dir = new int[][]{{-2, 1}, {-2, -1}, {2, 1}, {2, -1},
            {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};

    private boolean dfs(int[][] grid, int x, int y, int cur, int n) {
        if (cur == n * n - 1) return true;
        for (int i = 0; i < 8; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];
            if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
            if (grid[nx][ny] != cur + 1) continue;  // 八个方向必须有一个方向等于cur + 1
            if(dfs(grid, nx, ny, cur + 1, n))   // 下一个阶段
                return true;
        }
        return false;
    }
    public boolean checkValidGrid(int[][] grid) {
        int n = grid.length;
        return dfs(grid, 0, 0, 0, n);
    }
    private int ans = 0;
    private void dfs(int[] nums, int k, int cur, Set<Integer>set) {
        if (cur == nums.length) {
            ans++;
            return ;
        }
        dfs(nums, k, cur + 1, set);
        if (!set.contains(nums[cur] - k)) {
            set.add(nums[cur]);
            dfs(nums, k, cur + 1, set);
            set.remove(nums[cur]);
        }
    }
    public int beautifulSubsets(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
//        Set<Integer> confilict[] = new HashSet[n];
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                if (Math.abs(nums[i] - nums[j]) == k)
//                    confilict[i].add(j);
//            }
//        }
//        int ans = 0;
//        for (int i = 1; i < 1<<n; i++) {
//            Set<Integer>set = new HashSet<>();
//            for (int j = 0; j < n; j++) {
//                if ((i >> j & 1) == 1) {
//                    set.add(nums[j]);
//                }
//            }
//            int flag = 1;
//            for (Integer x : set) {
//                if (set.contains(x + k) || set.contains(x - k)) {
//                    flag = 0;
//                    break;
//                }
//            }
//            if (flag == 1) ans++;
//    }
        dfs(nums, k, 0, new HashSet<>());
        return ans - 1;
    }
    public int findSmallestInteger(int[] nums, int value)  {
        HashMap<Integer, Integer>mp = new HashMap<>();
        for (int x : nums) {
            int m = (x % value + value) % value;
            if (m < 0) throw new RuntimeException("取余出错" + x);
            mp.put(m, mp.getOrDefault(m, 0) + 1);
        }
        for (int i = 0; i <= nums.length; i++) {
            int m = i % value;
            if (mp.getOrDefault(m, 0) == 0)
                return i;
//            System.out.println(i);
            mp.put(m, mp.get(m) - 1);
        }
        return nums.length;
    }
    public static void main(String[] args) {
        String ss = " [[0,3,6],[5,8,1],[2,7,4]]";
//        String s = "[942,231,247,267,741,320,844,276,578,659,96,697,801,892,752,948,176,92,469,595]";
//        String s = "[2,4,6]";
        String s = " [1,2,3,4,5,6,7,8,-14]";
        int[] nums = ChangeToArrayOrList.changTo1DIntArray(s);
        int[][] numss = ChangeToArrayOrList.changeTo2DIntArray(ss);
        Forth f = new Forth();
//        System.out.println(f.checkValidGrid(numss));
//        int k = 473;
//        System.out.println(f.beautifulSubsets(nums, k));
        System.out.println(f.findSmallestInteger(nums, 7));
    }

}
