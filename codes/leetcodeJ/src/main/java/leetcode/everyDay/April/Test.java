package leetcode.everyDay.April;

import java.util.*;

/*
*
*
*   一些每天的测试
*   1. 注意一些错误点
*   2. 一些思想和技巧的积累
*   3. 知识点的积累
*    4_20_1 : 两数字之和
*    4_20_2:  状态压缩dp : 数组分配，保证数字不相同，并且sum(abs(maxv - minv))最小
*    4_20_3:  简单的动态规划,打家劫舍，线性dp
*
*
* */
public class Test {

    private Test() {

    }
    private static volatile Test testInstace;
    public static  Test getInstance() {
        if (testInstace == null) {
            synchronized (Test.class) {
                if (testInstace == null) {
                    testInstace = new Test();
                }
            }
        }
        return testInstace;
    }
    private static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> mp = new HashMap<>();
        int [] ans = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            if (mp.containsKey(target - x)) {
                ans[0] = mp.get(target - x);
                ans[1] = i;
                break;
            }
            mp.put(x, i);
        }
        return ans;
    }

    private int ans4_20 = Integer.MAX_VALUE;
    public void dfs(int []nums, int n, int k, int cur, int[][] groups, int[] idx, int sub) {
        if (cur == n) {
            int sum = 0;
            for (int i = 0; i < k; i++) {
                sum += groups[i][idx[i] - 1] - groups[i][0];
//                for (int j = 0; j < idx[i]; j++) {
//                    System.out.print(groups[i][j] + " ");
//                }
//                System.out.println();
            }
//            System.out.println("sum = " + sum);
            ans4_20 = Math.min(sum, ans4_20);
            return ;
        }
        int x = nums[cur];
        for (int i = 0; i < k; i++) {
            if (idx[i] >= n / k || (idx[i] > 0 && groups[i][idx[i] - 1] == x))
                continue;                       // 不满并且不能相等
            groups[i][idx[i]++] = x;            // 放到这组里面
            dfs(nums, n, k, cur + 1, groups, idx, sub);
            idx[i]--;                           // 回溯
        }
    }
    public int minimumIncompatibility(int[] nums, int k) {
        Arrays.sort(nums);                                      // 排序
        Map<Integer, Integer> mp = new HashMap<>();
        for (int x : nums) {                                    // 统计
            if (!mp.containsKey(x))
                mp.put(x, 1);
            else {
                int cnt = mp.get(x);
                if (++ cnt > k) return -1;
                mp.put(x, cnt);
            }
        }
//        Set<Integer>s = mp.keySet();
//        for (Integer x: s) {
//            System.out.printf("cnt[%d] = %d\n", x, mp.get(x));
//        }

        int n = nums.length;
        int [][] groups = new int[k][n / k];
        int [] idx = new int[n];

        dfs(nums, nums.length, k, 0, groups, idx,0);
        return ans4_20;
    }


    /*
     *
     *   三个数字的和
     *   1. 如果两个数字一样，第三个数字也就一样。
     *   2. 如何判断两个数字是否一样呢?
     *      2.
     * */
    private String get(int a, int b) {
        if (a > b) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        StringBuilder sb = new StringBuilder(String.valueOf(a)).append(String.valueOf(b));
        System.out.println(sb.toString());
        return sb.toString();
    }
    public List<List<Integer>> threeSum(int[] nums) {
        HashMap<Integer, Integer> mp = new HashMap<>();
        HashMap<String, Boolean> exist = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            mp.put(nums[i], i);
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int sum = nums[j] + nums[i];
                String idx = get(nums[i], nums[j]);
                if (exist.containsKey(idx)) continue;
                exist.put(idx, true);
                if (mp.containsKey(-sum)) {
                    int k = mp.get(-sum);
                    if (k == i || k == j) continue;
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(-sum);
                    ans.add(list);
                }
            }
        }
        return ans;
    }
    public List<List<Integer>> threeSum2(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>>ans = new ArrayList<>();
        for (int i = 0;  i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int a = nums[i];
            for (int j = i + 1, k = n - 1; j < k;) {
                int b = nums[j], c = nums[k];
                int sum = a + b + c;
                if (sum == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(a);
                    list.add(b);
                    list.add(c);
                    ans.add(list);
                    while(j < k && nums[j] == b) j++;
                    while(j < k && nums[k] == c) k--;
                } else if (sum > 0) k--;
                else j++;
            }
        }
        return ans;
    }

    public int rob(int[] nums) {
        /*
        *
        *   打家劫舍
        *   1. 注意边界
        *   2. dp[1] = (nums[1], nums[0])
        *
        *   3. 如果是环形
        *
        * */
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        if (n >= 2) dp[1] = Math.max(nums[1], nums[0]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[n - 1];
    }
    public int maxSubArray(int[] nums) {
        /**
         *  最大连续子数字和
         *  滑动窗口问题
         *  前缀和,维护最小的前缀和,
         *
         *
         * */
        int n = nums.length;
        int sum = 0, minv = 0;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            ans = Math.max(ans, sum - minv);
            minv = Math.min(sum, minv);
        }
        return ans;
    }
    public int maxSubArray2(int[] nums) {
        /**
         *  最大连续子数字和
         *  滑动窗口问题
         *  前缀和,维护最小的前缀和,
         *  如何采用分治的解法？
         *  就是线段树的模板题。
         *
         *
         * */
        return 0;
    }
    public int reverse1(int x) {
        /*
        *
        *   整数反转
        * 1. 如果是复数
        * 2. 如果是0
        * 3. 如果后面又0
        * 4. 如果反转之后，超过了int范围，怎么进行判断呢？
        *   4.1 -128 -> -821已经大于了
        *   4.1 每一位进行比较，如果等于继续比较
        *   4.2 如果有一位小于可以，如果有一位大于不可以
        * 5. 最后一个负数用int的整数保存不下，所以
        * */

        if (x == 0) return 0;
        int flag = 1;
        if (x < 0) {
            flag = 0;
            x = -x;
        }
        String nums = "";
        for (;x != 0; x /= 10) {
            nums += x % 10;
        }
        if (flag == 0) nums = "-" + nums;
        System.out.println(nums);
        String biggest = String.valueOf(Integer.MAX_VALUE);
        String smallest = String.valueOf(Integer.MIN_VALUE);

        if (flag > 0) {
            if (nums.length() > biggest.length()) return 0;
            if (nums.length() == biggest.length() && biggest.compareTo(nums) < 0)
                return 0;
        } else {
            if (nums.length() > smallest.length()) return 0;
            if (smallest.length() == nums.length() && smallest.compareTo(nums) < 0)
                return 0;
        }
        return Integer.valueOf(nums);
    }

    public int reverse2(int x) {
        /*
         *
         *   整数反转
         * 1. 如果是复数
         * 2. 如果是0
         * 3. 如果后面又0
         * 4. 如果反转之后，超过了int范围，怎么进行判断呢？
         *   4.1 -128 -> -821已经大于了
         *   4.1 每一位进行比较，如果等于继续比较
         *   4.2 如果有一位小于可以，如果有一位大于不可以
         * 5. 最后一个负数用int的整数保存不下，所以不可以直接x = -x
         * */

        if (x == 0) return 0;
        if (x == Integer.MIN_VALUE) return 0;
        int flag = 1;
        if (x < 0) {
            flag = 0;
            x = -x;
        }
        StringBuilder nums = new StringBuilder("");
        for (;x != 0; x /= 10) {
            nums.append(x % 10);
        }
        System.out.println(nums);

        String biggest = "2147483647";
        String smallest = "2147483648";
        String n = nums.toString();
        if (nums.length() == 10) {
            if (flag > 0) {
                if (biggest.compareTo(n) < 0) return 0;
            } else {
                if (smallest.compareTo(n) < 0) return 0;
            }
        }
        if (flag == 0) n = "-" + n;
        return Integer.valueOf(n);
    }

    public int reverse3(int x) {
        /*
        *   1. 0和Min_val单独考虑
        *   2. 直接反转，数组保存
        *   3. long进行接收
        *   4. 判断数字是否大于
        * */

        if (x == Integer.MIN_VALUE || x == 0) return 0;
        int flag = 1;
        if (x < 0) {
            flag = 0;
            x = -x;
        }
        int[] nums = new int[10];
        int cnt = 0;
        for (; x != 0 ; x /=10) nums[cnt++] = x % 10;
        long ans = 0;
        for (int i = 0; i < cnt; i++) {
            ans = ans * 10 + nums[i];
        }
        if (flag == 0) ans = -ans;
        if (ans > Integer.MAX_VALUE || ans < Integer.MIN_VALUE) return 0;

        return (int)ans;
    }

    public int reverse4(int x) {
        /*
        *
        *   这种是工作最建议使用的。可以使用long
        *   直接用最简单的代码写出来。
        * 1. 直接 /和%就是reverse
        * 2. 直接 * 10 和 + 就屏蔽了负数和正数。
        * */
        long rev = 0;
        while(x != 0) {
            rev = rev * 10 + x % 10;
            x /= 10;
        }
        if (rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE) return 0;
        return (int)rev;
    }
    public int reverse5(int x) {
        /*
        *   如果rev == Integer.MAX_VALUE * 10，那么
        *  加上之后会不会超过呢？
        * 最大值和最小值最后一位是7和8，那么x的开头必然是7和8
        * 又由于不存在以7 和 8开头且长度为10的int类型，
        * 所以必然不会。
        * */
        int rev = 0;
        while(x != 0) {
            if (rev < Integer.MIN_VALUE / 10 || rev > Integer.MAX_VALUE / 10)
                return 0;
            rev = rev * 10 + x % 10;
            x /= 10;
        }
        return (int)rev;
    }
    public int search(int[] nums, int target) {
        /*
        *
        *   搜索选转数组:
        *  一个排序好的，然后进行一个选转，然后问里面有没有一个数字target。
        *   O(n)和O(logn)
        * O(logn):
        * 1. 先找到选转点p, 那么[0, p)和[p, n)都是升序排列
        *   1.1 相对于nums[0]来说，左边都是大于等于它的
        *       右边和点都是小于它的。
        *       找右边的第一个，就是k
        * 2. 从两个里面进行二分查找
        *
        * 3. 如果l = 0，那就是完全逆序了,J
        *
        * */
        int l = 0, r = nums.length;
        while(l < r) {
//            System.out.println("l = " + l + " r = " + r);
            int mid = (l + r) >> 1;
            if (nums[mid] >= nums[0]) l = mid + 1;
            else r = mid;
        }
        System.out.println("k = " + l);
        int t = Arrays.binarySearch(nums, 0, l, target);
        if (t >= 0 && t < l) return t;

        t = Arrays.binarySearch(nums, l, nums.length, target);
        System.out.println("t = " + t);
        if (t >= l && t < nums.length) return t;
        return -1;
    }
    public int findInMountainArray(int target, int[] nums) {
        int n = nums.length;
        int l = 0, r = n, k = -1;
        while(l < r) {
//            System.out.println("l = " + l + " r = " + r);
            int mid = (l + r) >> 1;
            if (mid == n - 1) {
                k = n - 2;
                break;
            }
            if (mid == 0) {
                k = 1;
                break;
            }
            int m = nums[mid];
            int x = nums[mid + 1];
            int y = nums[mid - 1];
            if (m > x && m > y) {
                k = mid;
                break;
            }
            else if (m < x) l = mid + 1;
            else r = mid;
        }
        System.out.println("k = " + k);
        l = 0; r = k;
        while(l < r) {
            int mid = (l + r) >> 1;
            int m = nums[mid];
            if (m == target) return mid;
            else if (m > target) r = mid;
            else l = mid + 1;
        }
        l = k; r = n;
        while(l < r) {
            int mid = (l + r) >> 1;
            int m = nums[mid];
            if (m == target) return mid;
            else if (m > target) l = mid + 1;
            else r = mid;
        }
        return -1;
    }

    public int firstMissingPositive(int[] nums) {
        /*
        *
        *
        *   连续数字的数学性质
        *   1. 答案只可能出现在[1, n + 1]之间, 如果[1, n]都出现，为n + 1
        *   2. 负数或者大于n + 1的数字都不会是答案
        *
        *   注意加上负号，改成的是相反数，不一定是负数。
        *
        * */
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0) nums[i] = n + 1;
        }
        for (int i = 0; i < n; i++) {
            int x = Math.abs(nums[i]);
            if (x <= n && nums[x - 1] > 0) {
                nums[x - 1] = -nums[x - 1];
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }
    public boolean isValid(String s) {
        /*
        *
        *   判断括号是否有效
        *
        *   注意如果括号的数目不匹配也是无效的。
        *   注意如果cnt - 1 < 0不能直接--cnt进行判断的。
        * */

        int n = s.length();
        char[] st = new char[n];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == '(' || ch == '{' || ch == '[') {
                st[cnt++] = ch;
            }
            else if (ch == ')') {
                if (st[--cnt] != '(')
                    return false;
            }
            else if (ch == '}') {
                if (st[--cnt] != '{')
                    return false;
            }
            else if ( st[--cnt] != '[') {
                return false;
            }
        }
