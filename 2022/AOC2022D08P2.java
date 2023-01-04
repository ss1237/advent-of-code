import java.util.*;
import java.io.*;

public class AOC2022D08P2 {
	static String filename = "aoc";
	

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));
		
		ArrayList<ArrayList<Integer>> sgrid = new ArrayList();
		
		while (in.hasNextLine()) {
			String line = in.nextLine();
			sgrid.add(new ArrayList<Integer>());
			for (int i = 0; i < line.length(); i++) {
				sgrid.get(sgrid.size() - 1).add(Integer.parseInt(line.substring(i, i + 1)));
			}
		}
		
		int h = sgrid.size(), w = sgrid.get(0).size();
		int[][] grid = new int[h][w];
		for (int i = 0; i < h; i++) {
			 for (int j = 0; j < w; j++) {
				 grid[i][j] = sgrid.get(i).get(j);
			 }
		}
		
		int maxScore = 0;
		
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				int curScore = getScore(grid, i, j);
				maxScore = Math.max(maxScore, curScore);
				System.out.print(curScore + " ");
			}
			System.out.println();
		}
		
		out.println(maxScore);
		
		out.close();
	}
	
	static int getScore(int[][] grid, int r, int c) {
		System.out.print("(");
		
		int ans = 1, curCount = 0;
		for (int i = r + 1; i < grid.length; i++) {
			curCount++;
			if (grid[i][c] >= grid[r][c]) break;
		}
		
		System.out.print(curCount + " ");
		ans *= curCount;
		curCount = 0;
		
		for (int i = r - 1; i >= 0; i--) {
			curCount++;
			if (grid[i][c] >= grid[r][c]) break;
		}
		
		System.out.print(curCount + " ");
		ans *= curCount;
		curCount = 0;
		
		for (int i = c + 1; i < grid[0].length; i++) {
			curCount++;
			if (grid[r][i] >= grid[r][c]) break;
		}
		
		System.out.print(curCount + " ");
		ans *= curCount;
		curCount = 0;
		
		for (int i = c - 1; i >= 0; i--) {
			curCount++;
			if (grid[r][i] >= grid[r][c]) break;
		}
		
		System.out.print(curCount + ") ");
		ans *= curCount;
		return ans;
	}
}