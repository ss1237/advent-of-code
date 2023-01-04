import java.util.*;
import java.io.*;

public class AOC2021D05P2 {
	static String filename = "aoc";

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));

		String[] s = in.nextLine().split(",| -> ");
		int[][] grid = new int[1000][1000];
		
		while (in.hasNextLine()) {
			int r1 = Integer.parseInt(s[1]);
			int c1 = Integer.parseInt(s[0]);
			int r2 = Integer.parseInt(s[3]);
			int c2 = Integer.parseInt(s[2]);
			
			//out.println(r1 + " " + c1 + " " + r2 + " " + c2);
			
			if (r1 == r2) {
				int min = Math.min(c1, c2);
				int max = Math.max(c1, c2);
				
				for (int i = min; i <= max; i++) {
					grid[r1][i]++;
				}
			}
			else if (c1 == c2) {
				int min = Math.min(r1, r2);
				int max = Math.max(r1, r2);
				
				for (int i = min; i <= max; i++) {
					grid[i][c1]++;
				}
			}
			else if (r1 - r2 == c1 - c2) {
				int startR = Math.min(r1, r2);
				int startC = Math.min(c1, c2);
				
				for (int i = 0; i <= Math.max(r1, r2) - startR; i++) {
					grid[startR + i][startC + i]++;
				}
			}
			else if (r1 - r2 == c2 - c1) {
				int startR = Math.max(r1, r2);
				int startC = Math.min(c1, c2);
				
				for (int i = 0; i <= startR - Math.min(r1, r2); i++) {
					grid[startR - i][startC + i]++;
				}
			}
			
			s = in.nextLine().split(",| -> ");
		}
		
		int ct = 0;
		for (int[] r : grid) {
			for (int c : r) {
				if (c > 1) ct++;
				
				//if (c == 0) out.print(".");
				//else out.print(c);
			}
			//out.println();
		}

		out.println(ct);
		out.close();

	}
}