import java.util.*;
import java.io.*;

public class AOC2022D21P1 {
	static String filename = "aoc";
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));
		
		HashMap<String, Long> vMap = new HashMap();
		HashMap<String, String[]> oMap = new HashMap();
		
		while (in.hasNextLine()) {
			String[] l = in.nextLine().split(" ");
			if (l.length == 2) {
				vMap.put(l[0].substring(0, 4), Long.parseLong(l[1]));
			}
			else {
				oMap.put(l[0].substring(0, 4), new String[] {l[1], l[2], l[3]});
			}
		}
		
		while (!vMap.containsKey("root")) {
			String[] op;
			HashSet<String> set = new HashSet();
			for (String k : oMap.keySet()) {
				op = oMap.get(k);
				//System.out.println(k + " " + Arrays.toString(op));
				if (vMap.containsKey(op[0]) && vMap.containsKey(op[2])) {
					long n1 = vMap.get(op[0]), n2 = vMap.get(op[2]);
					if (op[1].equals("+")) {
						vMap.put(k, n1 + n2);
					}
					else if (op[1].equals("-")) {
						vMap.put(k, n1 - n2);
					}
					else if (op[1].equals("*")) {
						vMap.put(k, n1 * n2);
					}
					else {
						vMap.put(k, n1 / n2);
					}
					set.add(k);
				}
			}
			
			for (String s : set) oMap.remove(s);
		}
		
		out.println(vMap.get("root"));
		
		out.close();
	}
	
	
}