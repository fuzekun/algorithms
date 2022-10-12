# 字符串dp



| 题目                                                         | 知识点         | 难度 |
| ------------------------------------------------------------ | -------------- | ---- |
| [最大删除次数](https://leetcode.cn/problems/maximum-deletions-on-a-string/solution/zui-chang-by-man-qian-shu-xiao-ming-993z/) | 最长公共前缀   | 困难 |
| [最长回文子序列](https://leetcode.cn/problems/longest-palindromic-subsequence/solution/zui-chang-hui-wen-zi-chuan-by-man-qian-s-4lgw/) | 最长回文子序列 | 中等 |
| [最长回文子串](https://leetcode.cn/problems/longest-palindromic-substring/solution/zui-chang-shang-sheng-zi-chuan-by-man-qi-fux5/) | 最长回文子串   | 中等 |



## 子串 + 计数dp



### [字符串的总引力值](https://leetcode-cn.com/problems/total-appeal-of-a-string/)

暴力解法

1. 求出所有子串
2. 求子串的过程中统计引力值

- 枚举子串的方式

```java
// 方法一: 最容易想的解法,暴力可以使用
for (int i = 0; i < n; i++) {
    StringBuilder b = new StringBuilder();
    for (int j = i; j < n; j++) {
        b.append(s.charAt(i));						// 每一个j都是一个子串
    }
}

// 方法二：最模拟的想法
for (int i = 0; i < n; i++) {
    StringBuilder sb = new StringBuilder();
    for (int len = 1; len + i - 1 < n; len++) {
        int j = i + len - 1;
        b.append(s.charAt(j));
    }
}

// 方法三：以i开头的字符串
for (int i = n - 1; i >= 0; i--) {
    StringBuilder sb = new StringBuilder();
    for (int j = i; j < n; j++) {
        sb.append(s.charAt(j));
    }
}

// 方法四:这是一种错误的解法，因为求的字符串都是重复的。
for (int i = 0; i < n; i++) {
    String s;
    for (int j = 0; j <= i; j++) {
        s += s.charAt(j);
    }
}

// 方法5:以i结尾的子串，这种最容易使用dp
for (int i = 0; i < n; i++) {
    String ss;
    for (int j = i; j >= 0; j--) {
        ss = s.charAt(j) + ss;					// 把这个字符插入到字符的前面。
    } 
}


```

- 暴力解法

```java
long sum = 0L;
for (int i = 0; i < n; i++) {
    int[] vis = new int[26];
    long cnt = 0L;
    for (int j = i; j < n; j++) {
        if (vis[s.charAt(j) - 'a'] == 0) {
            vis[s.charAt(j) - 'a'] = 1;
            cnt++;
        } 
        ans += cnt;
    }
}


// 方法二：以i结尾的字符串
for (int i = 0; i < n; i++) {
    int[] vis = new int[26];
    long cnt = 0L;
    for (int j = i; j >= 0; j++) {
        if (vis[s.charAt(j) - 'a'] == 0) {
            vis[s.charAt(j) - 'a'] = 1;
            cnt++;
        }
        ans += cnt;
    }
}

// 为了更好讲解dp，使用方法三
for (int i = 0; i < n; i++) {					// 以i结尾的字符子串
    int vis[] = new int[26];
    long ans = 0L;
    for (int j = i; j >= 0; j++) {				// 碰见新的，为答案贡献j + 1个值。
        if (vis[s.charAt(j) - 'a'] == 0) {
            vis[s.charAt(j) - 'a'] = 1;
            ans += j + 1;
        }
    }
}

```

- 优化1

根据方法二：

**可以看到，如果没有新的字符进来，每次加的cnt都是上一次的值，而cnt最多更新26次，只要知道这些cnt在哪个下标更新的就可以直接计算得到了，而不用循环了**。



那么更新点在哪呢？**就是26个英文字母最右的位置。**

怎么计算贡献呢？**从j处更新，每一次向前会为答案贡献1次，一共向前j次**，**所以贡献了j + 1（包括自己）**。



如果不理解可以看下方法三。





- 所以我们只要记录在 i 之前的26个字母最右边出现的坐标 j 。就可以使用 j - i + 1，来得到以 i 结尾的字母对答案的贡献了。

- 初始 f[ch] = -1。

```java
for (int i = 0; i < n; i++) {
    int[] f = new int[26];
    Arrays.fill(f, -1);
    long ans = 0;
    for (int i = 0; i < n; i++) {
        f[s.charAt(i) - 'a'] = i;
        for (int j = 0; j < 26; j++)
			ans += f[j] + 1;
    }
}
```



- 优化二

```java
for (int i = 0; i < n; i++) {
    int[] f = new int[26];
    Arrays.fill(f, -1);
    long ans = 0;
    long sum = 0;
    for (int i = 0; i < n; i++) {
        int c = s.charAt(i) - 'a';
        sum += i - f[c];
        f[c] = i;
        ans += sum;
    }
}
```



可以看到，就算上述26次的循环中其实也就**更新了一次f[c]，其他的25次都是加的以前的f[c]**。所以只要知道以前的和，加上这一次**增加的贡献**。就可以直接省去第二层循环了。

那么这一次贡献是多少呢？**i - f[c]**;      可以这么想，

- 首先sum保存的是以前的和，$sum = \sum_0^{25}f[i]$
- 先去除这一次更新的$sum -= f[c]$

- 加上新的值$sum += i$

所以新的$sum = sum + i - f[c]$,这个值就代表了上述循环中的26次的和。

**最后别忘了更新f[c]**





### [2272. 最大波动的子字符串](https://leetcode.cn/problems/substring-with-largest-variance/)





转化成最后一个字符。



注意diffwithB的含义

diffwithB就是含有b的字符串的最大差值。



![image-20220608093001827](D:\blgs\source\imgs\image-20220608093001827.png)
