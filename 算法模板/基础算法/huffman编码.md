huffman编码

```c++
/*

建立haffman树。

*/

#include <iostream>
#include <cstring>
#include <cstdio>
#include <algorithm>
#include <queue>
#include <unordered_map>

using namespace std;

static const int maxn = 256;
// Encodes a URL to a shortened URL.
struct HuffmanTree {
    HuffmanTree *l, *r;
    int cnt;
    char ch;                              // 词频和单词
    HuffmanTree(int cnt, char ch, HuffmanTree *l, HuffmanTree *r):cnt(cnt), ch(ch), l(l), r(r) {}
};
class cmp{
public:
    /* Node::priority 大的优先, 重写小于号 */
    bool operator () (HuffmanTree* &a, HuffmanTree* &b) const
    {
        return a->cnt > b->cnt;
    }
};

typedef pair<int, char>PR;
priority_queue<HuffmanTree*, vector<HuffmanTree*>, cmp>que, bakcup;
unordered_map<char, int>mp;

HuffmanTree* buildHuffman() {
    bakcup = que;
    while (!bakcup.empty()) {
        printf("%d %c\n", bakcup.top()->cnt, bakcup.top()->ch);
        bakcup.pop();
    }

    while (que.size() >= 2) {
        HuffmanTree* t1 = que.top(); que.pop();
        HuffmanTree* t2 = que.top(); que.pop();
        HuffmanTree *fa = new HuffmanTree(t1->cnt + t2->cnt, '#', t1, t2);
        que.push(fa);
    }
    return que.top();
}
HuffmanTree *root;
unordered_map<char, string>enc;
void travel(HuffmanTree *root, string s) {              // 获取每个结点的huffman编码
    if (root == NULL) return ;
    // 叶子结点, 统计编码
    if (root->l == NULL && root->r == NULL) {
        cout << root->ch << " " << s << endl;
        enc[root->ch] = s;
        return ;
    }
    travel(root->l, s + '0');
    travel(root->r, s + '1');
}
string encode(string longUrl) {
    for (char ch : longUrl) {
        mp[ch]++;
    }
    for (auto [ch, cnt] : mp) {
        que.push(new HuffmanTree(cnt, ch, NULL, NULL)); // 存放的是指针。
    }

    root = buildHuffman();
    travel(root, "");                                   // 遍历haffman树获取Huffman编码
    string ans;
    for (char ch : longUrl) {
        ans += enc[ch];
    }
    return ans;
}


string treeDecode(HuffmanTree *root, int &cur, string &s) {
    if (root == NULL) return "";
    if (root->l == NULL && root->r == NULL) {
        string ans = "";
        ans += root->ch;
        return ans;
    }
    cur++;
    if (s[cur] == '0') return treeDecode(root->l, cur, s);
    else return treeDecode(root->r, cur, s);
}
// Decodes a shortened URL to its original URL.
string decode(string shortUrl) {
    int cur = -1;
    string ans;
    int n = shortUrl.size();
    for (cur; cur < n - 1;) {
        printf("cur = %d\n", cur);
        string s = treeDecode(root, cur, shortUrl);
        cout << s << endl;
        ans += s;
    }
    return ans;
}

int main() {

    string s = "aaabbc";
    s = encode(s);
    cout << s << endl;
    cout << decode(s) << endl;
}

```

