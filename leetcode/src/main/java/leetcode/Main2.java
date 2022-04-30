package leetcode;

import javafx.util.Pair;
import org.junit.Test;

import java.util.*;

public class Main2 {

    /*
    *
    *   27号开始的每日一题
    * @fuzekun
    * */
    private Main2() {
        // 私有构造器，直接不让外面的任用这个类
    }

    private static Main2 main2 =  new Main2();
    @SuppressWarnings("unchecked") // 别警告我的强转了, 一定不会错误的
    private void bfs(int[][] heights, int[][] vis, Queue<? super Pair> que) {
        int[][] dirs = {{-1,0},{1, 0},{0, -1},{0, 1}};
        int m = heights.length, n = heights[0].length;
        while(!que.isEmpty()) {
            Pair<Integer, Integer>p = (Pair<Integer, Integer>) que.poll();
            int x = p.getKey(), y = p.getValue();
//            System.out.println("x = " + x + " y = " + y);
            for (int i = 0; i < 4; i++) {
                int nx = x + dirs[i][0];
                int ny = y + dirs[i][1];
                // 比他矮的也别放进去了
                if (nx < 0 || nx >= m || ny < 0 || ny >= n || vis[nx][ny] == 1 || heights[nx][ny] < heights[x][y])
                    continue;
                vis[nx][ny] = 1;
                que.add(new Pair<>(nx, ny));
//                System.out.println("nx = " + nx + " ny = " + ny);
            }
        }
    }

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        /**


         如果可以进入大西洋和太平洋
         就加入
         1. 多源bfs
         先看是否能够流入太平洋
         1. 把第一行和第一列加入
         2. 每次找比他高的相邻的点都是可以流入的
         同理，看能否流入大西洋
         1. 把最后...

         最后看这个点是否有两个标记来决定
         */
        if (heights.length == 0) return new LinkedList<>();
        int m = heights.length, n = heights[0].length;
        int[][] vis1 = new int[m][n];
        int[][] vis2 = new int[m][n];
        List<List<Integer>>list = new LinkedList<>();
        Queue<Pair>que = new ArrayDeque<>();
        for (int j = 0; j < n; j++) {
            vis1[0][j] = 1;
            que.add(new Pair<>(0, j)); // 不用显示声明类型了
        }
        for (int i = 0; i < m; i++) {
            vis1[i][0] = 1;
            que.add(new Pair<>(i, 0));
        }
        bfs(heights, vis1, que);

