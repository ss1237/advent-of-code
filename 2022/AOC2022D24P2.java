import java.util.*;
import java.io.*;

public class AOC2022D24P2 {
	static String filename = "aoc";
	static int[] d = new int[] {0, 0, 0, 1, -1};
	
	//487 too low
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));
		
		
		ArrayList<String> tempGrid = new ArrayList();
		
		while (in.hasNextLine()) {
			tempGrid.add(in.nextLine());
		}
		
		char[][] grid = new char[tempGrid.size()][tempGrid.get(0).length()];
		
		for (int i = 0; i < tempGrid.size(); i++) {
			for (int j = 0; j < tempGrid.get(0).length(); j++) {
				grid[i][j] = tempGrid.get(i).charAt(j);
			}
		}
		
		int rows = grid.length - 2, cols = grid[0].length - 2, rclcm = lcm(rows, cols);
		
		HashSet<Triple> states = new HashSet();
		
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == '.') continue;
				
				else if (grid[i][j] == '#') {
					for (int k = 0; k < rclcm; k++) states.add(new Triple(i, j, k));
				}
				
				else {
					int idx = -1;
					
					if (grid[i][j] == '<') idx = 0;
					if (grid[i][j] == '>') idx = 1;
					if (grid[i][j] == 'v') idx = 3;
					if (grid[i][j] == '^') idx = 4;
					
					for (int k = 0; k < rclcm; k++) {
						int nr = (i - 1 + k * (d[idx] + rows)) % rows + 1;
						int nc = (j - 1 + k * (d[4 - idx] + cols)) % cols + 1;
						states.add(new Triple(nr, nc, k));
					}
				}
			}
		}
		
		HashSet<Triple> vis = new HashSet();
		ArrayDeque<Triple> q = new ArrayDeque();
		int trip = 0;
		
		q.add(new Triple(0, 1, 0));
		vis.add(new Triple(0, 1, 0));
		
		while (q.size() > 0) {
			Triple cur = q.poll();
			
			if (cur.a == rows + 1 && cur.b == cols && trip == 0) {
				trip = 1;
				q = new ArrayDeque();
				vis = new HashSet();
				
				vis.add(new Triple(cur.a, cur.b, cur.c % rclcm));
			}
			
			if (cur.a == 0 && cur.b == 1 && trip == 1) {
				trip = 2;
				q = new ArrayDeque();
				vis = new HashSet();
				
				vis.add(new Triple(cur.a, cur.b, cur.c % rclcm));
			}
			
			if (cur.a == rows + 1 && cur.b == cols && trip == 2) {
				out.println(cur.c);
				break;
			}
			
			for (int i = 0; i < 5; i++) {
				int nr = cur.a + d[i];
				int nc = cur.b + d[4 - i];
				Triple temp = new Triple(nr, nc, (cur.c + 1) % rclcm);
				
				if (valid(grid, temp) && !states.contains(temp) && !vis.contains(temp)) {
					q.add(new Triple(nr, nc, cur.c + 1));
					vis.add(temp);
				}
			}
		}
		
		out.close();
	}
	
	public static boolean valid(char[][] grid, Triple t) {
		return t.a >= 0 && t.a < grid.length && t.b >= 0 && t.b < grid[0].length;
	}
	
	
	public static class Triple {
		int a, b, c;
		public Triple(int aa, int bb, int cc) {
			a = aa;
			b = bb;
			c = cc;
		}
		
		public boolean equals(Object o) {
			Triple t = (Triple) o;
			return a == t.a && b == t.b && c == t.c;
		}
		
		public int hashCode() {
			return a * 10000 + b * 100 + c;
		}
	}
	
	
	public static int lcm(int a, int b) {
		return a * b / gcd(a, b);
	}
	
	public static int gcd(int a, int b) {
		if (a * b == 0) return Math.max(a, b);
		return gcd(b, a % b);
	}
	
	public static void printGrid(char[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				System.out.print(grid[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void printState(int[][][] states, int t) {
		for (int i = 0; i < states.length; i++) {
			for (int j = 0; j < states[0].length; j++) {
				System.out.print(states[i][j][t]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	
}