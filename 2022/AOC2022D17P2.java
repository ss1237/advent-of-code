import java.util.*;
import java.io.*;

public class AOC2022D17P2 {
	static String filename = "aoc";
	static int maxH = 0; //rocks spawn at maxH + 4
	static int numRocks = 2022;
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));
		
		char[] moves = in.nextLine().toCharArray();
		int moveIdx = 0;
		HashMap<Integer, int[]> map = new HashMap();
		map.put(0, new int[] {0, 0}); //rock#, maxH
		numRocks = moves.length * 5;
		
		char[][] grid = new char[5 * numRocks][9];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < 9; j++) {
				if (i == 0 && (j == 0 || j == 8)) grid[i][j] = '+';
				else if (i == 0) grid[i][j] = '-';
				else if (j == 0 || j == 8) grid[i][j] = '|';
				else grid[i][j] = '.';
			}
		}
		
		int curHash = 0, foundHash = -1, segLen = 0, period = 0;
		
		for (int i = 0; i < numRocks; i++) {
			
			ArrayList<int[]> rock = createRock(i % 5);
			for (int[] x : rock) {
				grid[x[0]][x[1]] = '@';
			}
			
			while (true) {
				push(grid, rock, moves[moveIdx]);
				moveIdx = (moveIdx + 1) % moves.length;
				if (!moveDown(grid, rock)) break;
			}
			
			for (int[] x : rock) maxH = Math.max(maxH, x[0]);
			
			curHash = getHash(grid, i % 5, moveIdx);
			
			if (map.containsKey(curHash)) {
				//printGrid(grid);
				foundHash = curHash;
				segLen = maxH - map.get(curHash)[1];
				period = i + 1 - map.get(curHash)[0];
				break;
			}
			else {
				map.put(curHash, new int[] {i + 1, maxH});
			}
			//printGrid(grid);
			
		}
		
		long numPeriods = ((long) 1e12 - map.get(foundHash)[0]) / period;
		long rem = ((long) 1e12 - map.get(foundHash)[0]) % period;
		
		int oldMaxH = maxH;
		for (int i = map.get(curHash)[0] + period; i < map.get(curHash)[0] + period + rem; i++) {
			ArrayList<int[]> rock = createRock(i % 5);
			for (int[] x : rock) {
				grid[x[0]][x[1]] = '@';
			}
			
			while (true) {
				push(grid, rock, moves[moveIdx]);
				moveIdx = (moveIdx + 1) % moves.length;
				if (!moveDown(grid, rock)) break;
			}
			
			for (int[] x : rock) maxH = Math.max(maxH, x[0]);
		}
		
		out.println(map.get(foundHash)[1] + numPeriods * segLen + (maxH - oldMaxH));
		
		out.close();
	}
	
	static int getHash(char[][] grid, int idx, int m) {
		int ret = m * 10 + idx;
		for (int i = 1; i < 8; i++) {
			ret *= 10;
			int cur = 0;
			while (cur < 9 && grid[maxH - cur][i] == '.') cur++;
			ret += cur;
		}
		return ret;
	}
	
	static boolean moveDown(char[][] grid, ArrayList<int[]> rock) {
		boolean canMove = true;
		for (int[] x : rock) {
			if (grid[x[0] - 1][x[1]] != '.' && grid[x[0] - 1][x[1]] != '@') {
				canMove = false;
				break;
			}
		}
		
		if (!canMove) {
			for (int[] y : rock) {
				grid[y[0]][y[1]] = '#';
			}
			return false;
		}
		
		for (int[] x : rock) {
			grid[x[0]][x[1]] = '.';
			x[0] -= 1;
		}
		for (int[] x : rock) {
			grid[x[0]][x[1]] = '@';
		}
		
		return true;
	}
	
	static void push(char[][] grid, ArrayList<int[]> rock, char move) {
		int dir = move == '>' ? 1: -1;
		for (int[] x : rock) {
			if (grid[x[0]][x[1] + dir] != '.' && grid[x[0]][x[1] + dir] != '@') {
				return;
			}
		}
		for (int[] x : rock) {
			grid[x[0]][x[1]] = '.';
			x[1] += dir;
		}
		for (int[] x : rock) {
			grid[x[0]][x[1]] = '@';
		}
	}
	
	static void printGrid(char[][] grid) {
		for (int i = maxH + 7; i >= Math.max(0, maxH - 10); i--) {
			for (int j = 0; j < grid[0].length; j++) {
				System.out.print(grid[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	static ArrayList<int[]> createRock(int i) {
		ArrayList<int[]> ret = new ArrayList();
		if (i == 0) {
			ret.add(new int[] {maxH + 4, 3});
			ret.add(new int[] {maxH + 4, 4});
			ret.add(new int[] {maxH + 4, 5});
			ret.add(new int[] {maxH + 4, 6});
		}
		else if (i == 1) {
			ret.add(new int[] {maxH + 4, 4});
			ret.add(new int[] {maxH + 5, 3});
			ret.add(new int[] {maxH + 5, 4});
			ret.add(new int[] {maxH + 5, 5});
			ret.add(new int[] {maxH + 6, 4});
		}
		else if (i == 2) {
			ret.add(new int[] {maxH + 4, 3});
			ret.add(new int[] {maxH + 4, 4});
			ret.add(new int[] {maxH + 4, 5});
			ret.add(new int[] {maxH + 5, 5});
			ret.add(new int[] {maxH + 6, 5});
		}
		else if (i == 3) {
			ret.add(new int[] {maxH + 4, 3});
			ret.add(new int[] {maxH + 5, 3});
			ret.add(new int[] {maxH + 6, 3});
			ret.add(new int[] {maxH + 7, 3});
		}
		else {
			ret.add(new int[] {maxH + 4, 3});
			ret.add(new int[] {maxH + 4, 4});
			ret.add(new int[] {maxH + 5, 3});
			ret.add(new int[] {maxH + 5, 4});
		}
		return ret;
	}
}