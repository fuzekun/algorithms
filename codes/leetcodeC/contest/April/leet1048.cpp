#include <iostream>
#include <cstring>
#include <cstdlib>
#include <algorithm>
#include <vector>
#include <unordered_map>
#include <cmath>
using namespace std;
    /*
        判断t是否可以成为s的前身。 t < s
        采用双指针，第一个不同的地方，进行扩展
    */
    int check(string &s, string &t) {
        if (t.size() != s.size() - 1) return 0;
        int i = 0, j = 0;
        int n = s.size();
        int cnt = 0;
        while (i < n && j < n - 1) {
            // 不等的时候，s指针向后移动一位。相等的时候，两者同时移动。
            if (s[i] != t[j]) {
                i++;
                cnt++;
                continue;
            }
            i++; j++;
        }
        // 中间字符不同，或者最后一个字符不同
        return cnt == 1 || (cnt == 0 && j == n - 1);
    }
     static bool cmp(string &s1, string &s2) {
        return s1.size() < s2.size();
    }
    int longestStrChain(vector<string>& words) {
        // 1. 判断两个是否可以形成前身
        // 2. 采用dp的方式,O(n^2), dp[i]: 表示以i结尾的最长上升子序列长度 dp[i] = min(dp[j] + 1);
        sort(words.begin(), words.end(), cmp);
        int n = words.size();
        vector<int>dp(n, 1);
        vector<vector<int>>ok(n, vector<int>(n));
        int ans = 1;
        for (int i = 0; i < n; i++) {
            cout << words[i] << ":";
            for (int j = 0; j < i; j++) {
                if (check(words[i], words[j])) { 
                    cout << words[j] << " " ;
                    dp[i] = max(dp[i], dp[j] + 1);
                }
                ans = max(ans, dp[i]);
            } 
            cout << endl;
        }
        return ans;
    }

int main() {
    vector<string> ss = {"a","b","ab","bac"};
    // string s = "ab", t = "a";
    // cout << check(s, t) << endl;
    int ans = longestStrChain(ss);
    cout << ans << endl;
    return 0;
}