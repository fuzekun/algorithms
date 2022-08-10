# 剑指Offer算法笔记





## 数组



### 1. 二分

### 2. 双指针

### 3. 矩阵

**1. 顺时针打印矩阵**











## 数学



### 丑数

- 三指针法，每次取最小的。

- map进行去重



### 逆序对

**方法1**



- 注意应该统计大于a[j]的前面的数字有多少。因为a[j]只会出现一次，而a[i]会使用很多次。
- 首先套用归并排序的模板。

```c++
class Solution {
public:
    /*
    
        
前面的比后面的大，给答案添加了多少逆序对呢？
a[i] > a[j]的话，那么a[i] > a[j - 1] > a[j - 2] ... > a[mid + 1]所以由j - mid个数字。

对于每一个a[i]，应该找到最后一个a[j]，否则会重复统计答案，就是找第一个a[j] > a[i]的值。如果j < n的话进行统计，否则直接break。
    
    
        对于每一个a[j]来说呢？
        如果a[j] < a[i]，那么a[j] < a[i + 1] < a[i + 2] <.. a[mid] // mid - l + 1
        并且对于每一个a[j]只会统计一次。
    */
    
    vector<int>tmp;
    int merge_sort(vector<int>&a, int l, int r) {
        if (l >= r) return 0;
        int mid = l + r >> 1;
        int lcnt = merge_sort(a, l, mid);
        int rcnt = merge_sort(a, mid + 1, r);
        
        int i = l, j = mid + 1, k = l;
        int ans = lcnt + rcnt;
        while (i <= mid && j <= r) { //[i, mid], (mid, r];
            if (a[i] <= a[j]) 
                tmp[k++] = a[i++];
            else {
                tmp[k++] = a[j++];
                ans += mid - i + 1; // a[j]只会统计一次，所以使用a[j], 比他大的前面[i, mid];
            }
        }
        while(i <= mid) tmp[k++] = a[i++];
        while(j <= r) tmp[k++] = a[j++];
        for (i = l; i <= r; i++) a[i] = tmp[i];
        return ans;
    }
    
    int inversePairs(vector<int>& nums) {
        int n = nums.size();
        tmp = vector<int>(n);
        int ans = merge_sort(nums, 0, n - 1);
        // for (int i = 0; i < n; i++) {
        //     printf("%d ", nums[i]);
        // }
        // printf("\n");
        return ans;
    }
};
```



**方法2:树状数组 + 离散化**



- 注意erase(nums.删除， **nums.end())，**
- 数组赋值给全局变量，相当于复制一份，不是指针的指向。

```c++
class Solution {
public:

    static const int maxn = 105;
    int c[maxn];
    int lowbit(int x) {
        return x & -x;
    }
    int add(int x) {
        for (int i = x; i; i -= lowbit(i)) c[i]++;
    }
    int sum(int x) {
        int res = 0;
        for (int i = x; i < maxn; i += lowbit(i)) res += c[i];
        return res;
    }
    vector<int>numss;
    int discretization() {
        sort(numss.begin(), numss.end());
        numss.erase(unique(numss.begin(), numss.end()), numss.end());
    }
    int get(int x) {
        int l = 0, r = numss.size();
        while(l < r) {
            int mid = l + r >> 1;
            if (numss[mid] == x) return mid + 1;
            else if (numss[mid] > x) r = mid;
            else l = mid + 1;
        }
        return -1;
    }
    int inversePairs(vector<int>& nums) {
        this->numss = nums;
        discretization();
        int ans = 0;
        for (int &x : nums) {
            int idx = get(x);
            // printf("x = %d idx = %d\n", x, idx);
            ans += sum(idx + 1);
            add(idx);
        }
        return ans;
    }
};
```





### 从1到n整数1出现的次数

### 组合数组使得数字最小

### 数字序列中某一位的数字

- 找到是几位数字
- 找到是第几个数字
- 找到第几位

