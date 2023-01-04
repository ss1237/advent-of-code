import java.util.*;
import java.io.*;

public class AOC2022D19P1 {
	static String filename = "aoc";
	static int[] c;
	static int endTime = 24;
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));
		
		int total = 0;
		
		while (in.hasNextLine()) {
			String[] l = in.nextLine().split(": | ");
			
			int idx = ti(l[1]);
			int Co = ti(l[6]), Cc = ti(l[12]), Co1 = ti(l[18]);
			int Co2 = ti(l[21]), Cg1 = ti(l[27]), Cg2 = ti(l[30]);
			int maxGeode = 0;
			
			ArrayDeque<State> q = new ArrayDeque();
			q.add(new State(new int[] {0, 0, 0, 0, 0, 1, 0, 0, 0}));
			HashSet<State> vis = new HashSet();
			
			while (q.size() > 0) {
				State cur = q.poll();
				maxGeode = Math.max(cur.g, maxGeode);
				
				cur.o = Math.min(cur.o, (endTime - cur.t) * Math.max(Math.max(Co, Cc), Math.max(Co1, Cg1)));
				cur.c = Math.min(cur.c, (endTime - cur.t) * Co2);
				cur.ob = Math.min(cur.ob, (endTime - cur.t) * Cg2);
				
				if (cur.t == endTime) continue;
				if (vis.contains(cur)) continue;
				
				vis.add(cur);
				
				q.add(new State(new int[] {cur.t+1, cur.o+cur.b1, cur.c+cur.b2, cur.ob+cur.b3, cur.g+cur.b4,
											cur.b1, cur.b2, cur.b3, cur.b4}));
				if (cur.o >= Co) {
					q.add(new State(new int[] {cur.t+1, cur.o+cur.b1-Co, cur.c+cur.b2, cur.ob+cur.b3, cur.g+cur.b4,
							cur.b1+1, cur.b2, cur.b3, cur.b4}));
				}
				if (cur.o >= Cc) {
					q.add(new State(new int[] {cur.t+1, cur.o+cur.b1-Cc, cur.c+cur.b2, cur.ob+cur.b3, cur.g+cur.b4,
							cur.b1, cur.b2+1, cur.b3, cur.b4}));
				}
				if (cur.o >= Co1 && cur.c >= Co2) {
					q.add(new State(new int[] {cur.t+1, cur.o+cur.b1-Co1, cur.c+cur.b2-Co2, cur.ob+cur.b3, cur.g+cur.b4,
							cur.b1, cur.b2, cur.b3+1, cur.b4}));
				}
				if (cur.o >= Cg1 && cur.ob >= Cg2) {
					q.add(new State(new int[] {cur.t+1, cur.o+cur.b1-Cg1, cur.c+cur.b2, cur.ob+cur.b3-Cg2, cur.g+cur.b4,
							cur.b1, cur.b2, cur.b3, cur.b4+1}));
				}
				
			}
			
			total += maxGeode * idx;
			System.out.println(idx + ": " + maxGeode);
		}
		
		out.println(total);
		out.close();
	}
	
	static int ti(String s) {
		return Integer.parseInt(s);
	}
	
	static class State {
		int t, o, c, ob, g, b1, b2, b3, b4;
		int[] vals;
		public State(int[] init) {
			t = init[0];
			o = init[1];
			c = init[2];
			ob = init[3];
			g = init[4];
			b1 = init[5];
			b2 = init[6];
			b3 = init[7];
			b4 = init[8];
			vals = init;
		}
		
		public boolean equals(Object o) {
			return Arrays.equals(vals, ((State) o).vals);
		}
		
		public int hashCode() {
			int ret = 0;
			for (int x : vals) {
				ret *= 10;
				ret += x % 10;
			}
			return ret;
		}
		
		public String toString() {
			return Arrays.toString(vals);
		}
	}
}