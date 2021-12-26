import java.util.*;
import java.io.*;

public class AOC2021D12P2 {
	static String filename = "aoc";

	static ArrayList<Integer>[] adj;
	static ArrayList<Boolean> upper;
	static boolean[] vis;
	static int ct, start, end;
	static boolean twice = false;
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));

		HashMap<String, Integer> nodeMap = new HashMap<String, Integer>();
		ArrayList<int[]> tempEdges = new ArrayList<int[]>();
		upper = new ArrayList<Boolean>();
		
		while (in.hasNextLine()) {
			String[] edge = in.nextLine().split("-");
			for (String s : edge) {
				if (!nodeMap.containsKey(s)) {
					nodeMap.put(s, nodeMap.size());
					upper.add(s.charAt(0) < 'a');
				}
			}
			
			tempEdges.add(new int[] {nodeMap.get(edge[0]), nodeMap.get(edge[1])});
		}
		
		adj = new ArrayList[nodeMap.size()];
		Arrays.setAll(adj, x -> new ArrayList<Integer>());
		vis = new boolean[adj.length];
		ct = 0;
		start = nodeMap.get("start");
		end = nodeMap.get("end");
		
		for (int[] edge : tempEdges) {
			adj[edge[0]].add(edge[1]);
			adj[edge[1]].add(edge[0]);
		}
		
		vis[start] = true;
		dfs(start);
		
		out.println(ct);
		out.close();

	}
	
	static void dfs(int cur) {
		if (cur == end) {
			ct++;
			return;
		}
		
		for (int to : adj[cur]) {
			if (!vis[to]) {
				if (!upper.get(to)) vis[to] = true;
				dfs(to);
				if (!upper.get(to)) vis[to] = false;
			}
			else if (vis[to] && !twice && to != start) {
				twice = true;
				dfs(to);
				twice = false;
			}
		}
	}
}