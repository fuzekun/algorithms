## 1. 算法介绍

### 算法描述



在最短路问题中，如果所有的边权都是非负的，那么就可以使用启发函数来优化bfs过程。

使用**启发式原则**，dist[s] + f[s]作为启发函数，使得如果这个值最小，那么一定不会有比dist[s]更优的解。

**性质**:定义g[s]为终点到s的长度，只要f[s] <= g[s]，就可以保证，当某一个状态s，第一次从优先队列中出来，它就一定是最短距离。

如果f[s] == 0, 就是迪杰斯特拉算法，如果f[s] = g[s]就是线性做法。

所以只要选择好了g[s]就可以把$O(n^2)$的算法降低为$O(mlongn)$

## 例题

### 找第K短路

给定一张 NN 个点（编号 1,2…N1,2…N），MM 条边的有向图，求从起点 SS 到终点 TT 的第 KK 短路的长度，路径允许重复经过点或边。

**注意：** 每条最短路中至少要包含一条边。

#### 输入格式

第一行包含两个整数 NN 和 MM。

接下来 MM 行，每行包含三个整数 A,BA,B 和 LL，表示点 AA 与点 BB 之间存在有向边，且边长为 LL。

最后一行包含三个整数 S,TS,T 和 KK，分别表示起点 SS，终点 TT 和第 KK 短路。

#### 输出格式

输出占一行，包含一个整数，表示第 KK 短路的长度，如果第 KK 短路不存在，则输出 −1−1。

#### 数据范围

>1≤S,T≤N≤10001≤S,T≤N≤1000,
>0≤M≤1040≤M≤104,
>1≤K≤10001≤K≤1000,
>1≤L≤1001≤L≤100

#### 输入样例：

```
2 2
1 2 5
2 1 4
1 2 2
```

#### 输出样例：

```
14
```

1. 先使用dijstra算法得到相应的终点到任意点的最短路作为f[i]
2. 使用启发式搜索的原则进行宽度优先搜索。

djstra算法就是一个宽度优先搜索。

```cpp
#include <cstdio>
#include <cstring>
#include <iostream>
#include <queue>
#include <algorithm>
#include <utility>
#define x first
#define y second

using namespace std;
typedef pair<int, int>PR;
typedef pair<int, PR>PRR;

const int N = 1005;

int dis[N];
int m, n;
vector<PR>G[N];
int S, T, K;

void dijstra() {
    vector<int>vis(n + 1, 0);
    priority_queue<PR, vector<PR>, greater<PR> > heap;
    heap.emplace(0, T);
    memset(dis, 0x3f, sizeof dis);
    dis[T] = 0;
    
    while(!heap.empty()) {
        auto [d, u] = heap.top(); heap.pop();
        
        if (vis[u]) continue;
        vis[u] = 1;
        // printf("u = %d\n", u);
        // printf("v:");
        for (auto [v, w] : G[u]) {
            if (dis[u] + w < dis[v]) {
                dis[v] = dis[u] + w;
                heap.emplace(dis[v], v);
                // printf("%d %d)", v, dis[v]);
            }
        }
        // printf("\n");
    }
}

int astar() {
    priority_queue<PRR, vector<PRR>, greater<PRR> >heap;
    vector<int>cnt(n + 1, 0);
    heap.push({dis[S], {0, S}});    // 第一个是函数，f[i] + d[i]
    
    while(!heap.empty()) {
        auto t = heap.top(); heap.pop();
        int u = t.y.y, d = t.y.x;
            
        if (cnt[u] >= K) continue;      // 剪枝
        cnt[u] ++;
        
        if (cnt[T] == K) return d;      // get解
        
        for (auto [v, w] : G[u]) {
            if (cnt[v] < K) {
                heap.push({dis[v] + w + d, {d + w, v}}); // 注意放的顺序
            }
        }
    }
    
    return -1;
}



int main() {
    
    cin >> n >> m;
    for (int i = 0; i < m; i++) {
        int a, b, c;
        scanf("%d%d%d", &a, &b, &c);
        G[a].emplace_back(b, c);
    }
    
    cin >> S >> T >> K;
    if (S == T) K--;
    
    dijstra();
    
    // for (int i = 1; i <= n; i++) {
    //     printf("%d %d\n", i, dis[i]);
    // }
    
    cout << astar() << endl;
    
    return 0;
    
}
```

