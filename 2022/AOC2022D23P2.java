import java.util.*;
import java.io.*;

public class AOC2022D23P2 {
	static String filename = "aoc";
	static int[][][] d = new int[4][2][3];
	static int numCycles = 1000;
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));
		
		Arrays.fill(d[0][0], -1);
		Arrays.fill(d[1][0], 1);
		Arrays.fill(d[2][1], -1);
		Arrays.fill(d[3][1], 1);
		for (int i = 0; i < 3; i++) {
			d[0][1][i] = i - 1;
			d[1][1][i] = i - 1;
			d[2][0][i] = i - 1;
			d[3][0][i] = i - 1;
		}
		
		ArrayList<String> tempGrid = new ArrayList();
		
		while (in.hasNextLine()) {
			tempGrid.add(in.nextLine());
		}
		
		int[][] grid = new int[tempGrid.size() + 2 * numCycles][tempGrid.get(0).length() + 2 * numCycles];
		
		for (int i = 0; i < tempGrid.size(); i++) {
			for (int j = 0; j < tempGrid.get(0).length(); j++) {
				grid[i + numCycles][j + numCycles] = (tempGrid.get(i).charAt(j) == '#') ? 1 : 0;
			}
		}
		
		//printGrid(grid);
		
		for (int i = 0; i < numCycles; i++) {
			int[][] temp = new int[grid.length][grid[0].length];
			int[][] temp2 = new int[grid.length][grid[0].length];
			boolean change = false;
			
			for (int r = 0; r < grid.length; r++) {
				o: for (int c = 0; c < grid[0].length; c++) {
					if (grid[r][c] == 1) {
						if (noneAround(grid, r, c)) {
							temp[r][c]++;
							continue o;
						}
						
						for (int t = 0; t < 4; t++) {
							int dir = (i + t) % 4;
							if (grid[r+d[dir][0][0]][c+d[dir][1][0]] + 
									grid[r+d[dir][0][1]][c+d[dir][1][1]] + 
									grid[r+d[dir][0][2]][c+d[dir][1][2]] == 0) {
								temp[r+d[dir][0][1]][c+d[dir][1][1]]++;
								continue o;
							}
						}
						temp[r][c]++;
					}
				}
			}
			
			for (int r = 0; r < grid.length; r++) {
				o: for (int c = 0; c < grid[0].length; c++) {
					if (grid[r][c] == 1) {
						if (noneAround(grid, r, c)) {
							temp2[r][c]++;
							continue o;
						}
						
						for (int t = 0; t < 4; t++) {
							int dir = (i + t) % 4;
							if (grid[r+d[dir][0][0]][c+d[dir][1][0]] + 
									grid[r+d[dir][0][1]][c+d[dir][1][1]] + 
									grid[r+d[dir][0][2]][c+d[dir][1][2]] == 0) {
								if (temp[r+d[dir][0][1]][c+d[dir][1][1]] == 1) {
									temp2[r+d[dir][0][1]][c+d[dir][1][1]]++;
									change = true;
								}
								else
									temp2[r][c]++;
								continue o;
							}
						}
						temp2[r][c]++;
					}
				}
			}
			
			//printGrid(temp);
			
			grid = temp2;
			if (!change) {
				out.println(i+1);
				break;
			}
			
			//printGrid(grid);
		}
		
		out.close();
	}
	
	public static boolean noneAround(int[][] grid, int r, int c) {
		for (int dr = -1; dr <= 1; dr++) {
			for (int dc = -1; dc <= 1; dc++) {
				if (!(dr == 0 && dc == 0) && grid[r+dr][c+dc] != 0) return false;
			}
		}
		return true;
	}
	
	public static void printGrid(int[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				System.out.print(grid[i][j]);
				//System.out.print((grid[i][j] == 1) ? '#' : '.');
			}
			System.out.println();
		}
		System.out.println();
	}
	
	
}