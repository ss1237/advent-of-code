import java.util.*;
import java.io.*;

public class AOC2022D09P2 {
	static String filename = "aoc";
	

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));
		
		HashSet<Pair> hs = new HashSet();
		HashMap<String, int[]> dir = new HashMap();
		dir.put("R", new int[] {1, 0});
		dir.put("L", new int[] {-1, 0});
		dir.put("U", new int[] {0, 1});
		dir.put("D", new int[] {0, -1});
		int[][] rope = new int[10][2];
		
		while (in.hasNextLine()) {
			String[] line = in.nextLine().split(" ");
			int len = Integer.parseInt(line[1]);
			
			while (len-->0) {
				int[][] nps = new int[10][2];
				for (int i = 0; i < 10; i++) for (int j = 0; j < 2; j++) nps[i][j] = rope[i][j];
				nps[0][0] += dir.get(line[0])[0];
				nps[0][1] += dir.get(line[0])[1];
				
				for (int i = 1; i < 10; i++) {
					if (Math.abs(nps[i - 1][0] - nps[i][0]) >= 2 || Math.abs(nps[i - 1][1] - nps[i][1]) >= 2) {
						nps[i][0] += (Math.abs(nps[i - 1][0] - nps[i][0]) + 1) / 2 * Integer.signum(nps[i - 1][0] - nps[i][0]);
						nps[i][1] += (Math.abs(nps[i - 1][1] - nps[i][1]) + 1) / 2 * Integer.signum(nps[i - 1][1] - nps[i][1]);
					}
				}
				
				rope = nps;
				hs.add(new Pair(rope[9][0], rope[9][1]));
				//System.out.println(line[0] + " " + Arrays.deepToString(rope));
			}
			
		}
		
		out.println(hs.size());
		
		out.close();
	}
	
	static class Pair {
		int x, y;
		public Pair(int a, int b) {
			x = a;
			y = b;
		}
		
		public int hashCode() {
			return Objects.hash(x, y);
		}
		
		public boolean equals(Object o) {
			if (this == o)
	            return true;
	        if (o == null || getClass() != o.getClass())
	            return false;
	        Pair that = (Pair) o;
	        return x == that.x && y == that.y;
		}
	}
}