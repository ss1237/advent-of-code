import java.util.*;
import java.io.*;

public class AOC2022D14P2 {
	static String filename = "aoc";
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));
		
		char[][] grid = new char[1000][200];
		for (char[] g : grid) Arrays.fill(g, '.');
		int maxY = 0;
		
		while (in.hasNextLine()) {
			int[] coords = Arrays.stream(in.nextLine().split(" -> |,")).mapToInt(Integer::valueOf).toArray();
			int fx = coords[0], fy = coords[1];
			maxY = Math.max(maxY, fy);
			
			for (int i = 2; i < coords.length; i += 2) {
				int tx = coords[i], ty = coords[i + 1];
				
				for (int j = Math.min(fx, tx); j <= Math.max(fx, tx); j++) {
					for (int k = Math.min(fy, ty); k <= Math.max(fy, ty); k++) {
						grid[j][k] = '#';
					}
				}
				
				fx = tx;
				fy = ty;
				maxY = Math.max(maxY, fy);
			}
		}
		
		for (int i = 0; i < 1000; i++) {
			grid[i][maxY + 2] = '#';
		}
		
		int ct = 0;
		while (true) {
			int cx = 500, cy = 0;
			while (true) {
				if (grid[cx][cy + 1] == '.') {
					cy++;
				}
				else if (grid[cx - 1][cy + 1] == '.') {
					cx--;
					cy++;
				}
				else if (grid[cx + 1][cy + 1] == '.') {
					cx++;
					cy++;
				}
				else break;
			}
			grid[cx][cy] = 'o';
			ct++;
			
			if (cx == 500 && cy == 0) break;
		}
		
		out.println(ct);
		
		out.close();
	}
	
	static void printGrid(char[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				System.out.print(grid[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
}