import java.util.*;
import java.io.*;

public class AOC2021D15P2 {
	static String filename = "aoc";
	static int[] d = new int[] {1, -1, 0, 0};

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));

		ArrayList<String> lines = new ArrayList<String>();
		
		while (in.hasNextLine()) lines.add(in.nextLine());
		
		int[][] vals = new int[lines.size() * 5][lines.get(0).length() * 5];
		int[][] dist = new int[lines.size() * 5][lines.get(0).length() * 5];
		
		for (int i = 0; i < vals.length; i++) {
			for (int j = 0; j < vals[0].length; j++) {
				dist[i][j] = Integer.MAX_VALUE;
				vals[i][j] = (lines.get(i % lines.size()).charAt(j % lines.get(0).length()) - '0' + i / lines.size() + j / lines.get(0).length() - 1) % 9 + 1;
			}
		}
		
		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
		dist[0][0] = 0;
		pq.add(new int[] {0, 0, 0});
		
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			
			if (dist[cur[0]][cur[1]] != cur[2]) continue;
			
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + d[i];
				int nc = cur[1] + d[3 - i];
				
				if (inGrid(nr, nc, dist) && dist[cur[0]][cur[1]] + vals[nr][nc] < dist[nr][nc]) {
					pq.add(new int[] {nr, nc, dist[nr][nc] = dist[cur[0]][cur[1]] + vals[nr][nc]});
				}
			}
		}
		
		out.println(dist[dist.length - 1][dist[0].length - 1]);
		
		out.close();

	}
	
	static boolean inGrid(int r, int c, int[][] dist) {
		return r >= 0 && r < dist.length && c >= 0 && c < dist[0].length;
	}
}