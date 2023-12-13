import java.util.*;
import java.io.*;

public class AOC2021D09P2 {
	static String filename = "aoc";
	static int[] d = new int[] {0, 0, 1, -1};

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));

		ArrayList<char[]> grid = new ArrayList<char[]>();
		
		while (in.hasNextLine()) {
			grid.add(in.nextLine().toCharArray());
		}
		
		ArrayList<int[]> lowPoints = new ArrayList();
		
		for (int i = 0; i < grid.size(); i++) {
			for (int j = 0; j < grid.get(i).length; j++) {
				boolean isLow = true;
				for (int k = 0; k < 4; k++) {
					if (inGrid(grid, i + d[k], j + d[3 - k]) && grid.get(i)[j] >= grid.get(i + d[k])[j + d[3 - k]]) {
						isLow = false;
					}
				}
				
				if (isLow) lowPoints.add(new int[] {i, j});
			}
		}
		
		ArrayList<Integer> sizes = new ArrayList();
		boolean[][] vis = new boolean[grid.size()][grid.get(0).length];
		
		for (int[] low : lowPoints) {
			vis[low[0]][low[1]] = true;
			ArrayDeque<int[]> bfs = new ArrayDeque();
			bfs.add(low);
			int size = 0;
			
			while (!bfs.isEmpty()) {
				size++;
				int[] cur = bfs.poll();
				
				for (int k = 0; k < 4; k++) {
					if (inGrid(grid, cur[0] + d[k], cur[1] + d[3 - k]) && !vis[cur[0] + d[k]][cur[1] + d[3 - k]] && grid.get(cur[0] + d[k])[cur[1] + d[3 - k]] != '9') {
						bfs.add(new int[] {cur[0] + d[k], cur[1] + d[3 - k]});
						vis[cur[0] + d[k]][cur[1] + d[3 - k]] = true;
					}
				}
			}
			
			sizes.add(size);
		}
		
		Collections.sort(sizes, Collections.reverseOrder());
		out.println(sizes.get(0) * sizes.get(1) * sizes.get(2));
		out.close();

	}
	
	static boolean inGrid(ArrayList<char[]> grid, int r, int c) {
		return r >= 0 && r < grid.size() && c >= 0 && c < grid.get(0).length;
	}
}