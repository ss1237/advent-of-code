import java.util.*;
import java.io.*;

public class AOC2021D11P2 {
	static String filename = "aoc";

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));

		int[][] grid = new int[100][100];
		
		for (int i = 0; i < 10; i++) {
			char[] line = in.nextLine().toCharArray();
			for (int j = 0; j < 10; j++) {
				grid[i][j] = line[j] - '0';
			}
		}
		
		int ct = 0;
		//printGrid(grid);
		
		while (true) {
			
			ct++;
			boolean[][] flashed = new boolean[10][10];
			ArrayDeque<int[]> q = new ArrayDeque<int[]>();
			
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					grid[i][j]++;
					if (grid[i][j] > 9) {
						flashed[i][j] = true;
						q.add(new int[] {i, j});
					}
				}
			}
			
			while (q.size() > 0) {
				int[] cur = q.poll();
				
				for (int dr = -1; dr <= 1; dr++) {
					for (int dc = -1; dc <= 1; dc++) {
						if (dr == 0 && dc == 0) continue;
						
						int nr = cur[0] + dr, nc = cur[1] + dc;
						if (inGrid(grid, nr, nc) && !flashed[nr][nc]) {
							grid[nr][nc]++;
							if (grid[nr][nc] > 9) {
								flashed[nr][nc] = true;
								q.add(new int[] {nr, nc});
							}
						}
					}
				}
			}
			
			boolean allZero = true;
			
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					if (grid[i][j] > 9) {
						grid[i][j] = 0;
					}
					if (grid[i][j] != 0) allZero = false;
				}
			}
			
			if (allZero) break;
			//printGrid(grid);
		}
		
		out.println(ct);
		out.close();

	}
	
	public static void printGrid(int[][] grid) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(grid[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static boolean inGrid(int[][] grid, int r, int c) {
		return r >= 0 && r < 10 && c >= 0 && c < 10;
	}
}