import java.util.*;
import java.io.*;

public class AOC2021D22P1 {
	static String filename = "aoc";

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));

		boolean[][][] grid = new boolean[101][101][101];
		int ct = 0;
		
		while (in.hasNextLine()) {
			String line = in.nextLine();
			String[] l = line.split("\\.\\.|\\D+\\=");
			int[] c = new int[6];
			boolean works = true;
			for (int i = 1; i < 7; i++) {
				c[i - 1] = Integer.parseInt(l[i]);
				if (Math.abs(c[i - 1]) > 50) works = false;
				c[i - 1] += 50;
			}
			
			if (works) {
				for (int i = c[0]; i <= c[1]; i++) {
					for (int j = c[2]; j <= c[3]; j++) {
						for (int k = c[4]; k <= c[5]; k++) {
							if (line.contains("on") && !grid[i][j][k]) {
								grid[i][j][k] = true;
								ct++;
							}
							else if (line.contains("off") && grid[i][j][k]) {
								grid[i][j][k] = false;
								ct--;
							}
						}
					}
				}
			}
		}
		
		System.out.println(ct);
	}
	
}