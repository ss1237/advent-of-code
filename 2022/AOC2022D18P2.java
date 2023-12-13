import java.util.*;
import java.io.*;

public class AOC2022D18P2 {
	static String filename = "aoc";
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));
		
		int[][][] vis = new int[40][40][40];
		int[][] d = new int[][] {{1,0,0}, {0,1,0}, {0,0,1}, {-1,0,0}, {0,-1,0}, {0,0,-1}};
		
		while (in.hasNextLine()) {
			String[] coords = in.nextLine().split(",");
			vis[Integer.parseInt(coords[0]) + 10][Integer.parseInt(coords[1]) + 10][Integer.parseInt(coords[2]) + 10] = 2;
		}
		
		ArrayDeque<int[]> q = new ArrayDeque();
		int ct = 0;
		q.add(new int[] {10, 10, 10});
		vis[10][10][10] = 1;
		
		while (q.size() > 0) {
			int[] cur = q.poll();
			if (Math.max(Math.max(Math.abs(cur[0] - 20), Math.abs(cur[1] - 20)), Math.abs(cur[2] - 20)) > 15) continue;
			
			
			
			for (int i = 0; i < 6; i++) {
				int nx = cur[0] + d[i][0], ny = cur[1] + d[i][1], nz = cur[2] + d[i][2];
				if (vis[nx][ny][nz] == 2) {
					ct++;
				}
				else if (vis[nx][ny][nz] == 0) {
					vis[nx][ny][nz] = 1;
					q.add(new int[] {nx, ny, nz});
				}
			}
		}
		
		out.println(ct);
		
		out.close();
	}
	
	static int diff(int[] x, int[] y) {
		return Math.abs(x[0] - y[0]) + Math.abs(x[1] - y[1]) + Math.abs(x[2] - y[2]);
	}
}