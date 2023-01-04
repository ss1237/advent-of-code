import java.util.*;
import java.io.*;

public class AOC2021D24P1 {
	static String filename = "aoc";
	static int[] ax, ay, dz;
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));

		int[] ax = new int[14];
		int[] ay = new int[14];
		int[] dz = new int[14];
		
		for (int i = 0; i < 14; i++) {
			for (int j = 0; j < 18; j++) {
				String[] line = in.nextLine().split(" ");
				if (j == 4) {
					dz[i] = Integer.parseInt(line[2]);
				}
				if (j == 5) {
					ax[i] = Integer.parseInt(line[2]);
				}
				if (j == 15) {
					ay[i] = Integer.parseInt(line[2]);
				}
			}
		}

		ArrayDeque<Integer> q = new ArrayDeque();
		int[] ans = new int[14];
		
		for (int i = 0; i < 14; i++) {
			if (dz[i] == 1) {
				q.push(i);
			}
			else {
				int old = q.poll();
				System.out.printf("w%d + %d = w%d\n", old, ay[old] + ax[i], i);
				
				for (int j = 9; j > 0; j--) {
					int temp = j - ay[old] - ax[i];
					if (temp > 0 && temp < 10) {
						ans[i] = j;
						ans[old] = j - ay[old] - ax[i];
						break;
					}
				}
			}
		}
		
		for (int x : ans) System.out.print(x);
	}
}