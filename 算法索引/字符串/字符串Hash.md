# 字符串Hash





把字符串映射到**123进制数上**，再使用**开放地址法**进行存储每一个值。注意使用ull存储node的值。

一些数字上的问题：

>h[i] : 保存pow(base, i)



> base = 123是因为'z'对应的ASCII码是123



>has[i]: 保存前i位的数字的hash值。
>
>> > 用十进制来举列子123456，has[1] = 1, has[2] = 12, has[3] = 123,...
>
>has[i] - has[i - n] * has[n] 就是取出后面的n位, 
>
>> > 比如has[6] - has[6 - 3] * 1000 = 123456 - 123 * 1000 = 456



所以说使用字符串hash可以很方便的取出前n位和后n位字符串所代表的hash值。这个hash值是一个123进制数。

可以在O(1)时间复杂度内求出子串。



```cpp

#include <iostream>
#include <cstdio>
#include <algorithm>
#include <cstring>

using namespace std;
typedef unsigned long long ull;
const int maxn = 2e6 + 5, base = 123, mod = 1e5 + 7;;

char str[maxn];

int head[mod + 1], idx, nx[maxn];
ull has[maxn], h[maxn], node[maxn];        // 注意node应该是ull, 调试了半个小时...


void add(ull x) {
    //cout << x << endl;
    int p = x % mod;
    node[++idx] = x;                                // 创建新结点
    nx[idx] = head[p];                    // 插入到hash表对应的位置
    head[p] = idx;
}

bool check(ull x) {
    int p = x % mod;

    for (int i = head[p]; i; i = nx[i]) {
        if (node[i] == x)
            return true;
    }
    return false;
}

int main () {

    scanf("%s", str + 1);
    // 构造一个循环字符串
    int n = strlen(str + 1);
    for (int i = 1; i <= n; i++) {
        str[i + n] = str[i];
    }

    // 计算pow(base, i)
    h[0] = 1;
    for (int i = 1; i < maxn; i++) {
        h[i] = h[i - 1] * base;
    }
    // 计算循环字符串的hash值，并且存储到hash表中
    has[0] = 0;
    for (int i = 1; i <= 2 * n; i++) {
        has[i] = has[i - 1] * base + str[i];
        if (i >= n) add(has[i] - has[i - n] * h[n]); // 注意等于n的时候是自己也需要加进去
    }
    //printf("插入完成\n");

    int T;
    scanf("%d", &T);
    while(T--) {
        int ans = 0;
        scanf("%s", str + 1);
        for (int i = 1; str[i]; i++) {
            has[i] = has[i - 1] * base + str[i];
            if (i >= n) ans += check(has[i] - has[i - n] * h[n]);

         }

        printf("%d\n", ans);
    }

    return 0;
}

```





## 问题

1. 使用trie树或者map是否可以直接A过呢？
2. unordered_map的时间复杂度不是默认为O(1)吗？如果使用map，不说别的，就单单这个构造循环字符串的时间复杂度都是O(n)，还有子字符串的取出就都不是一个小的数目。并且存储更是天文数字的空间。

