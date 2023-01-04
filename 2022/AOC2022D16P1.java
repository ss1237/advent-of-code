import java.util.*;
import java.io.*;

public class AOC2022D16P1 {
	static String filename = "aoc";
	static HashMap<Integer, String> rev = new HashMap<Integer, String>();
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		ArrayList<String>[] adj = new ArrayList[64];
		for (int i = 0; i < 64; i++) adj[i] = new ArrayList<String>();
		int[] rates = new int[64];
		HashMap<Integer, Integer> bitMap = new HashMap();
		
		int size = 0;
		while (in.hasNextLine()) {
			String[] l = in.nextLine().split(" |, |=|;");
			
			String name = l[1];
			rates[size] = Integer.parseInt(l[5]);
			if (rates[size] > 0) bitMap.put(size, bitMap.size());
			map.put(name, size);
			rev.put(size, name);
			
			for (int i = 11; i < l.length; i++) {
				adj[size].add(l[i]);
			}
			
			size++;
		}
		
		int[][][] vis = new int[31][64][1 << bitMap.size()];
		for (int i = 0; i < 31; i++) {
			for (int j = 0; j < 64; j++) {
				for (int k = 0; k < 1 << bitMap.size(); k++) {
					vis[i][j][k] = -1;
				}
			}
		}
		ArrayDeque<int[]> q = new ArrayDeque();
		q.add(new int[] {0, map.get("AA"), 0}); // time, pos, valves open
		vis[0][map.get("AA")][0] = 0;
		
		int maxVal = 0;
		
		while (q.size() > 0) {
			int[] cur = q.poll();
			int curP = vis[cur[0]][cur[1]][cur[2]];
			//printState(cur, curP);
			
			if (cur[0] == 30) {
				maxVal = Math.max(maxVal, curP);
				continue;
			}
			
			
			
			if (rates[cur[1]] > 0 && ((1 << bitMap.get(cur[1]) & cur[2]) == 0)
					&& curP + rates[cur[1]] * (29 - cur[0]) > vis[cur[0] + 1][cur[1]][cur[2] + (1 << bitMap.get(cur[1]))]) {
				q.add(new int[] {cur[0] + 1, cur[1], cur[2] + (1 << bitMap.get(cur[1]))});
				vis[cur[0] + 1][cur[1]][cur[2] + (1 << bitMap.get(cur[1]))] = curP + rates[cur[1]] * (29 - cur[0]);
			}
			
			for (String s : adj[cur[1]]) {
				if (curP > vis[cur[0] + 1][map.get(s)][cur[2]]) {
					q.add(new int[] {cur[0] + 1, map.get(s), cur[2]});
					vis[cur[0] + 1][map.get(s)][cur[2]] = curP;
				}
			}
		}
		
		out.println(maxVal);
		
		out.close();
	}
	
	static void printState(int[] s, int p) {
		System.out.printf("%d time, at %s\n", s[0], rev.get(s[1]));
		System.out.printf("current pressure %d\n\n", p);
	}
}