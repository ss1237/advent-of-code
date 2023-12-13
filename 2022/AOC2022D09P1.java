import java.util.*;
import java.io.*;

public class AOC2022D09P1 {
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
		
		int[] h = new int[] {0, 0}, t = new int[] {0, 0};
		while (in.hasNextLine()) {
			String[] line = in.nextLine().split(" ");
			int len = Integer.parseInt(line[1]);
			
			while (len-->0) {
				int[] nh = new int[] {h[0] + dir.get(line[0])[0], h[1] + dir.get(line[0])[1]};
				if (Math.abs(nh[0] - t[0]) >= 2 || Math.abs(nh[1] - t[1]) >= 2) {
					t = h;
				}
				h = nh;
				hs.add(new Pair(t[0], t[1]));
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