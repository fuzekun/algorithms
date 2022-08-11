"""
@author:fuzekun
@file:leet1417.py
@time:2022/08/11
@description: 字符串的题目, 判断数字还是字符
"""
import string

# s = "a0b1c2"
# digits = []
# chars = []
# for c in s:
#     if c.isdigit():
#         digits.append(c)
#     else :
#         chars.append(c)
# if (len(digits) < len(chars)) :
#     digits, chars = chars, digits
# if (len(digits) - len(chars) > 1) :
#     print("")
# ans = []
# for i in range(len(chars)):
#     ans.append(digits[i])
#     ans.append(chars[i])
#
# if (len(digits) > len(chars)) :
#     ans.append(digits[-1])
#
# ans = "".join(ans)
# print(ans)


def reformat(s: str) -> str:
    sumD = sum(c.isdigit() for c in s)
    sumA = len(s) - sumD
    if (abs(sumD - sumA) > 1):
        return ""
    flag = sumD > sumA
    s = list(s)
    j = 1
    for i in range(0, len(s), 2) :
        if s[i].isdigit() != flag :         # 如果是字母, 数字
            while s[j].isdigit() != flag :  # 如果是字母, 数字
                j += 2
            s[i], s[j] = s[j], s[i]
    return "".join(s)

s = "covid2019"
ans = reformat(s)
print(ans)