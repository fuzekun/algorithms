package String;
/*

注意看到r1 == l2,所以说，可以再次使用马拉车算法进行求解，或者直接进行扩展。


*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main
{
    static int a[],b[],n;
    static char c1[],c2[];

    // 普通的马拉车算法
    static void manacher (String s,int dis[],char c[])
    {
        char e[]=s.toCharArray();
        c[0]='?';c[1]='#';c[n*2+2]='!'; // 起始和终止不能相同，否则爆了
        for (int i=0,j=2;i<n;i++,j+=2)
        {
            c[j]=e[i];
            c[j+1]='#';
        }

        int mid=0,mx=0;
        for (int i=1;i<n*2+2;i++)
        {
            if (mx>i)dis[i]=Math.min(dis[2*mid-i], mx-i);
            while (c[i+dis[i]]==c[i-dis[i]])
            {
                dis[i]++;
            }
            if (i+dis[i]>mx)
            {
                mid=i;
                mx=i+dis[i];
            }
        }

    }
    public static void main(String[] args) throws IOException
    {
        while (in.nextToken()!=StreamTokenizer.TT_EOF)
        {
            n=(int)in.nval;
            String q=ins();
            String p=ins();
            a=new int [n*2+3];
            b=new int [n*2+3];
            c1=new char [n*2+3];
            c2=new char [n*2+3];
            manacher(q,a,c1);
            manacher(p,b,c2);
            int max=0;
            for (int i=2;i<n*2+2;i++)
            {
                int s=Math.max(a[i], b[i-2]);
                System.out.print("ch = " + c1[i] + " s = " + s);
            while (c1[i-s]==c2[i+s-2]) s++;
            System.out.println(" s = " + s);
            max=Math.max(max, s);
        }
        out.println(max-1);
            out.flush();
        }

    }

    static PrintWriter out=new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    static StreamTokenizer in=new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int ini() throws IOException
    {
        in.nextToken();
        return (int)in.nval;
    }
    static double ind() throws IOException
    {
        in.nextToken();
        return in.nval;
    }
    static String ins() throws IOException
    {
        in.nextToken();
        return in.sval;
    }

}
