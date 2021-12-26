import java.util.*;
import java.io.*;

public class AOC2021D23P1 {
	static String filename = "aoc";
	static int[] cost = new int[] {1, 10, 100, 1000};
	static HashMap<String, Integer> dist;
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));

		char[] init = new char[15];
		
		in.nextLine();
		char[] row = in.nextLine().toCharArray();
		init[8] = row[1];
		init[14] = row[11];
		for (int i = 2; i <= 10; i+=2) {
			init[8 + i/2] = row[i];
		}
		
		String[] line = in.nextLine().split("#+");
		for (int i = 0; i < 4; i++) {
			init[2 * i + 1] = line[i + 1].charAt(0);
		}
		
		line = in.nextLine().split("\s*#+");
		for (int i = 0; i < 4; i++) {
			init[2 * i] = line[i + 1].charAt(0);
		}
		
		dist = new HashMap();
		dist.put(new String(init), 0);
		PriorityQueue<State> q = new PriorityQueue();
		q.add(new State(new String(init), 0));
		

		while (q.size() > 0) {
			State cur = q.poll();
			
			//cur.print();
			
			if (getDist(cur.str) != cur.dist) continue;
			
			if (cur.str.equals("AABBCCDD.......")) {
				cur.print();
			}
			
			for (int i = 0; i < 8; i+=2) {
				if (cur.arr[i] != '.') {
					if (cur.arr[i]-'A' != i/2 && cur.arr[i + 1] == '.') {
						int oldDist = getDist(swap(cur.str, i, i + 1));
						if (cur.dist + cost[cur.arr[i]-'A'] < oldDist) {
							q.add(new State(swap(cur.str, i, i + 1), cur.dist + cost[cur.arr[i]-'A']));
							dist.put(swap(cur.str, i, i + 1), cur.dist + cost[cur.arr[i]-'A']);
						}
					}
				}
				if (cur.arr[i + 1] != '.') {
					if (cur.arr[i + 1]-'A' == i/2 && cur.arr[i] == '.') {
						int oldDist = getDist(swap(cur.str, i, i + 1));
						if (cur.dist + cost[cur.arr[i + 1]-'A'] < oldDist) {
							q.add(new State(swap(cur.str, i, i + 1), cur.dist + cost[cur.arr[i + 1]-'A']));
							dist.put(swap(cur.str, i, i + 1), cur.dist + cost[cur.arr[i + 1]-'A']);
						}
					}
					if (cur.arr[i + 1]-'A' == i/2 && cur.arr[i]-'A' != i/2) {
						for (int to : openHallSpace(cur.arr, i / 2)) {
							int temp = Math.abs(i + 19 - 2 * to) + 1;
							if (to == 14 || to == 8) temp--;
							int oldDist = getDist(swap(cur.str, i + 1, to));
							
							if (cur.dist + temp * cost[cur.arr[i + 1]-'A'] < oldDist) {
								q.add(new State(swap(cur.str, i + 1, to), cur.dist + temp * cost[cur.arr[i + 1]-'A']));
								dist.put(swap(cur.str, i + 1, to), cur.dist + temp * cost[cur.arr[i + 1]-'A']);
							}
						}
					}
					if (cur.arr[i + 1]-'A' != i/2) {
						for (int to : openHallSpace(cur.arr, i / 2)) {
							int temp = Math.abs(i + 19 - 2 * to) + 1;
							if (to == 14 || to == 8) temp--;
							int oldDist = getDist(swap(cur.str, i + 1, to));
							
							if (cur.dist + temp * cost[cur.arr[i + 1]-'A'] < oldDist) {
								q.add(new State(swap(cur.str, i + 1, to), cur.dist + temp * cost[cur.arr[i + 1]-'A']));
								dist.put(swap(cur.str, i + 1, to), cur.dist + temp * cost[cur.arr[i + 1]-'A']);
							}
						}
					}
				}
			}
			
			for (int i = 8; i < 15; i++) {
				if (cur.arr[i]-'A' >= 0) {
					for (int to : openRoomSpace(cur.arr, i)) {
						if (cur.arr[to - 1] != '.' && cur.arr[to - 1] != cur.arr[i]) continue;
						int temp = Math.abs(18 + to - 2 * i) + 1;
						if (i == 8 || i == 14) temp--;
						int oldDist = getDist(swap(cur.str, i, to));
						
						if (cur.dist + temp * cost[cur.arr[i]-'A'] < oldDist) {
							q.add(new State(swap(cur.str, i, to), cur.dist + temp * cost[cur.arr[i]-'A']));
							dist.put(swap(cur.str, i, to), cur.dist + temp * cost[cur.arr[i]-'A']);
						}
					}
				}
			}
		}
		
	}
	
	static ArrayList<Integer> openRoomSpace(char[] c, int start) {
		ArrayList<Integer> ret = new ArrayList();
		char at = c[start];
		if (start == 14) {
			if (c[13] != '.') return ret;
			start--;
		}
		if (start == 8) {
			if (c[9] != '.') return ret;
			start++;
		}
		
		for (int i = start; i >= 10; i--) {
			if (i != start && c[i] != '.') break;
			if (i - 10 == at-'A' && c[i * 2 - 19] == '.') {
				ret.add(i * 2 - 19);
			}
		}
		
		for (int i = start; i <= 12; i++) {
			if (i != start && c[i] != '.') break;
			if (i - 9 == at-'A' && c[i * 2 - 17] == '.') {
				ret.add(i * 2 - 17);
			}
		}
		
		return ret;
	}
	
	static ArrayList<Integer> openHallSpace(char[] c, int start) {
		ArrayList<Integer> ret = new ArrayList();
		
		for (int i = 9 + start; i >= 8; i--) {
			if (c[i] != '.') break;
			ret.add(i);
		}
		
		for (int i = 10 + start; i <= 14; i++) {
			if (c[i] != '.') break;
			ret.add(i);
		}
		
		return ret;
	}
	
	static String swap(String s, int p1, int p2) {
		char[] c = s.toCharArray();
		char temp = c[p1];
		c[p1] = c[p2];
		c[p2] = temp;
		return new String(c);
	}
	
	static int getDist(String s) {
		return dist.getOrDefault(s, (int) 1e9);
	}
	
	static class State implements Comparable<State> {
		char[] arr;
		String str;
		int dist, ct;
		
		public State(String s, int d) {
			str = s;
			dist = d;
			arr = s.toCharArray();
			for (int i = 0; i < 4; i++) {
				if (arr[2 * i] == 'A' + i) ct++;
				if (arr[2 * i + 1] == 'A' + i) ct++;
			}
		}

		@Override
		public int compareTo(State o) {
			if (dist == o.dist) return o.ct - ct;
			return dist - o.dist;
		}
		
		public void print() {
			System.out.println("Cost: " + dist);
			System.out.println("#############");
			System.out.printf("#%c%c.%c.%c.%c.%c%c#\n", arr[8], arr[9], arr[10], arr[11], arr[12], arr[13], arr[14]);
			System.out.printf("###%c#%c#%c#%c###\n", arr[1], arr[3], arr[5], arr[7]);
			System.out.printf("  #%c#%c#%c#%c#\n", arr[0], arr[2], arr[4], arr[6]);
			System.out.println("  #########\n");
		}
	}
}