//        System.out.println("cnt = " + cnt);
        return cnt == 0;
    }


      private static class TreeNode {  // 类加载的时候不加载他
          int val;
          TreeNode left;
          TreeNode right;
          public TreeNode() {}
          public TreeNode(int val) { this.val = val; }
          public TreeNode(int val, TreeNode left, TreeNode right) {
              this.val = val;
              this.left = left;
              this.right = right;
          }
    }
    private void dfsTreeOrder(TreeNode root, int op,  List<List<Integer>> ans) {
        if (root == null) return ;

        if (op == 0) {
            dfsTreeOrder(root.left, 1-op, ans);
            dfsTreeOrder(root.right, 1-op, ans);
        } else {
            dfsTreeOrder(root.right, 1-op, ans);
            dfsTreeOrder(root.left, 1-op, ans);
        }
    }
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();

        List<List<Integer>> ans = new ArrayList<>();
        List<Integer>anst = new ArrayList<>();
        anst.add(root.val);
        ans.add(anst);
        List<List<TreeNode>>que = new ArrayList<>();
        List<TreeNode>list = new ArrayList<>();
        list.add(root);
        que.add(list);

        int cnt = 1;
        int op = 0;
        while(true) {
            list = que.get(cnt - 1);                // 上一层
            List<TreeNode> tmp = new ArrayList<>();// 本层
            for (TreeNode node : list) {
                if (node.left != null) tmp.add(node.left);
                if (node.right != null) tmp.add(node.right);
            }
            if (tmp.isEmpty()) break;
            anst = new LinkedList<>();
            if (op == 1) {
                for (TreeNode node : tmp) {
                    anst.add(node.val);
                }
            } else {
                for (int i = tmp.size() - 1; i >=0; i--) {
                    anst.add(tmp.get(i).val);
                }
            }
            ans.add(anst);
            que.add(tmp);
            cnt++;
            op ^= 1;
        }
        return ans;
    }
    public List<List<Integer>> levelOrder(TreeNode root) {
        /*
        *
        * 层次遍历
        *
        *   缺点：java初始化的时候太麻烦了。
        * */
        if (root == null) return new ArrayList<>();

        List<List<Integer>> ans = new ArrayList<>();
        List<Integer>anst = new ArrayList<>();
        anst.add(root.val);
        ans.add(anst);
        List<List<TreeNode>>que = new ArrayList<>();
        List<TreeNode>list = new ArrayList<>();
        list.add(root);
        que.add(list);

        int cnt = 1;
        while(true) {
            list = que.get(cnt - 1);                // 上一层
            List<TreeNode> tmp = new ArrayList<>();// 本层
            anst = new LinkedList<>();

            for (TreeNode node : list) {
                if (node.left != null) {
                    tmp.add(node.left);
                    anst.add(node.left.val);
                }
                if (node.right != null) {
                    tmp.add(node.right);
                    anst.add(node.right.val);
                }
            }
            if (tmp.isEmpty()) break;
            ans.add(anst);
            que.add(tmp);
            cnt++;
        }
        return ans;
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        /*
         *
         * 层次遍历
         *
         *   缺点：java初始化的时候太麻烦了。
         * */
        Queue<TreeNode>que = new LinkedList<>();
        List<List<Integer>>ans = new LinkedList<>();
        que.offer(root);
        while(!que.isEmpty()) {
            int len = que.size();
            List<Integer> tmp = new LinkedList<>();
            for (int i = 0; i < len; i++) {
                TreeNode node = que.poll();
                tmp.add(node.val);
                if (node.left != null)
                    que.offer(node.left);
                if (node.right != null)
                    que.offer(node.right);
            }
            ans.add(tmp);
        }
        return ans;
    }
    public String addStrings(String num1, String num2) {
        /**
         *  大数加法
         *  1. 逆序
         *  2. 进位和保存位数
         *  3. 最后出来的是较长的位数，然后进位的处理
         *
         *
         *
         *
         */

        return "";

    }
    public void merge(int[] nums, int m, int[] nums2, int n) {
        /*
        *
        *   合并两个有序数组
        * 1. 使用while比使用for好
        * 2. while中别忘记了++
        * */

        int[] nums1 = Arrays.copyOf(nums, m); // nums拷贝前m个给nums1
        int p = 0, i = 0, j = 0;
        while(i < m && j < n) {
            if (nums1[i] < nums2[j]) nums[p++] = nums1[i++];
            else nums[p++] = nums2[j++];
        }
        while(i < m) nums[p++] = nums1[i++];
        while(j < n) nums[p++] = nums2[j++];
    }
    private void ParenThesistdfs2(int l, int r, int n, String t,  List<String>ans) {
        System.out.println("l = " + l + " r = " + r);
        if (r == n && l == n) {
            System.out.println(t);
            ans.add(t);
            return;
        }
        for (;l < n; l++)
            ParenThesistdfs2(l + 1, r, n, t + "(", ans);
        for (; r < l; r++)
            ParenThesistdfs2(l, r + 1, n, t + ")", ans);
    }
    private void ParenThesistdfs(int l, int r, int n, String t,  List<String>ans) {
//        System.out.println("l = " + l + " r = " + r);
        if (r == n && l == n) {
//            System.out.println(t);
            ans.add(t);
            return;
        }
        if (l < n) ParenThesistdfs(l + 1, r, n, t + "(", ans);
        if (r < l) ParenThesistdfs(l, r + 1, n, t + ")", ans);
    }
    public List<String> generateParenthesis(int n) {
        /*
        *
        *   括号生成
        * 3
        * ((()))) (()()) ()()() ()(()) (())()
        *   对于每一层来说，都有两种可能性，要么左括号，要么右括号
        *
        * */
        List<String>ans = new ArrayList<>();
        ParenThesistdfs2(0, 0, n, "", ans);
        return ans;
    }



    private static void tset_4_20_1() {
        Test t = new Test();
        int[] nums = {7,3,16,15,1,13,1,2,14,5,3,10,6,2,7,15};
        int ans = t.minimumIncompatibility(nums, 8);
        System.out.println(ans);
    }
    private static void test_4_20_2() {
        Test t = getInstance();
        int[] nums = {-1,0,1,2,-1,-4};
        List<List<Integer>> ans = t.threeSum2(nums);
        for (List<Integer> list : ans) {
            System.out.print("[ ");
            for (int x: list) {
                System.out.print(x + ",");
            }
            System.out.println(" ]");
        }
    }



    private static void test_4_19() {
        int[] nums = {3,2,4};
        int[] ans = twoSum(nums, 8);

        System.out.println(ans[0] + " " + ans[1]);
    }
    private static void test_4_20_3() {
        Test t = getInstance();
        int[] nums = {2,7,9,3,1};
        int ans = t.rob(nums);
        System.out.println(ans);
    }

    private static void test_4_21_1() {
        int []nums = {5,4,-1,7,8};

        int ans = getInstance().maxSubArray(nums);
        System.out.println(ans);
    }

    private static void test_4_21_2() {
        int []nums = {120, 1234567896, 0, -123, -120,-2143847412, -563847412,
                -2147483648};
        Test t = getInstance();
        for (int x : nums) {
            System.out.println("x = " + x);
            int ans = t.reverse5(x);
            System.out.println(" ans = " + ans);
        }
    }
    private static void test4_21_3() {
        int[] nums = {1,2,3};
        Test t = getInstance();
        int ans = t.search(nums, 1);
        System.out.println("ans = " + ans);
    }
    private static void test4_21_4() {
        int[] nums = {1,2,4,5,3};
        Test t = getInstance();
        int ans = t.findInMountainArray(5, nums);
        System.out.println("ans = " + ans);
    }
    private static void test4_22_1() {
        int[] nums = {1, 1};
        Test t = getInstance();
        int ans = t.firstMissingPositive(nums);
        System.out.println("ans = " + ans);
    }
    private static void test4_22_2() {
        String[] strings = {"()[]{}", "[}{(})]", "("};
        Test t = getInstance();
        for (String s : strings) {
            System.out.println("s =  " + s + " ans = " + t.isValid(s));
        }
    }

    private static void test4_22_3() {

    }
    private static void test4_24_1() {
        int[] nums1 = {1,2,3,0,0,0};
        int[] nums2 = {2,5,6};
        int m = nums1.length, n = nums2.length;
        Test tst = getInstance();
        tst.merge(nums1,m - n, nums2, n);
        for (int i = 0; i < m; i++) {
            System.out.print(nums1[i] + " ");
        }
        System.out.println();
    }
    private static void test4_24_2() {
        Test t = getInstance();
        List<String> ans = t.generateParenthesis(3);
        for (String s : ans) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
//        test_4_19();
//        tset_4_20_1();
//        test_4_20_2();
//          test_4_20_3();
//        test_4_21_1();
//        test_4_21_2();
//        test4_21_3();
//        test4_21_4();
//        test4_22_1();
//        test4_22_3();
//        test4_24_1();
        test4_24_2();
    }


}