```c++
class Solution {
public:
    int digitAtIndex(int n) {
        long base = 1, cnt = 10, k = 1; // cnt : 表示当前位数的数字有多少个
        while(n >= k * cnt) {           // 2. 这里应该等于，因为没有加上1，加上一表示的是个数
            n -= k * cnt;
            k++;
            base *= 10;                 // 1. 注意这里应该先乘以base
            cnt = base * 10 - base;     
        }
        // int bg = base == 1 ? 0 : base;  // 如果就只有一位，是从0开始的, 其他的从10开始的
        int num = (base == 1 ? 0 : base) + n / k;
        int idx = n % k;        
        for (int i = 0; i < k - idx - 1; i++) num /= 10; // 3. 这里应该减去1。第k - idx - 1位，所以应该把k - idx - 2位去掉
        return num % 10;
    }
};
```









**方法1**

- 使用归并排序



**方法2**

- 树状数组 + 离散化处理









## 堆



### 大小堆--数据流中的中位数





## dp



### 把数字翻译成字符串

简单dp

dp[i] : 表示以i结尾的解释方式有多少
1. 如果当前字符和前面的字符不可以组成字符，dp[i] = dp[i - 1]
2. 如果当前字符可以和前面字符组成字母, dp[i] = dp[i - 1] + dp[i - 2]
3. 注意如果是04,这种也没法组成字母。所以判断不能仅仅判断是否小于等于25
4. 初始化dp[0] = dp[1] =1;

最后注意到只用了两个变量，所以可以优化空间复杂度，使用滑动数组进行优化

未优化

    112
    
    1
    1 + 1
    2 + 1 = 3
    
    12258
    
    1
    2
    2 + 1 = 3
    3 + 2 = 5

```c++
int getTranslationCount(string s) {
    int n = s.size();
    vector<int>dp(n, 0);
    dp[0] = 1;
    for (int i = 1; i <= n; i++) {
        dp[i] = dp[i - 1];
        // if (i >= 2) printf("i = %d, t = %d\n", i, (s[i - 2] - '0') * 10 + s[i - 1] - '0');
        if (i >= 2 && s[i - 2] != '0' && (s[i - 2] - '0') * 10 + s[i - 1] - '0' <= 25) dp[i] += dp[i - 2];
    }
    return dp[n];
}
```



    112
    
    1
    1 + 1
    2 + 1 = 3
    
    12258
    1
    1
    2
    2 + 1 = 3
    3 + 2 = 5

```c++
int getTranslationCount(string s) {
    int n = s.size();
    vector<int>dp(2, 0);            // 优化空间复杂度
    dp[0] = dp[1] = 1;
    for (int i = 2; i <= n; i++) {
        // if (i >= 2) printf("i = %d, t = %d\n", i, (s[i - 2] - '0') * 10 + s[i - 1] - '0');
        if (s[i - 2] != '0' && (s[i - 2] - '0') * 10 + s[i - 1] - '0' <= 25) 
            dp[i & 1] = dp[(i - 1) & 1] + dp[(i - 2) & 1];
        else dp[i & 1] = dp[(i - 1) & 1];
    }
    return dp[n & 1];
}

// 不可以按照上面的那种，先进行赋值在判断加。因为一旦赋值，就会把dp[(i - 2) & 1]覆盖掉
```





## 树

### 二叉树遍历应用



**1. 寻找路径**

- 首先路径是到叶子结点，所以应该使用前序遍历
- 然后判断叶子节点的方法。
- 最后需要注意，应该特殊判断根节点为空的情况。

