import java.util.*;
import java.io.*;

public class AOC2021D14P1 {
	static String filename = "aoc";

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));

		String oldString = in.nextLine();
		in.nextLine();
		HashMap<String, String> rules = new HashMap();
		
		while (in.hasNextLine()) {
			String[] line = in.nextLine().split(" -> ");
			rules.put(line[0], line[1]);
		}
		
		HashMap<String, Long> oldFreq = new HashMap<String, Long>();
		
		for (int j = 0; j < oldString.length() - 1; j++) {
			String pair = oldString.substring(j, j + 2);
			oldFreq.put(pair, oldFreq.getOrDefault(pair, 0L) + 1);
		}
		
		//System.out.println(oldFreq.toString());
		
		for (int i = 0; i < 10; i++) {
			HashMap<String, Long> newFreq = new HashMap<String, Long>();
			
			for (String p : oldFreq.keySet()) {
				if (rules.containsKey(p)) {
					String p1 = p.charAt(0) + rules.get(p);
					String p2 = rules.get(p) + p.charAt(1);
					
					newFreq.put(p1, newFreq.getOrDefault(p1, 0L) + oldFreq.getOrDefault(p, 0L));
					newFreq.put(p2, newFreq.getOrDefault(p2, 0L) + oldFreq.getOrDefault(p, 0L));
				}
				else {
					newFreq.put(p, newFreq.getOrDefault(p, 0L) + oldFreq.get(p));
				}
			}
			
			//System.out.println(newFreq.toString());
			oldFreq = newFreq;
		}
		
		long[] freq = new long[26];
		freq[oldString.charAt(0) - 'A']++;
		freq[oldString.charAt(oldString.length() - 1) - 'A']++;
		
		for (String s : oldFreq.keySet()) {
			freq[s.charAt(0) - 'A'] += oldFreq.get(s);
			freq[s.charAt(1) - 'A'] += oldFreq.get(s);
		}
		
		long maxCt = 0, minCt = Long.MAX_VALUE;
		for (long x : freq) {
			maxCt = Math.max(maxCt, x);
			if (x != 0) minCt = Math.min(minCt, x);
		}
		
		out.println((maxCt - minCt) / 2);
		
		out.close();

	}
}