        // 大西洋
//        System.out.println("大西洋");
        que.clear();
        for (int j = 0; j < n; j++) {
            vis2[m - 1][j] = 1;
            que.add(new Pair<>(m - 1, j));
        }
        for (int i = 0; i < m; i++) {
            vis2[i][n - 1] = 1;
            que.add(new Pair<>(i, n - 1));
        }
        bfs(heights, vis2, que);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (vis1[i][j] == 1 && vis2[i][j] == 1) {
                    List<Integer> tmp = new LinkedList<>();
                    tmp.add(i); tmp.add(j);
                    list.add(tmp);
                }
            }
        }
        return list;
    }


    static void test1() {
        String s = "[[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]";
        s = s.replace("[", "{");
        s = s.replace("]", "}");
        System.out.println(s);
        int [][] heights = {{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
        List<List<Integer>> ans = main2.pacificAtlantic(heights);
        System.out.print("[");
        for (List ls: ans) {
            System.out.print("[" + ls.get(0) + "," + ls.get(1) + "]");
        }
        System.out.println("]");
    }



    public int[] sortArrayByParity() {
        /*
        *
        *   双指针的题目
        *
        * 1. 保证i前面的都是偶数
        * 2. 保证i和i后面的都是奇数
        * */
        int[] nums = {4, 1, 3, 2, 4};
        int n = nums.length, i = 0, j = 0;
        while(i < n && nums[i] % 2 == 0) i++;
        j = i;
        while(j < n) {
            if (nums[j] % 2 == 0) {
                int tmp = nums[i]; nums[i] = nums[j]; nums[j] = tmp;
                i++;
            }
            j++;
        }

        return nums;
    }

    public int unique(int[]nums) {
        /*
        *
        *   去除排好序的数组中重复的元素
        *   返回新的数组的长度
        *
        * 1. 保证i前面的都是不重复的
        * 2. 记录pre代表当前遍历到哪一个数字了
        *
        * nums[i...j]
        *  */
        int n = nums.length;
        int i = 1;
        while(i < n && nums[i] != nums[i - 1]) i++;
        int j = i + 1, pre = nums[i];
        while(j < n) {
            if (nums[j] != pre) {
                nums[i++] = pre = nums[j];
            }
            j++;
        }
        return i;
    }


    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }


    private Node dfs(int[][] grid, int x, int y, int len) {
        if (x >= grid.length || y >= grid.length) return null;

        boolean isLeaf = true; // 判断是否是叶子结点
        int first = grid[x][y];
        for (int i = x; i < x + len; i++) {
            for (int j = y; j < y + len; j++) {
                if (grid[i][j] != first) {
                    isLeaf = false;
                    break;
                }
                if (!isLeaf) break;
            }
        }
        Node cur = new Node();
        if (isLeaf) {
            cur.isLeaf = true;
            cur.val = first == 1;
            return cur;
        }

        cur.isLeaf = false;
        cur.val = true;

        cur.topLeft = dfs(grid, x, y, len / 2);
        cur.topRight = dfs(grid, x, y + len / 2, len / 2);
        cur.bottomLeft = dfs(grid, x + len / 2, y, len / 2);
        cur.bottomRight = dfs(grid, x + len / 2, y + len / 2, len / 2);

        return cur;
    }
    private int getSum(int[][] grid, int x1, int y1, int x2, int y2) {
        return grid[x2][y2] - grid[x1 - 1][y2] - grid[x2][y1 - 1] + grid[x1 - 1][y1 - 1];
    }
    private Node dfs2(int[][] grid, int x, int y, int len) {
        if (x >= grid.length || y >= grid.length) return null;
        int sum = getSum(grid, x, y, x + len - 1,y + len - 1);
        if (sum == 0) {
            return new Node(false, true);
        }
        if (sum == len * len) {
            return new Node(true, false);
        }
        int n = len / 2;
        return new Node(
                true,
                false,
                dfs2(grid, x, y, n),
                dfs2(grid, x, y + n, n),
                dfs2(grid, x + n, y, n),
                dfs2(grid, x + n, y + n, n)
        );
    }
    public Node construct(int[][] grid) {
        /**

         考察递归
         1. 如果划分之后，所有的结点都是1，那么就是叶子结点
         2. 否则，继续划分
         3. 可以使用二维前缀和进行优化

         */
        int n = grid.length;
        int[][] rec = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                rec[i][j] = rec[i - 1][j] + rec[i][j - 1] + grid[i - 1][j - 1] - rec[i - 1][j - 1];
            }
        }

        return dfs2(rec, 1, 1, n);
    }
    private static void travelNode(Node cur) {
        if (cur == null) {
            System.out.print("[null]");
            return ;
        }
        System.out.print("[");
        if (cur.isLeaf) System.out.print(1);
        else System.out.print(0);
        System.out.print(",");
        if (cur.val) System.out.print(1);
        else System.out.print(0);
        System.out.print("]");
        travelNode(cur.topLeft);
        travelNode(cur.topRight);
        travelNode(cur.bottomLeft);
        travelNode(cur.bottomRight);
    }

    private static void test2() {
        int[] nums = main2.sortArrayByParity();
        for (int x : nums) {
            System.out.print(x + " ");
        }
        System.out.println();
    }
    private static void test4() {
        String s = "[[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0]]";
        s = s.replace("[", "{");
        s = s.replace("]", "}");
        System.out.println(s);
        int[][] grid = {{1,1,1,1,0,0,0,0},{1,1,1,1,0,0,0,0},{1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1},{1,1,1,1,0,0,0,0},{1,1,1,1,0,0,0,0},{1,1,1,1,0,0,0,0},{1,1,1,1,0,0,0,0}};
        Node node = main2.construct(grid);
        travelNode(node);
    }

    private static void test3() {
        int[] nums = {1, 2, 2, 3, 3, 4, 4, 4, 5, 6 ,6};
        int n = main2.unique(nums);
        for (int i = 0; i < n; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    public static void main(String[] args) {
        test4();
    }
}
