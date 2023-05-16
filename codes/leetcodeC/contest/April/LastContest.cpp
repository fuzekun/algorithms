#include <iostream>
#include <cstring>
#include <cstdlib>
#include <algorithm>
#include <vector>
#include <unordered_map>
using namespace std;

int gcd(int a, int b) {
    return b ? gcd(b, a % b) : a;
}

int minOperations(vector<int>& nums) {
    // 找到交集为空集合，也就是gcd(nums1, num2, .. numsx) = 1的连续集合
    // 1. 遍历所有子数组，找到子数组的最大公约数为1
    // 2. 加n - 1就行了

    int n = nums.size();
    int ans = 2 * n + 2;
    for (int i = 0; i < n; i++) 
        if (nums[i] == 1) {
            int t = 0;
            for (int j = 0; j < n; j++) 
                if (nums[j] == 1) t++;
            return n - t;
        }
    for (int i = 0; i < n; i++) {
        // 找到以i开头的最小子数组，使得子数组的最大公约数等于1
        int d = nums[i], j;
        for (j = i; j < n; j++) {                       // 从i开始，因为nums[i]可能为1
            d = gcd(nums[j], d);
            if (d == 1) {
                break;
            }
        }
        // [i, j]需要j - i次相交可以得到空集合
        int cnt = j - i;
        if (d == 1) 
            ans = min(ans, cnt);
    }
    // 然后把剩下不是1的变成1
    for (int k = 0; k < n; k++) {
        if (nums[k] != 1) ans++;
    }
    ans -= 1;
    return ans < 2 * n ? ans : -1;
}

int main(int argc, char const *argv[])
{
    vector<int> nums = {2,10,6,14};
    vector<int>nums2 = {2,6,3,4};
    vector<int>nums3 = {1, 1};
    vector<int>nums4 = {1, 2};
    cout << minOperations(nums4) << endl;
    cout << minOperations(nums3) << endl;
    cout << minOperations(nums2) << endl;
    cout << minOperations(nums) << endl;
    return 0;
}
