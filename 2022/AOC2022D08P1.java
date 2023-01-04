import java.util.*;
import java.io.*;

public class AOC2022D08P1 {
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
		
		boolean[][] vis = new boolean[h][w];
		long ct = 0, maxSeen = 0;
		
		for (int i = 0; i < h; i++) {
			maxSeen = grid[i][0];
			for (int j = 1; j < w; j++) {
				if (grid[i][j] > maxSeen) {
					maxSeen = grid[i][j];
					if (!vis[i][j]) ct++;
					vis[i][j] = true;
				}
			}
			
			maxSeen = grid[i][w - 1];
			for (int j = w - 2; j >= 0; j--) {
				if (grid[i][j] > maxSeen) {
					maxSeen = grid[i][j];
					if (!vis[i][j]) ct++;
					vis[i][j] = true;
				}
			}
		}
		
		for (int i = 0; i < w; i++) {
			maxSeen = grid[0][i];
			for (int j = 1; j < h; j++) {
				if (grid[j][i] > maxSeen) {
					maxSeen = grid[j][i];
					if (!vis[j][i]) ct++;
					vis[j][i] = true;
				}
			}
			
			maxSeen = grid[h - 1][i];
			for (int j = h - 2; j >= 0; j--) {
				if (grid[j][i] > maxSeen) {
					maxSeen = grid[j][i];
					if (!vis[j][i]) ct++;
					vis[j][i] = true;
				}
			}
		}
		
		for (int i = 0; i < h; i++) {
			if (!vis[i][0]) ct++;
			if (!vis[i][w - 1]) ct++;
		}
		for (int j = 0; j < w; j++) {
			if (!vis[0][j]) ct++; 
			if (!vis[h - 1][j]) ct++;
		}
		
		for (boolean[] x : vis) {
			System.out.println(Arrays.toString(x));
		}
		
		out.println(ct - 4);
		
		out.close();
	}
}