```c++
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
 /*
 
    当前结点为NULL,不代表父节点已经到了叶子结点了。
    
    前序遍历找叶子结点，如果是叶子结点，直接判断否则遍历。
    
    另外还是需要加上 root == NULL 这个条件的。
    1. 如果根节点为NULL
*/
class Solution {
public:
    vector<vector<int>>ans;
    void dfs (TreeNode *root, int s, vector<int>tmp) {
        if (root == NULL) return;
        s -= root->val;
        tmp.push_back(root->val);
        if (!root->left && !root->right && !s) {
            ans.push_back(tmp);
            return ;
        }
        if (root->left) dfs(root->left, s, tmp);
        if (root->right) dfs(root->right, s, tmp);
    }
    vector<vector<int>> findPath(TreeNode* root, int sum) {
        dfs(root, sum, vector<int>());
        return ans;
    }
};
```

**2. 根据排序二叉树的后续遍历得到二叉树**

- 排序二叉树后续遍历的排序就是中序
- 根据中序和后续可以得到排序二叉树。
- 方法2：**根节点的左边的都小于根，右边的都大于根**。**所以可以根据这个性质找到左右子树。**



```java
class Solution {
public:
/*

排序之后就是中序遍历的结果

左边第一个大于它的是右子树的起始位置。
为什么呢？排序二叉树，左 < 根 < 右


4 6 8 10 12 14 16

    



*/

    vector<int>seq;
    bool verifySequenceOfBST(vector<int> sequence) {
        seq = sequence;
        return dfs(0, sequence.size() - 1);
    }
    bool dfs(int l, int r) {
        if (l >= r) return true;
        int k = l, root = seq[r];           /// 这里从l开始
        while (seq[k] < root) k++;
        // printf("k = %d\n", k);
        for (int i = k; i < r; i++) 
            if (seq[i] < root) 
                return false;
        // printf("%d %d %d\n", l, k, r);
        return dfs(l, k - 1) && dfs(k, r - 1);  /// 注意这里是r - 1。
    }
    
};
```

- 需要注意
  - r - 1
  - 从l开始找根







**3. 重建二叉树**







**序列化二叉树**

**1.简单版本**

**2. 困难版本**



编码使用前序遍历，每一个结点使用空格进行间隔。

解码重要的是使用全局变量，控制当前遍历到哪一个结点了。u永远指向结点的第一个。[u, k)表示结点的值。

- 如果是负数的时候需要注意数字的转化

- 每次需要去除空格 u = k + 1

```c++
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
/*


前序遍历。
1. 每次得到一个结点，如果是数字 val + ' ', 如果是空结点 null + ' ' 
2. 遍历使用全局变量。[u, k]是当前的结点代表的字符串。
*/
    // Encodes a tree to a single string.
    
    void dfs(TreeNode *root, string &res) {
        if (root == NULL) {
            res += "null ";
            return ;
        }
        res += to_string(root->val) + ' ';
        dfs(root->left, res);
        dfs(root->right, res);
        
    }
    string serialize(TreeNode* root) {
        string res;
        dfs(root, res);
        // cout << res << endl;
        return res;
    }

    // Decodes your encoded data to tree.
    TreeNode* deserialize(string data) {
        int u = 0;
        return dfs_d(data, u);
    }
    
    TreeNode *dfs_d(string data, int& u) {
        if (u == data.size()) return NULL;
        int k = u;
        while(data[k] != ' ') k++;      // 找到空格的位置，也就是下一个结点的位置
        if (data[u] == 'n') {
            u = k + 1;                  // 去除这个空格
            return NULL;
        }
        int val = 0;
        int flag = 1;
        if (data[u] == '-') flag = 0, u++;
        for (int i = u; i < k; i++) val = val * 10 + data[i] - '0';
        if (!flag) val = -val; 
        auto root = new TreeNode(val);
        u = k + 1;
        root->left = dfs_d(data, u);
        root->right = dfs_d(data, u);
        return root;
    }
    
};
```



**方法2**

- 使用#表示空结点
- 使用,进行非空结点的分割
- 使用前序遍历进行序列化
- **解码的时候，字符串加上引用。**
- 使用`stoi(s.substr())`更加简单

