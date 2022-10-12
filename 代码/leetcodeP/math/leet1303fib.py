"""
@author:fuzekun
@file:leet1303fib.py
@time:2022/10/09
@description:Fibonacci数列求和
"""
import numpy as np
line = input()
line = line.split(' ')
n, m = int(line[0]), int(line[1])

a = [[0, 1, 0], [1, 1, 1], [0, 0, 1]]
a = np.array(a)
a = np.linalg.matrix_power(a, n - 1)
b = [1,1,1]
b = np.dot(b, a)
# print(a)
print(b[2])