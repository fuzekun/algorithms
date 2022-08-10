# 状态压缩dp







## [464. 我能赢吗](https://leetcode.cn/problems/can-i-win/)



记忆化搜索改状态压缩dp

- 压缩dp如下。

```cpp
class Solution {
public:

    /**
    
    
        1. leetcode困难 
        2. 左神
        3. acwing一道

        不可以使用重复整数:使用状态压缩 + 记忆化搜索

     */

    int maxv, des;
    bool canIWin(int maxv, int des) {
        if ((maxv + 1) * maxv / 2 < des) return false;
        int max_s = 1 << maxv;
        vector<int>dp(max_s);
        dp[max_s - 1] = 1;
        for (int i = max_s - 2; i >= 0; i--) {
            int sum = 0;
            for (int j = 0; j < maxv; j++) {
                if (i >> j & 1) sum += j + 1;
            }
            for (int j = 0; j < maxv; j++) {
                if ((i >> j & 1) == 0) {
                    if (sum + j + 1 >= des) dp[i] = 1;
                    if (!dp[i | (1 << j)]) dp[i] = 1;
                }
            }
        }
        return dp[0];
    }
};
```



- 记忆化搜索如下



```cpp
class Solution {
public:
    unordered_map<int, bool> memo;

    bool canIWin(int maxChoosableInteger, int desiredTotal) {
        if ((1 + maxChoosableInteger) * (maxChoosableInteger) / 2 < desiredTotal) {
            return false;
        }
        return dfs(maxChoosableInteger, 0, desiredTotal, 0);
    }

    bool dfs(int maxChoosableInteger, int usedNumbers, int desiredTotal, int currentTotal) {
        if (!memo.count(usedNumbers)) {
            bool res = false;
            for (int i = 0; i < maxChoosableInteger; i++) {
                if (((usedNumbers >> i) & 1) == 0) {
                    if (i + 1 + currentTotal >= desiredTotal) {
                        res = true;
                        break;
                    }
                    if (!dfs(maxChoosableInteger, usedNumbers | (1 << i), desiredTotal, currentTotal + i + 1)) {
                        res = true;
                        break;
                    }
                }
            }
            memo[usedNumbers] = res;
        }
        return memo[usedNumbers];
    }
};
```







## 最小不兼容性

[题目链接](https://leetcode-cn.com/problems/minimum-incompatibility/)

**0. 暴力解法**

时间复杂度$O((n/2)^n)$

最坏为$8^{16}=2^{48}$超时了

```java
    private int ans = Integer.MAX_VALUE;
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
            ans = Math.min(sum, ans);
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
        return ans;
    }
```

 

**1. 计算可以的子集**

**2. 枚举子集**

如果长度为2的所有子集都有了。那么长度为4的就可以使用长度为2的子集进行推导了。

**3. 动态规划**



## [1595. 连通两组点的最小成本](https://leetcode.cn/problems/minimum-cost-to-connect-two-groups-of-points/)



题目大意是12点之间互相匹配，得到最小的花费。要求左边和右边的每一个点**至少**要被**匹配到一次**。



**思路**

看到很小的数据量，果断状态压缩动态规划。

因为左边的每一个点可以匹配到每一个右边的点多次。所以可以根据固定左边点，然后枚举右边点状态的方式。

状态表示: f(i, a):表示左边前i个，匹配到的右边的状态为a的最小方案。

状态计算：定义$s_i$为a的子集和，$cost(i, {a - b})$为i链接到集合${a - b}$每一个点的总花费。

那么$f(i, a) = min\{f(i - 1, s_i) + cost\{a - s_i\}\}$ 

```java
class Solution {
public:
    int connectTwoGroups(vector<vector<int>>& cost) {
        int n=cost.size(),m=cost[0].size();
        vector<vector<int>>dp(n+1,vector<int>(1<<m,INT_MAX));
        dp[0][0]=0;
        vector<vector<int>>pay(n,vector<int>(1<<m));  //要预处理出每一行所对应的花费
        for(int i=0;i<n;i++){
            for(int j=0;j<1<<m;j++){
                int sum=0;
                for(int k=0;k<m;k++){
                    if(j&(1<<k)) sum+=cost[i][k];
                }
                pay[i][j]=sum;
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<1<<m;j++){
                if(dp[i][j]==INT_MAX) continue;
                for(int k=1;k<1<<m;k++){  //从1枚举,因为每一个右边的点都要有对应左边的点
                    dp[i+1][j|k]=min(dp[i+1][j|k],dp[i][j]+pay[i][k]);
                }
            }
        }
        return dp[n][(1<<m)-1];
    }
};
```



分析时间复杂度$O(N*2^N*2^N)$，明显会超时。





1. 可以看到，k从小到大，也就是**右边**的点是**从小到大**进行枚举的。

2. 对于给定的右边的个数，选择的多少也是**从少到多**进行的。比如右边的前两个，一定是先选1个，然后选2的。
3. 这样来说，如果计算右边含有前t个中的3的时候，那么右边前t个中含有2个的已经算完了。结果保存在了$f(i, j | t')$里面。所以可以直接使用，不用把前两个的再算一遍了。
4. 如果只有一个，那么就是他自己。





其实就是多重背包问题的优化。





## 货郎担问题



## 图的色数



枚举子集如何进行？



```java
for (int S0 = S; S0; S0 = (S0 - 1) & S)				// 减去之后，在把不在S中的去掉。
```





## 教师授课问题

![image-20220520150105913](D:\blgs\source\imgs\image-20220520150105913.png)



## 20个问题

![image-20220520150125846](D:\blgs\source\imgs\image-20220520150125846.png)

## 基金管理



![image-20220520150158114](D:\blgs\source\imgs\image-20220520150158114.png)



## USACO 2006

![image-20220520150232434](D:\blgs\source\imgs\image-20220520150232434.png)
