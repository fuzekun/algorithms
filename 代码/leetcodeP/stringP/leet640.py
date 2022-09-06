"""
@author:fuzekun
@file:leet640.py
@time:2022/08/11
@description: 求解方程，字符串的处理 + 字符串
"""


def solveEquation(equation: str) -> str:
    ss = equation.split("=")
    pre, after = ss[0], ss[1]
    pre += '-'
    after += '-'
    print(pre, after)
    x = 0
    val = 0
    cur = 0
    flag = 1
    pres = 0                # 判断x之前是不是有数字
    for ch in pre:
        if ch.isdigit() :
            cur = cur * 10 + int(ch)
            pres = 1
        else :
            if ch == '-':
                val += -flag * cur
                flag = -1
            elif ch == '+':
                val += -flag * cur
                flag = 1
            elif ch == 'x':
                if pres == 0:            # 如果没有数字
                    x += flag
                else :
                    x += flag * cur
                pres = 0
            cur = 0
    print(x, val)
    flag = 1
    pres = 0
    for ch in after:
        if ch.isdigit() :
            cur = cur * 10 + int(ch)
            pres = 1
        else :
            if ch == '-':
                val += flag * cur
                flag = -1
            elif ch == '+':
                val += flag * cur
                flag = 1
            elif ch == 'x':
                if pres == 0:
                    x += -flag
                else :
                    x += -flag * cur
                pres = 0
            cur = 0

    print(x, val)
    if x == 0:
        if val != 0:
            return "No solution"
        else :
            return "Infinite solutions"
    else:
        return "x=" + str(val // x)

if __name__ == '__main__':
    s = "0x+0=0+0"
    ans = solveEquation(s)
    print(ans)