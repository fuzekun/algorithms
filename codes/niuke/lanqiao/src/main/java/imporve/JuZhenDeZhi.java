package lanqiao.imporve;

import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/10/29 16:09
 * @Description: 求解矩阵的秩
 */
//
//    int amount = 0;  //非零行的数目
//    for (int i = 1; i <= m; i++)
//    {
//    //寻找第 i 行和第 i 行以下的行中，列下标最小的不为 0 的元素
//    int row, col; //不为 0 的元素的行下标 row 和列下标 col
//    for (col = i; col <= n; col++)
//    {
//    bool flag = false;
//    for (row = i; row <= m; row++)
//    {
//    if (fabs(mat[row][col]) > 1e-10) //满足这个条件时，认为这个元素不为 0
//    {
//    flag = true;
//    break;
//    }
//    }
//    if (flag) break;
//    }
//    if (row <= m && col <= n)//找到不为 0 的元素
//    {
//    for (int j = col; j <= n; j++)//从第 col 个元素交换即可，因为前面的元素都为0
//    {//使用mat[0][j]作为中间变量交换元素
//    mat[0][j] = mat[i][j]; mat[i][j] = mat[row][j]; mat[row][j] = mat[0][j];
//    }
//    double a;//倍数
//    for (int j = i + 1; j <= m; j++)
//    {
//    a = -mat[j][col] / mat[i][col];
//    for (int k = col; k <= n; k++)
//    {//第 i 行 a 倍加到第 j 行，每行从第col个数开始，因为前面的数都是0
//    mat[j][k] += a * mat[i][k];
//    }
//    }
//    amount++; //每进行一次，就说明第i行元素已经固定并且不全为0，秩加1
//    }
//    else //没有找到不为 0 的元素，退出循环即可，因为秩数不必继续增加了
//    {
//    break;
//    }

public class JuZhenDeZhi {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        double[][] a = new double[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = sc.nextDouble();
            }
        }
        int rank = 0;
        for (int i = 0; i < m; i++) {
            // 找到非0的行和列
            int flag = 0, col = i, row = i;
            for (col = i; col < n; col ++) {
                for (row = i; row < m; row++) {
                    if (Math.abs(a[row][col]) > 1e-10) {
                        flag = 1;
                        break;
                    }
                }
                if (flag == 1) break;
            }
            // 下面已经没有不为0的元素了直接break
            if (flag == 0) break;
            rank++;
            // 使用这一行进行消元，把下面同列种不为0的直接消除
            for (int j = 0; j < m; j++) {

            }
        }
    }
}