```c++
class Codec {
public:

/*

    直接使用#代表空结点
    1. 不需要使用,进行分割。遇见#就代表着是一个空结点了/
    2. 遇到数字的时候，最后一定以逗号进行终止。
    3. 使用默认的函数
*/

    void dfs(TreeNode *root, string &ans) {
        if (root == NULL) {
            ans += '#';
            return ;
        }
        ans += to_string(root->val) + ',';
        dfs(root->left, ans);
        dfs(root->right, ans);
    }
    // Encodes a tree to a single string.
    string serialize(TreeNode* root) {
        string ans;
        dfs(root, ans);
        return ans;
    }

    TreeNode* build(string &data, int &cur) {
        if (data[cur] == '#') {
            cur++;           
            return NULL;
        }
        int k = cur;
        while (data[k] != ',') k++;
        int val = stoi(data.substr(cur, k - cur));          // 使用默认的函数更加简单
        cur = k + 1;                                        // 去掉这个逗号
        
        TreeNode *ans = new TreeNode(val);
        ans->left = build(data, cur);
        ans->right = build(data, cur);
        return ans;
    }
    // Decodes your encoded data to tree.
    TreeNode* deserialize(string data) {
        int x = 0;
        return build(data, x);
    }
};

// Your Codec object will be instantiated and called as such:
// Codec* ser = new Codec();
// Codec* deser = new Codec();
// string tree = ser->serialize(root);
// TreeNode* ans = deser->deserialize(tree);
// return ans;
```







### **结合map**

**二叉搜索树转成双向链表**

- 使用的二叉搜索的左右指针，不一定指向的是前驱和后继。比如**根节点的后继应该是左子树的最大值，而不是左儿子**。

```c++
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
/*

    1. 做指针指向前驱，右指针指向后记
*/
    unordered_map<int, TreeNode*>mp;
    vector<int>nums;
    void dfs(TreeNode *root) {
        if (!root) return ;
        dfs(root->left);
        mp[root->val] = root;
        nums.push_back(root->val);
        dfs(root->right);
    }
    TreeNode* convert(TreeNode* root) {
        dfs(root);
        if (nums.size() <= 1) return root;
        int n = nums.size();
        TreeNode* node = mp[nums[0]];
        node->right = mp[nums[1]];
        // printf("%d\n", n);
        for (int i = 1; i < n - 1; i++) {
            
            auto node = mp[nums[i]];
            auto nx = mp[nums[i + 1]];
            auto pre = mp[nums[i - 1]];
            // printf("node = %d pre = %d nx = %d\n", node->val, pre->val, nx->val);
            node->left = pre;           // 指向前驱，但是默认的left，不一定指向前驱
            node->right = nx;
        }
        node = mp[nums[n - 1]];
        node->left = mp[nums[n - 2]];
        return mp[nums[0]];
    }
};
```



### 杂七杂八的树的问题



**树的子结构**

- 直接暴力
- 每一个为根
- 前序遍历判断

```c++
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    bool hasSubtree(TreeNode* pRoot1, TreeNode* pRoot2) {
        if (!pRoot2 || !pRoot1) return false;
        if (check(pRoot1, pRoot2)) return true;
        return hasSubtree(pRoot1->left, pRoot2) || hasSubtree(pRoot1->right, pRoot2);
    }
    bool check(TreeNode *root1, TreeNode *root2) {
        if (!root2) return true;
        if (!root1 || root1->val != root2->val) return false;
        return check(root1->left, root2->left) && check(root1->right, root2->right);
    }
};
```



**镜像二叉树**

做题的方法

1. 理解题意
2. 找到性质
3. 提出方案



```c++
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
/*

    1. 理解题意
    2. 找到性质
    3. 解决方案
*/
    void mirror(TreeNode* root) {
        if (!root) return ;
        swap(root->left, root->right);
        mirror(root->left);
        mirror(root->right);
    }
};
```



升级版的镜像二叉树

- 左子树的右儿子和右子树的左儿子
- 左子树的左儿子和右子树的右儿子

是相同的，那么就是镜像二叉树。

