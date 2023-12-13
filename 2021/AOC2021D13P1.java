import java.util.*;
import java.io.*;

public class AOC2021D13P1 {
	static String filename = "aoc";

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));

		int maxX = 0, maxY = 0;
		ArrayList<int[]> points = new ArrayList();
		
		String[] line = in.nextLine().split(",");
		
		while (line.length == 2) {
			points.add(new int[] {Integer.parseInt(line[0]), Integer.parseInt(line[1])});
			maxX = Math.max(maxX, Integer.parseInt(line[0]));
			maxY = Math.max(maxY, Integer.parseInt(line[1]));
			line = in.nextLine().split(",");
		}
		
		System.out.println(maxX + " " + maxY);
		boolean[][] grid = new boolean[maxX + 1][maxY + 1];
		
		for (int[] pair : points) {
			grid[pair[0]][pair[1]] = true;
		}
		
		String[] instruction = in.nextLine().split(" |=");
		System.out.println(Arrays.toString(instruction));
		
		if (instruction[2].equals("x")) {
			int col = Integer.parseInt(instruction[3]);
			
			for (int i = col + 1; i <= maxX; i++) {
				for (int j = 0; j <= maxY; j++) {
					if (grid[i][j]) {
						grid[2 * col - i][j] = true;
					}
				}
			}
				
			maxX = col;
		}
		else {
			int row = Integer.parseInt(instruction[3]);
			
			for (int i = 0; i <= maxX; i++) {
				for (int j = row + 1; j <= maxY; j++) {
					if (grid[i][j]) {
						grid[i][2 * row - j] = true;
					}
				}
			}
			
			maxY = row;
		}
		
		int ct = 0;
		
		for (int j = 0; j <= maxY; j++) {
			for (int i = 0; i <= maxX; i++) {
				if (grid[i][j]) ct++;
				//out.print(grid[i][j] ? "#" : ".");
			}
			//out.println();
		}
		
		out.println(ct);
		out.close();

	}
}