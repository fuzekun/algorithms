> 持续更新[蓝桥杯算法训练题解](https://blog.csdn.net/fuzekun/article/details/127298556?spm=1001.2014.3001.5502)，有兴趣可以关注一波呀。



# 题目

George took sticks of the same length and cut them randomly until all parts became at most 50 units long. Now he wants to return sticks to the original state, but he forgot how many sticks he had originally and how long they were originally. Please help him and design a program which computes the smallest possible original length of those sticks. All lengths expressed in units are integers greater than zero.





# 题解



**首先说一下，题目中的例子答案错了，应该是6， 5。当然以后可能会更新吧，但是现在是错的**





题目的意思就是说：给定一个大小为n的集合，将集合划分成**最多**n - 1个子集合，使得**每一部分的集合的元素和都相等**。然后询问这样的

划分中，每一个集合的**元素和最小**是多少。

思路：暴力回溯。

首先，题目是有一些性质的：

- 每一个集合的元素和，一定是总和的约数。**划分后集合的元素和如果大于了总和的一半，那么只有一种可能，就是整个集合作为子集。**

有了上面的性质，问题就转化成了：**给定一个sum的约数k，判断集合是否可以将之划分成几个和恰好为k的子集合**。



这个题目可以参照[1723. 完成所有工作的最短时间](https://leetcode.cn/problems/find-minimum-time-to-finish-all-jobs/)去做。但是这道题目的数据量更大，也就意味着，需要更加精确的剪枝代码。



剪枝的思路有以下几个，下面把元素形容成球，把集合当作桶：

- **按照从大到小排序，这样可以更容易使得大的桶被放满。**
- **如果两个连续球相同，上一个装入本桶中没有答案，那么这个球装入桶中也不会有答案，可以直接返回。**
- **如果一个桶是空的，当前的放入不行，那么就没有桶行了。**
- **如果一个桶恰好装满了，没有答案，那么接下来也不会有答案，可以直接返回。**



好有了上面的几条剪枝的规则，就可以随便A掉1773题目了，但是本题仍旧不行。因为数据是n <= 64，很大。所以还要想办法。

- 记录当前遍历到了cur个球，如果cur+ a[i] < k，说明仍旧在使用当前的桶，并且前面的球已经不能用了，直接从cur + 1开始找下一个可以遍历的球。否则就是cur + a[i] == k，那么此时桶恰好装满了，需要从头开始找没有使用过的球。然后如果没答案可以直接返回(剪枝4)。



在实现的时候，使用sum表示当前桶中球的和，不用数组记录了，sum == 0的时候表示开启了一个新的桶。



下面是没有记录当前球个数的代码，我只能说短小精悍。但是下面的一个数据会超时：

> 64
> 40 40 30 35 35 26 15 40 40 40 40 40 40 40 40 40 40 40 40 40 40 
> 40 40 43 42 42 41 10 4 40 40 40 40 40 40 40 40 40 40 40 40 40 
> 40 25 39 46 40 10 4 40 40 37 18 17 16 15 40 40 40 40 40 40 40 
> 40

```cpp
#include <iostream>
#include <cstdio>
#include <algorithm>
#include <cstring>
#include <functional>
using namespace std;



const int N = 100;
int a[N];
int n;
int vis[N];

int check(int limit, int sum, int used) {
    if (used == n) return sum == 0;
    for (int i = 0; i < n; i++) {
        if (vis[i] || sum + a[i] > limit) continue;
        if (i && !vis[i - 1] && a[i] == a[i - 1]) continue;  // 剪枝2，相等的前不行，后一个也不行
        vis[i] = 1;
        if (check(limit, (sum + a[i]) % limit, used + 1))
            return true;
        vis[i] = 0;
        if (sum == 0 || limit == a[i]) break;              // 剪枝3，对于空桶来说，不用继续向下遍历了，第一个不行，后边都不行
        if (sum + a[i] == limit) break;                     // 剪枝4，如果有恰好装满的，仍及不行，直接break
    }
    return 0;
}

int main() {
    while(scanf("%d",&n) != EOF && n) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            int b;
            scanf("%d", &b);
            a[i] = b;
            sum += b;
        }
        sort(a, a + n, greater<int>()); // 从大到小进行排序, 剪枝1
        int ans = sum;                   // 如果大于了sum/2就应该是sum，否则就是i
        for (int i = a[0]; i <= sum / 2; i++) {
            memset(vis, 0, sizeof vis);
            if (sum % i != 0) continue; // 剪枝4, 如果不能整除肯定时不能作为长度的
            if (check(i, 0, 0)) {
                ans = i;
                break;
            }
        }
        printf("%d\n", ans);
    }
    return 0;
}
```



增加球编号的代码如下，我只能说巧夺天工：

```cpp
#include <iostream>
#include <cstdio>
#include <algorithm>
#include <cstring>
#include <functional>
using namespace std;

const int N = 100;
int a[N];
int n;
int vis[N];
int check2(int cur, int sum, int used, int limit) {
    if (cur == n) return used == n;                 // 一旦用了n个，sum一定为0，因为是整除的
    if (used == n) return true;
    for (int i = cur; i < n; i++) {
        if (vis[i] || sum + a[i] > limit) continue;
        if (i && !vis[i - 1] && a[i] == a[i- 1]) continue; // 剪枝2
        if (sum + a[i] == limit) {
            vis[i] = 1;
            if (check2(0, 0, used + 1, limit)) return true; // 需要从头看是否还有没有用过的
            vis[i] = 0;                                      // 这里不要忘了回溯
            return false;                                   // 剪枝4, 如果恰好装满了，还是没答案，
        }
        else {
            vis[i] = 1;
            if (check2(i + 1, sum + a[i], used + 1, limit)) return true; // 注意这个应该从i + 1开始
            vis[i] = 0;
            if (sum == 0) return false;                     // 剪枝3，不允许有空桶
        }
    }
    return false;
}

int main() {
    while(scanf("%d",&n) != EOF && n) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            int b;
            scanf("%d", &b);
            a[i] = b;
            sum += b;
        }
        sort(a, a + n, greater<int>()); // 从大到小进行排序, 剪枝1
        int ans = sum;                   // 如果大于了sum/2就应该是sum，否则就是i
        for (int i = a[0]; i <= sum / 2; i++) {
            memset(vis, 0, sizeof vis);
            if (sum % i != 0) continue; // 剪枝4, 如果不能整除肯定时不能作为长度的
            if (check2(0, 0, 0, i)) {
                ans = i;
                break;
            }
        }
        printf("%d\n", ans);
    }
    return 0;
}
```

