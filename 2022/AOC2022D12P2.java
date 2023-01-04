import java.util.*;
import java.io.*;

public class AOC2022D12P2 {
	static String filename = "aoc";
	
	static int[] d = new int[] {0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));
		
		ArrayList<String> tempGrid = new ArrayList();
		while (in.hasNextLine()) {
			tempGrid.add(in.nextLine());
		}
		
		char[][] grid = new char[tempGrid.size()][tempGrid.get(0).length()];
		boolean[][] vis = new boolean[tempGrid.size()][tempGrid.get(0).length()];
		ArrayDeque<int[]> q = new ArrayDeque();
		int[] end = new int[] {0,0};
		
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				grid[i][j] = tempGrid.get(i).charAt(j);
				if (grid[i][j] == 'S' || grid[i][j] == 'a') {
					grid[i][j] = 'a';
					q.add(new int[] {i, j, 0});
					vis[i][j] = true;
				}
				if (grid[i][j] == 'E') {
					grid[i][j] = 'z';
					end = new int[] {i, j};
				}
			}
		}
		
		while (q.size() > 0) {
			int[] cur = q.poll();
			char curChar = grid[cur[0]][cur[1]];
			
			//System.out.println(Arrays.toString(cur) + " " + curChar);
			
			if (cur[0] == end[0] && cur[1] == end[1]) {
				out.println(cur[2]);
				break;
			}
			
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + d[i];
				int nc = cur[1] + d[3 - i];
				if ((nr >= 0 && nr < grid.length && nc >= 0 && nc < grid[0].length) 
						&& !vis[nr][nc] && grid[nr][nc] - curChar <= 1) {
					q.add(new int[] {nr, nc, cur[2] + 1});
					vis[nr][nc] = true;
				}
			}
			grid[cur[0]][cur[1]] = '*';
			
		}
		
		//printGrid(grid);
		
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