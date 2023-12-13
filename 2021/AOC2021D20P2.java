import java.util.*;
import java.io.*;

public class AOC2021D20P2 {
	static String filename = "aoc";
	static String decode;
	static int border = 50;
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));

		decode = in.nextLine();
		in.nextLine();
		
		ArrayList<String> lines = new ArrayList();
		
		while (in.hasNextLine()) lines.add(in.nextLine());
		
		int[][] grid = new int[lines.size() + 2 * border][lines.size() + 2 * border];
		
		for (int i = 0; i < lines.size(); i++) {
			for (int j = 0; j < lines.get(0).length(); j++) {
				grid[i + border][j + border] = (lines.get(i).charAt(j) == '#') ? 1 : 0;
			}
		}
		
		for (int c = 0; c < 50; c++) {
			int[][] temp = new int[grid.length][grid[0].length];
			for (int i = 0; i < temp.length; i++) {
				for (int j = 0; j < temp[0].length; j++) {
					temp[i][j] = compute(grid, i, j);
				}
			}
			
			grid = temp;
			//printGrid(grid);
		}
		
		int ct = 0;
		for (int[] x : grid) {
			for (int y : x) {
				ct += y;
			}
		}
		
		System.out.println(ct);
	}
	
	public static int compute(int[][] grid, int r, int c) {
		int ret = 0;
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				ret <<= 1;
				if (r + i < 0 || c + j < 0 || r + i >= grid.length || c + j >= grid[0].length) {
					ret += grid[r][c];
				}
				else {
					ret += grid[r + i][c + j];
				}
			}
		}
		
		return (decode.charAt(ret) == '#') ? 1 : 0;
	}
	
	public static void printGrid(int[][] grid) {
		for (int[] x : grid) {
			for (int y : x) {
				System.out.print((y == 1) ? '#' : '.');
			}
			System.out.println();
		}
	}
}