#include <iostream>
#include <cstring>
#include <cstdlib>
#include <algorithm>
#include <vector>
#include <unordered_map>
#include <cmath>
using namespace std;
/**

    求以i结尾的，长度为len的最大子数组的值是多少
*/
vector<int>getSum(vector<int>&nums, int len) {
    int sum = 0;
    int maxv = 0;
    int n = nums.size();
    vector<int>ans(n, -0x3f3f3f3f);
    for (int i = 0, j = 0; i < n; i++) {
        sum += nums[i];
        if (i - j + 1 > len) sum -= nums[j++];
        if (i - j + 1 >= len) {
            if (i < 1) ans[i] = sum;
            else ans[i] = max(ans[i - 1], sum);
        }
    }
    return ans;
}
int getMaxv(vector<int>&nums, vector<int>&sum, int firstLen, int secondLen) {
    int ans = 0;
    int n = nums.size();
    vector<int>maxv = getSum(nums, firstLen);
    for (int l = n - secondLen; l > 0; l--) {
        int after = sum[l + secondLen] - sum[l];
        int pre = maxv[l - 1];
        ans = max(ans, pre + after);
    }
    return ans;
}
// [r - len + 1, r] sum[r + 1] - sum[r - len + 1]
// [l, l + len - 1] sum[l + len] - sum[l]
// 长度为n的最大值是多少， 使用双指针
int maxSumTwoNoOverlap(vector<int>& nums, int firstLen, int secondLen) {
    int n = nums.size();
    vector<int>sum(n + 1, 0);
    for (int i = 0; i < n; i++) {
        sum[i + 1] = sum[i] + nums[i]; 
    }
    // int ans = 0;
    // for (int i = firstLen - 1; i < n; i++) {
    //     int pre = sum[i + 1] - sum[i - firstLen + 1];
    //     // 出现在之后
    //     for (int l = i + 1; l + secondLen - 1 < n; l++) {
    //         int after = sum[l + secondLen] - sum[l];
    //         ans = max(after + pre, ans);
    //     }
    //     // 出现在之前
    //     for (int r = i - firstLen; r - secondLen + 1 >= 0; r--) {
    //         int after = sum[r + 1] - sum[r - secondLen + 1];
    //         ans = max(after + pre, ans);
    //     }
    // }
    // return ans;
    return max(getMaxv(nums, sum, firstLen, secondLen),
    getMaxv(nums, sum, secondLen, firstLen));
}



int main() {
    vector<int> nums = {1, 0, 1};
    int ans = maxSumTwoNoOverlap(nums, 1, 1);
    cout << ans << endl;
    return 0;
}