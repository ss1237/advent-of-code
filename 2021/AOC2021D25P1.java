import java.util.*;
import java.io.*;

public class AOC2021D25P1 {
	static String filename = "aoc";

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));

		ArrayList<String> lines = new ArrayList<String>();
		
		while (in.hasNextLine()) lines.add(in.nextLine());
		
		char[][] grid = new char[lines.size()][lines.get(0).length()];
		int rows = grid.length;
		int cols = grid[0].length;
		
		for(int i = 0; i < rows; i++) grid[i] = lines.get(i).toCharArray();
		
		char[][] temp = new char[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				temp[i][j] = grid[i][j];
			}
		}
		
		int ct = 1;
		
		while (true) {
			boolean changed = false;
			
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < grid[0].length; j++) {
					if (grid[i][j] == '>' && grid[i][(j + 1) % cols] == '.') {
						temp[i][j] = '.';
						temp[i][(j + 1) % cols] = '>';
						changed = true;
					}
				}
			}
			
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					grid[i][j] = temp[i][j];
				}
			}
			
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < grid[0].length; j++) {
					if (grid[i][j] == 'v' && grid[(i + 1) % rows][j] == '.') {
						temp[i][j] = '.';
						temp[(i + 1) % rows][j] = 'v';
						changed = true;
					}
				}
			}
			
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					grid[i][j] = temp[i][j];
				}
			}
			
			if (changed) {
				ct++;
			}
			else {
				break;
			}
		}
		
		System.out.println(ct);
	}
}