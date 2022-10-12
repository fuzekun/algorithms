| 题目                                                         | 知识点                  | 难度 |
| ------------------------------------------------------------ | ----------------------- | ---- |
| [IP到CIRD](https://leetcode.cn/problems/ip-to-cidr/solution/er-jin-zhi-ji-suan-trieshu-bian-li-by-in-xrw0/) | Trie树遍历 + 二进制计算 | 中等 |
|                                                              |                         |      |
|                                                              |                         |      |







# 字符串hash











# KMP





### 1. 问题

1. 如果就只有一个字符，那么++j会超过0,怎么解决的? nx数组开大一个或者两个
2. 时间复杂度的分析$O(len(smalls) * (m + n))$ = 1000 * (1000 + 1000); 这个题可以使用KMP是因为big串很小，如果big串到达1e6，那么每次需要遍历一次m，并且需要遍历len(small)次就不行了。

```cpp

class Solution {
public:

    // 获取失配指针
    vector<int>getFail(string &s) {
        int m = s.size();
        vector<int>nx(m + 2, -1);
        int j = 0, k = -1;
        while (j < m) {
            // printf("%d %d\n", j, k);
            if (k == -1 || s[j] == s[k]) {
                if (s[++j] == s[++k]) nx[j] = nx[k];
                else nx[j] = k;
            } else k = nx[k];
        }
        // cout << s << endl;
        // for (int i = 0; i < n; i++) {
        //     printf("%d ", nx[i]);
        // }
        // // printf("\n");
        return nx;
    }
    vector<vector<int>> multiSearch(string big, vector<string>& smalls) {
        
        int n = big.size();
        vector<vector<int>>ans;
        for (string &s : smalls) {

            vector<int>nx = getFail(s);
            vector<int>tmp;
            int i = 0, j = 0, m = s.size();
            if (m == 0) {ans.push_back(tmp); continue; }
            while(i < n) {
                if (j == -1 || big[i] == s[j]) {
                    i++, j++;
                } else j = nx[j];
                if (j == m) {
                    tmp.push_back(i - m);
                    // j = nx[j];
                }
            }
            ans.push_back(tmp);
        }
        // [i - m, i - 1] 长度是m
        return ans;
    }
};
```





# AC自动机



## AC自动机



### 1. 如何去调试

    对于Trie树如何去调试
    1. 是否cur问题 : 建树的时候，每一个单词看idx是否正确增加, 再不行就输出(cur, idx)看一下。
    2. 是否忘了入队列了, 输出u和v看一下。
    3. 失配指针是不是写错了？输出fail看一下。  	// 慎用
    3. 询问的时候，看一下是否让他为0，输出(i, cnt)截止到i有几个单词了, 还有就是cur指向的idx对不对。
    4. 如果多组数据是否忘记了初始化



### 2. AC自动机的last优化

**last优化，就是记录上一个不为0的cnt的结点。这样在统计的时候，就直接统计last，不用每次都统计fail指针了。**

**新构造链接优化，让无用的指针指向自己的大侄子。**

```cpp
#include <iostream>
#include <cstdio>
#include <cstring>
#include <algorithm>
#include <queue>
using namespace std;

const int N = 3e6 + 5, M = 1e5 + 5;


struct Q{
    int num;
    int type;
    string str;
}q[M];

int tr[N][26], idx, cnt[N], f[N], last[N];
int que[N];
string str;
int ans[M];
//
int add() {
    int cur = 0;                                                // cur 没有初始化为root
    for (int i = 0; str[i]; i++) {
        int nx = str[i] - 'a';
        if (!tr[cur][nx]) tr[cur][nx] = ++idx;
        cur = tr[cur][nx];
    }
    cnt[cur]++;
    return cur;
}

void build() {
    for (int i = 0; i <= idx ;i++) f[i] = -1;
    
    queue<int>que;
    que.push(0);
    
    while(!que.empty()) {
        int u = que.front(); que.pop();
        for (int i = 0; i < 26; i++) {
            int v = tr[u][i];
            if (v) {
                que.push(v);
                int fa = fail[u];
                while(fa == -1 && !ch[fa][i]) fa = fail[fa];
                fail[v] = fa == -1 ? 0 : tr[fa][i];
                last[v] = cnt[f[v]] ? f[v] : last[f[v]];
            } else if (f[u] != -1) tr[u][i] = tr[f[u]][i];
        }
    }
}

int cal(int t)
{
    int ans=0;
    while(t)
    {
        ans += cnt[t];
        t = last[t];
    }
    return ans;
}
int query()
{

    int ans = 0, cur = 0;
    for (auto &ch : str) {
        cur = tr[cur][ch - 'a'];
        if (cnt[cur]) ans += cal(cur);
        else if (last[cur]) ans += cal(last[cur]);
    }

    return ans;
}

void test() {

    cin >> str;
    add();
    cin >> str;
    add();
}

void init() {

    for (int i = 0; i <= idx; i++) {
        f[i] = 0, cnt[i] = 0;
        for (int j = 0; j < 26; j++) {
            tr[i][j] = 0;
        }
    }
    idx = 0;
}
```









### 3. 一些问题

1. 为什么不能将第一层的直接放入队列中？第一层的永远都是指向0的，因为没有人的子节点可以是0。
2. 全局变量一定要memset，因为会多次调用的！！！
3. 注意Trie树 + 后缀，或者KMP也可以部分实现AC自动机的功能。
4. **f[v]**一定要使用**while**进行一步到位的链接，不知道为什么，如果不是一步到位的化，很容易出现超时。

### 4. 相关题目

[牛客网 AC自动机 + 离线做法](https://ac.nowcoder.com/acm/problem/14612)

[leetcode中裸题](https://leetcode-cn.com/problems/multi-search-lcci/submissions/)





# Fail 树



就是把建立好的trie树，使用原来的fail指针，反向作为树的链接。这样如果把文本串的每一个对应的字符结点加上1，之后就可以使用dfs统计每一个单词在文本串中出现了多少次，也就是统计树根结点的权重。当然也可以统计每一个结点在trie树种出现的位置区间。这样就可以结合线树状数组进行求解了。

**为什么是统计树根的权重呢？**

类似于有向无环图，一个单词被匹配的次数，就是它尾结点**被匹配的次数+可被匹配的次数**。被匹配就是直接在文本串中，可匹配就是由别的结点跳转到它得到的。



```cpp
#include<iostream>
#include<cstdio>
#include<queue>
#include<algorithm>
#include<cstring>
using namespace std;
typedef long long lt;

int read()
{
    int f=1,x=0;
    char ss=getchar();
    while(ss<'0'||ss>'9'){if(ss=='-')f=-1;ss=getchar();}
    while(ss>='0'&&ss<='9'){x=x*10+ss-'0';ss=getchar();}
    return f*x;
}

const int maxn=200010;
int n;
char txt[maxn*10],pt[maxn];
int ch[maxn][26],fail[maxn];
int rem[maxn],num[maxn],cnt;
struct node{int v,nxt;}E[maxn<<1];
int head[maxn],tot;
int val[maxn],sum[maxn];

void addE(int u,int v)
{
	E[++tot].nxt=head[u];
	E[tot].v=v;
	head[u]=tot;
}

int ins(char *ss)
{
	int u=0,len=strlen(ss);
	for(int i=0;i<len;++i)
	{
		int x=ss[i]-'a';
		if(!ch[u][x]) ch[u][x]=++cnt;
		u=ch[u][x];
	}
	return u;
}

void getFail()
{
    queue<int> q;
    for(int i=0;i<26;++i)
    if(ch[0][i]) fail[ch[0][i]]=0,q.push(ch[0][i]);
     
    while(!q.empty())
    {
        int u=q.front(); q.pop();
        for(int i=0;i<=25;++i)
        {
            if(!ch[u][i]) continue;
            int tt=fail[u];
            while(!ch[tt][i]&&tt) tt=fail[tt];
            fail[ch[u][i]]=ch[tt][i];
            q.push(ch[u][i]);
        }
    }
    
    for(int i=1;i<=cnt;++i)//构造Fail树, 其实可以直接使用fail_tree[fail[v]].push_back(v)进行创建。
    addE(fail[i],i);
}

void solve(char *ss)		// 让每一个文本节点，设置为1
{
	int u=0,len=strlen(ss);
	for(int i=0;i<len;++i)
	{
		int x=ss[i]-'a';
		while(!ch[u][x]&&u) u=fail[u];		// 直接一趟文本结点，不用kmp这种。
		u=ch[u][x];
		
		val[u]++;
	}
}

void dfs(int u)							// 遍历fail树
{
	sum[u]=val[u];
	for(int i=head[u];i;i=E[i].nxt)		
	{
		int v=E[i].v;
		dfs(v);
		sum[u]+=sum[v];
	}
}

int main()
{
	n=read();
	for(int i=1;i<=n;++i)
	{
		scanf("%s",&pt);
		rem[i]=ins(pt);
	}
	
	getFail(); 
	
	scanf("%s",&txt);
	solve(txt);//将属于文本串的节点都权值都置为1
	
	dfs(0); // dfs统计子树权值和 
	for(int i=1;i<=n;++i)
	printf("%d\n",sum[rem[i]]);
	
	return 0;
}

```



##  问题

 	1. 给txt文章中的每一个结点进行赋值的时候，就已经遍历了整个txt和fail指针了吧。为什么不这个时候进行计数呢？
 	  	1. 传统的trie树，需要每一个txt[i]，进行一次last遍历，回溯到最顶，时间复杂度最坏可以极其大。
 	  	2. 但是赋值就完全不需要，因为只有在匹配失败的时候才进行回溯。而且并不是回溯到头，只是回溯到能继续匹配的位置。相当于O(M + N)的KMP匹配过程。




# 时间复杂度分析

- fail树的时间复杂度为O(S + M)，文本串的长度 + 构建trie树的时间复杂度。构建trie树就S = 字典的总长度。
- AC自动机的最坏时间复杂度是O(M*M), aaa..和aaa..进行匹配
- last优化的时间复杂度是O(M * sqrt(S))



# 参考文章



[fail树](https://blog.csdn.net/niiick/article/details/87947160)

[时间复杂度分析](https://zhuanlan.zhihu.com/p/297026679)
