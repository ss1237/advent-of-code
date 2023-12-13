import java.util.*;
import java.io.*;

public class AOC2021D23P2 {
	static String filename = "aoc";
	static int[] costs = new int[] {1, 10, 100, 1000};
	static HashMap<String, Integer> dist;
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));

		char[] init = new char[23];
		
		in.nextLine();
		char[] row = in.nextLine().toCharArray();
		init[16] = row[1];
		init[22] = row[11];
		for (int i = 2; i <= 10; i+=2) {
			init[16 + i/2] = row[i];
		}
		
		String[] line = in.nextLine().split("#+");
		for (int i = 0; i < 4; i++) {
			init[4 * i + 3] = line[i + 1].charAt(0);
		}
		
		for (int j = 2; j >= 0; j--) {
			line = in.nextLine().split("\s*#+");
			for (int i = 0; i < 4; i++) {
				init[4 * i + j] = line[i + 1].charAt(0);
			}
		}
		
		
		dist = new HashMap();
		dist.put(new String(init), 0);
		PriorityQueue<State> q = new PriorityQueue();
		q.add(new State(new String(init), 0));
		
		
		o: while (q.size() > 0) {
			State cur = q.poll();
			
			if (getDist(cur.str) != cur.dist) continue;
			
			//System.out.println(cur.dist + " " + cur.cost);
			
			if (cur.str.equals("AAAABBBBCCCCDDDD.......")) {
				cur.print();
				break;
			}
			
			for (int i = 16; i < 23; i++) {
				if (cur.arr[i]-'A' >= 0) {
					for (int to : openRoomSpace(cur.arr, i)) {
						if (wrongAtOrBelow(cur.arr, to)) continue;
						
						int temp = Math.abs(34 + to/2 - 2 * i) + 1;
						if (i == 16 || i == 22) temp--;
						int oldDist = getDist(swap(cur.str, i, to));
						
						if (cur.dist + temp * costs[cur.arr[i]-'A'] < oldDist) {
							q.add(new State(swap(cur.str, i, to), cur.dist + temp * costs[cur.arr[i]-'A']));
							dist.put(swap(cur.str, i, to), cur.dist + temp * costs[cur.arr[i]-'A']);
							
							if (!wrongAtOrBelow(cur.arr, to)) {
								continue o;
							}
						}
					}
				}
			}
			
			for (int i = 0; i < 16; i+=4) {
				if (!wrongAtOrBelow(cur.arr, i + 1) && cur.arr[i + 1]-'A' == i/4 && cur.arr[i] == '.') {
					int oldDist = getDist(swap(cur.str, i, i + 1));
					if (cur.dist + costs[cur.arr[i + 1]-'A'] < oldDist) {
						q.add(new State(swap(cur.str, i, i + 1), cur.dist + costs[cur.arr[i + 1]-'A']));
						dist.put(swap(cur.str, i, i + 1), cur.dist + costs[cur.arr[i + 1]-'A']);
						continue o;
					}
				}
				if (!wrongAtOrBelow(cur.arr, i + 2) && cur.arr[i + 2]-'A' == i/4 && cur.arr[i + 1] == '.') {
					int oldDist = getDist(swap(cur.str, i + 1, i + 2));
					if (cur.dist + costs[cur.arr[i + 2]-'A'] < oldDist) {
						q.add(new State(swap(cur.str, i + 1, i + 2), cur.dist + costs[cur.arr[i + 2]-'A']));
						dist.put(swap(cur.str, i + 1, i + 2), cur.dist + costs[cur.arr[i + 2]-'A']);
						continue o;
					}
				}
				if (!wrongAtOrBelow(cur.arr, i + 3) && cur.arr[i + 3]-'A' == i/4 && cur.arr[i + 2] == '.') {
					int oldDist = getDist(swap(cur.str, i + 2, i + 3));
					if (cur.dist + costs[cur.arr[i + 3]-'A'] < oldDist) {
						q.add(new State(swap(cur.str, i + 2, i + 3), cur.dist + costs[cur.arr[i + 3]-'A']));
						dist.put(swap(cur.str, i + 2, i + 3), cur.dist + costs[cur.arr[i + 3]-'A']);
						continue o;
					}
				}
			}
			
			for (int i = 0; i < 16; i+=4) {
				if (cur.arr[i] != '.') {
					if (wrongAtOrBelow(cur.arr, i) && cur.arr[i + 1] == '.') {
						int oldDist = getDist(swap(cur.str, i, i + 1));
						if (cur.dist + costs[cur.arr[i]-'A'] < oldDist) {
							q.add(new State(swap(cur.str, i, i + 1), cur.dist + costs[cur.arr[i]-'A']));
							dist.put(swap(cur.str, i, i + 1), cur.dist + costs[cur.arr[i]-'A']);
						}
					}
				}
				if (cur.arr[i + 1] != '.') {
					if (wrongAtOrBelow(cur.arr, i + 1) && cur.arr[i + 2] == '.') {
						int oldDist = getDist(swap(cur.str, i + 1, i + 2));
						if (cur.dist + costs[cur.arr[i + 1]-'A'] < oldDist) {
							q.add(new State(swap(cur.str, i + 1, i + 2), cur.dist + costs[cur.arr[i + 1]-'A']));
							dist.put(swap(cur.str, i + 1, i + 2), cur.dist + costs[cur.arr[i + 1]-'A']);
						}
					}
				}
				if (cur.arr[i + 2] != '.') {
					if (wrongAtOrBelow(cur.arr, i + 2) && cur.arr[i + 3] == '.') {
						int oldDist = getDist(swap(cur.str, i + 2, i + 3));
						if (cur.dist + costs[cur.arr[i + 2]-'A'] < oldDist) {
							q.add(new State(swap(cur.str, i + 2, i + 3), cur.dist + costs[cur.arr[i + 2]-'A']));
							dist.put(swap(cur.str, i + 2, i + 3), cur.dist + costs[cur.arr[i + 2]-'A']);
						}
					}
				}
				if (cur.arr[i + 3] != '.') {
					if (wrongAtOrBelow(cur.arr, i + 3)) {
						for (int to : openHallSpace(cur.arr, i / 4)) {
							int temp = Math.abs(i/2 + 35 - 2 * to) + 1;
							if (to == 22 || to == 16) temp--;
							int oldDist = getDist(swap(cur.str, i + 3, to));
							
							if (cur.dist + temp * costs[cur.arr[i + 3]-'A'] < oldDist) {
								q.add(new State(swap(cur.str, i + 3, to), cur.dist + temp * costs[cur.arr[i + 3]-'A']));
								dist.put(swap(cur.str, i + 3, to), cur.dist + temp * costs[cur.arr[i + 3]-'A']);
							}
						}
					}
				}
			}
			
		}
		
	}
	
	static boolean wrongAtOrBelow(char[] c, int cur) {
		for (int i = cur % 4; i >= 0; i--) {
			if (c[cur - i] != '.' && c[cur - i] != 'A' + cur/4) return true;
		}
		return false;
	}
	
	static ArrayList<Integer> openRoomSpace(char[] c, int start) {
		ArrayList<Integer> ret = new ArrayList();
		char at = c[start];
		if (start == 22) {
			if (c[21] != '.') return ret;
			start--;
		}
		if (start == 16) {
			if (c[17] != '.') return ret;
			start++;
		}
		
		for (int i = start; i >= 18; i--) {
			if (i != start && c[i] != '.') break;
			if (i - 18 == at-'A' && c[i * 4 - 69] == '.') {
				ret.add(i * 4 - 69);
			}
		}
		
		for (int i = start; i <= 20; i++) {
			if (i != start && c[i] != '.') break;
			if (i - 17 == at-'A' && c[i * 4 - 65] == '.') {
				ret.add(i * 4 - 65);
			}
		}
		
		return ret;
	}
	
	static ArrayList<Integer> openHallSpace(char[] c, int start) {
		ArrayList<Integer> ret = new ArrayList();
		
		for (int i = 17 + start; i >= 16; i--) {
			if (c[i] != '.') break;
			ret.add(i);
		}
		
		for (int i = 18 + start; i <= 22; i++) {
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
		int dist, heur, cost;
		
		public State(String s, int d) {
			str = s;
			dist = d;
			arr = s.toCharArray();
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if (arr[4 * i + j] != 'A' + i && arr[4 * i + j] != '.') {
						heur += costs[arr[4 * i + j] - 'A'] * (5 - j + 2 * Math.abs(arr[4 * i + j] - 'A' - i));
					}
				}
			}
			
			for (int i = 16; i <= 22; i++) {
				if (arr[i] != '.') {
					heur += costs[arr[i] - 'A'] * (1 + Math.round(Math.abs(17.5 + (arr[i] - 'A') - i)));
				}
			}
			
			cost = dist + heur;
		}

		@Override
		public int compareTo(State o) {
			return cost - o.cost;
			/*if (dist == o.dist) return o.ct - ct;
			return dist - o.dist;*/
		}
		
		public void print() {
			System.out.println("Cost: " + dist);
			System.out.println("#############");
			System.out.printf("#%c%c.%c.%c.%c.%c%c#\n", arr[16], arr[17], arr[18], arr[19], arr[20], arr[21], arr[22]);
			System.out.printf("###%c#%c#%c#%c###\n", arr[3], arr[7], arr[11], arr[15]);
			System.out.printf("  #%c#%c#%c#%c#\n", arr[2], arr[6], arr[10], arr[14]);
			System.out.printf("  #%c#%c#%c#%c#\n", arr[1], arr[5], arr[9], arr[13]);
			System.out.printf("  #%c#%c#%c#%c#\n", arr[0], arr[4], arr[8], arr[12]);
			System.out.println("  #########\n");
		}
	}
}