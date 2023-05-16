package lanqiao.chongci.graph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;

public class CheckDiff {
	
	/**
	 * 
	 * 判断两个文件的内容是否一模一样
	 * */
	public static void main(String[] args) throws Exception{
		String[] name = {"in1.txt", "in2.txt"};
		ArrayList<String>[] input = new ArrayList[name.length];
		for (int i = 0; i < name.length; i++) {
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(new File(name[i]))));
			input[i] = new ArrayList<>();
			String line;
			while ((line = in.readLine()) != null) {
				input[i].add(line);
			}
		}
		
		int flag = 1;
		for (int i = 1; i < name.length; i++) {
			if (input[i].size() != input[i - 1].size()) {
				System.out.printf("文件 %s 和文件 %s 长度不一样\n", name[i], name[i - 1]);
				System.out.printf("文件 %s 的长度为: %d\n", name[i], input[i].size());
				System.out.printf("文件 %s 的长度为: %d\n", name[i - 1], input[i - 1].size());
				flag = 0;
			}
			else {
				for (int j = 0; j < input[i].size(); j++) {
					String line1 = input[i].get(j), line2 = input[i - 1].get(j);
					line1 = line1.replaceAll(" ", "");
					line2 = line2.replaceAll(" ", "");
					if (!line1.equals(line2)) {
						System.out.printf("文件 %s 和文件 %s 在 %d 行不一样\n", name[i], name[i - 1], j + 1);
						flag = 0;
						System.out.println(line1);
						System.out.println(line2);
					}
				}
			}
		}
		if (flag == 1) System.out.println("两个文件一样");
	}
}