```c++
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    bool isSymmetric(TreeNode* root) {
       if (!root) return true;
       return dfs(root->left, root->right);
    }
    bool dfs(TreeNode *p, TreeNode *q) {
        if (!p || !q) return !p && !q;
        if (p->val != q->val) return false;
        return dfs(p->left, q->right) && dfs(p->right, q->left);
    }
};
```







## 图



## 链表



### **1.链表的公共结点。**

两个走，一个到头，返回到另一个结点从新开始。直到两个结点相遇就是第一个公共结点。

### **2. 链表找环**

### **3. 每k个一组，实现链表的逆序**



核心思想

- 采用头插法进行逆序
- 双指针，[l, r)标记头和尾。

注意事项

- 需要创建一个新的头节点nhead。
- 每次需要更新待插入的头节点，**第一个待插入的结点是新的头节点**。
- **最后一个头节点需要指向l**。因为每次反转都相当于一次断链，相当于把r后面的断开，最后需要重新连接。

代码调试

- 等于1的时候
- 等于链表长度的时候
- 输出每次反转的l和r以及当前的头结点。

```c++
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
/*
    一个一组就是完全逆序
    cnt : 移动次数
    r，哨兵指针
    [l, r)

    采用头插法，需要新创建一个头节点,
    每次需要保存第一个待插入的头节点作为新的头节点。


    [l, r)
    nhead
    bg
    pre

    边界：
    curhead最后需要指向当前的第一个待插入的结点才可以break, 因为每次反转之后，最后剩下的结点都是没有连接上的。
*/
    ListNode* reverseKGroup(ListNode* head, int k) {
        ListNode *nhead = new ListNode(-1, head);
        ListNode *l = head, *r = head;                  //[l, r)表示待反转的链表
        ListNode *nxhead = nhead;                       // 下一个头节点，每次是反转链表的第一个结点
        while (1) {
            int cnt = 0;                                // 移动的次数
            while (cnt < k && r) r = r->next, cnt++;
            // printf("cnt = %d\n", cnt);
            ListNode *curHead = nxhead;
            if (cnt < k) {
                curHead->next = l;
                break;
            }
            // if (r)
                // printf("%d %d %d\n", l->val, r->val, curHead->val);
            nxhead = NULL;
            while (l != r) {
                ListNode *p = l;                      // 头插法，待插入的结点
                if (!nxhead) nxhead = p;  
                l = l->next;
                p->next = curHead->next;
                curHead->next = p;
            }
        }
        return nhead->next;
    }
};
```



- 对于每一个l, 找到对应的r。

- 翻转[l, r)
- 然后把pre->next = nl, nr->next->r;
- 更新pre、l。

```c++
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
  typedef pair<ListNode*, ListNode*> PR;
    PR reverseList(ListNode *l, ListNode *r) {
        auto a = l, b = a->next;
        while (b != r) {
            auto c = b->next;
            b->next = a;
            a = b, b = c;
        }
        l->next = r;                            // 可加可不加
        return {a, l};                          // a永远指向头，l 就是原来的头，现在已经成了尾了
    }
    ListNode* reverseKGroup(ListNode* head, int k) {
        if (head == NULL || head->next == NULL || k == 1) return head; 
        ListNode *bg = new ListNode(-1, head);
        ListNode *l = head, *r = head;
        auto pre = bg;

        while (1) {
            int cnt = 0;
            while(cnt < k && r) cnt++, r = r->next;
            if (cnt < k) {
                break;
            }
            auto [nl, nr] = reverseList(l, r);  // 将[l, r)进行翻转
            pre->next = nl;
            nr->next = r;
            pre = nr;
            l = r;
        }
        return bg->next;
    }
};
```







### **4. 复刻复杂链表**



最高端的食材，往往只需要最简单的烹饪方式。应该就是这个意思了吧。

- 注意如果指针为空的时候。对应的rand也应该为空。

