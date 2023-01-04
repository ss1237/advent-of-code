import java.util.*;
import java.io.*;

public class AOC2021D05P1 {
	static String filename = "aoc";

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));

		String[] s = in.nextLine().split(",| -> ");
		int[][] grid = new int[1000][1000];
		
		while (in.hasNextLine()) {
			int r1 = Integer.parseInt(s[0]);
			int c1 = Integer.parseInt(s[1]);
			int r2 = Integer.parseInt(s[2]);
			int c2 = Integer.parseInt(s[3]);
			
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
			
			s = in.nextLine().split(",| -> ");
		}
		
		int ct = 0;
		for (int[] r : grid) {
			for (int c : r) {
				if (c > 1) ct++;
			}
		}

		out.println(ct);
		out.close();

	}
}