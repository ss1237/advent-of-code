import java.util.*;
import java.io.*;

public class AOC2021D09P1 {
	static String filename = "aoc";
	static int[] d = new int[] {0, 0, 1, -1};

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));

		ArrayList<char[]> grid = new ArrayList<char[]>();
		
		while (in.hasNextLine()) {
			grid.add(in.nextLine().toCharArray());
		}
		
		int sum = 0;
		
		for (int i = 0; i < grid.size(); i++) {
			for (int j = 0; j < grid.get(i).length; j++) {
				boolean isLow = true;
				for (int k = 0; k < 4; k++) {
					if (inGrid(grid, i + d[k], j + d[3 - k]) && grid.get(i)[j] >= grid.get(i + d[k])[j + d[3 - k]]) {
						isLow = false;
					}
				}
				
				if (isLow) sum += grid.get(i)[j] - '0' + 1;
			}
		}
		
		out.println(sum);
		out.close();

	}
	
	static boolean inGrid(ArrayList<char[]> grid, int r, int c) {
		return r >= 0 && r < grid.size() && c >= 0 && c < grid.get(0).length;
	}
}