```c++
/**
 * Definition for singly-linked list with a random pointer.
 * struct ListNode {
 *     int val;
 *     ListNode *next, *random;
 *     ListNode(int x) : val(x), next(NULL), random(NULL) {}
 * };
 */
 /*
 
    1. idx存储第一个链表每个结点的id
    2. mp存储第二个链表id对应的结点
 */
class Solution {
public:
    ListNode *copyRandomList(ListNode *head) {
        ListNode *nhead = new ListNode(-1);
        ListNode *p = nhead, *q = nhead;
        unordered_map<int, ListNode*>mp;
        unordered_map<ListNode*, int>idx;
        ListNode *tmp = head;
        int x = 0;
        while(tmp) idx[tmp] = x++, tmp = tmp->next;
        x = 0;
        tmp = head;
        while(tmp) {
            p = new ListNode(tmp->val);
            mp[x++] = p;
            q->next = p;
            q = p;
            tmp = tmp->next;
        }
        p = nhead->next;
        tmp = head;
        while(p) {
            ListNode *randm = tmp->random == NULL ? NULL : mp[idx[tmp->random]]; 
            p->random = randm;
            p = p->next;
            tmp = tmp->next;
        }
        return nhead->next;
    }
};

```





### **5.反转链表**

- 链表是否为空
- 创建头节点
- **反转完成后，最后的结点应该指向空结点。**

```c++
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    ListNode* reverseList(ListNode* head) {
        if (head == NULL) return head;          /// 注意前边界
        ListNode *nhead = new ListNode(-1); 
        nhead->next = head;
        ListNode *p = head;
        ListNode *ed = NULL;            
        while (p) {
            ListNode *q = p;                    // 当前待插入结点
            if (!ed) ed = q;
            p = p->next;                        // 下一个待插入结点
            q->next = nhead->next;
            nhead->next = q;
        }
        ed->next = NULL;                        /// 注意后边界
        return nhead->next;
    }
};
```



**三指针法：**



- a永远指向头
- b指向头待翻转的点
- c保存下一个点
- **最后head = NULL, 返回头a**
- **注意边界条件**

```c++

reverseList(ListNode *head) {
    auto a = head, b = a->next;
    while (b) {
        auto c = b;
        b->next = a;
        a = b, b = c;
    }
    head->next = NULL;
    return a;
}

```



**递归做法**



简洁的递归做法。

- 触底反弹
- 反弹的时候翻转一下
- 每个结点**会有两次指针改变**。第一次先指向NULL， 之后回到上一个结点，让这个结点的指针指向自己。

```c++
class Solution {
public:
    ListNode* reverseList(ListNode* head) {
        if (!head || !head->next) return head;
        auto tail = reverseList(head->next);
        head->next->next = head;
        head->next = NULL;
        return t
    }
};
```







## 其他



### 字符流中第一次出现一次的数字

### 滑动窗口最大值

- **入队的时候**，如果当前的值大于了之前的值，那么之前的值就不可能是窗口最大值了。所以直接出队列就行了。
- **出队的时候**，懒删除，如果队头的版本已经过期了直接删除。
- 注意**存放的是下标**



### 超过一半的数字

- 第一种写法，首先进行判断。如果cnt == 0, 说明前面的数字已经被消耗掉了。所以这个数字开始等于1就行了。
- 第二种写法，小于0，说明前面的数字等于0，从这里开始新生

```c++
// 不一定保证数字都大于0
int n = nums.size();
int ans = nums[0], cnt = 1;
for (int i = 1; i < n; i++) {
    int x = nums[i];
    if (cnt == 0) ans = x, cnt = 0;
    else {
        if (ans == x) cnt++;
        else cnt--;
    }
}


int n = nums.size();
int ans = nums[0], cnt = 1;
for (int i = 1; i < n; i++) {
    int x = nums[i];
    if (ans == x) cnt++;
    else {
        cnt--;
        if (cnt < 0) ans = x, cnt = 1;		// 小于0，说明前面的数字等于0，从这里开始新生。
    }
}

```





