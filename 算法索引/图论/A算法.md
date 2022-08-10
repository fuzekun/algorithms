## 1. 算法介绍

### 算法描述



在最短路问题中，如果所有的边权都是非负的，那么就可以使用启发函数来优化bfs过程。

使用**启发式原则**，dist[s] + f[s]作为启发函数，使得如果这个值最小，那么一定不会有比dist[s]更优的解。

**性质**:定义g[s]为终点到s的长度，只要f[s] <= g[s]，就可以保证，当某一个状态s，第一次从优先队列中出来，它就一定是最短距离。

如果f[s] == 0, 就是迪杰斯特拉算法，如果f[s] = g[s]就是线性做法。

所以只要选择好了g[s]就可以把$O(n^2)$的算法降低为$O(mlongn)$

## 2. 例题

### 2.1 找第K短路

给定一张 NN 个点（编号 1,2…N1,2…N），MM 条边的有向图，求从起点 SS 到终点 TT 的第 KK 短路的长度，路径允许重复经过点或边。

**注意：** 每条最短路中至少要包含一条边。

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







### 2.2 八数码



- 数据结构，状态表示 + dijstra的数据结构 + 记录路径的数据结构
  - que
  - dist
  - unordered_map<string, <string, char>>prev : state对应的前一个状态以及操作。
- f(string)的设计

```cpp
int f(string s) {						// 每一步到目标位置的最小移动步数 abs(x - rx) + abs(y + ry)。
    int ans;
    for (int i = 0; i < state.size(); i++) {
        if (state[i] != 'x') {
            int t = state[i] - '1';		// 注意是 - ‘1’， 不是减去'0',因为是从[1, 8]，映射到[0, 7]
            ans += abs(i / 3 - t / 3) + abs(i % 3 - t % 3);
        }
    }
    return ans;
}
```

- 宽搜的注意事项
  - 放入队列。
  - 到头判断，出队。

