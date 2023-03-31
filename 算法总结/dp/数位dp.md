# 数位dp
| 题目 | 知识点 | 难度 |
| --- | --- | --- |
| [2376. 统计特殊整数](https://leetcode.cn/problems/count-special-integers/) | 数位dp模板 | 困难 |
| [600. 不含连续1的非负整数](https://leetcode.cn/problems/non-negative-integers-without-consecutive-ones/solution/shu-wei-dp-by-man-qian-shu-xiao-ming-felv/) | 数位dp | 困难 |
| 233. 数字1的个数 | 数位dp模板题 | 困难 |
| [902. 最大为N的数字组合](https://leetcode.cn/problems/numbers-at-most-n-given-digit-set/) | 数位dp模板题目 | 困难 |
| [数字1的个数](https://leetcode.cn/problems/number-of-digit-one/submissions/) | mask记录数字x的个数 |  |
| acwing 1081 度的数量 | 使用进制思想 + 每一位是1或者0。mask记录1的个数 |  |
| acwing 1082、1083 ，1085 |  Mask记录前位的数是多少 |  |
| acwing 1084 | mask记录前面数字的和 |  |
|  |  |  |

# 数位dp做题思路



> 先将[a, b]区间改成从[0, a], [0, b]的单个计算。然后做差



> 其次使用标准模板代码就行了

标准模板代码，根据题目限定的条件，修改pre这一个状态变量就行了。

常见的状态变量有

1. 前一位，这个题目中有相邻位之间约数的情况。比如 acwing的1082， 1083， 1085
2. 位数之和，参考acwing 1084的情况
3. mask，1-9中数字不能重复。mask记录选择了那个数字
4. $cnt_x$ ， x出现的次数，用于统计出现的次数。 参考数字1的个数

```java
    private static int dfs(int cur, int pre, boolean isNum, boolean isLimit) {
        if (cur == s.length) {
            return isNum ? 1 : 0;
        }
        // 记忆化
        int res = dp[cur][pre];
        if (isNum && !isLimit && res >= 0)
            return res;
        res = 0;
        // 不是数字，可以跳过
        if (!isNum) res += dfs(cur + 1, pre, false, false);
        // 枚举是数字的情况
        int up = isLimit ? s[cur] - '0' : 9;
        for (int i = isNum ? 0 : 1; i <= up; i++) {
            if (i == 4 || (pre == 6 && i == 2)) continue;
            res += dfs(cur + 1, i, true, isLimit && i == up);
        }
        // 记录
        if (!isLimit && isNum) dp[cur][pre] = res;
        return res;
    }
```



