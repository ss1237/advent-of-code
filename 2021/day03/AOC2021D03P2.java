import java.util.*;
import java.io.*;

public class AOC2021D03P2 {
	static String filename = "aoc";

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));

		String s = in.nextLine();
		ArrayList<String> vals = new ArrayList<String>();
		
		while (in.hasNextLine()) {
			vals.add(s);
			s = in.nextLine();
		}
		vals.add(s);
		
		String oxygen = "", co2 = "";
		
		for (int i = 0; i < s.length(); i++) {
			int ctOxygen = 0, ctCO2 = 0;
			int numC = 0;
			
			for (String cur : vals) {
				if (cur.substring(0, oxygen.length()).equals(oxygen)) {
					ctOxygen += (cur.charAt(i) == '1') ? 1 : -1;
				}
				
				if (cur.substring(0, co2.length()).equals(co2)) {
					ctCO2 += (cur.charAt(i) == '1') ? 1 : -1;
					numC++;
				}
			}
			
			oxygen += (ctOxygen >= 0) ? "1" : "0";
			
			if (numC > 1) {
				co2 += (ctCO2 >= 0) ? "0" : "1";
			}
			else {
				co2 += (ctCO2 > 0) ? "1" : "0";
			}
			
		}
		
		out.println(Integer.parseInt(oxygen, 2) * Integer.parseInt(co2, 2));
		
		out.close();

	}
}